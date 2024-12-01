
package bsit2c.deiparine.prs;
import java.util.Scanner;

public class BSIT2CDEIPARINEPRS {

   
    public static void main(String[] args) {
        
       String response;
       
       Scanner sc = new Scanner(System.in);
       boolean exit = true;
       do{
       System.out.println("-------------------------------------"); 
       System.out.println("WELCOME TO PARKING RECORDS SYSTEM!");
       System.out.println("-------------------------------------");
       System.out.println("1.VEHICLE");
       System.out.println("2.PARKING SPACE");
       System.out.println("3.PARKING_TRANSACTION");
       System.out.println("4.REPORTS");
       System.out.println("5.EXIT");
       System.out.println("-------------------------------------");
       
       System.out.println("Enter Action:");
       int act = sc.nextInt();
       
       switch(act){
           
           case 1:
               Vehicle cs = new Vehicle();
               cs.VehicleRec();
               break;
           case 2:
               Parking_Space pk = new Parking_Space();
               pk.SpaceRec();
               break;
           case 3:
               Parking_Transaction rs = new Parking_Transaction();
               rs.ParkTrans();
               break;
           case 4:
               Reports rp = new Reports();
               rp.generateReport();
               break;
           case 5:
               System.out.println("Exit Selected... type (yes) to continue:");
                response = sc.next();
                if(response.equalsIgnoreCase("yes")){
                  exit = false;
                }
               break;
           default:
               System.out.println("Invalid Action!");
            }          
       
        }while(exit);
    }   
}
