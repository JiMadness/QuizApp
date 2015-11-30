package model;

import main.Adapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;


public class Quiz {
    private String quizName;
    private String instructor;
    private ArrayList<Question> questions;
    private int submissions;

    public Quiz() {
        setQuestions(new ArrayList<>());
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    @XmlJavaTypeAdapter(Adapter.class)
    public Integer getSubmissions() {
        return submissions;
    }
    public void setSubmissions(Integer submissions) {
        this.submissions = submissions;
    }
}