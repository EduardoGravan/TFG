package eduardo.gravan.tfg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.*;
import android.nfc.NfcAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa la actividad de Android encargada de ofrecerle a los usuarios no administradores la
 * posibilidad de emular una tarjeta NFC.
 */
public class EmulateNFCTagActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private AlertDialog.Builder builder;
    private Dialog dialog;
    private TextView ndefMessageTextView;
    private Button emulateTagButton;
    private SharedPreferences sharedPreferences;

    /**
     * Implementación anónima encargada de recoger los broadcasts mandados por el servicio de emulación de tarjetas NFC
     */
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        // Atributo encargado de evitar que la aplicación enseñe 2 AlertDialogs, esto se debe a que
        // ReaderMode necesita leer la tarjeta 2 veces para poder procesarla
        private boolean showDialog = false;

        /**
         * Método encargado de gestionar los broadcasts recogidos. En este caso, se analiza si la lectura
         * de la etiqueta se ha hecho correctamente o no, notificando al usuario en ambos casos.
         *
         * @param context parámetro sin usar
         * @param intent intent que recoge el broadcast
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.hasExtra("success") && showDialog) {
                dialog.dismiss();
                if(intent.getBooleanExtra("success", false)) {
                    Log.i("BroadcastReceiver", "Successful write: " + showDialog);
                    showOkDialog();
                    showDialog = false;
                }
                else {
                    Log.i("BroadcastReceiver", "Error while sending NFC tag");
                    showErrorDialog();
                }
            }
            else {
                showDialog = true;
            }
        }
    };

    /**
     * Método auxiliar encargado de crear y enseñar por pantalla un AlertDialog que representa que el lector de tarjetas
     * NFC ha conseguido leer la tarjeta emulada correctamente.
     */
    private void showOkDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Emulación de etiqueta")
                .setMessage("La información se ha recibido correctamente")
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .create().show();
    }

    /**
     * Método auxiliar encargado de crear y enseñar por pantalla un AlertDialog que representa que el lector de tarjetas
     * NFC no ha conseguido leer correctamente la información de la tarjeta emulada.
     */
    private void showErrorDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Emulación de etiqueta")
                .setMessage("Error al enviar la información de la etiqueta. Inténtelo de nuevo")
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .create().show();
    }

    /**
     * Recupera y enlaza las variables de la clase con los elementos de la aplicación. Se crea un AlertDialog
     * específico para la emulación de tarjetas, y se crea el listener del botón de emular.
     *
     * @param savedInstanceState parámetro sin usar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emulate_nfc_tag);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.progress_alert_dialog, null);
        TextView messageView = (TextView) customView.findViewById(R.id.loading_msg);
        builder.setView(customView);
        builder.setNegativeButton("Cancelar", null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                stopEmulationService();
            }
        });
        messageView.setText("Emulando etiqueta...");
        dialog = builder.create();

        sharedPreferences = getSharedPreferences("LOGIN_INFO", Context.MODE_PRIVATE);

        ndefMessageTextView = (TextView) findViewById(R.id.txtNdefMessage);
        ndefMessageTextView.setText(buildTagInfoString());

        emulateTagButton = (Button) findViewById(R.id.buttonEmulateNdefMessage);
        emulateTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                initEmulationService();
            }
        });
    }

    /**
     * Cada vez que la actividad se pone en primer plano se debe registrar el recibidor de broadcast y comprobar
     * que NFC está activado.
     */
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("message"));
    }

    /**
     * Cuando la actividad deja de estar en primer plano, se debe quitar el registro del recibidor de broadcast
     */
    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

        dialog.dismiss();
    }

    /**
     * Método llamado al pulsar el botón de emulación. Prepara la llamada al servicio de emulación de tarjetas (HCE),
     * muestra el diálogo creado en el método "onCreate()", y llama al servicio.
     */
    private void initEmulationService() {
        // Solo podemos proceder si NFC está activado
        if (checkNFCStatus()) {
            Intent intent = new Intent(this, EmulateNFCTagService.class);
            intent.putExtra("ndefMessage", sharedPreferences.getString("USER", "error"));
            dialog.show();
            startService(intent);
            Log.i("initEmulationService", "Launched emulation service");
        }
    }

    /**
     * En caso de que se deba parar el servicio por algo (el AlertDialog hace dismiss), se para el servicio de emulación
     * a través de un Intent
     */
    private void stopEmulationService() {
        stopService(new Intent(this, EmulateNFCTagService.class));

        Log.i("stopEmulationService", "Stopped emulation service");
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
     * Método auxiliar que construye la información que ve el usuario por pantalla. Esta información refleja
     * su correo electrónico y la fecha aproximada a la que está fichando.
     *
     * @return string con la información del registro de asistencia que se está enviando por NFC
     */
    private String buildTagInfoString() {
        return "Usuario: " + sharedPreferences.getString("USER", "error") + "\n\n"
                + "Fecha: " + new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date());
    }
}
