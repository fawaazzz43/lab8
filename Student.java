package lab7;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Student {
    int userId;
    String role;
    String username;
    String email;
    String passwordHash;
    ArrayList<Course> enrolledCourses;

    public Student(int userId, String role, String username, String email, String passwordHash) {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;

        this.enrolledCourses = new ArrayList<>();
    }

    // --- FIXED SEARCH METHOD ---
    public ArrayList<Course> search(String word) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("courses.json")));
            JSONArray array = new JSONArray(content);

            ArrayList<Course> coursesOfSearch = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                // Check if title matches the search word (case-insensitive)
                if (object.getString("title").toLowerCase().contains(word.toLowerCase()) && object.getString("status").toUpperCase().equals("APPROVED")) {

                    // 1. Create the Course object
                    Course course = new Course(
                            object.getInt("courseId"),
                            object.getString("title"),
                            object.getString("description"),
                            object.getInt("instructorId"),
                            object.getString("status")
                    );

                    // 2. CRITICAL FIX: Manually parse and add lessons to the course
                    if (object.has("lessons"))
                    {
                        JSONArray lessonsArray = object.getJSONArray("lessons");
                        for (int j = 0; j < lessonsArray.length(); j++)
                        {
                            JSONObject quizObj = lessonsArray.getJSONObject(j).getJSONObject("quiz");

                            JSONObject lessonObj = lessonsArray.getJSONObject(j);
                            Lesson lesson = new Lesson(
                                    lessonObj.getInt("lessonId"),
                                    lessonObj.getString("title"),
                                    lessonObj.getString("content")
                                    , new Quiz ( quizObj.getString("title") , quizObj.getInt("numberOfQuestions")) );

                            course.getLessons().add(lesson);
                        }
                    }

                    // 3. Add the fully populated course to the list
                    coursesOfSearch.add(course);
                }
            }

            if (!coursesOfSearch.isEmpty()) {
                //System.out.println("vv");
                return coursesOfSearch;
            } else {
                //System.out.println("bb");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error in search: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public void enroll(Course course) throws IOException {
        // Add to local list
        enrolledCourses.add(course);

        // Update the Course file (adds student to the course's student list)
        Courses courses = new Courses("courses.json");
        courses.load();
        courses.UpdateStudentOfCourse(course, this);
        courses.SaveToJsonCourses();

        // Update the User file (adds course to the student's enrolled list)
        updateUserInUsersFile();
    }

    private void updateUserInUsersFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("users.json")));
            JSONObject jsonObj = new JSONObject(content);
            JSONArray students = jsonObj.getJSONArray("students");


            // Find and update the current student
            for (int i = 0; i < students.length(); i++) {
                JSONObject studentObj = students.getJSONObject(i);
                if (studentObj.getInt("userId") == this.userId) {
                    

                    // Convert enrolledCourses to JSON array
                    JSONArray enrolledCoursesArray = new JSONArray();
                    for (Course course : this.enrolledCourses) {
                        JSONObject courseObj = new JSONObject();
                        courseObj.put("courseId", course.getCourseId());
                        courseObj.put("title", course.getTitle());

                        // Important: We should also save lessons here if we want them
                        // to persist without needing to re-fetch from courses.json every time
                        JSONArray lessonsArray = new JSONArray();
                        for(Lesson l : course.getLessons()){
                            JSONObject lObj = new JSONObject();
                            lObj.put("lessonId", l.getLessonId());
                            lObj.put("title", l.getTitle());
                            lObj.put("content", l.getContent());
                            lessonsArray.put(lObj);
                        }
                        courseObj.put("lessons", lessonsArray);

                        enrolledCoursesArray.put(courseObj);
                    }
                    studentObj.put("enrolledCourses", enrolledCoursesArray);
                    break;
                }
            }

            // Write back to file
            Files.write(Paths.get("users.json"), jsonObj.toString(4).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}