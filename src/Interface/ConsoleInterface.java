package Interface;

import java.util.*;

import Domain.Train;
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
        while (!"stop".equals(s)) {

            String[] splitcheck = s.split(" ");
            try {
                if (s.startsWith("new train ")) {
                    Train train = new Train(splitcheck[2]);

                    tdao.createTrain(train,splitcheck[3]);

                } else if (s.startsWith("new wagon ") && splitcheck.length > 3) {
                    if ("numseats".equals(splitcheck[3])) {
                        int seats = Integer.parseInt(splitcheck[4]);
                        Wagon wagon = new Wagon(splitcheck[2], seats);
                        wdao.addWagonType(wagon);
                    }
                } else if (s.startsWith("new wagon ")) {

                    Wagon wagon = new Wagon(splitcheck[2], 20);
                    wdao.addWagonType(wagon);
                } else if (s.startsWith("add wagon ") && "to".equals(splitcheck[3])) {
                    // adding existing wagon to existing train
                    int trainsrch = Integer.parseInt(splitcheck[4]);
                    int wagonsrch = Integer.parseInt(splitcheck[2]);
                    wtdao.addWagonTrain(tdao.findTrain(trainsrch), wdao.findWagon(wagonsrch));

                } else if (s.startsWith("getnumseats train ")) {
                    // get number of seats of existing train
                    int seatcount = Integer.parseInt(splitcheck[2]);
                    System.out.println(tdao.getTrainSeats(seatcount));

                } else if (s.startsWith("getnumseats wagon ")) {
                    //get number of seats of existing wagon
                    int wagonseats = Integer.parseInt(splitcheck[2]);
                    System.out.println(wdao.getWagonSeats(wagonseats));

                } else if (s.startsWith("delete train ")) {
                    System.out.println(s.substring(13));
                    int trainID = Integer.parseInt(splitcheck[2]);
                    tdao.deleteTrain(trainID);


                } else if ("remove".equals(splitcheck[0]) && "from".equals(splitcheck[2])) {
                   int wagondeletesrch = Integer.parseInt(splitcheck[1]);
                   int traindeletesrch = Integer.parseInt(splitcheck[3]);
                   Train train = tdao.findTrain(traindeletesrch);
                   Wagon wagon = wdao.findWagon(wagondeletesrch);
                    wtdao.deleteWagonTrain(train,wagon);
                    // remove wagon from train

                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                aioobe.printStackTrace();
            }
            s = sc.nextLine();

        }
        sc.close();
    }
}
