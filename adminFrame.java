package lab7;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

/**
 *
 * @author fawaa
 */
public class adminFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(adminFrame.class.getName());

    /**
     * Creates new form adminFrame
     */
    public adminFrame(admin admin) {
        initComponents();
        loadCoursesIntoTable();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Course Title", "Course ID", "Instructor ID", "Status"
                }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("APPROVE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REJECT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("UPDATE TABLE");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTableButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jButton1)
                                .addGap(50, 50, 50)
                                .addComponent(jButton2)
                                .addGap(50, 50, 50)
                                .addComponent(jButtonUpdate)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addComponent(jButtonUpdate))
                                .addGap(49, 49, 49))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
    {
        // TODO add your handling code here:
        Courses courses = new Courses ("courses.json");
        try {
            courses.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int selectedCourseRow = jTable1.getSelectedRow() ;
        String selectedCourseTitle = jTable1.getValueAt(selectedCourseRow,0).toString();
        Course selectedCourse = null ;
        for ( Course c : courses.getCourses() )
        {
            if ( selectedCourseTitle.toLowerCase().equals(c.getTitle().toLowerCase()) )
            {
                c.setStatus("APPROVED");
                try {
                    courses.SaveToJsonCourses();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break ;
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)
    {
        // TODO add your handling code here:
        Courses courses = new Courses ("courses.json");
        try {
            courses.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int selectedCourseRow = jTable1.getSelectedRow() ;
        String selectedCourseTitle = jTable1.getValueAt(selectedCourseRow,0).toString();
        Course selectedCourse = null ;
        for ( Course c : courses.getCourses() )
        {
            if ( selectedCourseTitle.toLowerCase().equals(c.getTitle().toLowerCase()) )
            {
                c.setStatus("REJECTED");
                try {
                    courses.SaveToJsonCourses();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break ;
            }
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void updateTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadCoursesIntoTable(); // reloads data from JSON into the table
    }

//    private void jTable1ComponentShown(java.awt.event.ComponentEvent evt)
//    {
//        // TODO add your handling code here:
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Course Title");
//        model.addColumn("Course ID");
//        model.addColumn("Instructor ID");
//        model.addColumn("Status");
//
//        Courses cs = new Courses("D:\\programming\\java\\lab8\\lab8_downloaded_from_githiub\\lab7\\courses.json") ;
//
//        for ( int i = 0 ; i < cs.getCourses().size() ; i++ )
//        {
//            model.addRow(new Object[]{cs.getCourses().get(i).getTitle(), cs.getCourses().get(i).getCourseId() , cs.getCourses().get(i).getInstructorId(), cs.getCourses().get(i).getStatus()});
//        }
//        jTable1.setModel(model);
//        jTable1.setVisible(true);
//
//    }

    private void loadCoursesIntoTable()
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Course Title");
        model.addColumn("Course ID");
        model.addColumn("Instructor ID");
        model.addColumn("Status");

        Courses cs = new Courses("courses.json");
        try {
            cs.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //if ( cs.getCourses().size() == 0 ){ System.out.println("No courses found");}

        for (Course c : cs.getCourses()) {
            model.addRow(new Object[]{c.getTitle(), c.getCourseId(), c.getInstructorId(), c.getStatus()});
        }

        jTable1.setModel(model);
    }


    /**
     * @param args the command line arguments
     */


//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new adminFrame(admin).setVisible(true));
//    }



    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jButtonUpdate;
    // End of variables declaration
}
