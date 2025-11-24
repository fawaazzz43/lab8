package lab7;
import java.util.ArrayList;

// The primary class definition
public class Lesson {

    private int lessonId;
    private String title;
    private String content;
    private ArrayList<String> resources;
    private Quiz quiz;

    public Lesson(int lessonId, String title, String content,Quiz quiz) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>();
        this.quiz=quiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

   
    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void addResource(String r) {
        resources.add(r);
    }
}