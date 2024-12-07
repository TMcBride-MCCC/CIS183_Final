package com.example.cis183_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealPlanAdapter extends RecyclerView.Adapter<MealPlanAdapter.MyViewHolder>
{
    Context context;
    ArrayList<Recipe> listOfRecipes;
    DatabaseHelper dbHelper;

    public MealPlanAdapter(Context c, ArrayList<Recipe> ls, DatabaseHelper db)
    {
        context = c;
        listOfRecipes = ls;
        dbHelper = db;
    }

    @NonNull
    @Override
    public MealPlanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Inflate the layout
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.recyclerview_meal_plan, parent, false);
        return new MealPlanAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealPlanAdapter.MyViewHolder holder, int position)
    {
        //Assign values to the views for the recyclerview layout file based on the position of the recycler view
        holder.tv_j_rvmp_recipeName.setText(listOfRecipes.get(position).getRecipeName());
    }

    @Override
    public int getItemCount()
    {
        //Tell the adapter how many total items are going to be displayed
        return listOfRecipes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        //Create the JAVA variables
        TextView tv_j_rvmp_recipeName;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //Assign the JAVA variable to the GUI variables
            tv_j_rvmp_recipeName = itemView.findViewById(R.id.tv_v_rvmp_recipeName);

        }
    }
}
