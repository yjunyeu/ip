import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;

    Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")) + ")";
    }

    @Override
    public String getSaveData() {
        return "E | " + super.getSaveData() + " | " + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + "-" + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
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
