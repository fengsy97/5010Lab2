// package breweryfac;
 
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

 
public class Main extends Application {
    @Override
    public void start(Stage primaryStage)  throws Exception {
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
