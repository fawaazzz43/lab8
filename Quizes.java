/*package lab7;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Quizes {
    private static ArrayList<Quiz> quizs = new ArrayList<>();
    private final static String FileName="courses.json";

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
                                    quizObj.optString("quiztitle", ""), quizObj.optString("numberOfQuestions", "")
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
                                            qObj.optString("questionText", ""),
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

// public void saveQuizzesToJson() throws IOException {
//         JSONArray quizzesArray = new JSONArray();

//         // 1. تسلسل الاختبارات (Serialize Quizzes)
//         for (Quiz quiz : quizs) {
//             JSONObject quizJson = new JSONObject();
//             quizJson.put("quizID", quiz.getQuizID());
//             quizJson.put("quiztitle", quiz.getQuizTitle());
//             // نستخدم دالة getNumberOfQuestions التي تحسب العدد الفعلي للأسئلة
//             quizJson.put("numberOfQuestions", String.valueOf(quiz.getnumberOfQuestions())); 

//             // 2. تسلسل الأسئلة (Serialize Questions)
//             JSONArray questionsArray = new JSONArray();
//             for (Questions question : quiz.getQuestions()) {
//                 JSONObject questionJson = new JSONObject();
//                 questionJson.put("questionText", question.getQuestionText());
//                 questionJson.put("correctAnswer", question.getCorrectAnswer());
                
//                 // تسلسل الخيارات (Options)
//                 JSONArray optionsArray = new JSONArray();
//                 // التأكد من أن المصفوفة غير فارغة قبل التكرار
//                 if (question.getOptions() != null) {
//                     for (String option : question.getOptions()) {
//                         optionsArray.put(option);
//                     }
//                 }
//                 questionJson.put("options", optionsArray);

//                 questionsArray.put(questionJson);
//             }
//             quizJson.put("questions", questionsArray); // إضافة الأسئلة للاختبار

//             quizzesArray.put(quizJson); // إضافة الاختبار إلى المصفوفة الرئيسية
//         }

//         // كتابة البيانات إلى الملف
//         try (FileWriter file = new FileWriter(FileName)) {
//             // استخدام toString(4) لتنسيق JSON وجعله مقروءاً
//             file.write(quizzesArray.toString(4));
//             System.out.println("Quizzes data successfully saved to: " + FileName);
//         } catch (Exception e) {
//             System.err.println("Error saving quizzes data: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }





    
}*/
