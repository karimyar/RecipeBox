package CS561.recipebox;

import java.util.ArrayList;

// Struct of Recipe
public class Recipe {
    private String rName;
    private String rInfo;

    public Recipe(String name, String info) {
        rName = name;
        rInfo = info;
    }

    public String getName() {
        return rName;
    }

    public String getInfo() {
        return rInfo;
    }

    // not necessary
    private static int lastRecipeId = 0;

    // Use class Recipe to create an list of information
    public static ArrayList<Recipe> createRecipesList(int numRecipes, String[] parsedOutput) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        for (int i = 0; i <= numRecipes; i++) {
            recipes.add(new Recipe("Recipe : " + (i+1) , parsedOutput[i]));
        }

        return recipes;
    }
}