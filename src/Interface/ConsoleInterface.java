package Interface;

import java.util.*;

import Domain.Wagon;
import persistency.TrainDAO;
import persistency.WagonTrainDAO;
import persistency.WagonTypeDAO;

/**
 * Created by patrick on 12/7/2017.
 */
public class ConsoleInterface {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        WagonTypeDAO wdao = new WagonTypeDAO();
        WagonTrainDAO wtdao = new WagonTrainDAO();
        TrainDAO tdao = new TrainDAO();
        while ( !"stop".equals( s)) {

            String[] splitcheck = s.split(" ");
            try {
                if (s.startsWith("new train ")) {
                    System.out.println(s.substring(10));

                } else if (s.startsWith("new wagon ") && "numseats".equals(splitcheck[3])) {
                    int seats = Integer.parseInt(splitcheck[4]);
                    Wagon wagon = new Wagon(splitcheck[2], seats);
                    wdao.addWagonType(wagon);
                } else if (s.startsWith("new wagon ")) {
                    Wagon wagon = new Wagon(splitcheck[2], 20);
                    wdao.addWagonType(wagon);
                } else if (s.startsWith("add ")&& "to".equals(splitcheck[2])) {
                    System.out.println(s.substring(4));
                    System.out.println(splitcheck[3]);
                    // adding existing wagon to existing train
                    
                } else if (s.startsWith("getnumseats train ")) {
                    System.out.println(s.substring(18));
                    // get number of seats of existing train
                    tdao.getTrainSeats(1);
                } else if (s.startsWith("getnumseats wagon ")) {
                    System.out.println(s.substring(18));
                    //get number of seats of existing wagon
                } else if (s.startsWith("delete train ")) {
                    System.out.println(s.substring(13));
                    //delete train, if it doesn't exist dont delete
                    
                } else if ("remove".equals(splitcheck[0]) && "from".equals(splitcheck[2])) {
                    System.out.println(splitcheck[1]);
                    System.out.println(splitcheck[3]);
                    // remove wagon from train
                }
            }
            catch (ArrayIndexOutOfBoundsException aioobe){
                System.out.println("fout");
            }
            s = sc.nextLine();

        }
        sc.close();
    }
}
