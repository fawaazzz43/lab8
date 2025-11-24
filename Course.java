package lab7;
import java.util.ArrayList;

public class Course {
    //private static final String fileName = "D:\\programming\\java\\lab7\\lab7_IJ\\courses.json";
    private int courseId;
    private String title;
    private String description;
    private int instructorId;
    private String status ;
    private int numberOflessons;
    private double progress;
    private ArrayList<Lesson> lessoncompletion;

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public ArrayList<Lesson> getLessoncompletion() {
        return lessoncompletion;
    }

    public void setLessoncompletion(ArrayList<Lesson> lessoncompletion) {
        this.lessoncompletion = lessoncompletion;
    }

    public int getNumberOflessons() {
        return numberOflessons;
    }

    public void setNumberOflessons(int numberOflessons) {
        this.numberOflessons = numberOflessons;
    }
    private ArrayList<Lesson> lessons = new ArrayList<>();
    private ArrayList<Student> studentsIncourse = new ArrayList<>();

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructorId(int instructorId) { // Changed argument type
        this.instructorId = instructorId;
    }


    public Course(int courseId, String title, String description, int instructorId , String status)
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
    public int getCourseId() {
        return courseId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getInstructorId() {return instructorId;}
    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}
    public void AddStudent(Student student)
    {
        studentsIncourse.add(student);
    }


}