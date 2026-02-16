package dorn.core;

import dorn.tasks.Task;
import dorn.parser.OutputHandler;
import dorn.parser.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dorn {
    public static List<Task> tasks = null;

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

    public static void receiveInputAndPrint() throws DornException {
        Scanner in = new Scanner(System.in);
        String line;

        while (true) {
            line = in.nextLine();

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(" ");

            try {
                CommandHandler.identifyCommand(parts, tasks);
                StorageSystem.saveList(tasks);
            } catch(DornException e){
                OutputHandler.printError(e.getMessage());
            } catch(Exception e){
                OutputHandler.printError("An unexpected error has occured");
            }
        }
    }

    public static void main(String[] args) throws DornException {
        initalisation();

        tasks = StorageSystem.loadList();

        receiveInputAndPrint();
    }
}
