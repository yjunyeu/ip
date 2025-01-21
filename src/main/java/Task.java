public class Task {
    private Boolean status;
    private final String description;

    Task(String description) {
        this.description = description;
        this.status = false;
    }

    public void unmark() {
        this.status = false;
    }

    public void mark() {
        this.status = true;
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
