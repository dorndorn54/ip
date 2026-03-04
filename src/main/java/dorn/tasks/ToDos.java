package dorn.tasks;

/**
 * Represents a basic to-do task with no associated date or time.
 * Displayed with a {@code [T]} prefix.
 */
public class ToDos extends Task {

    public ToDos(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
