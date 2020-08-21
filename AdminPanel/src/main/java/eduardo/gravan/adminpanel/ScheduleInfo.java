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
 * la GUI encargada de dar información sobre horarios.
 */
public class ScheduleInfo extends javax.swing.JFrame {
    private final MainMenu mainMenu;
    private final DefaultTableModel tableModel;
    
    public ScheduleInfo(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        initComponents();
        tableModel = (DefaultTableModel) jTable.getModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSearch1 = new javax.swing.JButton();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelName = new javax.swing.JLabel();
        jLabelScheduleInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabelMonth = new javax.swing.JLabel();
        jLabelMonth1 = new javax.swing.JLabel();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jComboBoxYear = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TFG - Admin Panel");
        setPreferredSize(new java.awt.Dimension(550, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButtonSearch1.setText("Buscar");
        jButtonSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearch1ActionPerformed(evt);
            }
        });

        jLabelName.setText("Email del empleado");

        jLabelScheduleInfo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelScheduleInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelScheduleInfo.setText("Información de horarios");

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
                "Email", "Fecha", "Inicio", "Fin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable);

        jLabelMonth.setText("Mes a analizar");

        jLabelMonth1.setText("Año a analizar");

        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelScheduleInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBoxYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelScheduleInfo)
                .addGap(18, 18, 18)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMonth)
                            .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMonth1)
                            .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jButtonSearch1)
                        .addGap(62, 62, 62)))
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
     * Función que gestiona el evento de pulsar el botón para buscar la información
     * de los horarios. Si el input es correcto, se llama a la función encargada
     * de mandar la petición al servidor. En caso contrario, se notifica al usuario
     * el error a través de un popup.
     * @param evt evento de swing
     */
    private void jButtonSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearch1ActionPerformed
        String email = jTextFieldEmail.getText();
        String month = (jComboBoxMonth.getSelectedIndex() > 8) 
                ? "" + (1 + jComboBoxMonth.getSelectedIndex()) 
                : "0" + (1 + jComboBoxMonth.getSelectedIndex());
        String year = (String) jComboBoxYear.getSelectedItem();
        
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Introduce el email del empleado a buscar", "Consultar información", JOptionPane.PLAIN_MESSAGE);
        } else {
            sendHTTPRequest(email, month, year);
        }
    }//GEN-LAST:event_jButtonSearch1ActionPerformed

    /**
     * Función encargada de gestionar la petición HTTP al servidor ReST.
     * Inicialmente, se configura los parámetros de la conexión HTTP. 
     * Después, dependiendo del código de respuesta de la conexión, se notifica 
     * al usuario sacando un popup con un mensaje en caso de que haya habido
     * algún error. En caso de que el código de respuesta sea positivo,
     * se lee la respuesta y se llama a una función auxiliar para actualizar
     * la GUI. 
     * 
     * @param email string con el email del empleado sobre el que hacer la búsqueda
     * @param month mes del registro de horario que se quiere buscar
     * @param year  año del registro de horario que se quiere buscar
     */
    private void sendHTTPRequest(String email, String month, String year) {
        try {
            // Configurar la llamada HTTP al servidor ReST
            HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/schedule/" + email + "/" + month + "/" + year).openConnection();
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
                    JOptionPane.showMessageDialog(this, "Error desconocido al recuperar información de horarios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (java.net.SocketTimeoutException e) {
            JOptionPane.showMessageDialog(this, "Error. No se ha podido conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
                jTable.setValueAt(obj.getString("start_time"), i, 2);
                jTable.setValueAt(obj.getString("end_time"), i, 3);
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch1;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JComboBox<String> jComboBoxYear;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelMonth1;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelScheduleInfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldEmail;
    // End of variables declaration//GEN-END:variables
}
