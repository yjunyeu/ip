import java.util.Scanner;
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String content) {
        String horizontalLine = "____________________________________________________________\n";
        String formatted = horizontalLine + content + horizontalLine;
        System.out.println(formatted);
    }

    public void displayWelcome() {
        displayMessage("""
                 Hello! I'm Waty
                 What can I do for you?
                """);
    }

    public void displayBye() {
        displayMessage(" Bye. Hope to see you again soon!\n");
    }

    public String readCommand() {
        return scanner.nextLine() + "\n";
    }
}
