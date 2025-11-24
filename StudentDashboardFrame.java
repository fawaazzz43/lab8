package lab7;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;

public class StudentDashboardFrame extends javax.swing.JFrame
{
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentDashboardFrame.class.getName());
    DefaultListModel<Course> modelofcourses = new DefaultListModel<>(); // For Search Results (jList3)
    DefaultListModel<Course> previousCourses = new DefaultListModel<>(); // For Enrolled Courses (jList2)

    Student student;

    // NEW BUTTON (ONLY addition you requested)
    private javax.swing.JButton jButtonUpdateCourses;

    public StudentDashboardFrame(Student student)
    {
        this.student = student;
        initComponents();
        loadPreviousCourses();
    }

    private void loadPreviousCourses() {
        previousCourses.clear();
        DefaultListModel<String> previousTitles = new DefaultListModel<>();

        for (Course course : student.getEnrolledCourses())
        {
            previousTitles.addElement(course.getTitle());
            previousCourses.addElement(course);
        }

        jList2.setModel(previousTitles);
    }
    

    @SuppressWarnings("unchecked")
    private void initComponents()
    {
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new JList<String>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new JList<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        // NEW UPDATE BUTTON
        jButtonUpdateCourses = new javax.swing.JButton();
        jButtonUpdateCourses.setText("UPDATE COURSES");
        jButtonUpdateCourses.setFont(new java.awt.Font("Cambria", 1, 16));
        jButtonUpdateCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPreviousCourses();
            }
        });

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24));
        jLabel1.setText("YOUR COURSES");

        jButton1.setFont(new java.awt.Font("Cambria", 1, 18));
        jButton1.setText("SEARCH ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jList2MouseClicked(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        jScrollPane2.setViewportView(jList2);

        jList3.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) { }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) { }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) { }
        });
        jList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jList3MouseClicked(evt);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        jList3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) { }
        });
        jList3.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) { }
        });
        jScrollPane3.setViewportView(jList3);

        jLabel5.setText("HI " + student.getUsername().toUpperCase());
        jLabel5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) { }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                )
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButtonUpdateCourses, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1))
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton1)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(43, 43, 43)
                                .addComponent(jScrollPane3))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(65, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonUpdateCourses)
                                .addGap(20, 20, 20))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
    {
        String courseTitle = jTextField1.getText().trim();

        if (courseTitle.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please enter a course tile");
            return;
        }

        modelofcourses.clear();
        DefaultListModel<String> modeloftitles = new DefaultListModel<>();

        ArrayList<Course> results = student.search(courseTitle);

        if (results == null || results.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "No course found");
            return;
        }

        for (Course course : results)
        {
            modelofcourses.addElement(course);
            modeloftitles.addElement(course.getTitle());
        }
        jList3.setModel(modeloftitles);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {}

    private void jList3MouseClicked(java.awt.event.MouseEvent evt) throws IOException
    {
        // search list
        if (evt.getClickCount() == 2)
        {
            String selectedtitle = jList3.getSelectedValue();
            if (selectedtitle != null)
            {
                Course selectedcourse = null;

                for (int i = 0; i < modelofcourses.getSize(); i++)
                {
                    if (selectedtitle.equals(modelofcourses.get(i).getTitle()))
                    {
                        selectedcourse = modelofcourses.get(i);
                        break;
                    }
                }
                if (selectedcourse != null)
                    new CourseWindow(selectedcourse, student).setVisible(true);
            }
        }
    }

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) throws IOException
    {
        if (evt.getClickCount() == 2)
        {
            String selectedtitle = jList2.getSelectedValue();
            if (selectedtitle != null)
            {
                Course selectedcourse = null;

                for (int i = 0; i < previousCourses.getSize(); i++)
                {
                    if (selectedtitle.equals(previousCourses.get(i).getTitle()))
                    {
                        selectedcourse = previousCourses.get(i);
                        break;
                    }
                }

                if (selectedcourse != null)
                {
                    //new CourseWindow(selectedcourse, student).setVisible(true);

                    new CourseWindowMine(selectedcourse, student).setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Course not found");
                }

            }
        }
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private JList<String> jList2;
    private JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
}
