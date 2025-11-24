package lab7;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CourseWindow extends JFrame
{
    private JPanel lessonsPanel;

    private JTextArea jTextAreaTitle;
    private JTextArea jTextAreaCourseId;
    private JTextArea jTextAreaInstructorId;
    private JTextArea jTextAreaDescription;

    private JButton enrollButton; // Button to enroll

    ArrayList<Lesson> lessons;
    static Course course;
    static Student student;

    public CourseWindow(Course course, Student student) throws IOException
    {
        this.course = course;
        this.student = student;
        this.lessons = course.getLessons();
        initComponents();

        // 1. Call the method to generate labels (instead of checkboxes)
        addLessonCheckboxes(course.getLessons());

        updateEnrollButton();
    }

    public CourseWindow()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel6 = new JLabel();
        jLabel7 = new JLabel();

        lessonsPanel = new JPanel();
        lessonsPanel.setLayout(new BoxLayout(lessonsPanel, BoxLayout.Y_AXIS));

        // Title TextArea
        jTextAreaTitle = createTextArea(course != null ? course.getTitle() : "", 20);

        // Course ID TextArea
        jTextAreaCourseId = createTextArea(course != null ? String.valueOf(course.getCourseId()) : "", 16);

        // Instructor ID TextArea
        jTextAreaInstructorId = createTextArea(course != null ? String.valueOf(course.getInstructorId()) : "", 16);

        // Description TextArea
        jTextAreaDescription = createTextArea(course != null ? course.getDescription() : "", 16);
        JScrollPane scrollPaneDescription = new JScrollPane(jTextAreaDescription);
        scrollPaneDescription.setPreferredSize(new Dimension(300, 100));

        // Enroll Button
        enrollButton = new JButton();
        enrollButton.setFont(new Font("Cambria", Font.BOLD, 20));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jLabel6.setText("Lessons Of Course");

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextAreaTitle)
                                        .addComponent(jTextAreaCourseId)
                                        .addComponent(scrollPaneDescription)
                                        .addComponent(jTextAreaInstructorId)
                                        .addComponent(enrollButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(lessonsPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(jLabel7)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(jTextAreaTitle, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(jTextAreaCourseId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(scrollPaneDescription, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(jTextAreaInstructorId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(enrollButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50)
                                .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(lessonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(180)
                                .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    // Helper to create non-editable JTextAreas for info
    private JTextArea createTextArea(String text, int fontSize)
    {
        JTextArea area = new JTextArea(text);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setFont(new Font("Cambria", Font.BOLD, fontSize));
        area.setOpaque(false);
        area.setBorder(null);
        return area;
    }

    // *** UPDATED: checkboxes → labels ONLY ***
    private void addLessonCheckboxes(ArrayList<Lesson> lessons)
    {
        lessonsPanel.removeAll();

        if (lessons != null && !lessons.isEmpty())
        {
            for (Lesson lesson : lessons)
            {
                JLabel lbl = new JLabel(lesson.getTitle());
                lbl.setFont(new Font("Cambria", Font.PLAIN, 14));
                lessonsPanel.add(lbl);
            }
        }
        else
        {
            JLabel emptyLabel = new JLabel("No lessons available.");
            emptyLabel.setFont(new Font("Cambria", Font.ITALIC, 12));
            lessonsPanel.add(emptyLabel);
        }

        lessonsPanel.revalidate();
        lessonsPanel.repaint();
    }

    // Update enroll button text and behavior
    private void updateEnrollButton()
    {
        boolean isEnrolled = false;
        if (student != null && student.getEnrolledCourses() != null) {
            for (Course c : student.getEnrolledCourses()) {
                if (c.getCourseId()==(course.getCourseId())) {
                    isEnrolled = true;
                    break;
                }
            }
        }

        if (isEnrolled)
        {
            enrollButton.setText("ENROLLED");
            enrollButton.setEnabled(false);
            setLessonsEnabled(true);
        } else
        {
            enrollButton.setText("ENROLL");
            enrollButton.setEnabled(true);
            setLessonsEnabled(false);

            enrollButton.addActionListener(e -> {
                try
                {
                    student.enroll(course);
                    enrollButton.setText("ENROLLED");
                    enrollButton.setEnabled(false);
                    setLessonsEnabled(true);
                    JOptionPane.showMessageDialog(this, "You have successfully enrolled in " + course.getTitle());
                    new StudentDashboardFrame(student);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    // Helper to enable/disable labels (won’t matter but left unchanged)
    private void setLessonsEnabled(boolean enabled) {
        Component[] components = lessonsPanel.getComponents();
        for (Component c : components) {
            c.setEnabled(enabled);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new CourseWindow(course, student).setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Variables
    private JLabel jLabel6;
    private JLabel jLabel7;
}
