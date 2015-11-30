package main;

import javafx.scene.control.Alert;
import model.QuizListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class DataLoader {
    public static void load(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(QuizListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            QuizListWrapper wrapper = (QuizListWrapper) um.unmarshal(file);
            Main.getQuizzes().clear();
            Main.getQuizzes().addAll(wrapper.getQuizzes());

        } catch (Exception e) { // catches ANY exception
            if(e.getClass().toString().equals("class java.lang.NullPointerException")){
                new Alert(Alert.AlertType.INFORMATION,"Database is empty.").showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Could not load data");
                alert.setContentText("Could not load data from database.");
                alert.showAndWait();
            }
        }
    }

    public static void save(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(QuizListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            QuizListWrapper wrapper = new QuizListWrapper();
            wrapper.setQuizzes(Main.getQuizzes());
            m.marshal(wrapper, file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file.");

            alert.showAndWait();
        }
    }
}
