package controller;

import persistency.WagonTrainDAO;

public class WagonTrainController {
    WagonTrainDAO wtdao = new WagonTrainDAO();

    public void deleteWagonTrain(int trainid, int wagonid){

        wtdao.deleteWagonTrain(trainid,wagonid);
    }
    public void createWagonTrain(int trainid, int wagonid){

        wtdao.addWagonTrain(trainid, wagonid);
    }
}
