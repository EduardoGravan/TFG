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
 * la GUI encargada de dar información sobre los empleados.
 */
public class EmployeeInfo extends javax.swing.JFrame {
    private final MainMenu mainMenu;
    private final DefaultTableModel tableModel;
    
    public EmployeeInfo(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        initComponents();
        tableModel = (DefaultTableModel) jTable.getModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelEmployeeInfo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButtonSearch = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jLabelName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TFG - Admin Panel");
        setPreferredSize(new java.awt.Dimension(550, 310));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabelEmployeeInfo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelEmployeeInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEmployeeInfo.setText("Información de empleados");

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Email", "Admin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        jLabelName.setText("Nombre del empleado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelEmployeeInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelEmployeeInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldName))
                .addGap(27, 27, 27)
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
     * del empleado. Si el input es correcto, se llama a la función encargada
     * de mandar la petición al servidor. En caso contrario, se notifica al usuario
     * el error a través de un popup.
     * @param evt evento de swing
     */
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        String name = jTextFieldName.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog(this, "Introduce el nombre del empleado a buscar", "Consultar información", JOptionPane.PLAIN_MESSAGE);
        } else {
            sendHTTPRequest(name);
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
     * @param name  string con el nombre del empleado sobre el que hacer la búsqueda
     */
    private void sendHTTPRequest(String name) {
        try {
            // Configurar la llamada HTTP al servidor ReST
            HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.136:8080/api/getInfo/" + name).openConnection();
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
                    JOptionPane.showMessageDialog(this, "No hay ningún empleado con ese nombre", "Error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(this, "Error desconocido al recuperar información de empleados.", "Error", JOptionPane.ERROR_MESSAGE);
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
                jTable.setValueAt(obj.getString("name"), i, 0);
                jTable.setValueAt(obj.getString("email"), i, 1);
                jTable.setValueAt(obj.getInt("is_admin") == 1, i, 2);
            }
    
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabelEmployeeInfo;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
