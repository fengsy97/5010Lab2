package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.chart.PieChart.Data;
import javafx.collections.ObservableList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;

import java.util.Arrays;

import data.RecipesLib;
import javafx.util.Duration;


public class viewController{

    public RecipesLib recipeslib;

    public boolean [] container;

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
    @FXML
    private Button CreateRecipe;
    @FXML
    private ChoiceBox<String> RecipeChoiceProduct;
    @FXML
    private TextField RecipeName;
    @FXML
    private Slider Amount_Juice;
    @FXML
    private Slider Amount_Milk;
    @FXML
    private Slider Amount_Tea;
    @FXML
    private Slider Amount_Yougurt;
    @FXML
    private Button Make;
    @FXML
    private Slider BatchSize;
    @FXML
    private TextArea LogScreen;
    @FXML
    private Button Container0;
    @FXML
    private Button Container1;

    public viewController(){
        System.out.println("Init viewController");
        recipeslib = new RecipesLib();
        container = new boolean[] {true,true};
    }

    // called by the FXML loader after the labels declared above are injected:
    public void initialize() {
        int recipe_num = 0;
        setRecipeShow(recipe_num);
        RecipeChoiceProduct.setValue("MilkTea");
        RecipeChoice.setValue("MilkTea");
        setRecipeChoice("");
        RecipeChoice.setOnAction((event) -> {
            String recipe_name = (String)RecipeChoice.getValue();
            if(recipe_name == null){
                System.out.println("Initialize:recipe_name is null");
                return;
            }
            int[] recipe = recipeslib.getRecipe(recipe_name);
            int ingredient_num = recipeslib.getIngredientNum();
            String[] ingredients = recipeslib.getIngredients();
            RecipeShow.setData(getRecipe(ingredients,recipe,ingredient_num));
        });

        Container0.setOnAction((event) -> {
            container[0] = true;
            Container0.setText("Container0");
        });

        Container1.setOnAction((event) -> {
            container[1] = true;
            Container1.setText("Container1");
        });

        Make.setOnAction((event) -> {
            String recipe_name = (String)RecipeChoiceProduct.getValue();
            int[] recipe = recipeslib.getRecipe(recipe_name);
            int ingredient_num = recipeslib.getIngredientNum();
            int[] ingredient_remain = recipeslib.getIngredientRemain();
            int batch_size = (int)BatchSize.getValue();
            if(batch_size == 0){
                LogScreen.appendText("Make fail, batch size is 0.\n");
                return;
            }
            for(int i = 0;i < ingredient_num;i++){
                if(ingredient_remain[i] < recipe[i] * batch_size){
                    LogScreen.appendText("Make fail, not enough ingredient.\n");
                    return;
                }
            }
            if(container[0]){
                useContainer(0,recipe_name);
            }
            else if(container[1]){
                useContainer(1,recipe_name);
            }
            else{
                LogScreen.appendText("Make fail, not enough container.\n");
                return;
            }
            
            for(int i = 0;i < ingredient_num;i++){
                ingredient_remain[i] -= recipe[i] * batch_size;
            }
            setRemain();
        });

        CreateRecipe.setOnAction((event) -> {
            String recipe_name = RecipeName.getText();
            int[] recipe = new int[4];
            recipe[0] = (int)Amount_Juice.getValue();
            recipe[1] = (int)Amount_Milk.getValue();
            recipe[2] = (int)Amount_Tea.getValue();
            recipe[3] = (int)Amount_Yougurt.getValue();
            // if( recipe_name.length() == 0 || recipe[0] + recipe[1] + recipe[2] + recipe[3] == 0){
            if( recipe_name.length() == 0){
                LogScreen.appendText("Create fail, name cant be none.\n");
                return;
            }
            if(Arrays.stream(recipe).sum() == 0){
                LogScreen.appendText("Create Recipe fail, recipe cant have nothing.\n");
                return;
            }
            if(recipeslib.addRecipe(recipe_name,recipe)){
                LogScreen.appendText("Create Recipe " + recipe_name + " success.\n");
                setRecipeChoice(recipe_name);
            }else{
                LogScreen.appendText("Create Recipe fail\n");
            }
        });

        AddIngredient.setOnAction((event) -> {
            String ingredient_name = (String)InventoryChoice.getValue();
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

    private void useContainer(int container_num,String recipe_name){
        container[container_num] = false;
        int delayTime = 3;
        if(container_num == 0){
            Container0.disableProperty().set(true);
            Container0.setText("Making " + recipe_name + ".");
        }else{
            Container1.disableProperty().set(true);
            Container1.setText("Making " + recipe_name + ".");
        }
        final Timeline animation = new Timeline(
            new KeyFrame(Duration.seconds(delayTime), e -> {
                if(container_num == 0){
                    Container0.disableProperty().set(false);
                    Container0.setText("Dirty");
                }else{
                    Container1.disableProperty().set(false);
                    Container1.setText("Dirty");
                }
                LogScreen.appendText("Make " + recipe_name + " success.\n");
            }
        ));
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), e -> {
            if(container_num == 0){
                Container0.setText("Bottling " + recipe_name + ".");
            }else{
                Container1.setText("Bottling " + recipe_name + ".");
            }
        });
        animation.getKeyFrames().add(keyFrame);
        animation.setCycleCount(1);
        animation.play();
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

    private void setRecipeChoice(String recipe_name){
        if(recipe_name.equals("")){
            recipe_name = (String)RecipeChoice.getValue();
        }
        RecipeChoiceProduct.getItems().clear();
        RecipeChoice.getItems().clear();
        String[] recipe_names = recipeslib.getRecipeNames();
        int max_recipe_num = recipeslib.getMaxRecipeNum();
        for(int i = 0;i < max_recipe_num;i++){
            if(recipe_names[i].equals("")){
                continue;
            }
            RecipeChoiceProduct.getItems().add(recipe_names[i]);
            RecipeChoice.getItems().add(recipe_names[i]);
        }
        RecipeChoiceProduct.setValue(recipe_name);
        RecipeChoice.setValue(recipe_name);
    }

    private void setRemain(){
        int[] ingredient_remain = recipeslib.getIngredientRemain();
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
        ObservableList<Data> pieChartData = getRecipe(ingredients,recipe,ingredient_num);
        RecipeShow.setData(pieChartData);
    }

    private ObservableList<Data> getRecipe(String[] ingredients,int[] recipe,int ingredient_num){
        ObservableList<Data> answer = FXCollections.observableArrayList();
        String [] pieColors = {"#ff8000", "#ffff00", "#80ff00","#00ffff", "#0080ff"};
        for(int i = 0;i < ingredient_num;i++){
            if(recipe[i]>0){
                answer.add(new PieChart.Data(ingredients[i] +":"+ recipe[i],recipe[i]));
            }   
        }
        RecipeShow.setData(answer);
        int i = 0;
        for (PieChart.Data data : answer) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
        return answer;
    }
}

