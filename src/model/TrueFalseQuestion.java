package model;


public class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(){
        questionType = 3;
    }
    public boolean checkAnswer(String answer) {
        return getCorrectAnswer().equals(answer);
    }
}
