// package helloworld;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
 
public class Main extends Application {
    int ingredient_num;
    int max_recipe_num;
    String[] ingredients;
    int[][] Recipes;
    String[] Recipe_name;
    public static void main(String[] args) {
        RecipesLib lib = new RecipesLib();
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)  throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("BreweryFac.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}