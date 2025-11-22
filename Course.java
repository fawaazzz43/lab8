package lab7;
import java.util.ArrayList;

public class Course {
    //private static final String fileName = "D:\\programming\\java\\lab7\\lab7_IJ\\courses.json";
    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private String status ;
    private ArrayList<Lesson> lessons = new ArrayList<>();;
    private ArrayList<Student> studentsIncourse = new ArrayList<>();

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructorId(String instructorId) { // Changed argument type
        this.instructorId = instructorId;
    }


    public Course(String courseId, String title, String description, String instructorId , String status)
    {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.status = status.toUpperCase();
    }

    public Course()
    {
        this.studentsIncourse = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }
    public ArrayList<Student> getStudentsIncourse() {
        return studentsIncourse;
    }
    public String getCourseId() {
        return courseId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getInstructorId() {return instructorId;}
    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}
    public void AddStudent(Student student)
    {
        studentsIncourse.add(student);
    }


}





