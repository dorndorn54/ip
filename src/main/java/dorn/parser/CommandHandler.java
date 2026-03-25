package dorn.parser;

import dorn.core.DornException;
import dorn.core.StorageSystem;
import dorn.tasks.Deadlines;
import dorn.tasks.Events;
import dorn.tasks.Task;
import dorn.tasks.ToDos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles parsing and execution of all user commands for the Dorn chatbot.
 * Dispatches commands to the appropriate handler methods and updates the task list accordingly.
 */
public class CommandHandler {

    /**
     * Identifies and dispatches the user's command to the appropriate handler.
     * Recognized commands: {@code bye}, {@code list}, {@code mark}, {@code unmark},
     * {@code todo}, {@code deadline}, {@code event}, {@code delete}, {@code find}, {@code help}.
     *
     * @param parts the tokenized user input, where {@code parts[0]} is the command
     * @param tasks the current list of tasks to operate on
     * @throws DornException if the command arguments are invalid
     * @throws IOException   if saving the task list on {@code bye} fails
     */
    public static void identifyCommand(String[] parts, List<Task> tasks) throws DornException, IOException {
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                StorageSystem.saveList(tasks);
                OutputHandler.printBye();
                System.exit(0);
            case "list":
                OutputHandler.printTaskList(tasks);
                break;
            case "mark":
                CommandHandler.handleMark(parts, tasks);
                break;
            case "unmark":
                CommandHandler.handleUnmark(parts, tasks);
                break;
            case "todo":
                CommandHandler.handleTodo(parts, tasks);
                break;
            case "deadline":
                CommandHandler.handleDeadline(parts, tasks);
                break;
            case "event":
                CommandHandler.handleEvent(parts, tasks);
                break;
            case "delete":
                CommandHandler.handleDelete(parts, tasks);
                break;
            case "find":
                CommandHandler.handleFind(parts, tasks);
                break;
            case "help":
                OutputHandler.printHelp();
                break;
            default:
                OutputHandler.printError("I'm sorry, but I don't know what that means :-(");
                break;
        }

    }

    /**
     * Searches for tasks whose descriptions contain the given query string (case-insensitive).
     * The query may consist of multiple words; all tokens after the command are joined as
     * a single search phrase. Prints matching tasks, or an error message if none are found.
     *
     * @param parts the tokenized input; tokens from {@code parts[1]} onward form the search query
     * @param tasks the current list of tasks to search through
     * @throws DornException if no keyword is provided
     */
    public static void handleFind(String[] parts, List<Task> tasks) throws DornException {
        if (parts.length < 2) {
            throw new DornException("Please specify a keyword to search for.");
        }

        // Join all tokens after the command to support multi-word searches
        StringBuilder queryBuilder = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            queryBuilder.append(parts[i]);
            if (i < parts.length - 1) {
                queryBuilder.append(" ");
            }
        }
        String keyword = queryBuilder.toString().toLowerCase();
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if(matchingTasks.isEmpty()){
            OutputHandler.printError("No tasks found containing keyword: " + keyword);
        } else {
            OutputHandler.printSimilarTasks(matchingTasks);
        }

    }

    /**
     * Marks the specified task as done.
     *
     * @param parts the tokenized input; {@code parts[1]} is the 1-based task number
     * @param tasks the current list of tasks
     * @throws DornException if no task number is provided, the number is out of range,
     *                       or the input is not a valid integer
     */
    public static void handleMark(String[] parts, List<Task> tasks) throws DornException {
        if(parts.length < 2){
            throw new DornException("please specify which task to mark");
        }

        try{
            int markIndex = Integer.parseInt(parts[1]) - 1;

            if (markIndex < 0 || markIndex >= tasks.size()) {
                throw new DornException("Task number " + (markIndex + 1) + " does not exist.");
            }

            tasks.get(markIndex).markDone();
            OutputHandler.printMark(tasks.get(markIndex));
        } catch(NumberFormatException e){
            throw new DornException("Please input a valid task number");
        }
    }

    /**
     * Marks the specified task as not done.
     *
     * @param parts the tokenized input; {@code parts[1]} is the 1-based task number
     * @param tasks the current list of tasks
     * @throws DornException if no task number is provided, the number is out of range,
     *                       or the input is not a valid integer
     */
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

    /**
     * Creates and adds a new {@link ToDos} task to the task list.
     *
     * @param parts the tokenized input; tokens after {@code parts[0]} form the description
     * @param tasks the current list of tasks
     * @throws DornException if the description is missing or empty
     */
    public static void handleTodo(String[] parts, List<Task> tasks) throws DornException {
        if (parts.length < 2) {
            throw new DornException("The description of a todo cannot be empty.");
        }

        String description = Parser.parseDescription(parts);
        if (description.isEmpty()) {
            throw new DornException("The description of a todo cannot be empty.");
        }

        tasks.add(new ToDos(description));
        OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
    }

    /**
     * Creates and adds a new {@link Deadlines} task to the task list.
     * Expects a description followed by {@code /by <date>}.
     *
     * @param parts the tokenized input containing the description and {@code /by} date
     * @param tasks the current list of tasks
     * @throws DornException if the description or {@code /by} date is missing or empty
     */
    public static void handleDeadline(String[] parts, List<Task> tasks) throws DornException{
        if(parts.length < 2){
            throw new DornException("The description of a deadline cannot be empty");
        }

        //checking for the presence of the /by keyword
        if (!Parser.contains(parts, "/by")) {
            throw new DornException("Please specify the deadline with /by");
        }

        String description = Parser.parseDescription(parts);

        //checking for empty inputs
        if (description.isEmpty()) {
            throw new DornException("The description of a deadline cannot be empty.");
        }

        LocalDate endDate;
        try {
            endDate = Parser.endDate(parts);
        } catch (DateTimeParseException e) {
            throw new DornException("Invalid date format for /by. Please use YYYY-MM-DD (e.g., 2026-03-24).");
        }

        if (endDate == null) {
            throw new DornException("The deadline date cannot be empty. Usage: deadline <description> /by YYYY-MM-DD");
        }

        //passed all the checks now add it to the deadlines
        tasks.add(new Deadlines(description, endDate));
        OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
    }

    /**
     * Creates and adds a new {@link Events} task to the task list.
     * Expects a description followed by {@code /from <date>} and {@code /to <date>}.
     *
     * @param parts the tokenized input containing the description, {@code /from}, and {@code /to} dates
     * @param tasks the current list of tasks
     * @throws DornException if the description, start date, or end date is missing or empty
     */
    public static void handleEvent(String[] parts, List<Task> tasks) throws DornException{
        if (parts.length < 2) {
            throw new DornException("The description of an event cannot be empty.");
        }

        // Check if both /from and /to exist
        if (!Parser.contains(parts, "/from") || !Parser.contains(parts, "/to")) {
            throw new DornException("Please specify event duration with /from and /to.");
        }

        String description = Parser.parseDescription(parts);

        if (description.isEmpty()) {
            throw new DornException("The description of an event cannot be empty.");
        }

        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = Parser.startDate(parts);
        } catch (DateTimeParseException e) {
            throw new DornException("Invalid date format for /from. Please use YYYY-MM-DD (e.g., 2026-03-24).");
        }

        try {
            endDate = Parser.endDate(parts);
        } catch (DateTimeParseException e) {
            throw new DornException("Invalid date format for /to. Please use YYYY-MM-DD (e.g., 2026-03-25).");
        }

        if (startDate == null) {
            throw new DornException("The event start date cannot be empty. Usage: event <description> /from YYYY-MM-DD /to YYYY-MM-DD");
        }

        if (endDate == null) {
            throw new DornException("The event end date cannot be empty. Usage: event <description> /from YYYY-MM-DD /to YYYY-MM-DD");
        }

        if (startDate.isAfter(endDate)) {
            throw new DornException("Event start date (" + startDate + ") cannot be after end date (" + endDate + ").");
        }

        tasks.add(new Events(description, startDate, endDate));
        OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param parts the tokenized input; {@code parts[1]} is the 1-based task number to delete
     * @param tasks the current list of tasks
     * @throws DornException if no task number is provided or the input is not a valid integer
     */
    public static void handleDelete(String[] parts, List<Task> tasks) throws  DornException{
        if(parts.length < 2){
            throw new DornException("The delete command must be followed by the task number");
        }

        try{
            int indexToDelete = Integer.parseInt(parts[1]) - 1;

            OutputHandler.printDelete(tasks.get(indexToDelete), tasks.size() - 1); //number of tasks left so subtract 1
            tasks.remove(indexToDelete);

        } catch(NumberFormatException e){
            throw new DornException("Please provide a valid task number.");
        }
    }
}