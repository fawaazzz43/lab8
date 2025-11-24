package lab7;
import java.util.ArrayList;

public class Quiz {
    private String title;
    private int numberOfQuestions;
    private ArrayList<Questions> questions;
    private int score;


    
    public String getTitle() {
        return title;
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

    
    public Quiz(String title,int numberOfQuestions) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Questions question) {
        questions.add(question);
    }

    public ArrayList<Questions> getQuestions() {
        return questions;
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
