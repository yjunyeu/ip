package waty;

import commands.Command;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * Controller for the main JavaFX GUI window of the Waty chatbot.
 * Handles user interactions, displays chat messages, and processes user input.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Waty waty;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image watyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setWaty(Waty waty) {
        this.waty = waty;
        this.ui = new Ui(this);
        ui.displayWelcome();
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        displayUserMessage(input);

        try {
            Command command = waty.getParser().parse(input);
            command.execute(waty.getTasks(), ui, waty.getStorage());
            if (!command.isRunning()) {
                System.exit(0); // Close application if "bye" command is entered
            }
        } catch (InvalidArgumentException | InvalidCommandException e) {
            ui.displayMessage(e.getMessage());
        } catch (Exception e) {
            ui.displayError("ERRORRRRR!!! THIS IS WEIRD");
        }

        userInput.clear();
    }

    public void displayBotMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getWatyDialog(message, watyImage));
    }

    public void displayUserMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

    public String getUserInput() {
        return userInput.getText();
    }
}
