package model;


public class QuizInstance extends Quiz {
    private int score = 0;
    private int fullMark;
    private Quiz quiz;
    public QuizInstance(Quiz quiz) {
        this.quiz=quiz;
        this.setQuestions(quiz.getQuestions());
        this.setQuizName(quiz.getQuizName());
        this.setInstructor(quiz.getInstructor());
        this.initFullMark();
    }
    public Quiz getQuiz(){return quiz;}
    public int getScore() {
        return score;
    }
    public int getFullMark(){return fullMark;}
    public void setScore(int score) {
        this.score = score;
    }
    private void initFullMark(){
        fullMark =0;
        for(Question i : this.getQuestions())
            fullMark+=i.getMark();
    }

}
