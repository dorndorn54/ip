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

    public static void receiveInputAndPrint(){
        String line;
        Scanner in = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();

        while(true) {
            line = in.nextLine();

            if(line.equals("bye")){
                System.out.println("\t____________________________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            }
            if(line.equals("list")){
                System.out.println("\t____________________________________________________________");
                for(int i = 0; i < inputs.toArray().length; i++){
                    System.out.println("\t"+ i +". " + inputs.get(i));
                }
                System.out.println("\t____________________________________________________________");
            }
            else {
                inputs.add(line); // store the input dynamically
                System.out.println("\t____________________________________________________________");
                System.out.println("\t" + line);
                System.out.println("\t____________________________________________________________");
            }
        }

        in.close(); // close scanner
    }

    public static void main(String[] args) {
        initalisation();

        receiveInputAndPrint();
    }
}
