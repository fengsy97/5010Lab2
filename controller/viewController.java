package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.chart.PieChart.Data;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;
import data.RecipesLib;
import javafx.scene.Node;


public class viewController{
    public RecipesLib recipeslib;
    @FXML
    private PieChart RecipeShow ;
    @FXML
    private ChoiceBox<String> RecipeChoice;
    @FXML
    private Label Remain_Juice;
    @FXML
    private Label Remain_Milk;
    @FXML
    private Label Remain_Tea;
    @FXML
    private Label Remain_Yougurt;
    @FXML
    private ChoiceBox<String> InventoryChoice;
    @FXML
    private Button AddIngredient;
    @FXML
    private Slider AddAmount;



    public viewController(){
        System.out.println("Init viewController");
        recipeslib = new RecipesLib();
        // RecipeChoice = new ChoiceBox();
    }

    // called by the FXML loader after the labels declared above are injected:
    public void initialize() {
        System.out.println("Initialize");
        int recipe_num = 0;
        setRecipeShow(recipe_num);
        setRecipeChoice(recipe_num);
        RecipeChoice.setOnAction((event) -> {
            String recipe_name = (String)RecipeChoice.getValue();
            int[] recipe = recipeslib.getRecipe(recipe_name);
            int ingredient_num = recipeslib.getIngredientNum();
            String[] ingredients = recipeslib.getIngredients();
            RecipeShow.setData(getRecipe(ingredients,recipe,ingredient_num));
        });
        AddIngredient.setOnAction((event) -> {
            String ingredient_name = (String)InventoryChoice.getValue();
            // int[] recipe = recipeslib.getRecipe((String)RecipeChoice.getValue());
            int ingredient_num = recipeslib.getIngredientNum();
            String[] ingredients = recipeslib.getIngredients();
            int[] ingredient_remain = recipeslib.getIngredientRemain();
            int index = 0;
            for(int i = 0;i < ingredient_num;i++){
                if(ingredients[i].equals(ingredient_name)){
                    index = i;
                    break;
                }
            }
            int add_amount = (int)AddAmount.getValue();
            ingredient_remain[index] += add_amount;
            setRemain();
        });
        setRemain();
        updateInventory();
    }

    private void updateInventory(){
        String[] ingredients = recipeslib.getIngredients();
        int ingredient_num = recipeslib.getIngredientNum();

        ObservableList<String> inventory = FXCollections.observableArrayList();
        for(int i = 0;i < ingredient_num;i++){
            inventory.add(ingredients[i]);
        }
        InventoryChoice.setItems(inventory);
        InventoryChoice.setValue(ingredients[0]);
    }

    private void setRecipeChoice(int recipe_num){
        String[] recipe_names = recipeslib.getRecipeNames();
        int max_recipe_num = recipeslib.getMaxRecipeNum();
        for(int i = 0;i < max_recipe_num;i++){
            if(recipe_names[i].equals("")){
                continue;
            }
            RecipeChoice.getItems().add(recipe_names[i]);
        }
        RecipeChoice.setValue(recipe_names[recipe_num]);
    }

    private void setRemain(){
        // String[] ingredients = recipeslib.getIngredients();
        // int ingredient_num = recipeslib.getIngredientNum();
        int[] ingredient_remain = recipeslib.getIngredientRemain();
        if(ingredient_remain == null){
            System.out.println("Initialize:ingredient_remain is null");
            return;
        }
        Remain_Juice.setText(""+ingredient_remain[0]);
        Remain_Milk.setText(""+ingredient_remain[1]);
        Remain_Tea.setText(""+ingredient_remain[2]);
        Remain_Yougurt.setText(""+ingredient_remain[3]);
    }

    private void setRecipeShow(int recipe_num){
        String[] ingredients = recipeslib.getIngredients();
        int ingredient_num = recipeslib.getIngredientNum();
        String[] recipe_names = recipeslib.getRecipeNames();
        int[] recipe = recipeslib.getRecipe(recipe_names[recipe_num]);
        if(recipe == null){
            System.out.println("Initialize:recipe is null");
        }
        ObservableList<Data> pieChartData = getRecipe(ingredients,recipe,ingredient_num);
        RecipeShow.setData(pieChartData);
    }

    private ObservableList<Data> getRecipe(String[] ingredients,int[] recipe,int ingredient_num){
        ObservableList<Data> answer = FXCollections.observableArrayList();
        String [] pieColors = {"#ff8000", "#ffff00", "#80ff00","#00ffff", "#0080ff", "#0000ff", "#8000ff", "#ff00ff", "#ff0080"};
        for(int i = 0;i < ingredient_num;i++){
            answer.add(new PieChart.Data(ingredients[i] +":"+ recipe[i],recipe[i]));
        }
        RecipeShow.setData(answer);
        int i = 0;
        for (PieChart.Data data : answer) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
        return answer;
    }

    private ObservableList<Data> getChartData() {
        ObservableList<Data> answer = FXCollections.observableArrayList();
        answer.addAll(new PieChart.Data("java", 17),
                new PieChart.Data("JavaFx", 31));
        return answer;
    }
}

