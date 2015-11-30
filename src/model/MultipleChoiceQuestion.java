package model;

public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(){
        questionType=0;
    }
    public boolean checkAnswer(String answer) {
        return getCorrectAnswer().equals(answer);
    }
   }
