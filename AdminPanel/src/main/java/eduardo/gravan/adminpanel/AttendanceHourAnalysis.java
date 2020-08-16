package eduardo.gravan.adminpanel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase que recoge la lógica de aplicación correspondiente a la ventana de
 * la GUI encargada de analizar los datos de asistencia de los empleados.
 */
public class AttendanceHourAnalysis extends javax.swing.JFrame {
    private final MainMenu mainMenu;
    
    public AttendanceHourAnalysis(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelHourAnalysis = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jLabelMonth = new javax.swing.JLabel();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldHoursAssigned = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldHoursWorked = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAbsences = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldOvertime = new javax.swing.JTextField();
        jLabelMonth1 = new javax.swing.JLabel();
        jComboBoxYear = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldRemainingHours = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TFG - Admin Panel");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabelHourAnalysis.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelHourAnalysis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHourAnalysis.setText("Análisis de horas");

        jButtonSearch.setText("Buscar");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jLabelEmail.setText("Email del empleado");

        jLabelMonth.setText("Mes a analizar");

        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jLabel1.setText("Total de horas asignadas");

        jTextFieldHoursAssigned.setEditable(false);
        jTextFieldHoursAssigned.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Total de horas trabajadas");

        jTextFieldHoursWorked.setEditable(false);
        jTextFieldHoursWorked.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("Faltas en el mes");

        jTextFieldAbsences.setEditable(false);
        jTextFieldAbsences.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setText("Horas extra");

        jTextFieldOvertime.setEditable(false);
        jTextFieldOvertime.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabelMonth1.setText("Año a analizar");

        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020" }));

        jLabel5.setText("Horas que debe");

        jTextFieldRemainingHours.setEditable(false);
        jTextFieldRemainingHours.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelHourAnalysis, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addComponent(jLabelEmail)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxMonth, 0, 143, Short.MAX_VALUE))))
                            .addGap(43, 43, 43)
                            .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(104, 104, 104)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldOvertime)
                                .addComponent(jTextFieldHoursWorked)
                                .addComponent(jTextFieldHoursAssigned)
                                .addComponent(jTextFieldAbsences, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldRemainingHours, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelHourAnalysis)
                .addGap(42, 42, 42)
                .addComponent(jLabelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMonth)
                    .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMonth1)
                    .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldHoursAssigned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHoursWorked, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAbsences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldOvertime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRemainingHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Al cerrarse la ventana, la ventana del menú principal se vuelve a hacer visible
     * @param evt evento de swing
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        mainMenu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    /**
     * Función que gestiona el evento de pulsar el botón para buscar el registro
     * de horario. Se formatea el input de fecha para que esté en el formato
     * correcto para la base de datos y, si todo está bien, se pasa a la función
     * encargada de enviar la petición.
     * @param evt evento de swing
     */
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        String email = jTextFieldEmail.getText();
        String month = (jComboBoxMonth.getSelectedIndex() > 8) 
                ? "" + (1 + jComboBoxMonth.getSelectedIndex()) 
                : "0" + (1 + jComboBoxMonth.getSelectedIndex());
        String year = (String) jComboBoxYear.getSelectedItem();

        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Introduce el email del empleado a buscar", "Consultar información", JOptionPane.PLAIN_MESSAGE);
        }
        else {
            jTextFieldHoursAssigned.setText("");
            jTextFieldHoursWorked.setText("");
            jTextFieldAbsences.setText("");
            jTextFieldOvertime.setText("");
            jTextFieldRemainingHours.setText("");
            sendHTTPRequest(email, month, year);
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    /**
     * Función encargada de gestionar la petición HTTP al servidor ReST.
     * Inicialmente, se configura los parámetros de la conexión HTTP. 
     * Después, dependiendo del código de respuesta de la conexión, se notifica 
     * al usuario sacando un popup con un mensaje en caso de que haya habido
     * algún error. En caso de que el código de respuesta sea positivo,
     * se lee la respuesta y se llama a una función auxiliar para actualizar
     * la GUI.
     * 
     * @param email string con el email del usuario a buscar en la BDD
     * @param month string con el mes a analizar
     * @param year  string con el año a analizar
     */
    private void sendHTTPRequest(String email, String month, String year) {
        try {
            // Configurar la llamada HTTP al servidor ReST
            HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/analysis/" + email + "/" + month + "/" + year).openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            
            switch (connection.getResponseCode()) {
                case 200:
                    String response = "";
                    Scanner scanner = new Scanner(connection.getInputStream());

                    while (scanner.hasNextLine()) {
                        response += scanner.nextLine() + "\n";
                    }
                    scanner.close();
                    updateInfo(response, month, year);
                    break;
                case 400:
                    JOptionPane.showMessageDialog(this, "No hay ningún registro asociado a ese email", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Error desconocido al recuperar información de asistencia.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.net.SocketTimeoutException e) {
            JOptionPane.showMessageDialog(this, "Error. No se ha podido conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Función auxilar encargada de actualizar la interfaz de usuario con los
     * de respuesta del servidor. En caso de que el administrador haya escogido
     * leer los datos del mes actual, se le notifica de que pueden no ser del todo
     * representativos.
     * 
     * @param jsonResponse string con el JSON de respuesta
     * @param month string con el mes analizado
     * @param year string con el año analizado
     */
    private void updateInfo(String jsonResponse, String month, String year) {
        try {
            String currentMonth = new SimpleDateFormat("MM/yyyy").format(new Date());
            if (currentMonth.equals(month + "/" + year))
                JOptionPane.showMessageDialog(this, "Se está buscando información correspondiente a este mes,\npor lo que la información sobre "
                        + "horas trabajadas respecto a\nhoras totales puede no ser representativa", "Consultar información", JOptionPane.INFORMATION_MESSAGE);
            
            JSONObject obj = new JSONArray(jsonResponse).getJSONObject(0);
            if(obj.optInt("total_hours",-1) == -1 ) {
                JOptionPane.showMessageDialog(this, "No hay información de este empleado para este mes", "Consultar información", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                jTextFieldHoursAssigned.setText(""+obj.optInt("total_hours", 0));
                jTextFieldHoursWorked.setText(""+obj.optInt("hours_worked", 0));
                jTextFieldAbsences.setText(""+obj.optInt("absences"));
                jTextFieldOvertime.setText((obj.optInt("total_hours", 0) > obj.optInt("hours_worked", 0)) ? "" + 0 : "" + (obj.optInt("hours_worked", 0) - obj.optInt("total_hours", 0)));
                jTextFieldRemainingHours.setText((obj.optInt("total_hours", 0) > obj.optInt("hours_worked", 0)) ? "" + (obj.optInt("total_hours", 0) - obj.optInt("hours_worked", 0)) : "" + 0);
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JComboBox<String> jComboBoxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelHourAnalysis;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelMonth1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldAbsences;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldHoursAssigned;
    private javax.swing.JTextField jTextFieldHoursWorked;
    private javax.swing.JTextField jTextFieldOvertime;
    private javax.swing.JTextField jTextFieldRemainingHours;
    // End of variables declaration//GEN-END:variables
}
