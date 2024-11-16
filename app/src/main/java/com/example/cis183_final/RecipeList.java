package com.example.cis183_final;

import java.util.ArrayList;

public class RecipeList
{
    private static RecipeList recipeList;
    private ArrayList<Recipe> recipes;
    private HomeListAdapter recipeListAdapter;

    private RecipeList()
    {
        //What if this list already exists? Probably need a check for that here.....................
        //CHECK CODE HERE
        //Create a new ArrayList names recipes
        recipes = new ArrayList<>();
    }

    public static RecipeList getInstance()
    {
        //If there is no instance of the recipeList lets create one
        if (recipeList == null)
        {
            recipeList = new RecipeList();
        }
        return recipeList;
    }

    public ArrayList<Recipe> getRecipes()
    {
        return recipes;
    }

    public void initRecipeList(ArrayList<>)
}
