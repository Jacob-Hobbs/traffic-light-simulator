package traffic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;

    public Menu () {
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("Welcome to the traffic management system!");

        System.out.print("Input the number of roads: ");
        validateForPositiveInt();

        System.out.print("Input the interval: ");
        validateForPositiveInt();

        while(true) {

            // FIXME : clear terminal not resolving test failure.
            clearTerminal();

            printMainMenu();
            String input = scanner.next();

            System.out.println(input.equals("1") ? "Road added" : input.equals("2") ? "Road deleted"
                    : input.equals("3") ? "System opened" : input.equals("0") ? "Bye!"
                    : "Incorrect option feedback.");

            if (input.equals("0")) {
                break;
            }
        }
    }

    // Outputs main menu of program
    public void printMainMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Add road");
        System.out.println("2. Delete road");
        System.out.println("3. Open System");
        System.out.println("0. Quit");
    }

    // Validates input is a valid, positive integer
    public void validateForPositiveInt() {
        while (true) {
            if (scanner.hasNextInt()) {
                int numOfRoads = Integer.valueOf(scanner.next());
                if (numOfRoads > 0) {
                    break;
                } else {
                    System.out.print("Error! Incorrect Input. Try again: ");
                }
            } else {
                /* scanner.next() is used to consume the invalid input,
                thus preventing an endless loop where only the invalid
                input is considered.
                * */
                scanner.next();
                System.out.print("Error! Incorrect Input. Try again: ");
            }
        }
    }

    // Clears current contents within terminal
    public void clearTerminal() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException e) {}
    }
}
