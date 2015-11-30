package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import main.Main;

public class FrameController {
    @FXML
    private void closePressed() {
        Main.getPrimaryStage().close();
    }
    @FXML
    private void aboutPressed(){
        new Alert(Alert.AlertType.INFORMATION,"QuizApp v1.0\nCreated by: Mohamed Gamal\nNovember 2015").show();
    }
}
