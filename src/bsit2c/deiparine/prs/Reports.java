
package bsit2c.deiparine.prs;

import java.util.Scanner;

public class Reports {
  
    public void generateReport() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        do {
            System.out.println("-------------------------------------");
            System.out.println("REPORT PANEL");
            System.out.println("-------------------------------------");
            System.out.println("(1) Individual Transaction Report");
            System.out.println("(2) General Report (All Transactions)");
            System.out.println("(3) EXIT");
            System.out.println("-------------------------------------");

            System.out.println("Enter Action:");
            int action = sc.nextInt();
            Config conf = new Config(); // Config to interact with the database

            switch (action) {
                case 1:
                    generateIndividualReport(sc, conf);  // Pass scanner for input and conf for DB interaction
                    break;

                case 2:
                    generateGeneralReport(conf);  // Generate the general report
                    break;

                case 3:
                    System.out.println("Exiting... Enter 'yes' to continue or any other key to exit:");
                    String response = sc.next();
                    exit = !response.equalsIgnoreCase("yes");
                    break;

                default:
                    System.out.println("Invalid Action! Please try again.");
            }

            if (!exit) {
                System.out.println("Do you want to continue to report generation? (yes/no):");
                String response = sc.next();
                exit = !response.equalsIgnoreCase("yes");
            }

        } while (!exit);
    }

    private void generateIndividualReport(Scanner sc, Config conf) {
        System.out.println("Enter Transaction ID for the report:");
        int transactionId = sc.nextInt();

        // Query to get details for a specific transaction
        String qry = "SELECT t_id, v_owner, v_plate, t_duration, t_cash, t_date, t_status " +
                     "FROM tbl_transactions " +
                     "LEFT JOIN tbl_vehicle ON tbl_vehicle.v_id = tbl_transactions.v_id " +
                     "WHERE t_id = ?";

        String[] clms = {"t_id", "v_owner", "v_plate", "t_duration", "t_cash", "t_date", "t_status"};

        // Assuming you have a method that can handle a single record query in Config
        conf.viewSingleRecords(qry, clms, transactionId);  // Fetch and display a single transaction record
    }

    private void generateGeneralReport(Config conf) {
        System.out.println("Generating General Report (All Transactions):");

        // Query to get all transaction details
        String qry = "SELECT t_id, v_owner, v_plate, t_duration, t_cash, t_date, t_status " +
                     "FROM tbl_transactions " +
                     "LEFT JOIN tbl_vehicle ON tbl_vehicle.v_id = tbl_transactions.v_id";

        String[] hdrs = {"TRANSACTION ID", "OWNER", "VEHICLE", "DURATION", "CASH RECEIVED", "DATE", "STATUS"};
        String[] clms = {"t_id", "v_owner", "v_plate", "t_duration", "t_cash", "t_date", "t_status"};

        // Display all transaction records using the method in Config
        conf.viewRecords(qry, hdrs, clms);  // Fetch and display all transaction records
    }
}