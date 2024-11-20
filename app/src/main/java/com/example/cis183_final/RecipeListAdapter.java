package com.example.cis183_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Recipe> listOfRecipes;

    public RecipeListAdapter(Context c, ArrayList<Recipe> ls)
    {
        context = c;
        listOfRecipes = ls;
    }
    //Counts the number of records
    @Override
    public int getCount()
    {
        return listOfRecipes.size();
    }
    //Finds the index
    @Override
    public Object getItem(int i)
    {
        return listOfRecipes.get(i);
    }
    //Returns the index
    @Override
    public long getItemId(int i)
    {
        return i;
    }
    //Powers the cell layout and references the variables
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(Recipes.LAYOUT_INFLATER_SERVICE); //Recipes or MainActivity??
            view = mInflator.inflate(R.layout.recipes_listview_cell, null);
        }

        //Find GUI
        TextView recipeName = view.findViewById(R.id.tv_v_recipeslistview_recipeName);
        TextView recipeMealTime = view.findViewById(R.id.tv_v_recipeslistview_recipeMealTime);
        TextView numIngredients = view.findViewById(R.id.tv_v_recipeslistview_numIngredientsInPantry);

        //Get the data from the list
        Recipe recipe = listOfRecipes.get(i);

        //Set the GUI
        //HOW DO I GET THE INGREDIENT COMPARISON EQUATION RESULT HERE?????
        recipeName.setText(recipe.getRecipeName());
        recipeMealTime.setText(recipe.getRecipeName());

        return view;
    }
}
