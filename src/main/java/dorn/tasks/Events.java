package dorn.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Events(String description, LocalDate startDate, LocalDate endDate){
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String formatDate(){
        return " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + formatDate();
    }
}
