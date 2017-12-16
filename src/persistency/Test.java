package persistency;


import Domain.Component;
import Domain.Locomotive;
import Domain.Train;
import Domain.Wagon;

import java.util.ArrayList;

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

        ArrayList<Component> components = new ArrayList<Component>();
        Train train = new Train(1,components,"geert");
       System.out.println(wtdao.getWagonFromTrain(train));
        }
    }

