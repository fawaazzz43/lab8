package lab7;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class SignUpFrame extends javax.swing.JFrame {
    private static int ID = 10;

    public SignUpFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        button1 = new java.awt.Button();
        textField1 = new java.awt.TextField();
        textField2 = new java.awt.TextField();
        textField3 = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        studentCheckBox = new javax.swing.JCheckBox();
        instructorCheckBox = new javax.swing.JCheckBox();
        adminCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button1.setLabel("SignUp");
        button1.addActionListener(evt -> button1ActionPerformed(evt));

        textField1.setName("");
        textField2.setName("");
        textField3.setName("");

        label1.setFont(new java.awt.Font("Cambria", 0, 12));
        label1.setText("WELCOME TO SKILLFORGE");

        label2.setFont(new java.awt.Font("Cambria", 1, 10));
        label2.setText("USERNAME");

        label3.setFont(new java.awt.Font("Cambria", 1, 10));
        label3.setText("EMAIL");

        label4.setFont(new java.awt.Font("Cambria", 1, 10));
        label4.setText("PASSWORD");

        studentCheckBox.setText("Student");
        studentCheckBox.addActionListener(evt -> roleCheckBoxSelected("student"));

        instructorCheckBox.setText("Instructor");
        instructorCheckBox.addActionListener(evt -> roleCheckBoxSelected("instructor"));

        adminCheckBox.setText("Admin");
        adminCheckBox.addActionListener(evt -> roleCheckBoxSelected("admin"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(141, 141, 141)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(studentCheckBox)
                                                        .addComponent(instructorCheckBox)
                                                        .addComponent(adminCheckBox)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(147, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(studentCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(instructorCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adminCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }

    private void roleCheckBoxSelected(String role) {
        studentCheckBox.setSelected(role.equals("student"));
        instructorCheckBox.setSelected(role.equals("instructor"));
        adminCheckBox.setSelected(role.equals("admin"));
    }

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
        String username = textField1.getText();
        String email = textField2.getText();
        String password = textField3.getText();
        String role = studentCheckBox.isSelected() ? "student" :
                instructorCheckBox.isSelected() ? "instructor" :
                        adminCheckBox.isSelected() ? "admin" : "";

        if (username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username is required!");
            return;
        }
        if (email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email is required!");
            return;
        }
        if (password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password is required!");
            return;
        }
        if (role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a role!");
            return;
        }

        int userId = generateUserId();

        User userFile = new User("users.json");
        userFile.load();

        SignUp signUp = new SignUp();

        if (signUp.validateuserId(userId)) {
            JOptionPane.showMessageDialog(this, "User ID already exists!");
            return;
        }
        int emailValidation = signUp.validateuserEmail(email);
        if (emailValidation == 1) {
            JOptionPane.showMessageDialog(this, "Invalid email format!");
            return;
        }
        if (emailValidation == 2) {
            JOptionPane.showMessageDialog(this, "Email already exists!");
            return;
        }

        password = SignUp.hashPassword(password);

        try {
            switch (role) {
                case "student" -> {
                    Student newUser = new Student(userId, role, username, email, password);
                    signUp.AddStudent(newUser);
                    new StudentDashboardFrame(newUser).setVisible(true);
                }
                case "instructor" -> {
                    Instructor newUser = new Instructor(userId, role, username, email, password);
                    signUp.AddInstructor(newUser);
                    new InstructorDashboardFrame(newUser).setVisible(true);
                }
                case "admin" -> {
                    admin newUser = new admin(userId, role, username, email, password);
                    signUp.AddAdmin(newUser);
                    new adminFrame(newUser).setVisible(true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        userFile.save();
        JOptionPane.showMessageDialog(this, "User added successfully!");
        this.setVisible(false);
    }

    private int generateUserId() {
        int id;
        return (int) (Math.random() * 10000);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new SignUpFrame().setVisible(true));
    }

    // Variables declaration
    private java.awt.Button button1;
    private javax.swing.JCheckBox studentCheckBox;
    private javax.swing.JCheckBox instructorCheckBox;
    private javax.swing.JCheckBox adminCheckBox;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;
    private java.awt.TextField textField3;
}
