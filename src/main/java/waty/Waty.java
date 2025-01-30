package waty;

import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import commands.Command;

public class Waty {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    public Waty() {
        this.ui = new Ui();
        this.storage = new Storage(ui);
        this.tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }


    public void run() {
        ui.displayWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isRunning = command.isRunning();
            } catch (InvalidArgumentException | InvalidCommandException e) {
                ui.displayMessage(e.getMessage());
            } catch (Exception e) {
                ui.displayError("ERRORRRRR!!! THIS IS WEIRD");
            }

        }
    }
    public static void main(String[] args) {
        new Waty().run();
    }

}
