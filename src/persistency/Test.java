package persistency;


import Domain.Component;
import Domain.Locomotive;
import Domain.Train;

/**
 * Created by patrick on 12/7/2017.
 */
public class Test {

    public static void main(String[] args) {

        BaseDAO bdao = new BaseDAO();
        TrainDAO tdao = new TrainDAO();
        LocomotiveDAO ldao = new LocomotiveDAO();


        System.out.println(tdao.findAllTrains());
        Train a =tdao.findTrain(2);
        System.out.println(a.getComponents());
        Train train = new Train("name");

        System.out.println(tdao.createTrain(train));
        }
    }

