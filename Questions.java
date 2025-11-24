package lab7;
public class Questions {
   
    private String questionText;
    private String correctAnswer;
    private String[] options;
    public int size;
    private int QuestionsId;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuestionsId() {
        return QuestionsId;
    }

    public void setQuestionsId(int QuestionsId) {
        this.QuestionsId = QuestionsId;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
    

    public Questions(String questionText, String correctAnswer, String[] options, int QuestionsId) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = options;
        this.QuestionsId = QuestionsId;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public String[] getOptions() {
        return options;
    }
    
}
