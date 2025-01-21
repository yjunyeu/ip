import java.util.ArrayList;
import java.util.Scanner;
public class Waty {
    private static ArrayList<String> tasks = new ArrayList<>();
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

    private static String addTasks(String task) {
        tasks.add(task);
        return " added: " + task;
    }
    public static void main(String[] args) {
        String greet = horizontalLineFormatter(" Hello! I'm Waty\n" +
                " What can I do for you?\n");
        String bye = horizontalLineFormatter(" Bye. Hope to see you again soon!\n");
        System.out.println(greet);
        Scanner reader = new Scanner(System.in);
        while (true) {
            String userInput = reader.nextLine() + "\n";
            if (userInput.equals("bye\n")) {
                System.out.println(bye);
                break;
            } else if (userInput.equals("list\n")) {
                String list = horizontalLineFormatter(listTasks());
                System.out.println(list);
            } else {
                String reply = horizontalLineFormatter(addTasks(userInput));
                System.out.println(reply);
            }
        }
    }

}
