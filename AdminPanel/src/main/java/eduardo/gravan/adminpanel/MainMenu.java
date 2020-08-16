package eduardo.gravan.adminpanel;

/**
 * Clase que recoge la lógica de aplicación correspondiente al menú principal
 * en la GUI. Es el nexo de unión de todas las funciones de la aplicación.
 * Su única funcionalidad es la de recopilar los botones que llevan al resto
 * de ventanas.
 */
public class MainMenu extends javax.swing.JFrame {
    
    public MainMenu() {
        initComponents(); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelEmployees = new javax.swing.JPanel();
        jLabelEmployees = new javax.swing.JLabel();
        jButtonRegisterEmployee = new javax.swing.JButton();
        jButtonDeleteEmployee = new javax.swing.JButton();
        jButtonEmployeeInfo = new javax.swing.JButton();
        jPanelSchedules = new javax.swing.JPanel();
        jLabelSchedules = new javax.swing.JLabel();
        jButtonAddSchedule = new javax.swing.JButton();
        jButtonRemoveSchedule = new javax.swing.JButton();
        jButtonCheckSchedule = new javax.swing.JButton();
        jPanelAttendance = new javax.swing.JPanel();
        jLabelAttendance = new javax.swing.JLabel();
        jButtonCheckAttendance = new javax.swing.JButton();
        jButtonHourAnalysis = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TFG - Admin Panel");
        setResizable(false);

        jLabelEmployees.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelEmployees.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEmployees.setText("Empleados");
        jLabelEmployees.setToolTipText("");

        jButtonRegisterEmployee.setText("Alta de empleado");
        jButtonRegisterEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterEmployeeActionPerformed(evt);
            }
        });

        jButtonDeleteEmployee.setText("Baja de empleado");
        jButtonDeleteEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteEmployeeActionPerformed(evt);
            }
        });

        jButtonEmployeeInfo.setText("Consultar empleado");
        jButtonEmployeeInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmployeeInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEmployeesLayout = new javax.swing.GroupLayout(jPanelEmployees);
        jPanelEmployees.setLayout(jPanelEmployeesLayout);
        jPanelEmployeesLayout.setHorizontalGroup(
            jPanelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEmployeesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonRegisterEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEmployeeInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDeleteEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(127, 127, 127))
        );
        jPanelEmployeesLayout.setVerticalGroup(
            jPanelEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployeesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelEmployees)
                .addGap(28, 28, 28)
                .addComponent(jButtonRegisterEmployee)
                .addGap(18, 18, 18)
                .addComponent(jButtonDeleteEmployee)
                .addGap(18, 18, 18)
                .addComponent(jButtonEmployeeInfo)
                .addGap(0, 67, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Empledos", jPanelEmployees);

        jLabelSchedules.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelSchedules.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSchedules.setText("Horarios");
        jLabelSchedules.setToolTipText("");

        jButtonAddSchedule.setText("Añadir horario");
        jButtonAddSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddScheduleActionPerformed(evt);
            }
        });

        jButtonRemoveSchedule.setText("Eliminar horario");
        jButtonRemoveSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveScheduleActionPerformed(evt);
            }
        });

        jButtonCheckSchedule.setText("Consultar horario");
        jButtonCheckSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckScheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSchedulesLayout = new javax.swing.GroupLayout(jPanelSchedules);
        jPanelSchedules.setLayout(jPanelSchedulesLayout);
        jPanelSchedulesLayout.setHorizontalGroup(
            jPanelSchedulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSchedules, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            .addGroup(jPanelSchedulesLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(jPanelSchedulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCheckSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRemoveSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSchedulesLayout.setVerticalGroup(
            jPanelSchedulesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSchedulesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabelSchedules)
                .addGap(28, 28, 28)
                .addComponent(jButtonAddSchedule)
                .addGap(18, 18, 18)
                .addComponent(jButtonRemoveSchedule)
                .addGap(18, 18, 18)
                .addComponent(jButtonCheckSchedule)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Horarios", jPanelSchedules);

        jLabelAttendance.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelAttendance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAttendance.setText("Asistencia");
        jLabelAttendance.setToolTipText("");

        jButtonCheckAttendance.setText("Consultar Asistencia");
        jButtonCheckAttendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckAttendanceActionPerformed(evt);
            }
        });

        jButtonHourAnalysis.setText("Análisis de horas");
        jButtonHourAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHourAnalysisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAttendanceLayout = new javax.swing.GroupLayout(jPanelAttendance);
        jPanelAttendance.setLayout(jPanelAttendanceLayout);
        jPanelAttendanceLayout.setHorizontalGroup(
            jPanelAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            .addGroup(jPanelAttendanceLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanelAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCheckAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHourAnalysis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAttendanceLayout.setVerticalGroup(
            jPanelAttendanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAttendanceLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelAttendance)
                .addGap(71, 71, 71)
                .addComponent(jButtonCheckAttendance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHourAnalysis)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Asistencia", jPanelAttendance);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Función encargada de gestionar el evento de pulsar el botón de
     * registro de empleado.
     * Se crea la nueva ventana y se hace que la actual pase a ser invisible.
     * @param evt evento de swing
     */
    private void jButtonRegisterEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegisterEmployeeActionPerformed
        new RegisterEmployee(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonRegisterEmployeeActionPerformed
    
    /**
     * Función encargada de gestionar el evento de pulsar el botón de
     * borrar empleados.
     * Se crea la nueva ventana y se hace que la actual pase a ser invisible.
     * @param evt evento de swing
     */
    private void jButtonDeleteEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteEmployeeActionPerformed
        new DeleteEmployee(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonDeleteEmployeeActionPerformed
    
    /**
     * Función encargada de gestionar el evento de pulsar el botón de
     * información de empleados.
     * Se crea la nueva ventana y se hace que la actual pase a ser invisible.
     * @param evt evento de swing
     */
    private void jButtonEmployeeInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmployeeInfoActionPerformed
        new EmployeeInfo(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonEmployeeInfoActionPerformed
    
    /**
     * Función encargada de gestionar el evento de pulsar el botón de
     * añadir un nuevo registro de horario.
     * Se crea la nueva ventana y se hace que la actual pase a ser invisible.
     * @param evt evento de swing
     */
    private void jButtonAddScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddScheduleActionPerformed
        new AddSchedule(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonAddScheduleActionPerformed
    
    /**
     * Función encargada de gestionar el evento de pulsar el botón de
     * eliminar un registro de horario.
     * Se crea la nueva ventana y se hace que la actual pase a ser invisible.
     * @param evt evento de swing
     */
    private void jButtonRemoveScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveScheduleActionPerformed
        new DeleteSchedule(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonRemoveScheduleActionPerformed
    
    /**
     * Función encargada de gestionar el evento de pulsar el botón de
     * recuperar información sobre horarios.
     * Se crea la nueva ventana y se hace que la actual pase a ser invisible.
     * @param evt evento de swing
     */
    private void jButtonCheckScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckScheduleActionPerformed
        new ScheduleInfo(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCheckScheduleActionPerformed
    
    /**
     * Función encargada de gestionar el evento de pulsar el botón de
     * comprobar información de asistencia.
     * Se crea la nueva ventana y se hace que la actual pase a ser invisible.
     * @param evt evento de swing 
     */
    private void jButtonCheckAttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckAttendanceActionPerformed
        new AttendanceInfo(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCheckAttendanceActionPerformed
    
    /**
     * Función encargada de gestionar el evento de pulsar el botón de
     * analizar la información de asistencia.
     * Se crea la nueva ventana y se hace que la actual pase a ser invisible.
     * @param evt evento de swing
     */
    private void jButtonHourAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHourAnalysisActionPerformed
        new AttendanceHourAnalysis(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonHourAnalysisActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddSchedule;
    private javax.swing.JButton jButtonCheckAttendance;
    private javax.swing.JButton jButtonCheckSchedule;
    private javax.swing.JButton jButtonDeleteEmployee;
    private javax.swing.JButton jButtonEmployeeInfo;
    private javax.swing.JButton jButtonHourAnalysis;
    private javax.swing.JButton jButtonRegisterEmployee;
    private javax.swing.JButton jButtonRemoveSchedule;
    private javax.swing.JLabel jLabelAttendance;
    private javax.swing.JLabel jLabelEmployees;
    private javax.swing.JLabel jLabelSchedules;
    private javax.swing.JPanel jPanelAttendance;
    private javax.swing.JPanel jPanelEmployees;
    private javax.swing.JPanel jPanelSchedules;
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables
}
