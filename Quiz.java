package lab7;
import java.util.ArrayList;

public class Quiz {
    private int quizID;
    private String title;
    private int numberOfQuestions;
    private ArrayList<Questions> questions;
    private int score;
    public String getTitle() {
        return title;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    
    public Quiz(String title, int quizID, int numberOfQuestions) {
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

    public int getQuizID() {
        return this.quizID;
    }

    public String getQuizTitle() {
        return this.title;
    }
    public int getnumberOfQuestions() {
        return this.numberOfQuestions;
    }
    public boolean isPassed(int score)
    {
        boolean passed=true;
        boolean faild=false;
        if(score>=(this.numberOfQuestions)/2)
        {
            return passed;
        }
        else 
        {
            return faild;
        }
    }

    public int getScore() {
        return score;
    }


}
