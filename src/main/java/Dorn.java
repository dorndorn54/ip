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

        while(true) {
            line = in.nextLine();

            if(line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println("\n" + line + "\n");
        }

        in.close(); // close scanner
    }

    public static void main(String[] args) {
        initalisation();

        receiveInputAndPrint();
    }
}
