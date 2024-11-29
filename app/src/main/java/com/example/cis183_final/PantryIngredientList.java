package com.example.cis183_final;

import android.util.Log;

import java.util.ArrayList;

public class PantryIngredientList
{
    private static PantryIngredientList pantryIngredientList;
    private ArrayList<PantryIngredient> pantryIngredients;
    private PantryListAdapter pantryListAdapter;

    private PantryIngredientList()
    {
        //Create a new ArrayList names recipes
        pantryIngredients = new ArrayList<>();
    }

    public static PantryIngredientList getInstance()
    {
        //if there is no instance of the pantryIngredientsList lets create one
        if (pantryIngredientList == null)
        {
            pantryIngredientList = new PantryIngredientList();
        }
        return pantryIngredientList;
    }

    public ArrayList<PantryIngredient> getPantryIngredients()
    {
        return pantryIngredients;
    }

    public void initPantryList(ArrayList<PantryIngredient> ls)
    {
        this.pantryIngredients = ls;
        notifyAdapterToRefresh();
    }

    public void setPantryListAdapter(PantryListAdapter adapter)
    {
        this.pantryListAdapter = adapter;
        notifyAdapterToRefresh();
    }

    public PantryListAdapter getPantryListAdapter()
    {
        if (pantryListAdapter == null)
        {
            Log.d("PANTRYINGREDIENTLIST getPantryListAdapter()", "pantryListAdapter is null");
        }
        else
        {
            notifyAdapterToRefresh();
        }
        return pantryListAdapter;
    }

    public void addPantryIngredient(PantryIngredient ingredientToAdd)
    {
        pantryIngredients.add(ingredientToAdd);
        notifyAdapterToRefresh();
    }

    public void notifyAdapterToRefresh()
    {
        if (pantryIngredientList != null)
        {
            pantryListAdapter.notifyDataSetChanged();
        }
        else
        {
            Log.d("PANTRYINGREDIENTLIST notifyAdapterToRefresh()", "pantryListAdapter is null");
        }
    }
}
