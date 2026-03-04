package dorn.tasks;

/**
 * Represents a generic task with a description and completion status.
 * Serves as the base class for specific task types such as
 * {@link ToDos}, {@link Deadlines}, and {@link Events}.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getDescription(){ return description; }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}