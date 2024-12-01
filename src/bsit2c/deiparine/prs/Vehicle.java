
package bsit2c.deiparine.prs;

import java.util.Scanner;


public class Vehicle {
    public void VehicleRec(){
        String response;
        
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        do{
        System.out.println("-------------------------------------");
        System.out.println("VEHICLE PANEL");
        System.out.println("-------------------------------------");
        System.out.println("(1)ADD VEHICLE");
        System.out.println("(2)VIEW VEHICLE");
        System.out.println("(3)UPDATE VEHICLE");
        System.out.println("(4)DELETE VEHICLE");
        System.out.println("(5)EXIT");
        System.out.println("-------------------------------------");
        
        System.out.println("Enter Action:");
        int act = sc.nextInt();
        Vehicle cs = new Vehicle();
        switch(act){
            
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
                System.out.println("Exiting Enter (yes) to continue:");
                response = sc.next();
                if(response.equalsIgnoreCase("yes")){
                  exit = false;  
                }
                break;
                
            default:
                System.out.println("Invalid Action!");                
            }      
            System.out.println("Do you want to continue?(yes/no):");
            response = sc.next();
            
        } while(response.equalsIgnoreCase("yes"));   
    }
    
    public void addVehicle(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("License Plate: ");
        String plate = sc.next();
        System.out.print("Vehicle Type: ");
        String type = sc.next();
        System.out.print("Owner Name: ");
        String owner = sc.next();
        System.out.print("Owner Contact Information: ");
        String contact = sc.next();
        
        String qry = "INSERT INTO tbl_vehicle(v_plate,v_type,v_owner,v_contact)VALUES(?,?,?,?)";
        Config conf = new Config();
        conf.addRecord(qry,plate,type,owner,contact);
        
        
    }
    
    public  void viewVehicle(){
        
        String qry = "SELECT*FROM tbl_vehicle";
        String[] hdrs = {"ID","PLATE","TYPE","OWNER","CONTACT NO"};
        String[] clms = {"v_id","v_plate","v_type","v_owner","v_contact"};
        Config conf = new Config();
        conf.viewRecords(qry, clms, clms);
        
        
        
    }
    
    private void updateVehicle(){
        
        Scanner sc = new Scanner (System.in);
        Config conf = new Config();
        System.out.println("Enter ID To Update:");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_vehicle WHERE v_id=?",id)==0){
            System.out.println("Selected ID Doesn't Exist!");
            System.out.println("Select ID Again: ");
            id = sc.nextInt();
        }
        
        
        System.out.println(" New Plate:");
        String plate = sc.next();
        System.out.println("New Type: ");
        String type = sc.next();
        System.out.println("New Owner:");
        String owner = sc.next();
        System.out.println("New Contact:");
        String contact = sc.next();
        
        String qry = "UPDATE tbl_vehicle SET v_plate=?, v_type=?, v_owner=?, v_contact=? WHERE v_id = ?";
        
        conf.updateRecord(qry,plate,type,owner,contact,id);
    }
    
    public void deleteVehicle(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID To Delete: ");
        Config conf = new Config();
        int id = sc.nextInt();
        
         while(conf.getSingleValue("SELECT v_id FROM tbl_vehicle WHERE v_id=?",id)==0){
            System.out.println("Selected ID Doesn't Exist!");
            System.out.println("Select ID Again: ");
            id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_vehicle WHERE v_id=?";
        
        conf.deleteRecord(qry,id);
    } 
}
