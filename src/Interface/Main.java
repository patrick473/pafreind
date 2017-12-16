package Interface;

import java.awt.Color;
import java.util.function.UnaryOperator;

import Domain.Train;
import Domain.Wagon;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
<<<<<<< HEAD
import persistency.LocomotiveDAO;
import persistency.TrainDAO;
import persistency.WagonTrainDAO;
=======
import persistency.TrainDAO;
import persistency.WagonTypeDAO;
>>>>>>> ccaff9e2c46263054c6927e5a7ce19fbc96d38dc

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
<<<<<<< HEAD
    	
    	LocomotiveDAO ldao = new LocomotiveDAO();
    	TrainDAO tdao = new TrainDAO();
    	WagonTrainDAO wdao = new WagonTrainDAO();
    	
=======
        TrainDAO tdao = new TrainDAO();
        WagonTypeDAO wdao = new WagonTypeDAO();



>>>>>>> ccaff9e2c46263054c6927e5a7ce19fbc96d38dc
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
        grid.setHgap(10);
        grid.setVgap(10);
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
        
        
        //Btntrain function
        BtnTrain.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	String TrainNameInput = TrainNameT.getText();
            	String LocomotiveNameInput = LocomotiveNameT.getText();
<<<<<<< HEAD
            	Train TrainObject = new Train(TrainNameInput);
            	tdao.createTrain(TrainObject, LocomotiveNameInput);
=======
                //Wat moet add trein button doen??? <----------------------------------------------------
                Train train = new Train(TrainNameInput);
                tdao.createTrain(train,LocomotiveNameInput);
>>>>>>> ccaff9e2c46263054c6927e5a7ce19fbc96d38dc
            }
        });
        
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
<<<<<<< HEAD
=======

        //Button function of BtnWagon
        BtnWagon.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent e) {
            	String WagonNameInput = WagonNameT.getText();
            	Integer WagonSeatInput = Integer.parseInt(WagonSeatT.getText());
            	Wagon wagon = new Wagon(WagonNameInput,WagonSeatInput);
                wdao.addWagonType(wagon);
            }
        });
>>>>>>> ccaff9e2c46263054c6927e5a7ce19fbc96d38dc
        
        //Create list view and title of the list for wagon
        ListView<String> WagonList = new ListView<String>();
        ObservableList<String> WagonItems = FXCollections.observableArrayList(
        		"Wagon1", "Wagon2", "Wagon3", "Wagon4"); // PUT WAGON NAMES OF SELECTED TRAIN HERE <----------------------
        WagonList.setItems(WagonItems);
        WagonList.setPrefHeight(200);
        Label WagonListL = new Label("Wagons of selected train");
        WagonListL .setFont(Font.font("Tahoma", FontWeight.BOLD , 12));
        WagonList.setDisable(true);
        grid.add(WagonListL, 4, 37);
        grid.add(WagonList, 4, 38, 20, 1);
        
        //List select listner for wagon
        WagonList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	
            	//test select
                System.out.println("ListView selection WAGON changed from oldValue = " 
                        + oldValue + " to newValue = " + newValue);
                
                //button function for deleting selected wagon
                BtnWagonDelete.setOnAction(new EventHandler<ActionEvent>() {                 	 
                    @Override
                    public void handle(ActionEvent e) {
                        //Wat moet Delete Wagon button doen??? <-----------------------------------------------
                    }
                });
                
            }
        });
        
        //Create list view and title of the list for train
        ListView<String> TrainList = new ListView<String>();
        ObservableList<String> TrainItems =FXCollections.observableArrayList (); //PUT TRAIN NAMES HERE <-------------------------------------
        for(int i=1; i<tdao.findAllTrains().size(); i++){
            TrainItems.add(tdao.findAllTrains().get(i).getName());
       }
        TrainList.setItems(TrainItems);
        TrainList.setPrefHeight(200);
        Label TrainListL = new Label("Select to add wagons or remove train");
        TrainListL.setFont(Font.font("Tahoma", FontWeight.BOLD , 12));
        grid.add(TrainListL, 1, 37);
        grid.add(TrainList, 1, 38, 3, 1);
        
        //List select listner for train
        TrainList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	
            	//activate input for wagon. If not activated input will be null
            	WagonSeatT.setDisable(false);
                WagonNameT.setDisable(false);
                WagonList.setDisable(false);
                
                //test select
                System.out.println("ListView selection TRAIN changed from oldValue = " 
                        + oldValue + " to newValue = " + newValue);
                
                //Button function for deleting selected train
                BtnTrainDelete.setOnAction(new EventHandler<ActionEvent>() {                 	 
                    @Override
                    public void handle(ActionEvent e) {
                        //Wat moet Delete Train button doen??? <-----------------------------------------------
                    }
                });
                
                //Button function of BtnWagon
                BtnWagon.setOnAction(new EventHandler<ActionEvent>() { 
                    @Override
                    public void handle(ActionEvent e) {
                    	String WagonNameInput = WagonNameT.getText();
                    	Integer WagonSeatInput = Integer.parseInt(WagonSeatT.getText());
                    	Wagon WagonObject = new Wagon(WagonNameInput, WagonSeatInput);
                        //wdao.addWagonTrain(//TRAIN OBJECT HERE, WagonObject);
                    }
                });
                
            }
        });
        
        //create locomotive
        String UrlLoco = "https://orig00.deviantart.net/96c0/f/2014/343/2/0/thomas_the_tank_engine_by_danielarkansanengine-d7aicax.png";
        Image ImageLoco = new Image(UrlLoco, true);
        ImageView ImageViewLoco = new ImageView(ImageLoco);
        ImageViewLoco.setFitHeight(200);
        ImageViewLoco.setFitWidth(250);
        ImageViewLoco.setVisible(true); //<------------ Hide Locomotive if no train is selected;
        grid.add(ImageViewLoco, WagonLocoPicCol, WagonLocoPicRow);
        
        //create wagon
        String UrlWagon1 = "https://us.123rf.com/450wm/studioworkstock/studioworkstock1611/studioworkstock161100145/67173618-railway-wagon-isolated-on-white-background-vector-illustration-railroad-transport-design-element-sid.jpg?ver=6";
        Image ImageWagon1 = new Image(UrlWagon1, true);
        ImageView ImageViewWagon1 = new ImageView(ImageWagon1);
        ImageViewWagon1.setFitHeight(200);
        ImageViewWagon1.setFitWidth(250);
        ImageViewWagon1.setVisible(true); //<--------- hide wagon if no wagon exists.
        grid.add(ImageViewWagon1, WagonLocoPicCol+1, WagonLocoPicRow); // WagonLocoPicCol+number --> number = The number of the wagon order? (this is the first wagon so number 1)
        
    }

    //Lift of for application
    public static void main(String[] args) {
        launch(args);
    }
}