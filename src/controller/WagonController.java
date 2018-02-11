package controller;

import Domain.Wagon;
import persistency.WagonTypeDAO;

import static Domain.Wagon.WagonBuilder.aWagon;
public class WagonController {

    WagonTypeDAO wtdao = new WagonTypeDAO();
    public Wagon createWagon(String name,int amountofseats){


        wtdao.addWagonType(name,amountofseats);
        Wagon wagon = wtdao.findLatestWagon();
        return wagon;
    }

    public Wagon findWagon(int id){

        return wtdao.findWagon(id);
    }
}
