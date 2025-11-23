import java.util.ArrayList;

public class Quiz {
    String quizID;
    String title;
    String numberOfQuestions;
    ArrayList<Questions> questions;

    
    public Quiz(String title, String quizID, String numberOfQuestions) {
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
    public String getnumberOfQuestions() {
        return this.numberOfQuestions;
    }
    public boolean isPassed(int score)
    {
        boolean passed=true;
        boolean faild=false;
        if(score>=Integer.parseInt(this.numberOfQuestions)/2)
        {
            return passed;
        }
        else 
        {
            return faild;
        }
    }


}
