package bsit2c.deiparine.prs;

import java.util.Scanner;

public class Vehicle {
    public void VehicleRec() {
        String response;
        Scanner sc = new Scanner(System.in);
        boolean exit = true;

        do {
        System.out.println("=====================================");
        System.out.println("        VEHICLE MANAGEMENT PANEL     ");
        System.out.println("=====================================");

        
        System.out.println("  1) Add Vehicle");
        System.out.println("  2) View Vehicle");
        System.out.println("  3) Update Vehicle");
        System.out.println("  4) Delete Vehicle");
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

            Vehicle cs = new Vehicle();
            switch (act) {
                case 1:
                    cs.addVehicle();
                    cs.viewVehicle();
                    break;

                case 2:
                    cs.viewVehicle();
                    break;

                case 3:
                    cs.viewVehicle();
                    cs.updateVehicle();
                    cs.viewVehicle();
                    break;

                case 4:
                    cs.viewVehicle();
                    cs.deleteVehicle();
                    cs.viewVehicle();
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

    public void addVehicle() {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("License Plate: ");
        String plate = sc.next();
        while (plate.isEmpty()) {
            System.out.println("License plate cannot be empty. Please enter again:");
            plate = sc.next();
        }

        
        System.out.print("Vehicle Type: ");
        String type = sc.next();
        while (type.isEmpty()) {
            System.out.println("Vehicle type cannot be empty. Please enter again:");
            type = sc.next();
        }

       
        System.out.print("Owner Name: ");
        String owner = sc.next();
        while (owner.isEmpty()) {
            System.out.println("Owner name cannot be empty. Please enter again:");
            owner = sc.next();
        }

        
        System.out.print("Owner Contact Information: ");
        String contact = sc.next();
        while (contact.isEmpty()) {
            System.out.println("Contact information cannot be empty. Please enter again:");
            contact = sc.next();
        }

        
        String qry = "INSERT INTO tbl_vehicle(v_plate, v_type, v_owner, v_contact) VALUES(?,?,?,?)";
        Config conf = new Config();
        conf.addRecord(qry, plate, type, owner, contact);
    }

    public void viewVehicle() {
        String qry = "SELECT * FROM tbl_vehicle";
        String[] hdrs = {"ID", "PLATE", "TYPE", "OWNER", "CONTACT NO"};
        String[] clms = {"v_id", "v_plate", "v_type", "v_owner", "v_contact"};
        Config conf = new Config();
        conf.viewRecords(qry, hdrs, clms);
    }

    private void updateVehicle() {
        Scanner sc = new Scanner(System.in);
        Config conf = new Config();

        
        System.out.println("Enter Vehicle ID to Update:");
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT v_id FROM tbl_vehicle WHERE v_id=?", id) == 0) {
            System.out.println("Selected ID doesn't exist! Please select an existing ID:");
            id = sc.nextInt();
        }

        
        System.out.println("New Plate:");
        String plate = sc.next();
        while (plate.isEmpty()) {
            System.out.println("License plate cannot be empty. Please enter again:");
            plate = sc.next();
        }

        
        System.out.println("New Type:");
        String type = sc.next();
        while (type.isEmpty()) {
            System.out.println("Vehicle type cannot be empty. Please enter again:");
            type = sc.next();
        }

        
        System.out.println("New Owner:");
        String owner = sc.next();
        while (owner.isEmpty()) {
            System.out.println("Owner name cannot be empty. Please enter again:");
            owner = sc.next();
        }

       
        System.out.println("New Contact:");
        String contact = sc.next();
        while (contact.isEmpty()) {
            System.out.println("Contact information cannot be empty. Please enter again:");
            contact = sc.next();
        }

        
        String qry = "UPDATE tbl_vehicle SET v_plate=?, v_type=?, v_owner=?, v_contact=? WHERE v_id=?";
        conf.updateRecord(qry, plate, type, owner, contact, id);
    }

    public void deleteVehicle() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Vehicle ID to Delete: ");
        Config conf = new Config();

        int id = sc.nextInt();

        
        while (conf.getSingleValue("SELECT v_id FROM tbl_vehicle WHERE v_id=?", id) == 0) {
            System.out.println("Selected ID doesn't exist! Please select an existing ID:");
            id = sc.nextInt();
        }

       
        String qry = "DELETE FROM tbl_vehicle WHERE v_id=?";
        conf.deleteRecord(qry, id);
    }
}