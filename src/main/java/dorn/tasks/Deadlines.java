package dorn.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private LocalDate dueDate;

    public Deadlines(String description, LocalDate dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    public String formatDate(){
        return " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + formatDate();
    }
}
