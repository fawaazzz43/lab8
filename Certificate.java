package lab7;
import java.util.UUID;

public class Certificate {
    private String certificateID;
    private String studentID;
    private String courseID;
    private String courseTitle;
    private String issueDate;

    public Certificate(String studentID, String courseID, String courseTitle, String issueDate) {
        this.certificateID = UUID.randomUUID().toString();
        this.studentID = studentID;
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.issueDate = issueDate;
    }

    public String getCertificateID() { return certificateID; }
    public String getStudentID() { return studentID; }
    public String getCourseID() { return courseID; }
    public String getCourseTitle() { return courseTitle; }
    public String getIssueDate() { return issueDate; }
}
