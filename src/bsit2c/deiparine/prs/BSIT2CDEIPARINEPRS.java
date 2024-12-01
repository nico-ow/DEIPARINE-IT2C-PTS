package bsit2c.deiparine.prs;

import java.util.Scanner;

public class BSIT2CDEIPARINEPRS {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String response;

        do {
            System.out.println("-------------------------------------");
            System.out.println("WELCOME TO PARKING RECORDS SYSTEM!");
            System.out.println("-------------------------------------");
            System.out.println("1. VEHICLE");
            System.out.println("2. PARKING SPACE");
            System.out.println("3. PARKING TRANSACTION");
            System.out.println("4. REPORTS");
            System.out.println("5. EXIT");
            System.out.println("-------------------------------------");

            System.out.print("Enter Action (1-5): ");
            int act = 0;
            boolean validAction = false;

            // Input Validation for Action
            while (!validAction) {
                if (sc.hasNextInt()) {
                    act = sc.nextInt();
                    if (act >= 1 && act <= 5) {
                        validAction = true;
                    } else {
                        System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.next();  // Consume invalid input
                }
            }

            switch (act) {
                case 1:
                    Vehicle vehicle = new Vehicle();
                    vehicle.VehicleRec();
                    break;
                case 2:
                    Parking_Space parkingSpace = new Parking_Space();
                    parkingSpace.SpaceRec();
                    break;
                case 3:
                    Parking_Transaction parkingTransaction = new Parking_Transaction();
                    parkingTransaction.ParkTrans();
                    break;
                case 4:
                    Reports reports = new Reports();
                    reports.generateReport();
                    break;
                case 5:
                    // Exit Confirmation
                    System.out.print("Are you sure you want to exit? Type (yes) to confirm: ");
                    response = sc.next();
                    if (response.equalsIgnoreCase("yes")) {
                        exit = true;
                    }
                    break;
                default:
                    System.out.println("Invalid Action! Please select a valid option.");
                    break;
            }

        } while (!exit);

        System.out.println("Thank you for using the Parking Records System!");
    }
}