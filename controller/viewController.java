package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart.Data;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;
// import data;

public class viewController{
    // private Recipeslib recipeslib;
    @FXML
    private PieChart RecipeShow ;
    public viewController(){
        // RecipesLib recipeslib;
        // recipeslib = new RecipesLib();
        System.out.println("Init viewController");
    }

    // called by the FXML loader after the labels declared above are injected:
    public void initialize() {
        RecipeShow.setData(getChartData());
    }

    private ObservableList<Data> getChartData() {
        ObservableList<Data> answer = FXCollections.observableArrayList();
        answer.addAll(new PieChart.Data("java", 17.56),
                new PieChart.Data("JavaFx", 31.37));
        return answer;
    }
}