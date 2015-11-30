package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import model.Quiz;


public class QuizCreator {
    private Main mainApp;
    private static QuizCreator instance;
    private Quiz quiz;
    @FXML
    private TextField nameField;
    @FXML
    private TextField instructorField;
    @FXML
    private Label createdQuestions;
    @FXML
    private Button doneButton;

    @FXML
    private void initialize(){
        instance=this;
        quiz = new Quiz();
        mainApp = Main.getMainApp();
        createdQuestions.textProperty().addListener(l->{if(createdQuestions.getText().equals("1"))doneButton.setDisable(false);});
        mainApp.getQuizCreator().showingProperty().addListener(l->{
            ContentController.getInstance().getInstructorBar().setDisable(!ContentController.getInstance().getInstructorBar().isDisabled());
        });
    }
    @FXML
    private void donePressed(){
        quiz.setQuizName(nameField.getText());
        quiz.setInstructor(instructorField.getText());
        Main.getQuizzes().add(quiz);
        clearFields();
        mainApp.getQuizCreator().close();
    }
    @FXML
    private void cancelPressed(){
        clearFields();
        mainApp.getQuizCreator().close();
    }
    @FXML
    private void newQuestionPressed(){
        mainApp.getQuestionCreator().show();
    }
    public Quiz getQuiz() {
        return quiz;
    }
    public static QuizCreator getInstance() {
        return instance;
    }
    public Label getCreatedQuestionsLabel(){return  createdQuestions;}
    public void clearFields(){
        nameField.clear();
        instructorField.clear();
        createdQuestions.setText("0");
        doneButton.setDisable(true);
        quiz = new Quiz();
    }
}
