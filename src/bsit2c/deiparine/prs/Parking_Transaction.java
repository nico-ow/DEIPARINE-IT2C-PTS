package bsit2c.deiparine.prs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parking_Transaction {
    public void ParkTrans() {
        String response;
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        
        do {
            System.out.println("-------------------------------------");
            System.out.println("TRANSACTIONS PANEL");
            System.out.println("-------------------------------------");
            System.out.println("(1)ADD RECORD");
            System.out.println("(2)VIEW RECORD");
            System.out.println("(3)UPDATE RECORD");
            System.out.println("(4)DELETE RECORD");
            System.out.println("(5)EXIT");
            System.out.println("-------------------------------------");
            
            System.out.println("Enter Action (1-5):");
            int act = 0;
            boolean validAction = false;
            
           
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
                    sc.next();  
                }
            }
            
            Parking_Transaction ts = new Parking_Transaction();
            switch(act) {
                case 1:
                    ts.addTransaction();
                    break;
                
                case 2:
                    ts.viewTransaction();
                    break;
                
                case 3:
                    ts.viewTransaction();
                    ts.updateTransaction();
                    ts.viewTransaction();
                    break;
                
                case 4:
                    ts.viewTransaction();
                    ts.deleteTransaction();
                    ts.viewTransaction();
                    break;
                
                case 5:
                    System.out.println("Exiting. Enter (yes) to continue:");
                    response = sc.next();
                    if (response.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
                
                default:
                    System.out.println("Invalid Action!");
                    break;
            }
            System.out.println("Do you want to continue? (yes/no):");
            response = sc.next();
            
        } while (response.equalsIgnoreCase("yes"));
    }
    
    private void addTransaction() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        Vehicle cs = new Vehicle();
        cs.viewVehicle();
        
        
        System.out.println("Enter The Vehicle ID:");
        int vid = sc.nextInt();
        
        String csql = "SELECT v_id FROM tbl_vehicle WHERE v_id=?";
        while (conf.getSingleValue(csql, vid) == 0) {
            System.out.println("Vehicle ID does not exist. Enter again:");
            vid = sc.nextInt();
        }

        Parking_Space pk = new Parking_Space();
        pk.viewSpace();
        
       
        System.out.print("Enter the ID of the Parking Space: ");
        int sid = sc.nextInt();
        
        String psql = "SELECT s_id FROM tbl_space WHERE s_id=?";
        while (conf.getSingleValue(psql, sid) == 0) {
            System.out.println("Space ID does not exist. Enter again:");
            sid = sc.nextInt();
        }
        
        
        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            System.out.print("Hourly price: ");
            price = sc.nextDouble();
            if (price > 0) {
                validPrice = true;
            } else {
                System.out.println("Invalid price! Please enter a positive number.");
            }
        }
        
       
        double duration = 0;
        boolean validDuration = false;
        while (!validDuration) {
            System.out.print("Number of hours parked: ");
            duration = sc.nextDouble();
            if (duration > 0) {
                validDuration = true;
            } else {
                System.out.println("Invalid duration! Please enter a positive number.");
            }
        }
        
        double due = duration * price;
        System.out.println("-----------------------------");
        System.out.println("Total Due: " + due);
        System.out.println("-----------------------------");
        
        
        double cashr = 0;
        boolean validCash = false;
        while (!validCash) {
            System.out.print("Cash Received: ");
            cashr = sc.nextDouble();
            if (cashr >= due) {
                validCash = true;
            } else {
                System.out.println("Invalid amount! Please enter an amount greater than or equal to the total due.");
            }
        }
        
       
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        
        String status = "Pending";
        
        
        String qry = "INSERT INTO tbl_transactions (v_id, s_id, t_duration, t_total, t_cash, t_date, t_status) VALUES (?,?,?,?,?,?,?)";
        conf.addRecord(qry, vid, sid, duration, due, cashr, date, status);
    }  
    
    public void viewTransaction() {
        String qry = "SELECT t_id, v_owner, v_plate, t_duration, t_cash, t_date, t_status FROM tbl_transactions " 
                     + "LEFT JOIN tbl_vehicle ON tbl_vehicle.v_id = tbl_transactions.v_id";
        String[] hdrs = {"TRANSACTION ID", "OWNER", "VEHICLE", "DURATION", "CASH RECEIVED", "DATE", "STATUS"};
        String[] clms = {"t_id", "v_owner", "v_plate", "t_duration", "t_cash", "t_date", "t_status"};
        Config conf = new Config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updateTransaction() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        
       
        System.out.println("Enter Transaction ID to Update:");
        int id = sc.nextInt();
        
        while (conf.getSingleValue("SELECT t_id FROM tbl_transactions WHERE t_id=?", id) == 0) {
            System.out.println("Selected ID doesn't exist! Please select an existing ID:");
            id = sc.nextInt();
        }
        
        
        System.out.println("New Duration (hours):");
        double duration = sc.nextDouble();
        while (duration <= 0) {
            System.out.println("Invalid duration! Please enter a positive number:");
            duration = sc.nextDouble();
        }
        
        
        System.out.println("New Cash Received:");
        double cash = sc.nextDouble();
        
        
        System.out.println("New Status (Pending/Completed):");
        String status = sc.next();
        while (!(status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Completed"))) {
            System.out.println("Invalid status! Please enter 'Pending' or 'Completed':");
            status = sc.next();
        }
        
       
        String qry = "UPDATE tbl_transactions SET t_duration=?, t_cash=?, t_status=? WHERE t_id=?";
        conf.updateRecord(qry, duration, cash, status, id);
    }  
    
    public void deleteTransaction() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Transaction ID to Delete: ");
        Config conf = new Config();
        
        int id = sc.nextInt();
        
        
        while (conf.getSingleValue("SELECT t_id FROM tbl_transactions WHERE t_id=?", id) == 0) {
            System.out.println("Selected ID doesn't exist! Please select an existing ID:");
            id = sc.nextInt();
        }
        
        
        String qry = "DELETE FROM tbl_transactions WHERE t_id=?";
        conf.deleteRecord(qry, id);
    }
}