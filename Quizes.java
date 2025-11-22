import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Quizes {
    private static ArrayList<Quiz> quizs = new ArrayList<>();
    private final static String FileName="src/courses.json";

    public List<Quiz> loadAllQuizzes() throws IOException {
   

    String jsonString = new String(Files.readAllBytes(Paths.get(FileName)));

    if (jsonString.trim().isEmpty() || jsonString.trim().equals("[]")) {
        return quizs;
    }

    try {
        JSONArray coursesArray = new JSONArray(jsonString);

        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseObj = coursesArray.getJSONObject(i);

            if (courseObj.has("lessons") && !courseObj.isNull("lessons")) {
                JSONArray lessonsArray = courseObj.getJSONArray("lessons");

                for (int j = 0; j < lessonsArray.length(); j++) {
                    JSONObject lessonObj = lessonsArray.getJSONObject(j);

                    if (lessonObj.has("quizes") && !lessonObj.isNull("quizes")) {
                        JSONArray quizzesArray = lessonObj.getJSONArray("quizes");

                        for (int q = 0; q < quizzesArray.length(); q++) {
                            JSONObject quizObj = quizzesArray.getJSONObject(q);

                            Quiz quiz = new Quiz(
                                    quizObj.optString("quizID", ""),
                                    quizObj.optString("quiztitle", ""), q
                            );

                            // قراءة الأسئلة
                            if (quizObj.has("questions") && !quizObj.isNull("questions")) {
                                JSONArray questionsArray = quizObj.getJSONArray("questions");

                                for (int k = 0; k < questionsArray.length(); k++) {
                                    JSONObject qObj = questionsArray.getJSONObject(k);

                                    // تحويل options من JSONArray لمصفوفة String[]
                                    String[] options = new String[0];
                                    if (qObj.has("options") && !qObj.isNull("options")) {
                                        JSONArray optionsArray = qObj.getJSONArray("options");
                                        options = new String[optionsArray.length()];
                                        for (int o = 0; o < optionsArray.length(); o++) {
                                            options[o] = optionsArray.getString(o);
                                        }
                                    }

                                    // تحويل index correct للإجابة الصحيحة كنص
                                    String correctAnswer = "";
                                    if (qObj.has("correct") && !qObj.isNull("correct")) {
                                        int correctIndex = qObj.getInt("correct");
                                        if (correctIndex >= 0 && correctIndex < options.length) {
                                            correctAnswer = options[correctIndex];
                                        }
                                    }

                                    Questions question = new Questions(
                                            qObj.optString("question", ""),
                                            correctAnswer,
                                            options
                                    );

                                    quiz.getQuestions().add(question);
                                }
                            }

                            quizs.add(quiz);
                        }
                    }
                }
            }
        }

    } catch (Exception e) {
        System.out.println("Error loading quizzes: " + e.getMessage());
        e.printStackTrace();
    }

    return quizs;
}



    
}
