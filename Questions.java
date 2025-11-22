public class Questions {
   
    private String questionText;
    private String correctAnswer;
    private String[] options;
    
    public Questions( String questionText, String correctAnswer, String[] options) {
       
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = options; 
    }
    public void addOption(String option) {
        String[] newOptions = new String[options.length + 1];
        System.arraycopy(options, 0, newOptions, 0, options.length);
        newOptions[options.length] = option;
        options = newOptions;
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
