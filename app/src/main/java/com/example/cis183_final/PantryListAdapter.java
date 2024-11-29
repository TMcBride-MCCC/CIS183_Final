package com.example.cis183_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PantryListAdapter extends BaseAdapter
{

    Context context;
    ArrayList<PantryIngredients> listOfPantryIngredients;
    DatabaseHelper dbHelper;

    public PantryListAdapter( Context c, ArrayList<PantryIngredients> ls, DatabaseHelper db)
    {
        context = c;
        listOfPantryIngredients = ls;
        dbHelper = db;
    }

    @Override
    public int getCount()
    {
        return listOfPantryIngredients.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfPantryIngredients.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(Pantry.LAYOUT_INFLATER_SERVICE); //Pantry or MainActivity??
            view = mInflator.inflate(R.layout.pantry_listview_cell, null);
        }

        //Find GUI
        TextView categoryName = view.findViewById(R.id.tv_v_pantrylistview_categoryName);
        TextView itemName = view.findViewById(R.id.tv_v_pantrylistview_itemName);
        TextView amountLeft = view.findViewById(R.id.tv_v_pantrylistview_amountLeft);

        //Get the data from the list
        PantryIngredients pantryIngredient = listOfPantryIngredients.get(i);

        //Set the GUI
        itemName.setText("IngredientId is: " + pantryIngredient.getIngredientId());

        //To get the categoryName we need to:
            //1. Set up the Ingredients class
            //2. Create a function in database to reference the Ingredients table categoryID to the Category Table and pull the name
            //3. Create a function in database to reference the PantryIngredients table ingredientID to the Ingredient table
        //To get the Item Name we need to:
            //1. Get the PantryIngredients ingredientId
            //2.


        return view;
    }
}
