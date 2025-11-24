package lab7;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Courses {

    private static ArrayList<Course> courses = new ArrayList<>();
    private String FileName;

    public Courses(String FileName) {
        this.FileName = FileName;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    // ======================================================
    //                     LOAD COURSES
    // ======================================================
    public void load() throws IOException {

        courses = new ArrayList<>();

        String jsonString = new String(Files.readAllBytes(Paths.get(FileName)));

        if (jsonString.trim().isEmpty() || jsonString.trim().equals("[]"))
            return;

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

                // ======================================================
                //                 LOAD LESSONS + QUIZ
                // ======================================================
                if (courseObj.has("lessons")) {

                    JSONArray lessonsArray = courseObj.getJSONArray("lessons");

                    for (int j = 0; j < lessonsArray.length(); j++) {

                        JSONObject lessonObj = lessonsArray.getJSONObject(j);

                        Quiz quiz = null;

                        if (lessonObj.has("quiz")) {

                            JSONObject quizObj = lessonObj.getJSONObject("quiz");

                            String quizTitle = quizObj.getString("title");
                            int numberOfQuestions = quizObj.getInt("numberOfQuestions");

                            ArrayList<Questions> questionsList = new ArrayList<>();
                            if (quizObj.has("questions")) {

                                JSONArray questionsArray = quizObj.getJSONArray("questions");

                                for (int q = 0; q < questionsArray.length(); q++) {

                                    JSONObject qObj = questionsArray.getJSONObject(q);

                                    Questions question = new Questions(
                                            qObj.getInt("questionsId"),
                                            qObj.getString("questionText"),
                                            qObj.getString("correctAnswer"),
                                            qObj.getJSONArray("options")
                                    );
                                    questionsList.add(question);
                                }
                            }

                            quiz = new Quiz(quizTitle, numberOfQuestions, questionsList);
                        }

                        Lesson lesson = new Lesson(
                                lessonObj.getInt("lessonId"),
                                lessonObj.getString("title"),
                                lessonObj.getString("content"),
                                quiz
                        );

                        course.getLessons().add(lesson);
                    }
                }

                // ======================================================
                //               LOAD STUDENTS IN COURSE
                // ======================================================
                if (courseObj.has("studentsIncourse")) {

                    JSONArray arr = courseObj.getJSONArray("studentsIncourse");

                    for (int s = 0; s < arr.length(); s++) {

                        JSONObject st = arr.getJSONObject(s);

                        Student student = new Student(
                                st.getInt("userId"),
                                st.getString("role"),
                                st.getString("username"),
                                st.getString("email"),
                                st.getString("passwordHash")
                        );

                        course.getStudentsIncourse().add(student);
                    }
                }

                courses.add(course);
            }

        } catch (Exception e) {
            System.out.println("Error parsing courses JSON: " + e.getMessage());
        }
    }

    // ======================================================
    //                      ADD / DELETE
    // ======================================================
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
    }


    // ======================================================
    //                    SAVE COURSES TO JSON
    // ======================================================
    public void SaveToJsonCourses() throws IOException {

        JSONArray arr = new JSONArray();

        for (Course c : courses) {

            JSONObject courseJson = new JSONObject();

            courseJson.put("courseId", c.getCourseId());
            courseJson.put("title", c.getTitle());
            courseJson.put("description", c.getDescription());
            courseJson.put("instructorId", c.getInstructorId());
            courseJson.put("status", c.getStatus());

            // -----------------------------------
            // SAVE LESSONS + QUIZ
            // -----------------------------------
            JSONArray lessonsArray = new JSONArray();

            for (Lesson lesson : c.getLessons()) {

                JSONObject lessonJson = new JSONObject();

                lessonJson.put("lessonId", lesson.getLessonId());
                lessonJson.put("title", lesson.getTitle());
                lessonJson.put("content", lesson.getContent());
                lessonJson.put("resources", new JSONArray());

                if (lesson.getQuiz() != null) {

                    JSONObject quizJson = new JSONObject();
                    quizJson.put("title", lesson.getQuiz().getTitle());
                    quizJson.put("numberOfQuestions", lesson.getQuiz().getNumberOfQuestions());

                    JSONArray questionsJsonArr = new JSONArray();

                    for (Questions q : lesson.getQuiz().getQuestions()) {
                        JSONObject qJson = new JSONObject();
                        qJson.put("questionsId", q.getQuestionsId());
                        qJson.put("questionText", q.getQuestionText());
                        qJson.put("correctAnswer", q.getCorrectAnswer());
                        qJson.put("options", q.getOptions());
                        questionsJsonArr.put(qJson);
                    }

                    quizJson.put("questions", questionsJsonArr);
                    lessonJson.put("quiz", quizJson);
                }

                lessonsArray.put(lessonJson);
            }

            courseJson.put("lessons", lessonsArray);

            // -----------------------------------
            // SAVE STUDENTS
            // -----------------------------------
            JSONArray studentsArray = new JSONArray();

            for (Student student : c.getStudentsIncourse()) {

                JSONObject st = new JSONObject();

                st.put("userId", student.getUserId());
                st.put("username", student.getUsername());
                st.put("email", student.getEmail());
                st.put("passwordHash", student.getPasswordHash());
                st.put("role", student.getRole());

                studentsArray.put(st);
            }

            courseJson.put("studentsIncourse", studentsArray);

            arr.put(courseJson);
        }

        FileWriter file = new FileWriter(FileName);
        file.write(arr.toString(4));
        file.close();
    }


    // ======================================================
    //                      UPDATE STUDENT
    // ======================================================
    public void UpdateStudentOfCourse(Course course, Student student) throws IOException {

        for (Course c : courses) {
            if (c.getCourseId() == course.getCourseId()) {
                c.AddStudent(student);
                break;
            }
        }

        SaveToJsonCourses();
    }
}
