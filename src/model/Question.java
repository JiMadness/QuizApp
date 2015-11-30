package model;


import main.Adapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Question {
    private String questionStatement;
    private int mark;
    protected int questionType;
    private String correctAnswer;
    private String[] answersList;
    public String[] getAnswersList(){
        return answersList;
    }
    public void setAnswersList(String[] answersList){this.answersList=answersList;}

    public String getQuestionStatement() {
        return questionStatement;
    }
    public boolean checkAnswer(String answer) {
        if(questionType==1)
            return answer.contains(getCorrectAnswer());
        return getCorrectAnswer().equals(answer);
    }
    public void setQuestionStatement(String questionStatement) {
        this.questionStatement = questionStatement;
    }
    public String getCorrectAnswer(){
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer = correctAnswer;
    }
    public void setMark(Integer mark){this.mark = mark;}
    public void setQuestionType(Integer questionType){this.questionType=questionType;}
    @XmlJavaTypeAdapter(Adapter.class)
    public Integer getQuestionType(){return questionType;}
    @XmlJavaTypeAdapter(Adapter.class)
    public Integer getMark(){return mark;}
}
