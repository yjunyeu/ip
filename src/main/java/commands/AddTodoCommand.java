package commands;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import task.ToDo;
public class AddTodoCommand extends Command{

    private final ToDo todo;

    public AddTodoCommand(String description) {
        this.todo = new ToDo(description);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int size = tasks.addTask(todo);
        ui.displayMessage(" Got it. I've added this task:\n" + " " + todo.toString() + "\n" + " Now you have " +
                String.valueOf(size) + " tasks in the list.\n");
    }
}
