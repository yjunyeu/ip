package waty;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Waty using FXML.
 */
public class Main extends Application {

    private final Waty waty = new Waty();

    /**
     * Starts the JavaFX application by loading the FXML layout and setting up the stage.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWaty(waty);
            stage.setTitle("Waty Chatbot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
