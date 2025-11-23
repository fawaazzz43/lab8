public class Questions {
   
    private String questionText;
    private String correctAnswer;
    private String[] options;
    public int size;
    
    public Questions( String questionText, String correctAnswer, String[] options) {
       
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = options; 
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
