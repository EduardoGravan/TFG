package eduardo.gravan.adminpanel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase que recoge la lógica de aplicación correspondiente a la ventana de
 * la GUI encargada de gestionar el login del usuario administrador.
 * Es también la clase que recoge la función main de la aplicación y, por lo tanto,
 * el punto de inicio en el ciclo de vida de la ejecución de la aplicación.
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        // Se añade el boton "enter" para que intente hacer el login
        this.getRootPane().setDefaultButton(jButtonLogin);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogin = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jButtonLogin = new javax.swing.JButton();
        jPasswordFieldPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TFG - Admin Panel");
        setResizable(false);

        jLabelLogin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogin.setText("Login");
        jLabelLogin.setToolTipText("");

        jLabelUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUsername.setText("Usuario");

        jLabelPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPassword.setText("Contraseña");

        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabelLogin)
                .addGap(53, 53, 53)
                .addComponent(jLabelUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButtonLogin)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Función que gestiona el evento de pulsar el botón para hacer login.
     * Si el input es correcto, se llama a la función encargada
     * de mandar la petición al servidor. En caso contrario, se notifica al usuario
     * del error a través de un popup.
     * @param evt evento de swing
     */
    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        String user = jTextFieldUser.getText();
        String password = new String(jPasswordFieldPassword.getPassword());

        if (!user.equals("") && !password.equals(""))
            attemptLogin(user, password);
        else 
            JOptionPane.showMessageDialog(this, "Rellene todos los campos.", "Error", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jButtonLoginActionPerformed

    /**
     * Función main, pone el estilo de la GUI en "Nimbus" y lanza el hilo
     * encargado de gestionar la GUI, instanciando la ventana de Login y 
     * haciendola visible
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    /**
     * Función encargada de mandar los datos proporcionados por el usuario al 
     * servidor y de analizar la respuesta recibida para comprobar si el login
     * es correcto.
     * Inicialmente se configura la llamada al servidor y se escribe el cuerpo
     * de la petición en formato JSON.
     * Después, se analiza la respuesta del servidor. Si credenciales proporcionados
     * por el usuario son correctos, se comprueba que el usuario sea un administrador;
     * en caso de que sea un administrador, el login es correcto y se le
     * redirige al menú principal de la aplicación. En caso de que no sea un
     * administrador, no puede acceder al panel de administración, por lo que
     * no se le permite continuar.
     * En caso de que los credenciales no sean válidos, también se le notifica
     * a través de un popup.
     * 
     * @param user string con el nombre de usuario (email)
     * @param password contraseña asociada al usuario
     */
    private void attemptLogin(String user, String password) {
        try {
            // Configurar la llamada HTTP al servidor ReST
            HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/login").openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Se construye la petición JSON y se escribe en el paquete HTTP
            String jsonRequest = "{\"username\": \"" + user + "\", \"password\": \"" + password + "\"}";
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonRequest.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);

            String response = "";
            // Si la información recuperada está asociada a una cuenta
            if (connection.getResponseCode() == 200) {
                Scanner scanner = new Scanner(connection.getInputStream());

                while (scanner.hasNextLine()) {
                    response += scanner.nextLine() + "\n";
                }
                scanner.close();
                
                JSONObject obj = new JSONArray(response).getJSONObject(0);
                // Si el usuario es un admin
                if(obj.getInt("is_admin") == 1) {
                    // Continuamos con el proceso de login
                    JOptionPane.showMessageDialog(this, "Bienvenido "+ user +".", "Login correcto", JOptionPane.PLAIN_MESSAGE);
                    new MainMenu().setVisible(true);
                    this.dispose();
                }
                // En caso contrario, si el usuario no es un admin
                else {
                    // El usuario no tiene permisos para entrar en el panel de administración
                    jPasswordFieldPassword.setText("");
                    JOptionPane.showMessageDialog(this, "Error. El usuario no es un administrador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Si los credenciales no corresponden con los datos guardados en la base de datos
            else {
                jPasswordFieldPassword.setText("");
                JOptionPane.showMessageDialog(this, "Error. Datos de inicio de sesión incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            connection.disconnect();
            
        } catch (java.net.SocketTimeoutException e) {
            jPasswordFieldPassword.setText("");
            JOptionPane.showMessageDialog(this, "Error. No se ha podido conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | JSONException e) {
            System.out.println(e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}
