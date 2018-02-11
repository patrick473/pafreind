package Interface;


import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import Domain.Train;
import Domain.Wagon;
import controller.LocomotiveController;
import controller.TrainController;
import controller.WagonController;
import controller.WagonTrainController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import persistency.TrainDAO;
import persistency.WagonTrainDAO;
import persistency.WagonTypeDAO;





public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        WagonController wc = new WagonController();
        TrainController tc = new TrainController();
        LocomotiveController lc = new LocomotiveController();
        WagonTrainController wtc = new WagonTrainController();

        String UrlWagon1 = "https://us.123rf.com/450wm/studioworkstock/studioworkstock1611/studioworkstock161100145/67173618-railway-wagon-isolated-on-white-background-vector-illustration-railroad-transport-design-element-sid.jpg?ver=6";
        String UrlLoco = "https://orig00.deviantart.net/96c0/f/2014/343/2/0/thomas_the_tank_engine_by_danielarkansanengine-d7aicax.png";





        //adjust location of scene components here. Watch out: they can conflict with each other.
    	Integer SceneTitleRow = 34;
    	Integer SceneTitleColumn = 0;
    	Integer TrainNameColumn = 1;
    	Integer TrainNameRow = 35;
    	Integer WagonNameColumn = 1;
    	Integer WagonNameRow = 36;
    	Integer LocomotiveNameColumn = 3;
    	Integer LocomotiveNameRow = 35;
    	Integer WagonLocoPicCol = 1;
    	Integer WagonLocoPicRow = 1;
    	
    	
    	//set title for scene
    	primaryStage.setTitle("Thomas The Train Application");
        
    	//create scene
        primaryStage.show();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT );
        grid.setHgap(4);
        grid.setVgap(2);
        //grid.setPadding(new Insets(25, 25, 25, 25)); only activate if you want some nice padding. extra THICC.
        
        //how big does the screen need to be?
        Scene scene = new Scene(grid, 1200, 850);
        primaryStage.setScene(scene);
        
        //main title of scene
        Text scenetitle = new Text("NS Not Simple");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, SceneTitleColumn, SceneTitleRow, 12, 1);
        
        //label infront of train name input
        Label TrainNameL = new Label("Trein name:");
        grid.add(TrainNameL, TrainNameColumn, TrainNameRow);

        //Input for train name
        TextField TrainNameT = new TextField();
        grid.add(TrainNameT, TrainNameColumn+1, TrainNameRow);
        
        //Button to activate the function for adding a train
        Button BtnTrain = new Button("Add Train");
        HBox HbBtnTrain = new HBox(10);
        HbBtnTrain.getChildren().add(BtnTrain);
        grid.add(HbBtnTrain, TrainNameColumn+4, TrainNameRow);
        
        //label infornt of input for locomotive name
        Label LocomotiveNameL = new Label("Locomotive name:");
        grid.add(LocomotiveNameL, LocomotiveNameColumn, LocomotiveNameRow);
        
        //Input field for locomotive name
        TextField LocomotiveNameT = new TextField();
        grid.add(LocomotiveNameT, LocomotiveNameColumn+1, LocomotiveNameRow);
        
        //label infornt of input for Wagon name
        Label WagonNameL = new Label("Wagon name:");
        grid.add(WagonNameL, WagonNameColumn, WagonNameRow);
        
        //Input for wagon name
        TextField WagonNameT = new TextField();

        grid.add(WagonNameT,WagonNameColumn+1, WagonNameRow);
        
        //label infornt of input for amount of seats
        Label WagonSeatL = new Label("Amount of seats:");
        grid.add(WagonSeatL,WagonNameColumn+2, WagonNameRow);
        
        //Input for amount of seats. ONLY NUMBERS ALLOWED
        TextField WagonSeatT = new TextField();

        grid.add(WagonSeatT,WagonNameColumn+3, WagonNameRow);
        
        //Button to activate the function for deleting a wagon
        Button BtnWagonDelete = new Button ("Delete wagon");
        HBox HbBtnWagonDelete =  new HBox(10);
        HbBtnWagonDelete.getChildren().add(BtnWagonDelete);
        grid.add(HbBtnWagonDelete, 4, 39);
        
        //Button to activate the function for adding a wagon to the selected train
        Button BtnWagon = new Button("Add Wagontype");
        HBox HbBtnWagon = new HBox(10);
        HbBtnWagon.getChildren().add(BtnWagon);
        grid.add(HbBtnWagon,WagonNameColumn+4, WagonNameRow);
        
        //Button to activate the function for deleting a Train
        Button BtnTrainDelete = new Button ("Delete Train");
        HBox HbBtnTrainDelete =  new HBox(10);
        HbBtnTrainDelete.getChildren().add(BtnTrainDelete);
        grid.add(HbBtnTrainDelete, 1, 39);

        //Button to add a wagon to a train
        Button BtnAddWagonToTrain = new Button ("Add Wagon to Train");
        HBox HbBtnAddWagonToTrain =  new HBox(10);
        HbBtnAddWagonToTrain.getChildren().add(BtnAddWagonToTrain);
        grid.add(HbBtnAddWagonToTrain, 5, 39);
        

        //Blocks any letters from getting in the WagonSeatT input field
        UnaryOperator<Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };
        
        //turn wagonseat into Integer value
        WagonSeatT.setTextFormatter(
            new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));





        //Create list view and title of the list for wagon
        ListView<String> WagonList = new ListView<String>();
        ListView<String> TrainList = new ListView<String>();
        ListView<String> WagonTypeList = new ListView<String>();
        ObservableList<String> WagonItems = FXCollections.observableArrayList(); // PUT WAGON NAMES OF SELECTED TRAIN HERE <----------------------
        ObservableList<String> TrainItems =FXCollections.observableArrayList (); //PUT TRAIN NAMES HERE <-------------------------------------
        ObservableList<String> WagonTypeItems =FXCollections.observableArrayList (); //PUT TRAIN NAMES HERE <-------------------------------------


        WagonList.setItems(WagonItems);
        WagonList.setPrefHeight(200);
        Label WagonListL = new Label("Wagons of selected train");
        WagonListL .setFont(Font.font("Tahoma", FontWeight.BOLD , 12));
        WagonList.setDisable(true);
        grid.add(WagonListL, 3, 37);
        grid.add(WagonList, 3, 38, 2, 1);

        WagonTypeList.setItems(WagonTypeItems);
        WagonTypeList.setPrefHeight(200);
        Label WagonTypeListL = new Label("Add wagon to train");
        WagonTypeListL .setFont(Font.font("Tahoma", FontWeight.BOLD , 12));

        grid.add(WagonTypeListL, 5, 37);
        grid.add(WagonTypeList, 5, 38, 2, 1);
        //Create list view and title of the list for train



        ArrayList<String> selecteditems = refreshtrainList();

        for (String i: selecteditems
                ) {
            TrainItems.add(i);
        }
        ArrayList<String> wagontypes = refreshWagonTypeList();
        for (String i: wagontypes
                ) {
            WagonTypeItems.add(i);
        }
        TrainList.setItems(TrainItems);
        TrainList.setPrefHeight(200);
        Label TrainListL = new Label("Select to add wagons or remove train");
        TrainListL.setFont(Font.font("Tahoma", FontWeight.BOLD , 12));
        grid.add(TrainListL, 1, 37);
        grid.add(TrainList, 1, 38, 2, 1);


        //create locomotive

        Image ImageLoco = new Image(UrlLoco, true);
        ImageView ImageViewLoco = new ImageView(ImageLoco);
        ImageViewLoco.setFitHeight(200);
        ImageViewLoco.setFitWidth(250);
        ImageViewLoco.setVisible(false); //<------------ Hide Locomotive if no train is selected;
        grid.add(ImageViewLoco, WagonLocoPicCol, WagonLocoPicRow);



        //create wagon
        Image ImageWagon1 = new Image(UrlWagon1, true);
        ImageView ImageViewWagon1 = new ImageView(ImageWagon1);
        ImageViewWagon1.setFitHeight(200);
        ImageViewWagon1.setFitWidth(250);
        ImageViewWagon1.setVisible(false); //<--------- hide wagon if no wagon exists.
        grid.add(ImageViewWagon1, WagonLocoPicCol+1, WagonLocoPicRow); // WagonLocoPicCol+number --> number = The number of the wagon order? (this is the first wagon so number 1)

        Image ImageWagon2 = new Image(UrlWagon1, true);
        ImageView ImageViewWagon2 = new ImageView(ImageWagon2);
        ImageViewWagon2.setFitHeight(200);
        ImageViewWagon2.setFitWidth(250);
        ImageViewWagon2.setVisible(false); //<--------- hide wagon if no wagon exists.
        grid.add(ImageViewWagon2, WagonLocoPicCol+2, WagonLocoPicRow); // WagonLocoPicCol+number --> number = The number of the wagon order? (this is the first wagon so number 1)

        Image ImageWagon3 = new Image(UrlWagon1, true);
        ImageView ImageViewWagon3 = new ImageView(ImageWagon3);
        ImageViewWagon3.setFitHeight(200);
        ImageViewWagon3.setFitWidth(250);
        ImageViewWagon3.setVisible(false); //<--------- hide wagon if no wagon exists.
        grid.add(ImageViewWagon3, WagonLocoPicCol+3, WagonLocoPicRow); // WagonLocoPicCol+number --> number = The number of the wagon order? (this is the first wagon so number 1)





        Image ImageWagon4 = new Image(UrlWagon1, true);
        ImageView ImageViewWagon4 = new ImageView(ImageWagon4);
        ImageViewWagon4.setFitHeight(200);
        ImageViewWagon4.setFitWidth(250);
        ImageViewWagon4.setVisible(false); //<--------- hide wagon if no wagon exists.
        grid.add(ImageViewWagon4, WagonLocoPicCol, WagonLocoPicRow+1); // WagonLocoPicCol+number --> number = The number of the wagon order? (this is the first wagon so number 1)

        Image ImageWagon5 = new Image(UrlWagon1, true);
        ImageView ImageViewWagon5 = new ImageView(ImageWagon5);
        ImageViewWagon5.setFitHeight(200);
        ImageViewWagon5.setFitWidth(250);
        ImageViewWagon5.setVisible(false); //<--------- hide wagon if no wagon exists.
        grid.add(ImageViewWagon5, WagonLocoPicCol+1, WagonLocoPicRow+1); // WagonLocoPicCol+number --> number = The number of the wagon order? (this is the first wagon so number 1)

        Image ImageWagon6 = new Image(UrlWagon1, true);
        ImageView ImageViewWagon6 = new ImageView(ImageWagon6);
        ImageViewWagon6.setFitHeight(200);
        ImageViewWagon6.setFitWidth(250);
        ImageViewWagon6.setVisible(false); //<--------- hide wagon if no wagon exists.
        grid.add(ImageViewWagon6, WagonLocoPicCol+2, WagonLocoPicRow+1); // WagonLocoPicCol+number --> number = The number of the wagon order? (this is the first wagon so number 1)

        Image ImageWagon7 = new Image(UrlWagon1, true);
        ImageView ImageViewWagon7 = new ImageView(ImageWagon7);
        ImageViewWagon7.setFitHeight(200);
        ImageViewWagon7.setFitWidth(250);
        ImageViewWagon7.setVisible(false); //<--------- hide wagon if no wagon exists.
        grid.add(ImageViewWagon7, WagonLocoPicCol+3, WagonLocoPicRow+1); // WagonLocoPicCol+number --> number = The number of the wagon order? (this is the first wagon so number 1)

        BtnAddWagonToTrain.setDisable(true);
        //List select listner for train


        //Button function of BtnWagon
        BtnWagon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int wagontypeseats ;
                System.out.println("ugiui");
                if (WagonSeatT.getText() == null || WagonSeatT.getText() == "0"){
                    wagontypeseats = 20;
                }
                else {
                    wagontypeseats = Integer.parseInt(WagonSeatT.getText());
                }
                Wagon WagonObject = wc.createWagon(WagonNameT.getText(), wagontypeseats);
                ArrayList<String> wtitems = refreshWagonTypeList();


                for (String i : wtitems
                        ) {
                    WagonItems.add(i);

                }
            }
        });

        //List select listner for wagon


        TrainList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            	//activate input for wagon. If not activated input will be null
            	WagonSeatT.setDisable(false);
                WagonNameT.setDisable(false);
                WagonList.setDisable(false);
                
                //test select

                if (newValue == null){
                    newValue = "1 train";
                }
                if (newValue == "1 train"){
                    BtnTrainDelete.setDisable(true);
                    ImageViewLoco.setVisible(false);
                }
                else{
                    BtnTrainDelete.setDisable(false);
                    ImageViewLoco.setVisible(true);
                }


                String[]  splitstring = newValue.split(" ", 2);

                   Train selectedTrain = tc.findTrain(Integer.parseInt(splitstring[0]));

                WagonItems.clear();
                for (String i : refreshWagonList(selectedTrain)
                        ) {
                    WagonItems.add(i);

                }
                BtnAddWagonToTrain.setDisable(true);
                Integer listSize = WagonItems.size();
                ImageViewWagon1.setVisible(false);
                ImageViewWagon2.setVisible(false);
                ImageViewWagon3.setVisible(false);
                ImageViewWagon4.setVisible(false);
                ImageViewWagon5.setVisible(false);
                ImageViewWagon6.setVisible(false);
                ImageViewWagon7.setVisible(false);

                if (listSize > 0){
                    ImageViewWagon1.setVisible(true);
                }
                if (listSize > 1){
                    ImageViewWagon2.setVisible(true);
                }
                if (listSize > 2){
                    ImageViewWagon3.setVisible(true);
                }
                if (listSize > 3){
                    ImageViewWagon4.setVisible(true);
                }
                if (listSize > 4){
                    ImageViewWagon5.setVisible(true);
                }
                if (listSize > 5){
                    ImageViewWagon6.setVisible(true);
                }
                if (listSize > 6){
                    ImageViewWagon7.setVisible(true);
                }
                    System.out.println("ListView selection TRAIN changed from oldValue = "
                            + oldValue + " to newValue = " + splitstring[0]);

                //Button function for deleting selected train
                BtnTrainDelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        tc.deleteTrain(selectedTrain.getTrainID());

                        try{TrainItems.clear();}
                        catch (Exception exc){
                                exc.printStackTrace();
                        }
                        ArrayList<String> selecteditems = refreshtrainList();

                        for (String i : selecteditems
                                ) {
                            TrainItems.add(i);

                        }
                    }



                });


                WagonList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValueWagon) {


                        if (newValueWagon == null){
                            newValueWagon = "1 Wagon";
                        }
                        if (newValueWagon == "1 Wagon"){
                            BtnWagonDelete.setDisable(true);
                        }
                        else{
                            BtnWagonDelete.setDisable(false);
                        }



                        String[] splitcheck = newValueWagon.split(" ", 2);
                        Wagon selectedWagon = wc.findWagon(Integer.parseInt(splitcheck[0]));
                        //test select



                            System.out.println("ListView selection WAGON changed from oldValue = "
                                    + oldValue + " to newValue = " + newValueWagon);




                        //button function for deleting selected wagon
                        BtnWagonDelete.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {
                                //Wat moet Delete Wagon button doen??? <-----------------------------------------------
                                System.out.println("test");
                                wtc.deleteWagonTrain(selectedTrain.getTrainID(),selectedWagon.getWagonID());
                                WagonItems.clear();
                                ArrayList<String> selecteditems = refreshWagonList(selectedTrain);

                                for (String i : selecteditems
                                        ) {
                                    WagonItems.add(i);

                                }
                                Integer listSize = WagonItems.size();
                                ImageViewWagon1.setVisible(false);
                                ImageViewWagon2.setVisible(false);
                                ImageViewWagon3.setVisible(false);
                                ImageViewWagon4.setVisible(false);
                                ImageViewWagon5.setVisible(false);
                                ImageViewWagon6.setVisible(false);
                                ImageViewWagon7.setVisible(false);

                                if (listSize > 0){
                                    ImageViewWagon1.setVisible(true);
                                }
                                if (listSize > 1){
                                    ImageViewWagon2.setVisible(true);
                                }
                                if (listSize > 2){
                                    ImageViewWagon3.setVisible(true);
                                }
                                if (listSize > 3){
                                    ImageViewWagon4.setVisible(true);
                                }
                                if (listSize > 4){
                                    ImageViewWagon5.setVisible(true);
                                }
                                if (listSize > 5){
                                    ImageViewWagon6.setVisible(true);
                                }
                                if (listSize > 6){
                                    ImageViewWagon7.setVisible(true);
                                }
                            }
                        });

                    }

                });
                WagonTypeList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValueWagonType) {
                        BtnAddWagonToTrain.setDisable(false);

                        if (newValueWagonType == null){
                            newValueWagonType= "1 Wagon";
                        }
                        if (newValueWagonType == "1 Wagon"){
                            BtnAddWagonToTrain.setDisable(true);
                        }
                        else{
                            BtnAddWagonToTrain.setDisable(false);
                        }

                        String[] splitstringcheck = newValueWagonType.split(" ", 2);

                        BtnAddWagonToTrain.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent e) {

                                wtc.createWagonTrain(selectedTrain.getTrainID(),wc.findWagon(Integer.parseInt(splitstringcheck[0])).getWagonID());
                                WagonItems.clear();
                                ArrayList<String> selecteditems = refreshWagonList(selectedTrain);

                                for (String i : selecteditems
                                        ) {
                                    WagonItems.add(i);

                                }
                                Integer listSize = WagonItems.size();
                                ImageViewWagon1.setVisible(false);
                                ImageViewWagon2.setVisible(false);
                                ImageViewWagon3.setVisible(false);
                                ImageViewWagon4.setVisible(false);
                                ImageViewWagon5.setVisible(false);
                                ImageViewWagon6.setVisible(false);
                                ImageViewWagon7.setVisible(false);

                                if (listSize > 0){
                                    ImageViewWagon1.setVisible(true);
                                }
                                if (listSize > 1){
                                    ImageViewWagon2.setVisible(true);
                                }
                                if (listSize > 2){
                                    ImageViewWagon3.setVisible(true);
                                }
                                if (listSize > 3){
                                    ImageViewWagon4.setVisible(true);
                                }
                                if (listSize > 4){
                                    ImageViewWagon5.setVisible(true);
                                }
                                if (listSize > 5){
                                    ImageViewWagon6.setVisible(true);
                                }
                                if (listSize > 6){
                                    ImageViewWagon7.setVisible(true);
                                }

                            }
                        });

                    }
                });


            }

        });


        //Btntrain function

        BtnTrain.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                tc.createTrain(TrainNameT.getText(), LocomotiveNameT.getText());

                TrainItems.clear();
                ArrayList<String> selecteditems = refreshtrainList();

                for (String i : selecteditems
                        ) {
                    TrainItems.add(i);

                }

            }
        });


    }
    public ArrayList<String> refreshtrainList(){
        TrainDAO tdao = new TrainDAO();
        ArrayList<String> TrainItems = new ArrayList<>();
        ArrayList<Train> trainList = tdao.findAllTrains();
        for(Train i: trainList){
            if (i.getTrainID() != 1) {
                String value = i.getTrainID() + " " + i.getName();
                TrainItems.add(value);
            }
        }
        return TrainItems;
    }
    public ArrayList<String> refreshWagonTypeList(){
        WagonTypeDAO wdao = new WagonTypeDAO();
        ArrayList<String> WagontypeItems = new ArrayList<>();
        List<Wagon> wagonTypeList = wdao.selectAllWagonTypes();
        for(Wagon i: wagonTypeList){
            if (i.getWagonID() != 1) {
                String value = i.getWagonID() + " " + i.getName();
                WagontypeItems.add(value);
            }
        }

        return WagontypeItems;
    }
    public ArrayList<String> refreshWagonList(Train train){
        WagonTrainDAO wdao = new WagonTrainDAO();
        ArrayList<String> WagonItems = new ArrayList<>();
        ArrayList<Wagon> WagonList = wdao.getWagonFromTrain(train);
        for(Wagon i: WagonList){

                String value = i.getWagonID() + " " + i.getName();
                WagonItems.add(value);

        }
        return WagonItems;
    }
    //Lift of for application
    public static void main(String[] args) {
        launch(args);
    }


}