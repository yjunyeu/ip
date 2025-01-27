public class Deadline extends Task{
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by.strip();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getSaveData() {
        return "D | " + super.getSaveData() + " | " + this.by;
    }

    public static Deadline loadTask(String[] args) {
        Deadline deadline  = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            deadline.mark();
        }
        return deadline;
    }
}
