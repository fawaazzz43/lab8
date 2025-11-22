import java.util.ArrayList;

public class Quiz {
    String quizID;
    String title;
    int numberOfQuestions;
    ArrayList<Questions> questions;

    // كونستركتور أساسي
  

    // كونستركتور مع عدد الأسئلة
    public Quiz(String title, String quizID, int numberOfQuestions) {
        this.quizID = quizID;
        this.title = title;
        this.numberOfQuestions = numberOfQuestions;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Questions question) {
        questions.add(question);
    }

    public ArrayList<Questions> getQuestions() {
        return questions;
    }

    public String getQuizID() {
        return this.quizID;
    }

    public String getQuizTitle() {
        return this.title;
    }
}
