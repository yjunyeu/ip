import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
public class Waty {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final Path STORAGE_DIR = Paths.get(System.getProperty("user.dir"),  "data");
    private static final Path STORAGE_FILE = STORAGE_DIR.resolve("waty.txt");

    private enum Command {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    }
    private static String horizontalLineFormatter(String content) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + content + horizontalLine;
    }

    private static String bye() {
        return " Bye. Hope to see you again soon!\n";
    }

    private static String listTasks() {
        StringBuilder listOfTasks = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String numberedTask = " " + String.valueOf(i + 1) + ". " + tasks.get(i) + "\n";
            listOfTasks.append(numberedTask);
        }
        return listOfTasks.toString();
    }

    private static String markTask(int index) {
        tasks.get(index).mark();
        return " Nice! I've marked this task as done:\n " + tasks.get(index);
    }

    private static String unmarkTask(int index) {
        tasks.get(index).unmark();
        return " OK, I've marked this task as not done yet:\n " + tasks.get(index);
    }

    private static void setupStorage() {
        try {
            if (!Files.exists(STORAGE_DIR)) {
                Files.createDirectories(STORAGE_DIR);
            }
            if (!Files.exists(STORAGE_FILE)) {
                Files.createFile(STORAGE_FILE);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while setting up storage: " + e.getMessage());
        }
    }

    private static void saveTasks() {
        try {
            FileWriter storageWriter = new FileWriter(STORAGE_FILE.toFile());
            for (Task task: tasks) {
                storageWriter.write(task.getSaveData() + "\n");
            }
            storageWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving tasks");
            e.printStackTrace();
        }
    }

    private static void loadTasks() {
        setupStorage();
        try {
            Scanner storageReader = new Scanner(STORAGE_FILE.toFile());
            while (storageReader.hasNextLine()) {
                String data = storageReader.nextLine();
                String[] args = data.split(" \\| ");
                String type = args[0].trim();;
                if (type.equals("T")) {
                    tasks.add(ToDo.loadTask(args));
                } else if (type.equals("D")) {
                    tasks.add(Deadline.loadTask(args));
                } else if (type.equals("E")) {
                    tasks.add(Event.loadTask(args));
                }
            }
            storageReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved task found. Welcome!");
        } catch (Exception e) {
            System.out.println("Error occurred when loading tasks");
            e.printStackTrace();
        }
    }

    private static String addToDo(String taskDescription) {
        ToDo newToDo = new ToDo(taskDescription);
        tasks.add(newToDo);
        return " Got it. I've added this task:\n" + " " + newToDo.toString() + "\n" + " Now you have " +
                String.valueOf(tasks.size()) + " tasks in the list.\n";
    }

    private static String addDeadline(String taskDescription, String by) {
        Deadline newDeadline = new Deadline(taskDescription, by);
        tasks.add(newDeadline);
        return " Got it. I've added this task:\n" + " " + newDeadline.toString() + "\n" + " Now you have " +
                String.valueOf(tasks.size()) + " tasks in the list.\n";
    }

    private static String addEvent(String taskDescription, String from, String to) {
        Event newEvent = new Event(taskDescription, from, to);
        tasks.add(newEvent);
        return " Got it. I've added this task:\n" + " " + newEvent.toString() + "\n" + " Now you have " +
                String.valueOf(tasks.size()) + " tasks in the list.\n";
    }

    private static String deleteTask(int index) {
        String deleteTaskDescription = tasks.get(index).toString();
        tasks.remove(index);
        return " Noted. I've removed this task:\n" + " " + deleteTaskDescription + "\n" + " Now you have " +
                String.valueOf(tasks.size()) + " tasks in the list.\n";
    }

    private static boolean handleCommand(String[] split) throws InvalidArgumentException, InvalidCommandException {
        try {
            Command command = Command.valueOf(split[0].toUpperCase());
            switch (command) {
                case BYE:
                    saveTasks();
                    System.out.println(horizontalLineFormatter(bye()));
                    return false;
                case LIST:
                    String list = horizontalLineFormatter(listTasks());
                    System.out.println(list);
                    break;
                case MARK:
                    if (split.length < 2) {
                        throw new InvalidArgumentException("Example usage: mark 1\n");
                    }
                    int markIndex = Integer.parseInt(split[1].strip()) - 1;
                    String markStatus = markTask(markIndex) + "\n";
                    System.out.println(horizontalLineFormatter(markStatus));
                    break;
                case UNMARK:
                    if (split.length < 2) {
                        throw new InvalidArgumentException("Example usage: unmark 1\n");
                    }
                    int unmarkIndex = Integer.parseInt(split[1].strip()) - 1;
                    String unmarkStatus = unmarkTask(unmarkIndex) + "\n";
                    System.out.println(horizontalLineFormatter(unmarkStatus));
                    break;
                case TODO:
                    if (split.length < 2 || split[1].trim().isEmpty()) {
                        throw new InvalidArgumentException("The description of a todo cannot be empty.\n");
                    }
                    String taskDescription = split[1].strip();
                    String taskStatus = addToDo(taskDescription);
                    System.out.println(horizontalLineFormatter(taskStatus));
                    break;
                case DEADLINE:
                    if (split.length < 2 || !split[1].contains("/by"))
                        throw new InvalidArgumentException(
                                "The deadline task must include a description and a '/by' argument.\n");
                    String[] deadlineSplit = split[1].split(" /by", 2);
                    String deadlineStatus = addDeadline(deadlineSplit[0].strip(), deadlineSplit[1].strip());
                    System.out.println(horizontalLineFormatter(deadlineStatus));
                    break;
                case EVENT:
                    if (split.length < 2 || !split[1].contains("/from") || !split[1].contains("/to"))
                        throw new InvalidArgumentException(
                                "The event task must include a description, a '/from' argument, and a '/to' argument.\n");
                    String[] eventSplit = split[1].split(" /from ", 2);
                    String[] timeSplit = eventSplit[1].split(" /to", 2);
                    String eventStatus = addEvent(eventSplit[0].strip(), timeSplit[0].strip(), timeSplit[1].strip());
                    System.out.println(horizontalLineFormatter(eventStatus));
                    break;
                case DELETE:
                    if (split.length < 2) {
                        throw new InvalidArgumentException("Example usage: delete 1\n");
                    }
                    int deleteIndex = Integer.parseInt(split[1].strip()) - 1;
                    String deleteStatus = deleteTask(deleteIndex);
                    System.out.println(horizontalLineFormatter(deleteStatus));
                    break;
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("OOPS!!! What do you mean???\n");
        }
        return true;
    }
    public static void main(String[] args) {
        loadTasks();
        String greet = horizontalLineFormatter("""
                 Hello! I'm Waty
                 What can I do for you?
                """);
        System.out.println(greet);
        Scanner reader = new Scanner(System.in);
        boolean running = true;

        while (running) {
            String userInput = reader.nextLine() + "\n";
            String[] split = userInput.split("\\s+", 2);
            try {
                running = handleCommand(split);
            } catch (InvalidArgumentException | InvalidCommandException e) {
                System.out.println(horizontalLineFormatter(e.getMessage()));
            } catch (Exception e) {
                System.out.println("ERRORRRRR!!! THIS IS WEIRD");
            }

        }
    }

}
