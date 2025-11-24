package lab7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class QuizFram extends javax.swing.JFrame {

    // --- State Variables ---
    private Quiz quiz;
    private int currentIndex = 0;
    // Array to store the selected answer text for each question
    private String[] userAnswers;
    private int score = 0;

    // NOTE: For a real application, you should also pass the Student
    // object and the Course object here to update the student's progress and save the score.
    // public QuizFram(Quiz quiz, Student student, Course course) { ... }

    // --- Constructor (Includes the fix) ---
    public QuizFram(Quiz quiz) {
        // Initialize UI components (usually auto-generated)
        initComponents();

        this.quiz = quiz;

        // *** THE FIX: Defensive Programming Against Null Quiz ***
        if (quiz == null || quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Error: Quiz data is missing or incomplete. Cannot start quiz.",
                    "Initialization Error",
                    JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Close the frame immediately
            return; // Stop constructor execution
        }
        // *** END FIX ***

        // Initialization is safe now:
        this.userAnswers = new String[quiz.getQuestions().size()];
        viewQuestions(currentIndex);
    }

    // --- Core Logic Methods ---

    /**
     * Loads the question and options for the given index into the UI.
     */
    private void viewQuestions(int index) {
        if (index < 0 || index >= quiz.getQuestions().size()) {
            return;
        }

        Questions q = quiz.getQuestions().get(index);

        // Update Question Index Label (e.g., "Q 1 / 5")
        jLabel1.setText("Question " + (index + 1) + " of " + quiz.getQuestions().size());

        // Update Question Text Label
        jLabel2.setText("<html>" + q.getQuestionText() + "</html>"); // Use HTML for wrapping long text

        String[] options = q.getOptions();

        // Populate Radio Buttons with options (Handle case where options array is shorter than 4)
        jRadioButton1.setText(options.length > 0 ? options[0] : "");
        jRadioButton2.setText(options.length > 1 ? options[1] : "");
        jRadioButton3.setText(options.length > 2 ? options[2] : "");
        jRadioButton4.setText(options.length > 3 ? options[3] : "");

        // Hide unused radio buttons
        jRadioButton3.setVisible(options.length > 2);
        jRadioButton4.setVisible(options.length > 3);

        // Restore previous selection
        buttonGroup1.clearSelection();
        if (userAnswers[index] != null) {
            if (userAnswers[index].equals(jRadioButton1.getText())) jRadioButton1.setSelected(true);
            else if (userAnswers[index].equals(jRadioButton2.getText())) jRadioButton2.setSelected(true);
            else if (userAnswers[index].equals(jRadioButton3.getText())) jRadioButton3.setSelected(true);
            else if (userAnswers[index].equals(jRadioButton4.getText())) jRadioButton4.setSelected(true);
        }

        // Update button text
        jButton1.setText(index == quiz.getQuestions().size() - 1 ? "Submit Quiz" : "Next");
        jButton2.setVisible(index > 0); // Hide previous button on the first question
    }

    /**
     * Saves the current radio button selection to the userAnswers array.
     */
    private void saveCurrentAnswer() {
        String selectedAnswer = getRadioAnswer();
        if (selectedAnswer != null) {
            userAnswers[currentIndex] = selectedAnswer;
        }
    }

    /**
     * Gets the text of the currently selected radio button.
     */
    private String getRadioAnswer() {
        if (jRadioButton1.isSelected()) return jRadioButton1.getText();
        if (jRadioButton2.isSelected()) return jRadioButton2.getText();
        if (jRadioButton3.isSelected()) return jRadioButton3.getText();
        if (jRadioButton4.isSelected()) return jRadioButton4.getText();
        return null;
    }

    /**
     * Calculates the final score and displays the result.
     */
    private void calculateScoreAndFinish() {
        score = 0;
        int totalQuestions = quiz.getQuestions().size();

        for (int i = 0; i < totalQuestions; i++) {
            Questions q = quiz.getQuestions().get(i);
            String userAnswer = userAnswers[i];

            // Check if the user's answer is not null and matches the correct answer
            if (userAnswer != null && userAnswer.equals(q.getCorrectAnswer())) {
                score++;
            }
        }

        String resultMessage = String.format(
                "Quiz Finished!\nYour Score: %d / %d", score, totalQuestions
        );

        // Check if the student passed (assuming quiz.isPassed() is implemented)
        if (quiz.isPassed(score)) {
            resultMessage += "\nCongratulations! You passed the quiz.";
        } else {
            resultMessage += "\nUnfortunately, you did not pass. Please review the lesson and try again.";
        }

        JOptionPane.showMessageDialog(this, resultMessage, "Quiz Result", JOptionPane.INFORMATION_MESSAGE);

        // --- SCORE SAVING PLACEHOLDER ---
        // At this point, you should implement the logic to:
        // 1. Save the 'score' to the Student's record for this Course/Lesson.
        // 2. Update the Student's overall course progress.
        // 3. Check if a certificate should be generated.
        // ---------------------------------

        this.dispose(); // Close the quiz window
        // You might want to open the StudentDashboardFrame or CourseWindowMine again here
    }

    // --- Event Handlers (ActionPerformed methods) ---

    private void jButton1ActionPerformed(ActionEvent evt) {
        saveCurrentAnswer();

        if (currentIndex < quiz.getQuestions().size() - 1) {
            // Move to the next question
            currentIndex++;
            viewQuestions(currentIndex);
        } else {
            // End of quiz - Submit
            calculateScoreAndFinish();
        }
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        saveCurrentAnswer(); // Save the current answer before moving back

        if (currentIndex > 0) {
            // Move to the previous question
            currentIndex--;
            viewQuestions(currentIndex);
        }
    }

    // --- Auto-Generated Code (Placeholders for UI Components) ---

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel(); // Question Index
        jLabel2 = new javax.swing.JLabel(); // Question Text
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton(); // Next/Submit
        jButton2 = new javax.swing.JButton(); // Previous

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quiz");

        // Add Radio Buttons to a ButtonGroup so only one can be selected
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);

        // Add action listeners
        jButton1.setText("Next");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Previous");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        // --- Minimal Layout Setup (Simulating the builder's job) ---
        // This is a minimal, non-functional layout placeholder to complete the class structure.
        // The actual GUI builder layout is complex, but the components are named here.

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jRadioButton1)
                                                        .addComponent(jRadioButton2))
                                                .addGap(50, 50, 50)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jRadioButton4)
                                                        .addComponent(jRadioButton3)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButton2)
                                                .addGap(180, 180, 180)
                                                .addComponent(jButton1)))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton1)
                                        .addComponent(jRadioButton3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton2)
                                        .addComponent(jRadioButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration
}