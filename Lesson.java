package lab7;
import java.util.ArrayList;

// The primary class definition
public class Lesson {

    private String lessonId;
    private String title;
    private String content;
    private ArrayList<String> resources;

    public Lesson(String lessonId, String title, String content) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>();
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
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