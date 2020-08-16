package eduardo.gravan.adminpanel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * Clase que recoge la lógica de aplicación correspondiente a la ventana de
 * la GUI encargada de borrar registros de horarios de la base de datos.
 */
public class DeleteSchedule extends javax.swing.JFrame {
    private final MainMenu mainMenu;
    private final String longMonths [];
    
    public DeleteSchedule(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        longMonths = new String []{"0","2","4","6","7","9","11"};
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelDeleteSchedule = new javax.swing.JLabel();
        jLabelDate = new javax.swing.JLabel();
        jComboBoxDay = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxMonth = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxYear = new javax.swing.JComboBox<>();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jButtonDeleteRecord = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TFG - Admin Panel");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabelDeleteSchedule.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelDeleteSchedule.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDeleteSchedule.setText("Eliminar horario");

        jLabelDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDate.setText("Fecha");

        jComboBoxDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Día");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mes");

        jComboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        jComboBoxMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMonthActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Año");

        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021" }));

        jLabelEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEmail.setText("Email del empleado");

        jButtonDeleteRecord.setText("Eliminar");
        jButtonDeleteRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteRecordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelDeleteSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabelDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDeleteRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxMonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelDeleteSchedule)
                .addGap(27, 27, 27)
                .addComponent(jLabelEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelDate)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(jComboBoxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(1, 1, 1)
                        .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(1, 1, 1)
                        .addComponent(jComboBoxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButtonDeleteRecord)
                .addGap(22, 22, 22))
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
     * Esta funcion se encarga de gestionar el evento de selección de mes.
     * Al seleccionar un mes, se actualiza la lista de días para que concuerden
     * con los días que tiene ese mes (ej. Enero = 31, Febrero = 28, Abril = 30)
     * @param evt evento de swing
     */
    private void jComboBoxMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMonthActionPerformed
        int counter;
        int selectedItem = jComboBoxMonth.getSelectedIndex();
        int selectedDay = jComboBoxDay.getSelectedIndex();
        jComboBoxDay.removeAllItems();

        if(Arrays.asList(longMonths).contains(""+selectedItem)) {
            for(int i = 1; i <= 31; i++) {
                jComboBoxDay.addItem("" + i);
            }
            counter = 30;
        }
        else if(selectedItem == 1) {
            for(int i = 1; i <= 28; i++) {
                jComboBoxDay.addItem("" + i);
            }
            counter = 27;
        }
        else {
            for(int i = 1; i <= 30; i++) {
                jComboBoxDay.addItem("" + i);
            }
            counter = 29;
        }
        
        // Si se puede restaurar el día que estaba escogido después de regenerar
        // el combobox de los días, se restaura
        if(selectedDay <= counter) {
            jComboBoxDay.setSelectedIndex(selectedDay);
        }
    }//GEN-LAST:event_jComboBoxMonthActionPerformed

    /**
     * Función que gestiona el evento de pulsar el botón para eliminar el registro
     * de horario. Se formatea el input de fecha para que esté en el formato
     * correcto para la base de datos y, si todo está bien, se pasa a la función
     * encargada de enviar la petición al servidor.
     * @param evt evento de swing
     */
    private void jButtonDeleteRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteRecordActionPerformed
        String email = jTextFieldEmail.getText();
        String day = (jComboBoxDay.getSelectedIndex() > 8)
        ? (String) jComboBoxDay.getSelectedItem()
        : (String) "0" + jComboBoxDay.getSelectedItem();

        String month = (jComboBoxMonth.getSelectedIndex() > 8)
        ? "" + (1 + jComboBoxMonth.getSelectedIndex())
        : "0" + (1 + jComboBoxMonth.getSelectedIndex());

        String year = (String) jComboBoxYear.getSelectedItem();
        String date = year + "-" + month + "-" + day ;


        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Error. Rellene los campos especificados.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        else {
            sendHTTPRequest(email, date);
        }
    }//GEN-LAST:event_jButtonDeleteRecordActionPerformed

    /**
     * Función encargada de gestionar la petición HTTP al servidor ReST.
     * Inicialmente, se configura los parámetros de la conexión HTTP. 
     * Después, dependiendo del código de respuesta de la conexión, se notifica 
     * al usuario sacando un popup con un mensaje que refleja la respuesta
     * del servidor.
     * 
     * @param email string con el email del empleado
     * @param date  string con la fecha del registro de horario a borrar
     */
    private void sendHTTPRequest(String email, String date) {
        try {
            // Configurar la llamada HTTP al servidor ReST
            HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/schedule/" + email + "/" + date).openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("DELETE");
            
            switch(connection.getResponseCode()) {
                case 204:
                    JOptionPane.showMessageDialog(this, "Horario eliminado correctamente", "Horario eliminado", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                case 400:
                    JOptionPane.showMessageDialog(this, "Error. No hay registros de horarios para ese empleado en ese día.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(this, "Error desconocido al intentar borrar los datos de horarios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }  
        catch (java.net.SocketTimeoutException e) {
            JOptionPane.showMessageDialog(this, "Error. No se ha podido conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeleteRecord;
    private javax.swing.JComboBox<String> jComboBoxDay;
    private javax.swing.JComboBox<String> jComboBoxMonth;
    private javax.swing.JComboBox<String> jComboBoxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelDeleteSchedule;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JTextField jTextFieldEmail;
    // End of variables declaration//GEN-END:variables
}
