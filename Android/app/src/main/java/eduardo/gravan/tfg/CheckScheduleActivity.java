package eduardo.gravan.tfg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

/**
 * Clase que representa la actividad de Android encargada de ofrecerle a los usuarios no administradores la
 * posibilidad de comprobar su horario para un determinado día.
 */
public class CheckScheduleActivity extends AppCompatActivity {
    private EditText dateEditText;
    private TextView resultTextView;
    private Button checkScheduleButton;
    private SharedPreferences sharedPreferences;
    private AlertDialog dialog;

    /**
     * Recupera y enlaza las variables de la clase con los elementos de la aplicación. Se crea el
     * listener para el botón de "Comprobar horario".
     *
     * @param savedInstanceState parámetro sin usar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_check_schedule);

        dateEditText = (EditText) findViewById(R.id.date_check_schedule);
        resultTextView = (TextView) findViewById(R.id.check_schedule_textView);
        checkScheduleButton = (Button) findViewById(R.id.check_schedule_button);
        checkScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkScheduleInfo();
            }
        });
        sharedPreferences = getSharedPreferences("LOGIN_INFO", Context.MODE_PRIVATE);
    }

    /**
     * Método llamado al pulsar el botón de "Comprobar Horario".
     * <p>
     *  Comprueba si el input realizado por el usuario es correcto. En el caso de que lo sea, lanza una instancia
     *  de la clase interna encargada de comunicarse con el servidor.
     * </p>
     * En caso de que no lo sea, le envía un mensaje el usuario a través de un mensaje Toast para avisarle.
     */
    private void checkScheduleInfo() {
        resultTextView.setText("");
        String date = dateEditText.getText().toString();

        if (date.equals("")) {
            Toast.makeText(this, "Rellene el campo de fecha", Toast.LENGTH_LONG).show();
        }
        else if(checkDateFormat(date)) {
            AsyncGetScheduleInfoTask task = new AsyncGetScheduleInfoTask(this, sharedPreferences.getString("USER", "error"), date);
            task.execute();

            dialog = new AlertDialogFactory(this, "Enviando credenciales al servidor").create();
            dialog.show();
        }
        else {
            Toast.makeText(this, "El campo de fecha no está en el formato especificado", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Clase auxiliar encargada de comprobar el formato de la fecha para que corresponda con el formato de la base
     * de datos.
     *
     * @param date string con la fecha
     * @return true si el formato es correcto, false si no lo es
     */
    private boolean checkDateFormat(String date) {
        String [] parts = date.split("-");
        if(parts.length != 3)
            return false;
        else if(parts[0].length() != 4)
            return false;
        else if(parts[1].length() != 2)
            return false;
        else if(parts[2].length() != 2)
            return false;
        else
            return true;
    }

    /**
     * Método llamado una vez la comunicación con el servidor ha acabado. Analiza la respuesta del servidor y,
     * en el caso de que se haya recuperado información correctamente, llama a una función auxilar para actualizar
     * la interfaz de usuario.
     *
     * @param responseCode int que representa el código de respuesta devuelto por el servidor HTTP
     * @param jsonResponse string con el cuerpo de la respuesta en formato JSON
     */
    private void updateUserInfo(int responseCode, String jsonResponse) {
        dialog.dismiss();

        switch(responseCode) {
            case 200:
                updateUI(jsonResponse);
                break;
            case 400:
                Toast.makeText(this, "Error. No hay datos para ese día", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "No se ha podido conectar con el servidor", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Función auxilar encargada de parsear el JSON y escribirlo en un TextView de la interfaz de Android para que la
     * información sea accesible por el usuario de la aplicación.
     *
     * @param jsonResponse string con el cuerpo de la respuesta en formato JSON
     */
    private void updateUI(String jsonResponse) {
        try {
            JSONArray array = new JSONArray(jsonResponse);
            if(array.length() == 0) {
                Toast.makeText(this, "Error. No hay datos para ese día", Toast.LENGTH_LONG).show();
            }
            else {
                JSONObject obj = array.getJSONObject(0);
                resultTextView.setText("Fecha: " + obj.getString("date") +"\nHora de inicio: "
                        + obj.getString("start_time") + "\nHora de finalización: " + obj.getString("end_time"));
            }
        } catch (JSONException e) {
            Log.e("JSONException", Objects.requireNonNull(e.getMessage()));
        }
    }

    /**
     * Clase privada interna que representa la AsyncTask encargada de recuperar la información de horarios, comunicándose
     * con la API ReST.
     */
    private class AsyncGetScheduleInfoTask extends AsyncTask<Void, Void, Void> {
        private CheckScheduleActivity checkScheduleActivity;
        private String email;
        private String date;
        private String response;
        private int responseCode;

        public AsyncGetScheduleInfoTask(CheckScheduleActivity checkScheduleActivity, String email, String date) {
            this.checkScheduleActivity = checkScheduleActivity;
            this.email = email;
            this.date = date;
            this.responseCode = 0;
        }

        /**
         * Se configura la llamada al servidor HTTP y se analiza la respuesta del servidor.
         * En el caso de que la respuesta sea correcta (200), se lee el cuerpo de la respuesta y se recupera el
         * JSON en un String.
         *
         * @param voids parámetro sin usar
         * @return parámetro sin usar
         */
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Se configura la llamada a la API ReST del servidor HTTP
                HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/schedule/" + email + "/" + date).openConnection();
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("GET");

                responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    response = "";
                    Scanner scanner = new Scanner(connection.getInputStream());

                    while (scanner.hasNextLine()) {
                        response += scanner.nextLine() + "\n";
                    }
                    scanner.close();
                    Log.i("AsyncGetScheduleInfoTask", "Succesfully retrieved data.");
                }
                connection.disconnect();
            }
            catch (java.net.SocketTimeoutException e) {
                Log.e("AsyncGetScheduleInfoTask", "Connection timeout");
            }
            catch (IOException e) {
                Log.e("AsyncGetScheduleInfoTask", Objects.requireNonNull(e.getMessage()));
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
            checkScheduleActivity.updateUserInfo(responseCode, response);
        }
    }
}