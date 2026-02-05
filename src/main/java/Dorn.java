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
            String[] parts = line.split(" ");
            String command = parts[0];

            switch(command) {
                case "bye":
                    OutputHandler.printBye();
                    return;
                case "list":
                    OutputHandler.printTaskList(tasks);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(parts[1]) - 1;
                    tasks.get(markIndex).markDone();
                    OutputHandler.printMark(tasks.get(markIndex));
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                    tasks.get(unmarkIndex).markUndone();
                    OutputHandler.printUnmark(tasks.get(unmarkIndex));
                    break;
                case "todo":
                    tasks.add(new ToDos(parser.parseDescription(parts)));
                    OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
                    break;
                case "deadline":
                    tasks.add(new Deadlines(parser.parseDescription(parts), parser.endDate(parts)));
                    OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
                    break;
                case "event":
                    tasks.add(new Events(parser.parseDescription(parts), parser.startDate(parts), parser.endDate(parts)));
                    OutputHandler.printAdded(tasks.get(tasks.size() - 1), tasks.size());
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        initalisation();

        receiveInputAndPrint();
    }
}
