package model;


public class FillInBlankQuestion extends Question {
    public FillInBlankQuestion(){
        questionType=2;
    }
    public boolean checkAnswer(String answer) {
        return this.getCorrectAnswer().compareToIgnoreCase(answer) == 0;
    }

}
