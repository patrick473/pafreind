package Interface;

import java.util.function.UnaryOperator;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	
    	//pas locatie van interface onderdelen hier aan
    	Integer SceneTitleRow = 34;
    	Integer SceneTitleColumn = 0;
    	Integer TrainNameColumn = 1;
    	Integer TrainNameRow = 35;
    	Integer WagonNameColumn = 1;
    	Integer WagonNameRow = 36;
    	Integer LocomotiveNameColumn = 3;
    	Integer LocomotiveNameRow = 35;
    	
    	
    	primaryStage.setTitle("JavaFX Welcome");
        
        primaryStage.show();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT );
        grid.setHgap(10);
        grid.setVgap(10);
        //grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 1200, 850);
        primaryStage.setScene(scene);
        
        Text scenetitle = new Text("NS Not Simple");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, SceneTitleColumn, SceneTitleRow, 12, 1);

        Label TrainNameL = new Label("Trein name:");
        grid.add(TrainNameL, TrainNameColumn, TrainNameRow);

        TextField TrainNameT = new TextField();
        grid.add(TrainNameT, TrainNameColumn+1, TrainNameRow);
        
        Button BtnTrain = new Button("Add Train");
        HBox HbBtnTrain = new HBox(10);
        HbBtnTrain.getChildren().add(BtnTrain);
        grid.add(HbBtnTrain, TrainNameColumn+4, TrainNameRow);
        
        BtnTrain.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
                //Wat moet add trein button doen??? <----------------------------------------------------
            }
        });
        
        Label LocomotiveNameL = new Label("Locomotive name:");
        grid.add(LocomotiveNameL, LocomotiveNameColumn, LocomotiveNameRow);

        TextField LocomotiveNameT = new TextField();
        grid.add(LocomotiveNameT, LocomotiveNameColumn+1, LocomotiveNameRow);

        Label WagonNameL = new Label("Wagon name:");
        grid.add(WagonNameL, WagonNameColumn, WagonNameRow);

        TextField WagonNameT = new TextField();
        grid.add(WagonNameT,WagonNameColumn+1, WagonNameRow);
        
        Label WagonSeatL = new Label("Amount of seats:");
        grid.add(WagonSeatL,WagonNameColumn+2, WagonNameRow);

        TextField WagonSeatT = new TextField();
        grid.add(WagonSeatT,WagonNameColumn+3, WagonNameRow);
        
        UnaryOperator<Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };

        WagonSeatT.setTextFormatter(
            new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
        
        Button BtnWagon = new Button("Add Wagontype");
        HBox HbBtnWagon = new HBox(10);
        HbBtnWagon.getChildren().add(BtnWagon);
        grid.add(HbBtnWagon,WagonNameColumn+4, WagonNameRow);
        
        BtnWagon.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                //Wat moet add Wagon button doen??? <-----------------------------------------------
            }
        });
        
        ListView<String> TrainList = new ListView<String>();
        ObservableList<String> TrainItems =FXCollections.observableArrayList (
            "Single", "Double", "Suite", "Family App"); //PUT TRAIN NAMES HERE <-------------------------------------
        TrainList.setItems(TrainItems);
        TrainList.setPrefHeight(200);
        Label TrainListL = new Label("Select train before adding the wagon");
        TrainListL.setFont(Font.font("Tahoma", FontWeight.BOLD , 12));
        grid.add(TrainListL, 1, 37);
        grid.add(TrainList, 1, 38, 8, 1);
        
        TrainList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("ListView selection changed from oldValue = " 
                        + oldValue + " to newValue = " + newValue);
            }
        });
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}