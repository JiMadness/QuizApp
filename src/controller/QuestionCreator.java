package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import main.Main;
import model.FillInBlankQuestion;
import model.MultipleChoiceQuestion;
import model.ShortAnswerQuestion;
import model.TrueFalseQuestion;

public class QuestionCreator {
    private Main mainApp;
    private int selectedIndex;
    @FXML
    private TextField questionStatement;
    @FXML
    private ChoiceBox<String> questionType;
    @FXML
    private TextField choice1;
    @FXML
    private TextField choice2;
    @FXML
    private TextField choice3;
    @FXML
    private TextField choice4;
    @FXML
    private ChoiceBox<String> correctChoice;
    @FXML
    private ChoiceBox<String> trueFalse;
    @FXML
    private TextField correctAnswer;
    @FXML
    private Button create;
    @FXML
    private Button cancel;
    @FXML
    private GridPane multipleChoice;
    @FXML
    private GridPane trueOrFalse;
    @FXML
    private GridPane shortAnswer;
    @FXML
    private Label answerLabel;
    @FXML
    private Spinner<Integer> questionScore;
    @FXML
    private void initialize() {
        mainApp = Main.getMainApp();
        initChoiceBoxes();
    }

    private void initChoiceBoxes() {
        ObservableList<String> questionTypes = FXCollections.observableArrayList();
        questionTypes.addAll("True or False", "Multiple Choice", "Short Answer", "Fill in Blank");
        questionType.setItems(questionTypes);
        ObservableList<String> correctChoices = FXCollections.observableArrayList();
        correctChoices.addAll("Choice 1", "Choice 2", "Choice 3", "Choice 4");
        correctChoice.setItems(correctChoices);
        ObservableList<String> trueFalseValues = FXCollections.observableArrayList();
        trueFalseValues.addAll("True", "False");
        trueFalse.setItems(trueFalseValues);
        questionType.getSelectionModel().selectedItemProperty().addListener(l -> enableQuestionType(questionType.getSelectionModel().getSelectedIndex()));
        questionScore.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));
    }

    private void enableQuestionType(int index) {
        selectedIndex = index;
        switch (index) {
            case 0:
                trueOrFalse.setDisable(false);
                multipleChoice.setDisable(true);
                shortAnswer.setDisable(true);
                break;
            case 1:
                trueOrFalse.setDisable(true);
                multipleChoice.setDisable(false);
                shortAnswer.setDisable(true);
                break;
            case 2:
                trueOrFalse.setDisable(true);
                multipleChoice.setDisable(true);
                shortAnswer.setDisable(false);
                answerLabel.setText("Keyword:");
                break;
            case 3:
                trueOrFalse.setDisable(true);
                multipleChoice.setDisable(true);
                shortAnswer.setDisable(false);
                answerLabel.setText("Correct Answer:");
                break;
            default:
                trueOrFalse.setDisable(true);
                multipleChoice.setDisable(true);
                shortAnswer.setDisable(true);
                answerLabel.setText("Correct Answer:");
                break;
        }
        create.setDisable(false);
    }
    @FXML
    private void cancelPressed(){
        clearFields();
        mainApp.getQuestionCreator().close();
    }
    @FXML
    private void createPressed(){
        switch (selectedIndex){
            case 0:
                TrueFalseQuestion tfq = new TrueFalseQuestion();
                tfq.setQuestionStatement(questionStatement.getText());
                tfq.setCorrectAnswer(trueFalse.getSelectionModel().getSelectedItem());
                tfq.setMark(questionScore.getValue());
                QuizCreator.getInstance().getQuiz().getQuestions().add(tfq);
                break;
            case 1:
                String[] answersList = new String[4];
                answersList[0]=choice1.getText();
                answersList[1]=choice2.getText();
                answersList[2]=choice3.getText();
                answersList[3]=choice4.getText();
                MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
                mcq.setQuestionStatement(questionStatement.getText());
                mcq.setAnswersList(answersList);
                mcq.setCorrectAnswer(answersList[correctChoice.getSelectionModel().getSelectedIndex()]);
                mcq.setMark(questionScore.getValue());
                QuizCreator.getInstance().getQuiz().getQuestions().add(mcq);
                break;
            case 2:
                ShortAnswerQuestion saq = new ShortAnswerQuestion();
                saq.setQuestionStatement(questionStatement.getText());
                saq.setCorrectAnswer(correctAnswer.getText());
                saq.setMark(questionScore.getValue());
                QuizCreator.getInstance().getQuiz().getQuestions().add(saq);
                break;
            case 3:
                FillInBlankQuestion fbq = new FillInBlankQuestion();
                fbq.setQuestionStatement(questionStatement.getText());
                fbq.setCorrectAnswer(correctAnswer.getText());
                fbq.setMark(questionScore.getValue());
                QuizCreator.getInstance().getQuiz().getQuestions().add(fbq);
                break;
        }
        clearFields();
        QuizCreator.getInstance().getCreatedQuestionsLabel().setText(String.valueOf(Integer.valueOf(QuizCreator.getInstance().getCreatedQuestionsLabel().getText()) + 1));
        mainApp.getQuestionCreator().close();
    }
    private void clearFields(){
        questionStatement.clear();
        questionType.getSelectionModel().clearSelection();
        choice1.clear();
        choice2.clear();
        choice3.clear();
        choice4.clear();
        correctChoice.getSelectionModel().clearSelection();
        correctAnswer.clear();
        trueFalse.getSelectionModel().clearSelection();
        questionScore.getValueFactory().setValue(0);
        enableQuestionType(-1);
    }
}