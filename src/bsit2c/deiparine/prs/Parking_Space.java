package bsit2c.deiparine.prs;

import java.util.Scanner;

public class Parking_Space {
    
    public void SpaceRec() {
        String response;
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        
        do {
        System.out.println("=====================================");
        System.out.println("      PARKING SPACE MANAGEMENT      ");
        System.out.println("=====================================");

       
        System.out.println("  1) Add Park Space");
        System.out.println("  2) View Park Space");
        System.out.println("  3) Update Park Space");
        System.out.println("  4) Delete Park Space");
        System.out.println("  5) Exit");
        
        
       
        System.out.println("=====================================");
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
            
            Parking_Space cs = new Parking_Space();
            switch (act) {
                case 1:
                    cs.addSpace();
                    cs.viewSpace();
                    break;
                
                case 2:
                    cs.viewSpace();
                    break;
                
                case 3:
                    cs.viewSpace();
                    cs.updateSpace();
                    cs.viewSpace();
                    break;
                
                case 4:
                    
                    cs.viewSpace();
                    cs.deleteSpace();
                    cs.viewSpace();
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
    
    public void addSpace() {
        Scanner sc = new Scanner(System.in);
        
        System.out.printf("Space Number: ");
        String snumber = sc.next();
        
       
        while (snumber.isEmpty()) {
            System.out.println("Space number cannot be empty! Please enter a valid space number:");
            snumber = sc.next();
        }
        
        System.out.printf("Space Type: ");
        String stype = sc.next();
        
        
        while (stype.isEmpty()) {
            System.out.println("Space type cannot be empty! Please enter a valid space type:");
            stype = sc.next();
        }
        
        System.out.println("Availability Status (Available/Unavailable): ");
        String status = sc.next();
        
        
        while (!(status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Unavailable"))) {
            System.out.println("Invalid status! Please enter 'Available' or 'Unavailable':");
            status = sc.next();
        }
        
        System.out.printf("Location: ");
        String location = sc.next();
        
        
        while (location.isEmpty()) {
            System.out.println("Location cannot be empty! Please enter a valid location:");
            location = sc.next();
        }
        
        String qry = "INSERT INTO tbl_space(s_no,s_type,s_status,s_location)VALUES(?,?,?,?)";
        Config conf = new Config();
        conf.addRecord(qry, snumber, stype, status, location);
    }
    
    public void viewSpace() {
        String qry = "SELECT * FROM tbl_space";
        String[] hdrs = {"ID", "SPACE NUMBER", "TYPE", "STATUS", "LOCATION"};
        String[] clms = {"s_id", "s_no", "s_type", "s_status", "s_location"};
        Config conf = new Config();
        conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updateSpace() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();
        
        System.out.println("Enter ID To Update:");
        int id = sc.nextInt();
        
        
        while (conf.getSingleValue("SELECT s_id FROM tbl_space WHERE s_id=?", id) == 0) {
            System.out.println("Selected ID doesn't exist! Please select an existing ID:");
            id = sc.nextInt();
        }
        
        System.out.println("Space Number: ");
        String snumber = sc.next();
        
        
        while (snumber.isEmpty()) {
            System.out.println("Space number cannot be empty! Please enter a valid space number:");
            snumber = sc.next();
        }
        
        System.out.println("Space Type: ");
        String stype = sc.next();
        
       
        while (stype.isEmpty()) {
            System.out.println("Space type cannot be empty! Please enter a valid space type:");
            stype = sc.next();
        }
        
        System.out.println("Availability Status (Available/Unavailable): ");
        String status = sc.next();
        
        
        while (!(status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("Unavailable"))) {
            System.out.println("Invalid status! Please enter 'Available' or 'Unavailable':");
            status = sc.next();
        }
        
        System.out.println("Location: ");
        String location = sc.next();
        
       
        while (location.isEmpty()) {
            System.out.println("Location cannot be empty! Please enter a valid location:");
            location = sc.next();
        }
        
        String qry = "UPDATE tbl_space SET s_no=?, s_type=?, s_status=?, s_location=? WHERE s_id=?";
        conf.updateRecord(qry, snumber, stype, status, location, id);
    }
    
    public void deleteSpace() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID To Delete: ");
        Config conf = new Config();
        
        int id = sc.nextInt();
        
       
        while (conf.getSingleValue("SELECT s_id FROM tbl_space WHERE s_id=?", id) == 0) {
            System.out.println("Selected ID doesn't exist! Please select an existing ID:");
            id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_space WHERE s_id=?";
        conf.deleteRecord(qry, id);
    }
}