package eduardo.gravan.tfg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * Clase que representa la actividad de Android encargada de gestionar el Login de los usuarios en la aplicación
 */
public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView userTextField;
    private EditText passwordTextField;
    private Button loginButton;
    private SharedPreferences sharedPreferences;
    private AlertDialog dialog;

    /**
     * Recupera y enlaza las variables de la clase con los elementos de la aplicación. Se crea el
     * listener para el botón de "Login".
     *
     * @param savedInstanceState parámetro sin usar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userTextField = (AutoCompleteTextView) findViewById(R.id.user_login);
        passwordTextField = (EditText) findViewById(R.id.password_login);
        loginButton = (Button) findViewById(R.id.login_button);

        sharedPreferences = getSharedPreferences("LOGIN_INFO", Context.MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    /**
     * Método llamado al pulsar el botón de "Login".
     * <p>
     *     Se recuperan los datos introducidos por el usuario y si no hay errores, se lanza un hilo encargado de
     *     comunicarse con el servidor HTTP para verificar la validez de los credenciales utilizados por el usuario.
     * </p>
     */
    private void attemptLogin() {
        String user, password;
        user = userTextField.getText().toString();
        password = passwordTextField.getText().toString();

        if (!user.equals("") && !password.equals("")) {
            AsyncGetLoginInfoTask task = new AsyncGetLoginInfoTask(user, password);
            task.execute();
            // Create the alert dialog with the progress bar
            dialog = new AlertDialogFactory(this, "Enviando credenciales al servidor").create();
            dialog.show();
        }
        else {
            passwordTextField.setText("");
            Toast.makeText(this, "Rellene los campos especificados", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método llamado una vez se ha terminado la comunicación con el servidor HTTP para actualizar la información de
     * usuario en base a la respuesta del servidor.
     * <p>
     *     En caso de que el login sea correcto, se actualizan las sharedPreferences (memoria de la aplicación) para que
     *     almacene los datos del usuario y se lanza la actividad del menú principal de la aplicación.
     *     En caso de que los datos sean incorrectos, se lanza un mensaje Toast notificando al usuario.
     * </p>
     *
     * @param responseCode int con el código de respuesta del servidor HTTP
     * @param jsonResponse string con el cuerpo de la respuesta del servidor en formato JSON
     */
    protected void updateUserInfo(int responseCode, String jsonResponse) {
        dialog.dismiss();

        switch (responseCode) {
            case 200:
                try {
                    JSONObject obj = new JSONArray(jsonResponse).getJSONObject(0);
                    sharedPreferences.edit().putString("USER", obj.getString("email")).apply();
                    sharedPreferences.edit().putBoolean("ADMIN", obj.getInt("is_admin") == 1).apply();
                    startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
                    finish();
                } catch (JSONException e) {
                    Log.e("JSONException", Objects.requireNonNull(e.getMessage()));
                }
                break;

            case 401:
                passwordTextField.setText("");
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                break;

            default:
                passwordTextField.setText("");
                Toast.makeText(this, "No se ha podido conectar con el servidor", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Clase privada interna encargada de lanzar la petición de login contra el servidor HTTP
     */
    private class AsyncGetLoginInfoTask extends AsyncTask<Void, Void, Void> {
        private int responseCode;
        private String user;
        private String password;
        private String response;

        public AsyncGetLoginInfoTask(String user, String password) {
            this.user = user;
            this.password = password;
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
                // Configurar la llamada a la API ReST del servidor HTTP
                HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/login").openConnection();
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);

                // Construir el JSON y escribirlo en la petición
                String jsonRequest = "{\"username\": \"" + user + "\", \"password\": \"" + password + "\"}";
                OutputStream os = connection.getOutputStream();
                byte[] input = jsonRequest.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);

                responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    response = "";
                    Scanner scanner = new Scanner(connection.getInputStream());

                    while (scanner.hasNextLine()) {
                        response += scanner.nextLine() + "\n";
                    }
                    scanner.close();
                    Log.i("AsyncGetLoginInfo", "Succesful login. Code 200 in response.");
                }
                connection.disconnect();
            }
            catch (java.net.SocketTimeoutException e) {
                Log.e("AsyncGetLoginInfo", "Connection timeout");
            }
            catch (IOException e) {
                Log.e("AsyncGetLoginInfo", Objects.requireNonNull(e.getMessage()));
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
            updateUserInfo(responseCode, response);
        }
    }
}
