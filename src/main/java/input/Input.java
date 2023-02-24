package input;

import entity.Fabricator;
import entity.Souvenir;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Class Input is designed for input.
 * Contains helper methods for simple input,
 * input of object field values and for displaying messages.
 */

public class Input {
    Scanner scanner;
    public Input() {
        scanner = new Scanner(System.in);
    }

    public Fabricator inputFabricator() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Country: ");
        String country = scanner.nextLine();
        System.out.print("Payment details: ");
        String paymentDetails = scanner.nextLine();
        return new Fabricator(name, country, paymentDetails);
    }

    public Souvenir inputSouvenir() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Payment details: ");
        String paymentDetails = scanner.nextLine();
        System.out.print("Day: ");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.print("Month: ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        LocalDate localDate = LocalDate.of(year, month, day);
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Currency: ");
        String currency = scanner.nextLine();
        return new Souvenir(name, paymentDetails, localDate, price, currency);
    }

    public String input(String some) {
        System.out.println("Enter " + some + ":");
        return scanner.nextLine();
    }

    public void showMessage(String s) {
        System.out.println(s);
    }
}
