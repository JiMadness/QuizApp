package controller;

import main.Main;
import model.Quiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.QuizInstance;

import java.util.Optional;


public class ContentController {
    private static Main mainApp;
    private static ContentController instance;
    @FXML
    private TableView<Quiz> quizTable;
    @FXML
    private TableColumn<Quiz, String> quizName;
    @FXML
    private TableColumn<Quiz, String> quizInstructor;
    @FXML
    private Label nameField;
    @FXML
    private Label instructorField;
    @FXML
    private Label submissionsField;
    @FXML
    private ButtonBar instructorBar;
    @FXML
    private Button quizButton;

    @FXML
    private void initialize() {
        mainApp = Main.getMainApp();
        instance = this;
        quizTable.setItems(Main.getQuizzes());
        quizName.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getQuizName()));
        quizInstructor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getInstructor()));
        quizTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> showQuizDetails(newValue));
    }

    void showQuizDetails(Quiz quiz) {
        if(quiz!=null) {
            if (!quiz.getQuizName().equals(""))
                nameField.setText(quiz.getQuizName());
            else nameField.setText("Not Specified");
            if (!quiz.getInstructor().equals(""))
                instructorField.setText(quiz.getInstructor());
            else instructorField.setText("Not Specified");
            submissionsField.setText(quiz.getSubmissions().toString());
        }
    }

    public void start(boolean isInstructor) {
        if (isInstructor) {
            instructorBar.setDisable(false);
            quizButton.setDisable(true);
        } else {
            instructorBar.setDisable(true);
            quizButton.setDisable(false);
            mainApp.getQuizViewer().showingProperty().addListener(l -> {
                ContentController.getInstance().getQuizButton().setDisable(!ContentController.getInstance().getQuizButton().isDisabled());
            });
        }
    }
    public void refresh(){
        showQuizDetails(quizTable.getSelectionModel().getSelectedItem());
    }
    public ButtonBar getInstructorBar(){
        return this.instructorBar;
    }
    public static ContentController getInstance() {
        return instance;
    }
    @FXML
    private void takeQuiz(){
        if(quizTable.getSelectionModel().getSelectedItem()!=null) {
            QuizInstance quizInstance = new QuizInstance(quizTable.getSelectionModel().getSelectedItem());
            QuizViewer.start(quizInstance);
        }
        else
            new Alert(Alert.AlertType.ERROR,"You have to choose a quiz first to take it.").show();
    }
    @FXML
    private void newPressed() {
        mainApp.getQuizCreator().show();
    }

    @FXML
    private void deletePressed() {
        Quiz quiz =quizTable.getSelectionModel().getSelectedItem();
        if(quiz==null)
            new Alert(Alert.AlertType.ERROR,"You have to select a quiz first to delete it.").show();
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Quiz");
            alert.setHeaderText("Confirmation");
            alert.setContentText("Are you sure to delete " + quiz.getQuizName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==ButtonType.OK){
                Main.getQuizzes().remove(quiz);
                new Alert(Alert.AlertType.INFORMATION,"Quiz deleted successfully.").show();
            }
        }
    }
    public Button getQuizButton(){return quizButton;}
}
