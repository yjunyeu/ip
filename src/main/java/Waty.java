import java.util.Scanner;
public class Waty {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________\n";
        String greet = horizontalLine +
                " Hello! I'm Waty\n" +
                " What can I do for you?\n" +
                horizontalLine;
        String bye = horizontalLine +
                " Bye. Hope to see you again soon!\n" +
                horizontalLine;
        System.out.println(greet);
        Scanner reader = new Scanner(System.in);
        while (true) {
            String userInput = reader.nextLine() + "\n";
            if (userInput.equals("bye\n")) {
                System.out.println(bye);
                break;
            }
            System.out.println(horizontalLine + userInput + horizontalLine);
        }
    }
}
