import java.util.ArrayList;
import java.util.Scanner;
public class Waty {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private enum Command {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT;

    }
    private static String horizontalLineFormatter(String content) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + content + horizontalLine;
    }

    private static String listTasks() {
        StringBuilder listOfTasks = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String numberedTask = " " + String.valueOf(i + 1) + ". " + tasks.get(i);
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

    private static String addToDo(String taskDescription) {
        ToDo newToDo = new ToDo(taskDescription);
        tasks.add(newToDo);
        return " Got it. I've added this task:\n" + " " + newToDo.toString() + " Now you have " +
                String.valueOf(tasks.size()) + " tasks in the list.\n";
    }

    private static String addDeadline(String taskDescription, String by) {
        Deadline newDeadline = new Deadline(taskDescription, by);
        tasks.add(newDeadline);
        return " Got it. I've added this task:\n" + " " + newDeadline.toString() + " Now you have " +
                String.valueOf(tasks.size()) + " tasks in the list.\n";
    }

    private static String addEvent(String taskDescription, String from, String to) {
        Event newEvent = new Event(taskDescription, from, to);
        tasks.add(newEvent);
        return " Got it. I've added this task:\n" + " " + newEvent.toString() + " Now you have " +
                String.valueOf(tasks.size()) + " tasks in the list.\n";
    }
    public static void main(String[] args) {
        String greet = horizontalLineFormatter(" Hello! I'm Waty\n" +
                " What can I do for you?\n");
        String bye = horizontalLineFormatter(" Bye. Hope to see you again soon!\n");
        System.out.println(greet);
        Scanner reader = new Scanner(System.in);

        while (true) {
            String userInput = reader.nextLine() + "\n";
            String[] split = userInput.split("\\s+", 2);
            try {
                Command command = Command.valueOf(split[0].toUpperCase());

                switch (command) {
                    case BYE:
                        System.out.println(bye);
                        return;
                    case LIST:
                        String list = horizontalLineFormatter(listTasks());
                        System.out.println(list);
                        break;
                    case MARK:
                        int markIndex = Integer.parseInt(split[1].strip()) - 1;
                        String markStatus = markTask(markIndex);
                        System.out.println(horizontalLineFormatter(markStatus));
                        break;
                    case UNMARK:
                        int unmarkIndex = Integer.parseInt(split[1].strip()) - 1;
                        String unmarkStatus = unmarkTask(unmarkIndex);
                        System.out.println(horizontalLineFormatter(unmarkStatus));
                        break;
                    case TODO:
                        String taskDescription = split[1];
                        String taskStatus = addToDo(taskDescription);
                        System.out.println(horizontalLineFormatter(taskStatus));
                        break;
                    case DEADLINE:
                        String[] deadlineSplit = split[1].split(" /by", 2);
                        String deadlineStatus = addDeadline(deadlineSplit[0], deadlineSplit[1]);
                        System.out.println(horizontalLineFormatter(deadlineStatus));
                        break;
                    case EVENT:
                        String[] eventSplit = split[1].split(" /from ", 2);
                        String[] timeSplit = eventSplit[1].split(" /to", 2);
                        String eventStatus = addEvent(eventSplit[0], timeSplit[0], timeSplit[1]);
                        System.out.println(horizontalLineFormatter(eventStatus));
                        break;

                }
            } catch (IllegalArgumentException e) {
                System.out.println("WHAT THE WATY!!!");
            }

        }
    }

}
