abstract class Task {
    private boolean status;
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

    public String getSaveData() {
        return (this.status ? "1 | " : "0 | ") + this.description;
    }

    public static Task loadTask(String[] args) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Cannot load a abstract Task");
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
