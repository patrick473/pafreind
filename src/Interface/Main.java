package Interface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    	
    	primaryStage.setTitle("JavaFX Welcome");
        
        primaryStage.show();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT );
        grid.setHgap(10);
        grid.setVgap(10);
        //grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 1200, 850);
        primaryStage.setScene(scene);
        
        Text scenetitle = new Text("NS nooit snel");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, SceneTitleColumn, SceneTitleRow, 12, 1);

        Label TrainNameL = new Label("Trein naam:");
        grid.add(TrainNameL, TrainNameColumn, TrainNameRow);

        TextField TrainNameT = new TextField();
        grid.add(TrainNameT, TrainNameColumn+1, TrainNameRow);

        Label WagonNameL = new Label("Wagon naam:");
        grid.add(WagonNameL, WagonNameColumn, WagonNameRow);

        TextField WagonNameT = new PasswordField();
        grid.add(WagonNameT, WagonNameColumn+1, WagonNameRow);
        
        Button BtnTrain = new Button("Add Train");
        HBox HbBtnTrain = new HBox(10);
        HbBtnTrain.getChildren().add(BtnTrain);
        grid.add(HbBtnTrain, TrainNameColumn+2, TrainNameRow);
        
        BtnTrain.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
                //Wat moet add trein button doen???
            }
        });
        
        Button BtnWagon = new Button("Add Wagon");
        HBox HbBtnWagon = new HBox(10);
        HbBtnWagon.getChildren().add(BtnWagon);
        grid.add(HbBtnWagon, WagonNameColumn+2, WagonNameRow);
        
        BtnWagon.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                //Wat moet add Wagon button doen???
            }
        });
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}