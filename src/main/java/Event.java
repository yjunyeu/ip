public class Event extends Task{
    private final String from;
    private final String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to.strip();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")\n";
    }

    @Override
    public String getSaveData() {
        return "E | " + super.getSaveData() + " | " + this.from + "-" + this.to;
    }
}
