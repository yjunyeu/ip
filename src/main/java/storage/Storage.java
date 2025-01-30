package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import ui.Ui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
public class Storage {
    private final Path STORAGE_DIR = Paths.get(System.getProperty("user.dir"),  "data");
    private final Path STORAGE_FILE = STORAGE_DIR.resolve("waty.txt");

    private final Ui ui;
    private final ArrayList<Task> tasks;

    public Storage(Ui ui) {
        this.ui = ui;
        tasks = new ArrayList<>();
    }

    private void setupStorage() {
        try {
            if (!Files.exists(STORAGE_DIR)) {
                Files.createDirectories(STORAGE_DIR);
            }
            if (!Files.exists(STORAGE_FILE)) {
                Files.createFile(STORAGE_FILE);
            }
        } catch (IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void saveTasks() {
        try {
            FileWriter storageWriter = new FileWriter(STORAGE_FILE.toFile());
            for (Task task: tasks) {
                storageWriter.write(task.getSaveData() + "\n");
            }
            storageWriter.close();
        } catch (IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() {
        setupStorage();
        try {
            Scanner storageReader = new Scanner(STORAGE_FILE.toFile());
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
}
