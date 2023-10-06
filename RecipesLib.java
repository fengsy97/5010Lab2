// Purpose: The class of RecipesLib



public class RecipesLib  {
    int ingredient_num;
    int max_recipe_num;
    String[] ingredients;
    int[][] Recipes;
    String[] Recipe_name;

    RecipesLib(){
        System.out.println("Init RecipesLib");
        this.ingredient_num = 4;
        this.max_recipe_num = 10000;
        this.ingredients = new String[]{"Juice","Milk","Tea","Yougurt"};
        this.Recipes = new int[this.max_recipe_num][this.ingredient_num];
        this.Recipe_name = new String[this.max_recipe_num];
        for(int i = 0;i < this.max_recipe_num;i++){
            this.Recipe_name[i] = "";
            for(int j = 0;j < this.ingredient_num;j++){
                this.Recipes[i][j] = 0;
            }
        }
        Recipe_name[0] = "MilkTea";
        Recipes[0][0] = 20;
        Recipes[0][1] = 10;
        Recipes[0][2] = 5;
        Recipes[0][3] = 30;
    }
    // public static void main(String[] args){
    //     System.out.println("Hello World!");
    //     return;
    // }
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
                for(int j = 0;j < this.ingredient_num;j++){
                    this.Recipes[i][j] = recipe[j];
                }
                break;
            }
        }
    }
}