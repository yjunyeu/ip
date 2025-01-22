public class Deadline extends Task{
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
