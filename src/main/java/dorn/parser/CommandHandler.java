package dorn.parser;

import dorn.core.DornException;
import dorn.tasks.Deadlines;
import dorn.tasks.Events;
import dorn.tasks.Task;
import dorn.tasks.ToDos;

import java.util.List;

public class CommandHandler {
    public static void handleMark(String[] parts, List<Task> tasks) throws DornException {
        if(parts.length < 2){
            throw new DornException("please specify which task to mark");
        }

        try{
            int markIndex = Integer.parseInt(parts[1]) - 1;

            if(markIndex < 0 || markIndex >= tasks.size()){
                throw new DornException("Task number" + (markIndex + 1) + "does not exist.");
            }

            tasks.get(markIndex).markDone();
        } catch(NumberFormatException e){
            throw new DornException("Please input a valid task number");
        }
    }

    public static void handleUnmark(String[] parts, List<Task> tasks) throws DornException{
        if(parts.length < 2){
            throw new DornException("Please specify which task to unmark");
        }

        try {
            int unmarkIndex = Integer.parseInt(parts[1]) - 1;

            if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                throw new DornException("Task number " + (unmarkIndex + 1) + " does not exist.");
            }

            tasks.get(unmarkIndex).markUndone();
            OutputHandler.printUnmark(tasks.get(unmarkIndex));
        } catch (NumberFormatException e) {
            throw new DornException("Please provide a valid task number.");
        }
    }

    public static void handleTodo(String[] parts, List<Task> tasks) throws DornException {
        if (parts.length < 2) {
            throw new DornException("The description of a todo cannot be empty.");
        }

        String description = parser.parseDescription(parts);
        if (description.isEmpty()) {
            throw new DornException("The description of a todo cannot be empty.");
        }

        tasks.add(new ToDos(description));
        OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
    }

    public static void handleDeadline(String[] parts, List<Task> tasks) throws DornException{
        if(parts.length < 2){
            throw new DornException("The description of a deadline cannot be empty");
        }

        //checking for the presence of the /by keyword
        boolean hasByKeyword = false;
        for(String part : parts){
            if(part.equals("/by")) {
                hasByKeyword = true;
                break;
            }
        }

        if(!hasByKeyword){
            throw new DornException("Please specify the deadline with /by");
        }

        String description = parser.parseDescription(parts);
        String endDate = parser.endDate(parts);

        //checking for empty inputs
        if(description.isEmpty()){
            throw new DornException("The description of a deadline cannot be empty");
        }
        if(endDate.isEmpty() || endDate.equals("Invalid input format")){
            throw new DornException("The deadline date cannot be empty");
        }

        //passed all the checks now add it to the deadlines
        tasks.add(new Deadlines(description, endDate));
        OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
    }

    public static void handleEvent(String[] parts, List<Task> tasks) throws DornException{
        if (parts.length < 2) {
            throw new DornException("The description of an event cannot be empty.");
        }

        // Check if both /from and /to exist
        boolean hasFromKeyword = false;
        boolean hasToKeyword = false;

        for (String part : parts) {
            if (part.equals("/from")) hasFromKeyword = true;
            if (part.equals("/to")) hasToKeyword = true;
        }

        if (!hasFromKeyword || !hasToKeyword) {
            throw new DornException("Please specify event duration with /from and /to.");
        }

        String description = parser.parseDescription(parts);
        String startDate = parser.startDate(parts);
        String endDate = parser.endDate(parts);

        if (description.isEmpty()) {
            throw new DornException("The description of an event cannot be empty.");
        }

        if (startDate.equals("Invalid input format") || startDate.isEmpty()) {
            throw new DornException("The event start date cannot be empty.");
        }

        if (endDate.equals("Invalid input format") || endDate.isEmpty()) {
            throw new DornException("The event end date cannot be empty.");
        }

        tasks.add(new Events(description, startDate, endDate));
        OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
    }
}
