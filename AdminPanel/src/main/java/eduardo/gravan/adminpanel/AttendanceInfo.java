package eduardo.gravan.adminpanel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Clase que recoge la lógica de aplicación correspondiente a la ventana de
 * la GUI encargada de dar información sobre la asistencia de empleados.
 */
public class AttendanceInfo extends javax.swing.JFrame {
    private final MainMenu mainMenu;
    private final DefaultTableModel tableModel;
    
    public AttendanceInfo(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        initComponents();
        tableModel = (DefaultTableModel) jTable.getModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelAttendanceInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonSearch = new javax.swing.JButton();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldDate = new javax.swing.JTextField();
        jLabelDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TFG - Admin Panel");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabelAttendanceInfo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelAttendanceInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAttendanceInfo.setText("Información de asistencia");

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Email", "Fecha", "Entrada", "Salida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable);

        jButtonSearch.setText("Buscar");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jLabelEmail.setText("Email del empleado");

        jLabelDate.setText("Fecha (yyyy-mm-dd, opcional)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAttendanceInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEmail)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabelDate)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelAttendanceInfo)
                .addGap(18, 18, 18)
                .addComponent(jLabelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDate)
                    .addComponent(jButtonSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
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
     * de asistencia. Si el input es correcto, se llama a la función encargada
     * de mandar la petición al servidor. En caso contrario, se notifica al usuario
     * el error a través de un popup.
     * @param evt evento de swing
     */
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        String email = jTextFieldEmail.getText();
        String date = jTextFieldDate.getText();
        
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Introduce el email del empleado a buscar", "Consultar información", JOptionPane.PLAIN_MESSAGE);
        } 
        else if (date.equals("") || checkDateFormat(date)){
            sendHTTPRequest(email, date);
        }
        else {
            JOptionPane.showMessageDialog(this, "Error. El formato de la fecha no es válido (ej. 09/08/2020)", "Error", JOptionPane.ERROR_MESSAGE);
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
     * @param email string con el email del empleado que se quiere analizar
     * @param date  fecha del registro de asistencia que se quiere analizar
     */
    private void sendHTTPRequest(String email, String date) {
        try {
            // Configurar la llamada HTTP al servidor ReST
            HttpURLConnection connection;
            if(date.equals("")) {
                connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/attendance/" + email).openConnection(); 
            }
            else {
                connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/attendance/" + email + "/" + date).openConnection();
            }
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            
            // Se borran los datos que haya en la tabla
            tableModel.setRowCount(0);
            switch (connection.getResponseCode()) {
                case 200:
                    String response = "";
                    Scanner scanner = new Scanner(connection.getInputStream());

                    while (scanner.hasNextLine()) {
                        response += scanner.nextLine() + "\n";
                    }
                    scanner.close();
                    fillTable(response);
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
     * Función auxilar encargada de comprobar que la fecha se encuentra
     * en el formato correcto para poder realizar la consulta a la base de datos.
     * Simplemente mira que esté en formato "xxxx-xx-xx".
     * 
     * @param date string con la fecha cuyo formato se quiere comprobar
     * @return true si la fecha está en formato correcto, false si no
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
     * Función auxilar encargada de rellenar la tabla de la GUI con la información
     * recuperada desde el servidor. 
     * @param jsonResponse String con la respuesta del servidor en formato JSON
     */
    private void fillTable (String jsonResponse) {
        try {
            JSONArray array = new JSONArray(jsonResponse);
            JSONObject obj;
            int arrayLength = array.length();
            tableModel.setRowCount(arrayLength);
            
            for(int i = 0; i < arrayLength; i++) {
                obj = array.getJSONObject(i);
                jTable.setValueAt(obj.getString("email"), i, 0);
                jTable.setValueAt(obj.getString("date"), i, 1);
                jTable.setValueAt(obj.optString("arrived_time", ""), i, 2);
                jTable.setValueAt(obj.optString("left_time", ""), i, 3);
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabelAttendanceInfo;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldDate;
    private javax.swing.JTextField jTextFieldEmail;
    // End of variables declaration//GEN-END:variables
}
