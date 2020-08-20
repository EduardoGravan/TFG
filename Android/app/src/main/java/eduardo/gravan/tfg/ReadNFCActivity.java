package eduardo.gravan.tfg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.*;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Clase que representa la actividad de Android encargada de ofrecerle a los usuarios administradores la
 * posibilidad de poner su teléfono en modo lectura de tarjetas NFC para recuperar mensajes NDEF y mandarlos al servidor.
 */
public class ReadNFCActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {
    private NfcAdapter nfcAdapter;
    private Button buttonReadTag;
    private TextView txtTagContent;
    private String tagContent;
    private AlertDialog dialog;

    /**
     * Recupera y enlaza las variables de la clase con los elementos de la aplicación. Se crea el
     * listener para el botón de "Leer Etiqueta".
     *
     * @param savedInstanceState parámetro sin usar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_nfc);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        txtTagContent = (TextView) findViewById(R.id.txtTagContent);
        buttonReadTag = (Button) findViewById(R.id.buttonReadTag);
        buttonReadTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableReaderMode();
            }
        });

        // Se crea el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.progress_alert_dialog, null);
        TextView messageView = (TextView) customView.findViewById(R.id.loading_msg);
        builder.setView(customView);
        builder.setNegativeButton("Cancelar", null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                disableReaderMode();
            }
        });
        messageView.setText("Leyendo etiquetas NDEF cercanas...");
        dialog = builder.create();
    }

    /**
     * En caso de que la actividad deje de estar en primer plano, si el diálogo está activo, se dismissea el AlertDialog,
     * quitando el modo lectura.
     */
    @Override
    protected void onPause() {
        super.onPause();

        if(dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * Cuando el teléfono en modo lectura descubre una tarjeta NFC se llama a este método.
     * <p>
     *     Nuestra implementación busca a ver si la tarjeta es una tarjeta NDEF y, si es así, recupera el mensaje
     *     NDEF y lanza un hilo encargado de comunicarse con el servidor HTTP.
     * </p>
     *
     * @param tag objeto que representa una tarjeta NFC
     */
    @Override
    public void onTagDiscovered(Tag tag) {
        for (String tech : tag.getTechList()) {
            if (tech.equals(Ndef.class.getName())) {
                Ndef ndef = Ndef.get(tag);
                try {
                    // Recuperamos el mensaje NDEF de la tarjeta
                    ndef.connect();
                    NdefMessage ndefMessage = ndef.getNdefMessage();
                    NdefRecord record = ndefMessage.getRecords()[0];
                    byte[] payload = record.getPayload();

                    // Parseamos el mensaje quitándole los caracteres innecesarios en base a su codificación.
                    String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
                    int languageSize = payload[0] & 0063;
                    tagContent = new String(payload, languageSize + 1, payload.length - languageSize - 1, textEncoding);
                    Log.i("onTagDiscovered", tagContent);
                    ndef.close();

                    // Se lanza el hilo para comunicarse con el servidor
                    AsyncSendAttendanceInfoTask task = new AsyncSendAttendanceInfoTask(tagContent);
                    task.execute();
                }
                catch (IOException | FormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Método encargado de activar el modo lectura. Este método es llamado cuando se pulsa el botón de "Leer Tarjeta".
     * <p>
     *     Se activa el modo lectura para que busque tarjetas NFC de tipo A. Se crea también un AlertDialog
     *     que quede en primer plano indicando que el teléfono está buscando tarjetas NDEF cercanas.
     * </p>
     * <p>
     *     Se crea un listener para que, en el caso de que el AlertDialog tenga un Dismiss, se llame a un método
     *     encargado de desactivar el modo lectura.
     * </p>
     */
    private void enableReaderMode() {
        // Solo podemos proceder si NFC está activado
        if (checkNFCStatus()) {
            txtTagContent.setText("");
            Bundle options = new Bundle();
            // Se crea una opción para que haya un delay de 1 segundo entre lecturas
            options.putInt(NfcAdapter.EXTRA_READER_PRESENCE_CHECK_DELAY, 1000);

            // Se activa el modo lectura para tarjetas NFC de tipo A
            nfcAdapter.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A, options);
            Log.i("enableReaderMode", "Reader mode enabled");

            dialog.show();
        }
    }

    /**
     * Método encargado de desactivar el modo lectura
     */
    private void disableReaderMode() {
        nfcAdapter.disableReaderMode(this);
        Log.i("disableReaderMode", "Reader mode disabled");
    }

    /**
     * Método auxiliar encargado de comprobar que el teléfono tiene un adaptador NFC y que está activado.
     * Devuelve un booleano indicando si está activo o no.
     *
     * @return True si el adaptador NFC está activo y funcionando, False si no
     */
    private boolean checkNFCStatus() {
        if (nfcAdapter == null || !nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC debe estar activado", Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;
    }

    /**
     * Método llamado cuando el hilo encargado de comunicarse con el servidor HTTP termina su ejecución.
     * Se encarga de parsear la respuesta del servidor para saber si la actualización de la base de datos ha sido
     * satisfactoria. Actualiza la interfaz de usuario para notificar al usuario administrador del resultado.
     *
     * @param result string con la respuesta del servidor HTTP
     */
    protected void updateInterface(String result) {
        switch (result.trim().toLowerCase()) {
            case "arrived_time updated":
                txtTagContent.setText("Se ha actualizado correctamente la hora de llegada del usuario " + tagContent);
                break;
            case "left_time updated":
                txtTagContent.setText("Se ha actualizado correctamente la hora de salida del usuario " + tagContent);
                break;
            case "table already updated":
                txtTagContent.setText("Error. El usuario " + tagContent + " ya ha fichado 2 veces hoy");
                break;
            case "update too soon":
                txtTagContent.setText("Error. No ha pasado 1 minuto desde que el usuario intentó fichar");
                break;
            case "no data to update":
                txtTagContent.setText("Error. El usuario " + tagContent + " no tiene datos de asistencia para el día de hoy");
                break;
            case "timeout":
                txtTagContent.setText("Error. No se ha podido conectar con el servidor. Inténtelo de nuevo más tarde");
                break;
        }

        dialog.dismiss();
    }

    /**
     * Clase privada interna que representa la AsyncTask encargada de crear un registro de asistencia en la base de datos,
     * comunicándose con la API ReST
     */
    private class AsyncSendAttendanceInfoTask extends AsyncTask<Void, Void, Void> {
        private final String username;
        private String response;

        public AsyncSendAttendanceInfoTask(String username) {
            this.username = username;
        }

        /**
         * Se configura la llamada al servidor HTTP y se analiza la respuesta del servidor.
         * En el caso de que la respuesta sea correcta (200), se lee el cuerpo de la respuesta y se recupera el
         * string de respuesta.
         *
         * @param voids parámetro sin usar
         * @return parámetro sin usar
         */
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Se configura la llamada a la API ReST del servidor HTTP
                HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/attendance/" + username).openConnection();
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("PUT");
                connection.setRequestProperty("Accept", "application/problem+json");

                response = "";
                if(connection.getResponseCode() == 200) {
                    Scanner scanner = new Scanner(connection.getInputStream());

                    while (scanner.hasNextLine()) {
                        response += scanner.nextLine() + "\n";
                    }
                    scanner.close();
                }
                else {
                    Scanner scanner = new Scanner(connection.getErrorStream());

                    while(scanner.hasNextLine()) {
                        response += scanner.nextLine() + "\n";
                    }
                }

                Log.i("AsyncSendAttendanceInfoTask", response);
            }
            catch (java.net.SocketTimeoutException e) {
                Log.e("AsyncGetLoginInfo", "Connection timeout");
                response = "timeout";
            }
            catch (IOException e) {
                e.printStackTrace();
                Log.e("AsyncSendAttendanceInfoTask", e.getMessage());
            }

            return null;
        }

        /**
         * Una vez ha terminado la ejecución del hilo, se hace la llamada para notificar a la clase padre actualizando
         * la interfaz de usuario.
         * @param result parámetro sin usar
         */
        @Override
        protected void onPostExecute(Void result) {
            updateInterface(response);
        }
    }
}
