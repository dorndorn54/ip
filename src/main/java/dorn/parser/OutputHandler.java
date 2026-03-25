package dorn.parser;

import dorn.tasks.Task;

import java.util.List;

/**
 * Handles all formatted output to the standard output stream for the Dorn chatbot.
 * Each method prints a decorated, bordered message to the console.
 *
 * <p>All output lines are prefixed with a tab ({@code \t}) for consistent indentation,
 * and every message is wrapped in {@link #SEPARATOR} lines.</p>
 */
public class OutputHandler {

    /** Border line used to visually separate each chatbot response. */
    private static final String SEPARATOR =
            "\t____________________________________________________________";

    /** Indentation prefix for all content lines. */
    private static final String INDENT = "\t";

    /** Extra indentation for task entries displayed within a list. */
    private static final String TASK_INDENT = "\t  ";

    // -------------------------------------------------------------------------
    // Task list output
    // -------------------------------------------------------------------------

    /**
     * Prints all tasks currently in the task list, numbered from 1.
     * Displays a placeholder message if the list is empty.
     *
     * @param tasks the current list of tasks to display
     */
    public static void printTaskList(List<Task> tasks) {
        System.out.println(SEPARATOR);
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "Your task list is empty.");
        } else {
            System.out.println(INDENT + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(TASK_INDENT + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Prints tasks that matched a search query, numbered from 1.
     *
     * @param matchingTasks the list of tasks that matched the search keyword
     */
    public static void printSimilarTasks(List<Task> matchingTasks) {
        System.out.println(SEPARATOR);
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(TASK_INDENT + (i + 1) + ". " + matchingTasks.get(i));
        }
        System.out.println(SEPARATOR);
    }

    // -------------------------------------------------------------------------
    // Task mutation output
    // -------------------------------------------------------------------------

    /**
     * Prints a confirmation that a task was added, along with the new total task count.
     *
     * @param task          the task that was added
     * @param numberOfTasks the updated total number of tasks in the list
     */
    public static void printAdded(Task task, int numberOfTasks) {
        System.out.println(SEPARATOR);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(TASK_INDENT + task);
        System.out.println(INDENT + "Now you have " + numberOfTasks + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a confirmation that a task was deleted, along with the new total task count.
     *
     * @param task          the task that was removed
     * @param numberOfTasks the updated total number of tasks remaining
     */
    public static void printDelete(Task task, int numberOfTasks) {
        System.out.println(SEPARATOR);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(TASK_INDENT + task);
        System.out.println(INDENT + "Now you have " + numberOfTasks + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a confirmation that a task has been marked as done.
     *
     * @param task the task that was marked done
     */
    public static void printMark(Task task) {
        System.out.println(SEPARATOR);
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(TASK_INDENT + task);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a confirmation that a task has been marked as not done.
     *
     * @param task the task that was marked not done
     */
    public static void printUnmark(Task task) {
        System.out.println(SEPARATOR);
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(TASK_INDENT + task);
        System.out.println(SEPARATOR);
    }

    // -------------------------------------------------------------------------
    // General output
    // -------------------------------------------------------------------------

    /**
     * Prints an error message to the console.
     *
     * @param errorMessage the error message to display
     */
    public static void printError(String errorMessage) {
        System.out.println(SEPARATOR);
        System.out.println(INDENT + errorMessage);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the goodbye message shown when the user exits with {@code bye}.
     */
    public static void printBye() {
        System.out.println(SEPARATOR);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a formatted list of all available commands and their usage syntax,
     * with one example per command.
     */
    public static void printHelp() {
        System.out.println(SEPARATOR);
        System.out.println(INDENT + "Here are the commands you can use:");
        System.out.println();
        System.out.println(TASK_INDENT + "list");
        System.out.println(TASK_INDENT + "  Shows all your tasks.");
        System.out.println();
        System.out.println(TASK_INDENT + "todo <description>");
        System.out.println(TASK_INDENT + "  Adds a to-do task.");
        System.out.println(TASK_INDENT + "  e.g. todo read book");
        System.out.println();
        System.out.println(TASK_INDENT + "deadline <description> /by <YYYY-MM-DD>");
        System.out.println(TASK_INDENT + "  Adds a task with a deadline.");
        System.out.println(TASK_INDENT + "  e.g. deadline submit report /by 2026-04-01");
        System.out.println();
        System.out.println(TASK_INDENT + "event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>");
        System.out.println(TASK_INDENT + "  Adds an event spanning two dates.");
        System.out.println(TASK_INDENT + "  e.g. event team camp /from 2026-04-05 /to 2026-04-07");
        System.out.println();
        System.out.println(TASK_INDENT + "mark <task number>");
        System.out.println(TASK_INDENT + "  Marks a task as done.");
        System.out.println(TASK_INDENT + "  e.g. mark 2");
        System.out.println();
        System.out.println(TASK_INDENT + "unmark <task number>");
        System.out.println(TASK_INDENT + "  Marks a task as not done.");
        System.out.println(TASK_INDENT + "  e.g. unmark 2");
        System.out.println();
        System.out.println(TASK_INDENT + "delete <task number>");
        System.out.println(TASK_INDENT + "  Deletes a task from the list.");
        System.out.println(TASK_INDENT + "  e.g. delete 3");
        System.out.println();
        System.out.println(TASK_INDENT + "find <keyword(s)>");
        System.out.println(TASK_INDENT + "  Searches for tasks matching a keyword or phrase.");
        System.out.println(TASK_INDENT + "  e.g. find buy a car");
        System.out.println();
        System.out.println(TASK_INDENT + "help");
        System.out.println(TASK_INDENT + "  Shows this help message.");
        System.out.println();
        System.out.println(TASK_INDENT + "bye");
        System.out.println(TASK_INDENT + "  Saves your tasks and exits.");
        System.out.println(SEPARATOR);
    }
}