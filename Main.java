// package helloworld;
 
import javafx.application.Application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

 
public class Main extends Application {
    @Override
    public void start(Stage primaryStage)  throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("BreweryFac.fxml"));
        primaryStage.setTitle("Brewery Factory");
        Parent root = FXMLLoader.load(getClass().getResource("resources/BreweryFac.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // private TableView materials;
    public static void main(String[] args) {
        launch(args);
    }
}
