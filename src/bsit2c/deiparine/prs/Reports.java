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
            Config conf = new Config();

            switch (action) {
                case 1:
                    generateIndividualReport(sc, conf);  
                    break;

                case 2:
                    generateGeneralReport(conf); 
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

    // SQL query for individual report
    String qry = "SELECT t_id, v_owner, v_plate, t_duration, t_cash, t_date, t_status " +
                 "FROM tbl_transactions " +
                 "LEFT JOIN tbl_vehicle ON tbl_vehicle.v_id = tbl_transactions.v_id " +
                 "WHERE t_id = ?";

    // Column headers for the individual transaction report
    String[] clms = {"t_id", "v_owner", "v_plate", "t_duration", "t_cash", "t_date", "t_status"};
    String[] hdrs = {"TRANSACTION ID", "OWNER", "VEHICLE", "DURATION", "CASH RECEIVED", "DATE", "STATUS"};

    // Correctly bind the transactionId to the query before calling viewRecords
    conf.viewRecords(qry, hdrs, clms, transactionId); 
}

    private void generateGeneralReport(Config conf) {
        System.out.println("Generating General Report (All Transactions):");

        // SQL query for general report
        String qry = "SELECT t_id, v_owner, v_plate, t_duration, t_cash, t_date, t_status " +
                     "FROM tbl_transactions " +
                     "LEFT JOIN tbl_vehicle ON tbl_vehicle.v_id = tbl_transactions.v_id";

        // Column headers for the general report
        String[] hdrs = {"TRANSACTION ID", "OWNER", "VEHICLE", "DURATION", "CASH RECEIVED", "DATE", "STATUS"};
        String[] clms = {"t_id", "v_owner", "v_plate", "t_duration", "t_cash", "t_date", "t_status"};

        // Calling viewRecords to display the result of the query
        conf.viewRecords(qry, hdrs, clms);  
    }
}