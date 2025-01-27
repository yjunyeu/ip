public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveData() {
        return "T | " + super.getSaveData();
    }

    public static ToDo loadTask(String[] args) {
        ToDo todo  = new ToDo(args[2]);
        if (args[1].equals("1")) {
            todo.mark();
        }
        return todo;
    }
}
