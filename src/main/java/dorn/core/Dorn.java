package dorn.core;

import dorn.tasks.Task;
import dorn.parser.OutputHandler;

import java.util.List;
import java.util.Scanner;

import static dorn.parser.CommandHandler.identifyCommand;

public class Dorn {
    public static void initialisation() {
        String logo = " ____   ___  ____  _   _ \n"
                + "|  _ \\ / _ \\|  _ \\| \\ | |\n"
                + "| | | | | | | |_) |  \\| |\n"
                + "| |_| | |_| |  _ <| |\\  |\n"
                + "|____/ \\___/|_| \\_\\_| \\_|\n";
        String name = "Dorn";

        System.out.println("Hello! I'm " + name +
                "\nWhat can I do for you?" +
                "\n\n" + logo + "\n");

        OutputHandler.printHelp();
    }
    /**
     * Continuously reads user input from standard input and processes commands.
     * Loads the saved task list on startup, then enters an infinite loop reading
     * one line at a time. Blank lines are ignored. Each non-empty line is split
     * into tokens and passed to {@link #identifyCommand} for dispatch.
     *
     * <p>Handles {@link DornException} for expected application errors and
     * {@link Exception} for any other unexpected errors, printing an error
     * message via {@link OutputHandler#printError} in both cases.</p>
     */

    public static void receiveInputAndPrint() {
        Scanner in = new Scanner(System.in);
        List<Task> tasks = StorageSystem.loadList(); // load saved tasks
        String line;

        while (true) {
            line = in.nextLine();

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");

            try {
                identifyCommand(parts, tasks);
            } catch (DornException e) {
                OutputHandler.printError(e.getMessage());
            } catch (Exception e) {
                OutputHandler.printError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Entry point for the Dorn application.
     * Calls {@link #initalisation()} to display the welcome screen, then
     * delegates to {@link #receiveInputAndPrint()} to begin the input loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        initialisation();
        receiveInputAndPrint();
    }
}