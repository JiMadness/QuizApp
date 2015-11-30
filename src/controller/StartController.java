package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import main.Main;

import java.util.Optional;


public class StartController {
    private static Main mainApp;

    @FXML
    private void instructorPressed() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Instructor login");
        dialog.setHeaderText("Please enter your Instructor Password");
        dialog.setContentText("Password: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(Main.getPassword())) {
            new Alert(Alert.AlertType.INFORMATION, "Logged in successfully.").showAndWait();
            ContentController.getInstance().start(true);
            mainApp.getRoot().setCenter(mainApp.getContent());
            Main.getPrimaryStage().setTitle("QuizApp - Instructor");
        } else if(result.isPresent()&&!result.get().equals(Main.getPassword()))
                    new Alert(Alert.AlertType.ERROR, "Invalid instructor password.").show();
    }

    @FXML
    private void initialize() {
        mainApp = Main.getMainApp();
    }

    @FXML
    private void studentPressed() {
        ContentController.getInstance().start(false);
        mainApp.getRoot().setCenter(mainApp.getContent());
        Main.getPrimaryStage().setTitle("QuizApp - Student");
    }
}
