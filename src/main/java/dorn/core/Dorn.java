package dorn.core;

import dorn.tasks.Task;
import dorn.parser.OutputHandler;
import dorn.parser.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static dorn.parser.CommandHandler.identifyCommand;

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
            } catch(DornException e){
                OutputHandler.printError(e.getMessage());
            } catch(Exception e){
                OutputHandler.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        initalisation();

        receiveInputAndPrint();
    }
}
