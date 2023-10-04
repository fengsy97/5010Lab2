import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class Ingredient{
    Ingredient next;
    private String name;
    private String unit;
    private float amount;
    Ingredient(String name,String unit,float amount){
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }
    void set_amount(float amount){this.amount = amount;}

    public void print_ingredient(){
        System.out.println(this.name + this.amount + String.valueOf(this.unit));
    }

    public String get_name(){return this.name;}
}

class Recipe{
    Recipe next;
    private String name;
    private Ingredient head;
    Recipe(String name){
        this.name = name;
        this.head = new Ingredient(name+"_head","",0);
    }
    public void add_ingredient(String name,String unit,float amount){
        System.out.println(name);
    }
    public void print_recipe(){
        System.out.println("The ingredient of" + this.name + ":");
        Ingredient temp = this.head;
        while(temp != null){
            temp.print_ingredient();
            // System.out.println()
        }
    }
    public String get_name(){return this.name;}
}


public class RecipesLib{
    Recipe lib_ingred;
    RecipesLib(){
        this.lib_ingred = new Recipe("head");
        
        BufferedReader reader;
        String filename = "./Ingredient";
        try{
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while(line  != null){
                Tmp.add(line);
                line = reader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Recipe recipe = new Recipe("head");
        recipe.add_ingredient("null", "null", 0);
        return;
    }
}