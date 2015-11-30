package main;

import controller.QuestionViewer;
import controller.QuizCreator;
import javafx.beans.InvalidationListener;
import model.Quiz;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private static ObservableList<Quiz> quizzes = FXCollections.observableArrayList();
    private static Stage primaryStage;
    private  Stage quizCreator;
    private Stage questionCreator;
    private Stage quizViewer;
    public  File dataBase = new File("resources/databases/data.xml");
    public static Main mainApp;
    private BorderPane root;
    private Node content;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.mainApp = this;
        Main.primaryStage = primaryStage;
        DataLoader.load(dataBase);
        initViews();
        quizzes.addListener((InvalidationListener) l -> DataLoader.save(dataBase));
        getQuizViewer().setOnCloseRequest(event -> {
            QuestionViewer.getInstance().clearPressed();
            QuestionViewer.getInstance().setQuestionIterator(0);
        });
        getQuizCreator().setOnCloseRequest(event -> QuizCreator.getInstance().clearFields());
        primaryStage.show();
    }

    public static String getPassword() {
        return "InstructorPassword";
    }

    public static ObservableList<Quiz> getQuizzes() {
        return quizzes;
    }

    public BorderPane getRoot() {
        return root;
    }

    public Node getContent() {
        return content;
    }

    public  Stage getQuizCreator() {return quizCreator;}

    public Stage getQuizViewer(){return quizViewer;}

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Main getMainApp() {
        return mainApp;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getQuestionCreator() {
        return questionCreator;
    }
    private void initViews()throws Exception{
        root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
        Node start = FXMLLoader.load(getClass().getResource("../view/starter.fxml"));
        content = FXMLLoader.load(getClass().getResource("../view/content.fxml"));
        Node questionViewer = FXMLLoader.load(getClass().getResource("../view/questionViewer.fxml"));
        BorderPane quizViewerPane = FXMLLoader.load(getClass().getResource("../view/quizViewer.fxml"));
        quizViewerPane.setCenter(questionViewer);
        root.setCenter(start);
        quizCreator = new Stage();
        getQuizCreator().setTitle("New Quiz");
        getQuizCreator().setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/quizCreator.fxml")), 500, 270));
        questionCreator = new Stage();
        getQuestionCreator().setTitle("New Question");
        getQuestionCreator().setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/questionCreator.fxml")), 560, 395));
        primaryStage.setTitle("QuizApp");
        primaryStage.setScene(new Scene(root, 800, 600));
        quizViewer = new Stage();
        getQuizViewer().setScene(new Scene(quizViewerPane,500,570));
    }
}
