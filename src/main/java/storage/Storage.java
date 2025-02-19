package storage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;
/**
 * Handles the storage and retrieval of tasks from a text file.
 */
public class Storage {
    private final Path storageDir = Paths.get(System.getProperty("user.dir"), "data");
    private final Path storageFile = storageDir.resolve("waty.txt");

    private Ui ui;
    private final ArrayList<Task> tasks;

    /**
     * Constructs a Storage instance.
     *
     */
    public Storage() {
        tasks = new ArrayList<>();
    }

    /**
     * Sets up the storage directory and file if they do not exist.
     */
    private void setupStorage() {
        try {
            if (!Files.exists(storageDir)) {
                Files.createDirectories(storageDir);
            }
            if (!Files.exists(storageFile)) {
                Files.createFile(storageFile);
            }
        } catch (IOException e) {
            ui.displayError(e.getMessage());
        }
        assert Files.exists(storageDir) : "Storage directory should be present";
        assert Files.exists(storageFile) : "Storage file should be present";
    }

    /**
     * Saves the current list of tasks to the storage file.
     */
    public void saveTasks() {
        try {
            FileWriter storageWriter = new FileWriter(storageFile.toFile());
            for (Task task: tasks) {
                storageWriter.write(task.getSaveData() + "\n");
            }
            storageWriter.close();
        } catch (IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks retrieved from storage.
     */
    public ArrayList<Task> loadTasks() {
        setupStorage();
        try {
            Scanner storageReader = new Scanner(storageFile.toFile());
            while (storageReader.hasNextLine()) {
                String data = storageReader.nextLine();
                String[] args = data.split(" \\| ");
                String type = args[0].trim();
                if (type.equals("T")) {
                    tasks.add(ToDo.loadTask(args));
                } else if (type.equals("D")) {
                    tasks.add(Deadline.loadTask(args));
                } else if (type.equals("E")) {
                    tasks.add(Event.loadTask(args));
                } else {
                    ui.displayError("Unknown task type found in storage.");
                }
            }
            storageReader.close();
        } catch (FileNotFoundException e) {
            ui.displayError("No saved task found. Welcome!");
        } catch (Exception e) {
            ui.displayError(e.getMessage());
        }
        return tasks;
    }
    public void setUi(Ui ui, TaskList tasks) {
        this.ui = ui;
        tasks.setTasks(loadTasks());
    }
}
