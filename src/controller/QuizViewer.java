package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.Main;
import model.QuizInstance;

public class QuizViewer {
    private static Main mainApp;
    private static QuizViewer instance;

    @FXML
    private Label completed;
    @FXML
    private Label remaining;
    @FXML
    private Label lastAnswer;

    public static void start(QuizInstance quizInstance){
        mainApp.getQuizViewer().setTitle("Quiz - "+quizInstance.getQuizName());
        instance.remaining.setText(String.valueOf(quizInstance.getQuestions().size()));
        mainApp.getQuizViewer().show();
        QuestionViewer.getInstance().setQuizInstance(quizInstance);
        QuestionViewer.getInstance().start();
    }
    public static QuizViewer getInstance(){return instance;}
    public Label getLastAnswer(){return lastAnswer;}
    public Label getCompleted(){return completed;}
    public Label getRemaining(){return remaining;}
    @FXML
    private void initialize(){
        instance = this;
        mainApp = Main.getMainApp();
    }
}
