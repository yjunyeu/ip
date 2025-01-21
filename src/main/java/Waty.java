import java.util.Scanner;
public class Waty {
    private static String horizontalLineFormatter(String content) {
        String horizontalLine = "____________________________________________________________\n";
        return horizontalLine + content + horizontalLine;
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
            }
            System.out.println(horizontalLineFormatter(userInput));
        }
    }

}
