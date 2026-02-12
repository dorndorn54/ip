package dorn.tasks;

public class Deadlines extends Task {

    private String dueDate;

    public Deadlines(String description, String dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    public String formatDate(){
        return " (by: " + this.dueDate + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + formatDate();
    }
}
