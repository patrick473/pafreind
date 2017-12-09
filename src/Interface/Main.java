package Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
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
        grid.add(scenetitle, 0, 34, 12, 1);

        Label TreinNaamL = new Label("Trein naam:");
        grid.add(TreinNaamL, 0, 35);

        TextField TreinNaamT = new TextField();
        grid.add(TreinNaamT, 1, 35);

        Label WagonNaamL = new Label("Wagon naam:");
        grid.add(WagonNaamL, 3, 35);

        TextField WagonNaamT = new PasswordField();
        grid.add(WagonNaamT, 4, 35);
        
    }


    public static void main(String[] args) {
        launch(args);
    }
}