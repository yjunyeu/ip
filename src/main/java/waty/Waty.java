package waty;

import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import commands.*;

public class Waty {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Waty() {
        this.ui = new Ui();
        this.storage = new Storage(ui);
        this.tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }


    public void run() {
        ui.displayWelcome();
        boolean running = true;
        while (running) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parse(userInput);
                command.execute(tasks, ui, storage);
                running = command.isRunning();
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
