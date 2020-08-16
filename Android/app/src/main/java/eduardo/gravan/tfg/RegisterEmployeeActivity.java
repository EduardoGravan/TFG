package eduardo.gravan.tfg;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Clase que representa la actividad de Android encargada de ofrecerle a los usuarios administradores la
 * posibilidad de registrar a nuevos empleados a través del móvil.
 */
public class RegisterEmployeeActivity extends AppCompatActivity {
    private AutoCompleteTextView emailTextView;
    private EditText passwordTextView;
    private EditText confirmPasswordTextView;
    private EditText nameTextView;
    private CheckBox isAdminCheckBox;
    private Button registerButton;
    private AlertDialog dialog;

    /**
     * Recupera y enlaza las variables de la clase con los elementos de la aplicación. Se crea el
     * listener para el botón de "Alta de Empleado".
     *
     * @param savedInstanceState parámetro sin usar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        emailTextView = (AutoCompleteTextView) findViewById(R.id.user_register);
        passwordTextView = (EditText) findViewById(R.id.password_register);
        confirmPasswordTextView = (EditText) findViewById(R.id.confirm_password_register);
        nameTextView = (EditText) findViewById(R.id.name_register);
        isAdminCheckBox = (CheckBox) findViewById(R.id.is_admin);
        registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptEmployeeRegister();
            }
        });
    }

    /**
     * Se recogen todos los datos introducidos por el usuario administrador y se comprueba que no queden campos en blanco.
     * Se comprueba también que las contraseñas introducidas coinciden.
     * <p>
     *     Si los parámetros están bien, se crea un String con el cuerpo de la petición en formato JSON y se lanza
     *     el hilo encargado de comunicarse con el servidor HTTP.
     * </p>
     */
    private void attemptEmployeeRegister() {
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmedPassword = confirmPasswordTextView.getText().toString();
        String name = nameTextView.getText().toString();
        boolean isAdmin = isAdminCheckBox.isChecked();

        if(email.equals("") || password.equals("") || confirmedPassword.equals("") || name.equals("")) {
            Toast.makeText(this, "Rellene los campos especificados", Toast.LENGTH_LONG).show();
        }
        else if (!password.equals(confirmedPassword)) {
            passwordTextView.setText("");
            confirmPasswordTextView.setText("");
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
        }
        else {
            String jsonRequest = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", " +
                    "\"name\": \"" + name + "\", \"is_admin\": " + isAdmin + "}";
            Log.i ("attemptEmployeeRegister | jsonRequest", jsonRequest);

            AsyncCreateEmployeeTask task = new AsyncCreateEmployeeTask(this, jsonRequest);
            task.execute();

            // Se crea el AlertDialog
            dialog = new AlertDialogFactory(this, "Enviando datos al servidor").create();
            dialog.show();
        }
    }

    /**
     * Una vez ha acabado la ejecución del hilo encargado de comunicarse con el servidor HTTP, se llama a este método.
     * <p>
     *     El meétodo se encarga de analizar el código de respuesta del servidor, dejándole saber al usuario
     *     administrador si la inserción del nuevo empleado se ha hecho correctamente o si ha habido algún tipo de error.
     * </p>
     * @param responseCode
     */
    protected void processResponse(int responseCode) {
        dialog.dismiss();

        switch(responseCode) {
            case 201:
                new AlertDialog.Builder(this)
                        .setTitle("Registro de empleados")
                        .setMessage("Empleado dado de alta satisfactoriamente")
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .create().show();

                emailTextView.setText("");
                passwordTextView.setText("");
                confirmPasswordTextView.setText("");
                nameTextView.setText("");
                isAdminCheckBox.setChecked(false);
                break;

            case 400:
                new AlertDialog.Builder(this)
                        .setTitle("Registro de empleados")
                        .setMessage("Error al crear el empleado. El empleado ya existe")
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .create().show();

                emailTextView.setText("");
                passwordTextView.setText("");
                confirmPasswordTextView.setText("");
                nameTextView.setText("");
                isAdminCheckBox.setChecked(false);
                break;

            case 1000:
                new AlertDialog.Builder(this)
                        .setTitle("Registro de empleados")
                        .setMessage("No se ha podido conectar con el servidor. Inténtelo de nuevo")
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .create().show();
                break;

            default:
                new AlertDialog.Builder(this)
                        .setTitle("Registro de empleados")
                        .setMessage("Error desconocido al intentar crear el empleado")
                        .setPositiveButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .create().show();
        }
    }

    /**
     * Clase privada interna que representa la AsyncTask encargada de crear un nuevo usuario en la base de datos,
     * comunicándose con la API ReST.
     */
    private class AsyncCreateEmployeeTask extends AsyncTask<Void, Void, Void> {
        private final RegisterEmployeeActivity registerEmployeeActivity;
        private final String jsonRequest;
        private int responseCode;

        public AsyncCreateEmployeeTask(RegisterEmployeeActivity registerEmployeeActivity, String jsonRequest) {
            this.registerEmployeeActivity = registerEmployeeActivity;
            this.jsonRequest = jsonRequest;
            this.responseCode = 0;
        }

        /**
         * Se configura la llamada al servidor HTTP y se analiza la respuesta del servidor.
         * Se escribe el JSON al cuerpo del mensaje y se recupera el código de respuesta de la petición HTTP.
         *
         * @param voids parámetro sin usar
         * @return parámetro sin usar
         */
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Se configura la llamada a la API ReST en el servidor HTTP
                HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/employee").openConnection();
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Se escribe el JSON al cuerpo del mensaje HTTP
                OutputStream os = connection.getOutputStream();
                byte[] input = jsonRequest.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);

                responseCode = connection.getResponseCode();
            }
            catch (java.net.SocketTimeoutException e) {
                Log.e("AsyncGetLoginInfo", "Connection timeout");
                responseCode = 1000;
            }
            catch (IOException e) {
                e.printStackTrace();
                Log.e("AsyncSendAttendanceInfoTask", e.getMessage());
            }

            return null;
        }

        /**
         * Una vez ha terminado la ejecución del hilo, se hace la llamada para notificar a la clase padre actualizando
         * la interfaz de usuario, pasándole el código de respuesta del servidor para que pueda actuar en consecuencia.
         * @param result parámetro sin usar
         */
        @Override
        protected void onPostExecute(Void result) {
            registerEmployeeActivity.processResponse(responseCode);
        }
    }
}