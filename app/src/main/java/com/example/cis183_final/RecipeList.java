package com.example.cis183_final;

import android.util.Log;

import java.util.ArrayList;

public class RecipeList
{
    private static RecipeList recipeList;
    private ArrayList<Recipe> recipes;
    private RecipeListAdapter recipeListAdapter;

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

    public void initRecipeList(ArrayList<Recipe> recipes)
    {
        this.recipes = recipes;
        notifyAdapterToRefresh();
    }

    public void setRecipeListAdapter(RecipeListAdapter adapter)
    {
        this.recipeListAdapter = adapter;
        notifyAdapterToRefresh();
    }

    public RecipeListAdapter getRecipeListAdapter()
    {
        if (recipeListAdapter == null)
        {
            Log.d("RECIPELIST getRecipeListAdapter()", "recipeListAdapter is null");
        }
        else
        {
            notifyAdapterToRefresh();
        }
        return recipeListAdapter;
    }

    public void addRecipe(Recipe recipeToAdd)
    {
        recipes.add(recipeToAdd);
        notifyAdapterToRefresh();
    }

    public ArrayList<Recipe> getRecipesByMealTime(int mealTimeId)
    {
        ArrayList<Recipe> recipesByMealTime = new ArrayList<>();

        if (RecipeList.getInstance().getRecipes() != null)
        {
            for (int i = 0; i < RecipeList.getInstance().getRecipes().size(); i++)
            {
                if (RecipeList.getInstance().getRecipes().get(i).getMealTimeId() == mealTimeId)
                {
                    recipesByMealTime.add(RecipeList.getInstance().getRecipes().get(i));
                }
            }
        }
        else
        {
            Log.d("RECIPELIST getRecipesByMealTime()","Failed to grab instance of the RecipeList");
        }

        return recipesByMealTime;
    }

    public void notifyAdapterToRefresh()
    {
        if (recipeListAdapter != null)
        {
            recipeListAdapter.notifyDataSetChanged();
        }
        else
        {
            //Log.d("RECIPELIST notifyAdapterToRefresh()", "recipeListAdapter is null");
        }
    }
}
