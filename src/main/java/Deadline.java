import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{
    private final LocalDateTime by;

    Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")) + ")";
    }

    @Override
    public String getSaveData() {
        return "D | " + super.getSaveData() + " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public static Deadline loadTask(String[] args) {
        Deadline deadline  = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            deadline.mark();
        }
        return deadline;
    }
}
