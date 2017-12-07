package Interface;

import java.util.*;

/**
 * Created by patrick on 12/7/2017.
 */
public class ConsoleInterface {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while ( !"stop".equals( s)){

            String[] splitcheck = s.split(" ");
            if (s.startsWith("new train ")){
                System.out.println(s.substring(10));
                // new train using controller
            } else if (s.startsWith("new wagon ") && "numseats".equals(splitcheck[3])){
                System.out.println("avef");
                System.out.println(splitcheck[4]);
                // new wagon type with specified amount of seats
            }
            else if ( s.startsWith("new wagon ")){
                System.out.println(s.substring(10));
                // new wagon type with standard amount of seats using controller
            }
            else if ( s.startsWith("add "))
                System.out.println(s.substring(4));

            s = sc.nextLine();
        }
        sc.close();
    }
}
