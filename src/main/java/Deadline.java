public class Deadline extends Task{
    private final String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by.strip();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")\n";
    }

    @Override
    public String getSaveData() {
        return "D | " + super.getSaveData() + " | " + this.by;
    }
}
