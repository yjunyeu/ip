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
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String getSaveData() {
        return "E | " + super.getSaveData() + " | " + this.from + "-" + this.to;
    }
    public static Event loadTask(String[] args) {
        String[] period = args[3].split("-");
        Event event  = new Event(args[2], period[0], period[1]);
        if (args[1].equals("1")) {
            event.mark();
        }
        return event;
    }
}
