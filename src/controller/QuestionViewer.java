package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.DataLoader;
import main.Main;
import model.*;


public class QuestionViewer {
    private static Main mainApp;
    private static QuestionViewer instance;
    private Question question;
    private int questionIterator=0;
    private ToggleGroup choices;
    private QuizInstance quizInstance;
    @FXML
    private Label questionStatement;
    @FXML
    private TextField fillInBlank;
    @FXML
    private TextArea shortAnswer;
    @FXML
    private GridPane multipleChoice;
    @FXML
    private RadioButton choice1;
    @FXML
    private RadioButton choice2;
    @FXML
    private RadioButton choice3;
    @FXML
    private RadioButton choice4;
    @FXML
    private ChoiceBox<String> trueFalse;
    public void start(){
        if(questionIterator==quizInstance.getQuestions().size()){
            quizInstance.getQuiz().setSubmissions(quizInstance.getQuiz().getSubmissions() + 1);
            DataLoader.save(mainApp.dataBase);
            ContentController.getInstance().refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Quiz Completed");
            alert.setHeaderText("Quiz completed successfully.");
            alert.setContentText("Your score is " + quizInstance.getScore() + " out of " + quizInstance.getFullMark());
            alert.showAndWait();
            questionIterator=0;
            mainApp.getQuizViewer().close();
            return;
        }
        clearPressed();
        question = quizInstance.getQuestions().get(questionIterator++);
        questionStatement.setText(question.getQuestionStatement());
        switch (question.getQuestionType()){
            case 0:
                choice1.setText(question.getAnswersList()[0]);
                choice2.setText(question.getAnswersList()[1]);
                choice3.setText(question.getAnswersList()[2]);
                choice4.setText(question.getAnswersList()[3]);
                multipleChoice.setDisable(false);
                shortAnswer.setDisable(true);
                fillInBlank.setDisable(true);
                trueFalse.setDisable(true);
                break;
            case 1:
                shortAnswer.setDisable(false);
                multipleChoice.setDisable(true);
                fillInBlank.setDisable(true);
                trueFalse.setDisable(true);
                break;
            case 2:
                fillInBlank.setDisable(false);
                shortAnswer.setDisable(true);
                multipleChoice.setDisable(true);
                trueFalse.setDisable(true);
                break;
            case 3:
                trueFalse.setDisable(false);
                shortAnswer.setDisable(true);
                fillInBlank.setDisable(true);
                multipleChoice.setDisable(true);
                break;
        }
    }
    @FXML
    private void initialize(){
        mainApp=Main.getMainApp();
        instance = this;
        trueFalse.getItems().addAll("True", "False");
        choices = new ToggleGroup();
        choice1.setToggleGroup(choices);
        choice2.setToggleGroup(choices);
        choice3.setToggleGroup(choices);
        choice4.setToggleGroup(choices);
    }
    @FXML
    private void submitPressed(){
        boolean checker=false;
        switch (question.getQuestionType()){
            case 0:
                checker= question.checkAnswer(((RadioButton)choices.getSelectedToggle()).getText());
                break;
            case 1:
                checker= question.checkAnswer(shortAnswer.getText());
                break;
            case 2:
                checker= question.checkAnswer(fillInBlank.getText());
                break;
            case 3:
                checker= question.checkAnswer(trueFalse.getValue());
                break;
        }
        if(checker){
            quizInstance.setScore(quizInstance.getScore()+question.getMark());
            QuizViewer.getInstance().getLastAnswer().setText("Correct.");
        }
        else QuizViewer.getInstance().getLastAnswer().setText("Wrong.");
        QuizViewer.getInstance().getCompleted().setText(String.valueOf(Integer.valueOf(QuizViewer.getInstance().getCompleted().getText()) + 1));
        QuizViewer.getInstance().getRemaining().setText(String.valueOf(Integer.valueOf(QuizViewer.getInstance().getRemaining().getText()) - 1));
        clearPressed();
        start();
    }
    @FXML
    public void clearPressed(){
        shortAnswer.clear();
        fillInBlank.clear();
        trueFalse.getSelectionModel().clearSelection();
        choices.selectToggle(null);
    }
    public void setQuestionIterator(int questionIterator){this.questionIterator=questionIterator;}
    public void setQuizInstance(QuizInstance quizInstance){this.quizInstance=quizInstance;}
    public static QuestionViewer getInstance(){return instance;}
}
