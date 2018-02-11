package controller;

import Domain.Train;
import persistency.TrainDAO;

public class TrainController {

    TrainDAO tdao = new TrainDAO();
    public Train findTrain(int id){

        return tdao.findTrain(id);
    }

    public void deleteTrain(int id){

        tdao.deleteTrain(id);
    }
    public void createTrain(String trainname,String loconame){

        tdao.createTrain(trainname,loconame);
    }
}
