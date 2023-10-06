package data;
// Purpose: The class of RecipesLib

import javafx.collections.ObservableList;

public class RecipesLib  {
    int ingredient_types;
    int max_recipe_num;
    String[] ingredients;
    int[] ingredient_num;
    int[][] Recipes;
    String[] Recipe_name;

    public RecipesLib(){
        this.ingredient_types = 4;
        this.max_recipe_num = 100;
        this.ingredient_num = new int[] {1000,1000,1000,1000};
        this.ingredients = new String[]{"Juice","Milk","Tea","Yougurt"};
        this.Recipes = new int[this.max_recipe_num][this.ingredient_types];
        this.Recipe_name = new String[this.max_recipe_num];
        for(int i = 0;i < this.max_recipe_num;i++){
            this.Recipe_name[i] = "";
            for(int j = 0;j < this.ingredient_types;j++){
                this.Recipes[i][j] = 0;
            }
        }
        Recipe_name[0] = "MilkTea";
        Recipe_name[1] = "JuiceYougurt";
        Recipes[0] = new int[]{20,10,5,30};
        Recipes[1] = new int[]{50,10,0,30};
    }

    public int[] getIngredientRemain(){
        return this.ingredient_num;
    }

    public String[] getIngredients(){
        return this.ingredients;
    }

    public int getIngredientNum(){
        return this.ingredient_types;
    }

    public int getMaxRecipeNum(){
        return this.max_recipe_num;
    }

    public String[] getRecipeNames(){
        return this.Recipe_name;
    }

    public int[] getRecipe(String name){
        for(int i = 0;i < this.max_recipe_num;i++){
            if(this.Recipe_name[i].equals(name)){
                return this.Recipes[i];
            }
        }
        return null;
    }

    public void addRecipe(String name,int[] recipe){
        for(int i = 0;i < this.max_recipe_num;i++){
            if(this.Recipe_name[i].equals("")){
                this.Recipe_name[i] = name;
                for(int j = 0;j < this.ingredient_types;j++){
                    this.Recipes[i][j] = recipe[j];
                }
                break;
            }
        }
    }
}