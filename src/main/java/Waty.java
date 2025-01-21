import java.util.ArrayList;
import java.util.Scanner;
public class Waty {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private enum Command {
        LIST, BYE, MARK, UNMARK;

    }
    private static String horizontalLineFormatter(String content) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + content + horizontalLine;
    }

    private static String listTasks() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String numberedTask = " " + String.valueOf(i + 1) + ". " + tasks.get(i);
            listOfTasks.append(numberedTask);
        }
        return listOfTasks.toString();
    }

    private static String addTasks(String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
        return " added: " + taskDescription;
    }

    private static String markTask(int index) {
        tasks.get(index).mark();
        return " Nice! I've marked this task as done:\n " + tasks.get(index);
    }

    private static String unmarkTask(int index) {
        tasks.get(index).unmark();
        return " OK, I've marked this task as not done yet:\n " + tasks.get(index);
    }
    public static void main(String[] args) {
        String greet = horizontalLineFormatter(" Hello! I'm Waty\n" +
                " What can I do for you?\n");
        String bye = horizontalLineFormatter(" Bye. Hope to see you again soon!\n");
        System.out.println(greet);
        Scanner reader = new Scanner(System.in);

        while (true) {
            String userInput = reader.nextLine() + "\n";
            String[] split = userInput.split("\\s+");
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
                        int markIndex = Integer.parseInt(split[1]) - 1;
                        System.out.println(markTask(markIndex));
                        break;
                    case UNMARK:
                        int unmarkIndex = Integer.parseInt(split[1]) - 1;
                        System.out.println(unmarkTask(unmarkIndex));
                        break;
                }
            } catch (IllegalArgumentException e) {
                String reply = horizontalLineFormatter(addTasks(userInput));
                System.out.println(reply);
            }

        }
    }

}
