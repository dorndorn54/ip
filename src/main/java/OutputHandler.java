import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutputHandler {

    public static void printBye(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void printTaskList(List<Task> tasks){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void printMark(Task task){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + task);
        System.out.println("\t____________________________________________________________");
    }

    public static void printUnmark(Task task){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + task);
        System.out.println("\t____________________________________________________________");
    }

    public static void printAdded(Task task, int number){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t   " + task);
        System.out.println("\tNow you have " + number + " tasks in the list");
        System.out.println("\t____________________________________________________________");
    }
}

