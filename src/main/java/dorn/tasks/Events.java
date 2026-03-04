package dorn.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that spans a specific date range.
 * Displayed with an {@code [E]} prefix and the start/end dates formatted as {@code MMM d yyyy}.
 */
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
