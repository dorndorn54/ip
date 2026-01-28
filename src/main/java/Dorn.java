import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

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

            if (line.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            }

            if (line.equals("list")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println("\t____________________________________________________________");
            } else if (line.startsWith("mark ")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks.get(index).markDone();
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t   " + tasks.get(index));
                System.out.println("\t____________________________________________________________");
            } else if (line.startsWith("unmark ")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                tasks.get(index).markUndone();
                System.out.println("\t____________________________________________________________");
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t   " + tasks.get(index));
                System.out.println("\t____________________________________________________________");
            } else {
                tasks.add(new Task(line));
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Added: " + line);
                System.out.println("\t____________________________________________________________");
            }
        }

        in.close();
    }

    public static void main(String[] args) {
        initalisation();

        receiveInputAndPrint();
    }
}
