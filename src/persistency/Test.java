package persistency;


import Domain.Component;
import Domain.Locomotive;
import Domain.Train;
import Domain.Wagon;

/**
 * Created by patrick on 12/7/2017.
 */
public class Test {

    public static void main(String[] args) {

        BaseDAO bdao = new BaseDAO();
        TrainDAO tdao = new TrainDAO();
        LocomotiveDAO ldao = new LocomotiveDAO();
        WagonTypeDAO wdao = new WagonTypeDAO();
        WagonTrainDAO wtdao = new WagonTrainDAO();


        System.out.println(tdao.findAllTrains());
        Train a =tdao.findTrain(2);
        System.out.println(a.getComponents());
        Train train = new Train("name");

       //System.out.println(tdao.createTrain(train));
        Wagon wagon = new Wagon("wagon2",64);
        wdao.addWagonType(wagon);
        wagon = wdao.selectWagon(1);
       System.out.println(wdao.selectAllWagonTypes());


       wtdao.deleteWagonTrain(a,wagon);
        }
    }

