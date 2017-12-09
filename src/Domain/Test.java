package Domain;

import java.util.ArrayList;

/**
 * Created by patrick on 12/7/2017.
 */
public class Test {

     public static void main(String[] args) {
        Component loco = new Locomotive("thomas de stoomtrein",1);
        Component wagon = new Wagon ("waggon",2,0);
         ArrayList<Component> components = new ArrayList<Component>();
        System.out.println(loco);
        System.out.println(wagon);
        Train train1 = new Train(1,components);
        System.out.println(train1);
        train1.addComponent(loco);
        train1.addComponent(wagon);
        train1.addComponent(wagon);
         System.out.println(train1);
        System.out.println(train1.calculateSeats());

    }
}
