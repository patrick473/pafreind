package Interface;

import java.util.*;

/**
 * Created by patrick on 12/7/2017.
 */
public class ConsoleInterface {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        while ( !"stop".equals( s)) {

            String[] splitcheck = s.split(" ");
            try {
                if (s.startsWith("new train ")) {
                    System.out.println(s.substring(10));

                } else if (s.startsWith("new wagon ") && "numseats".equals(splitcheck[3])) {
                    System.out.println("avef");
                    System.out.println(splitcheck[4]);
                    // new wagon type with specified amount of seats
                } else if (s.startsWith("new wagon ")) {
                    System.out.println(s.substring(10));
                    // new wagon type with standard amount of seats using controller
                } else if (s.startsWith("add ")&& "to".equals(splitcheck[2])) {
                    System.out.println(s.substring(4));
                    System.out.println(splitcheck[3]);
                    // adding existing wagon to existing train
                } else if (s.startsWith("getnumseats train ")) {
                    System.out.println(s.substring(18));
                    // get number of seats of existing train
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
