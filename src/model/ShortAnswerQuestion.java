package model;


public class ShortAnswerQuestion extends Question {
    public ShortAnswerQuestion(){
        questionType = 1;
    }
    public boolean checkAnswer(String answer) {
        return answer.contains(getCorrectAnswer());
    }
}
