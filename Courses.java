package lab7;

import lab7.Course;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Courses {
    private static ArrayList<Course> courses = new ArrayList<>();
    private String FileName;

    public Courses(String FileName)
    {
        this.FileName = FileName;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void load() throws IOException {
        courses = new ArrayList<>();

        String jsonString = new String(Files.readAllBytes(Paths.get(FileName)));

        if (jsonString.trim().isEmpty() || jsonString.trim().equals("[]")) {
            return;
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject courseObj = jsonArray.getJSONObject(i);

                Course course = new Course(
                        courseObj.getInt("courseId"),
                        courseObj.getString("title"),
                        courseObj.getString("description"),
                        courseObj.getInt("instructorId"),
                        courseObj.getString("status")
                );

                if (courseObj.has("lessons"))
                {
                    JSONArray lessonsArray = courseObj.getJSONArray("lessons");
                    for (int j = 0; j < lessonsArray.length(); j++)
                    {
                        JSONObject quizObj = lessonsArray.getJSONObject(j).getJSONObject("quiz");

                        JSONObject lessonObj = lessonsArray.getJSONObject(j);
                        Lesson lesson = new Lesson(
                                lessonObj.getInt("lessonId"),
                                lessonObj.getString("title"),
                                lessonObj.getString("content")
                                , new Quiz ( quizObj.getString("title"), quizObj.getInt("numberOfQuestions")) );

                        course.getLessons().add(lesson);
                    }
                }

                courses.add(course);
            }
        } catch (Exception e) {
            System.out.println("Error parsing courses JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
    }

    // In Courses.java

    // REPLACE the existing SaveToJsonCourses method with this one.
    // In Courses.java

    // REPLACE the existing SaveToJsonCourses method with this one.
    public void SaveToJsonCourses() throws IOException
    {
        JSONArray arr = new JSONArray();

        for (Course c : courses)
        {
            // Manually build the JSON object to ensure all data is included
            JSONObject courseJson = new JSONObject();
            courseJson.put("courseId", c.getCourseId());
            courseJson.put("title", c.getTitle());
            courseJson.put("description", c.getDescription());
            courseJson.put("instructorId", c.getInstructorId());
            courseJson.put("status", c.getStatus());

            // Serialize lessons
            JSONArray lessonsArray = new JSONArray();
            for (Lesson lesson : c.getLessons()) {
                JSONObject lessonJson = new JSONObject();
                lessonJson.put("lessonId", lesson.getLessonId());
                lessonJson.put("title", lesson.getTitle());
                lessonJson.put("content", lesson.getContent());
                lessonJson.put("resources", new JSONArray()); // Empty resources array
                lessonsArray.put(lessonJson);
            }
            courseJson.put("lessons", lessonsArray);

            // Serialize students in course - THIS IS WHAT WAS MISSING
            JSONArray studentsArray = new JSONArray();
            for (Student student : c.getStudentsIncourse()) {
                JSONObject studentJson = new JSONObject();
                studentJson.put("userId", student.getUserId());
                studentJson.put("username", student.getUsername());
                studentJson.put("email", student.getEmail());
                studentJson.put("passwordHash", student.getPasswordHash());
                studentJson.put("role", student.getRole());
                studentsArray.put(studentJson);
            }
            courseJson.put("studentsIncourse", studentsArray);

            arr.put(courseJson);
        }

        try (FileWriter file = new FileWriter(FileName))
        {
            file.write(arr.toString(4));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void UpdateStudentOfCourse(Course course, Student student) throws IOException
    {
        for (int i = 0; i < courses.size(); i++)
        {
            if (courses.get(i).getCourseId()==(course.getCourseId()))
            {
                courses.get(i).AddStudent(student);
                break;
            }
        }
    }
}