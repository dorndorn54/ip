package dorn.core;

import dorn.tasks.Task;
import dorn.parser.OutputHandler;
import dorn.parser.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dorn {
    public static void initalisation(){
        String logo = " ____   ___  ____  _   _ \n"
                + "|  _ \\ / _ \\|  _ \\| \\ | |\n"
                + "| | | | | | | |_) |  \\| |\n"
                + "| |_| | |_| |  _ <| |\\  |\n"
                + "|____/ \\___/|_| \\_\\_| \\_|\n";
        String name = "Dorn";

        System.out.println("Hello! I'm " + name +
                "\nWhat can I do for you?" +
                "\n\n" + logo + "\n");
    }

    public static void receiveInputAndPrint() {
        Scanner in = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String line;

        while (true) {
            line = in.nextLine();

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");
            String command = parts[0].toLowerCase();
            try {
                switch (command) {
                    case "bye":
                        OutputHandler.printBye();
                        return;
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
                    default:
                        OutputHandler.printError("I'm sorry, but I don't know what that means :-(");
                        break;
                }
            } catch(DornException e){
                OutputHandler.printError(e.getMessage());
            } catch(Exception e){
                OutputHandler.printError("An unexpected error has occured");
            }
        }
    }

    public static void main(String[] args) {
        initalisation();

        receiveInputAndPrint();
    }
}
