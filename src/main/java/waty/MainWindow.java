package waty;

import commands.Command;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Waty waty;
    private Ui ui;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/mcqueen.png"));
    private final Image watyImage = new Image(this.getClass().getResourceAsStream("/images/wally.png"));

    /**
     * Initializes the GUI components.
     * Binds the scroll pane's vertical scroll property to the dialog container height
     * to ensure automatic scrolling as messages are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Waty chatbot instance and initializes the UI.
     *
     * @param waty The instance of {@code Waty} chatbot.
     */
    public void setWaty(Waty waty) {
        this.waty = waty;
        this.ui = new Ui(this);
        waty.setUi(this.ui);
        ui.displayWelcome();
    }

    /**
     * Handles user input when the user presses Enter.
     * Processes the command, updates the UI, and executes Waty's response.
     * If Waty terminates, the application exits.
     */
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
                ui.displayBye();
                System.exit(0);
            }
        } catch (InvalidArgumentException | InvalidCommandException e) {
            ui.displayError(e.getMessage());
        } catch (Exception e) {
            ui.displayError("ERRORRRRR!!! THIS IS WEIRD");
        }

        userInput.clear();
    }

    /**
     * Displays a message from Waty in the dialog container.
     *
     * @param message The Waty's response to display.
     */
    public void displayBotMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getWatyDialog(message, watyImage));
    }

    /**
     * Displays a message from the user in the dialog container.
     *
     * @param message The user's input message to display.
     */
    public void displayUserMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

    /**
     * Retrieves the current text input from the user.
     *
     * @return The text entered by the user in the input field.
     */
    public String getUserInput() {
        return userInput.getText();
    }
}
