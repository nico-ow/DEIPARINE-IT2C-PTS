
package bsit2c.deiparine.prs;

import java.util.Scanner;


public class Parking_Space {
    public void SpaceRec(){
        String response;
        
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        do{
        System.out.println("-------------------------------------");
        System.out.println("PARKING SPACE PANEL");
        System.out.println("-------------------------------------");
        System.out.println("(1)ADD PARK SPACE");
        System.out.println("(2)VIEW PARK SPACE");
        System.out.println("(3)UPDATE PARK SPACE");
        System.out.println("(4)DELETE PARK SPACE");
        System.out.println("(5)EXIT");
        System.out.println("-------------------------------------");
        
        System.out.println("Enter Action:");
        int act = sc.nextInt();
        Parking_Space cs = new Parking_Space();
        switch(act){
            
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
     public void addSpace(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Space Number: ");
        String snumber = sc.next();
        System.out.println("Space Type: ");
        String stype = sc.next();
        System.out.println("Availability Status: ");
        String status = sc.next();
        System.out.println("Location:" );
        String location = sc.next();
        
        
        String qry = "INSERT INTO tbl_space(s_no,s_type,s_status,s_location)VALUES(?,?,?,?)";
        Config conf = new Config();
        conf.addRecord(qry,snumber,stype,status,location);
        
        
    }
    
    public void viewSpace(){
        
        String qry = "SELECT*FROM tbl_space";
        String[] hdrs = {"ID","SPACE NUMBER","TYPE","STATUS","LOCATION"};
        String[] clms = {"s_id","s_no","s_type","s_status","s_location"};
        Config conf = new Config();
        conf.viewRecords(qry, clms, clms);
        
        
        
    }
    
    public void updateSpace(){
        
        Scanner sc = new Scanner (System.in);
        Config conf = new Config();
        System.out.println("Enter ID To Update:");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT s_id FROM tbl_space WHERE s_id=?",id)==0){
            System.out.println("Selected ID Doesn't Exist!");
            System.out.println("Select ID Again: ");
            id = sc.nextInt();
        }
        
        System.out.println("Space Number: ");
        String snumber = sc.next();
        System.out.println("Space Type: ");
        String stype = sc.next();
        System.out.println("Availability Status: ");
        String status = sc.next();
        System.out.println("Location:" );
        String location = sc.next();
        
        
        
        
       
        
        String qry = "UPDATE tbl_space SET s_no=?, s_type=?, s_status=?, s_location=? WHERE s_id = ?";
        
        conf.updateRecord(qry,snumber,stype,status,location,id);
    }
    
    public void deleteSpace(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID To Delete: ");
        Config conf = new Config();
        int id = sc.nextInt();
        
         while(conf.getSingleValue("SELECT s_id FROM tbl_space WHERE s_id=?",id)==0){
            System.out.println("Selected ID Doesn't Exist!");
            System.out.println("Select ID Again: ");
            id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_space WHERE s_id=?";
        
        conf.deleteRecord(qry,id);
    }
    
}
