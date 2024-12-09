package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MealPlan extends AppCompatActivity
{
    //Create JAVA variables
    TextView tv_j_mealPlan_day;
    Button btn_j_mealPlan_submit;
    BottomNavigationView bnv_j_mealPlan_bottomNav;
    Intent camefrom;
    DatabaseHelper dbHelper;
    private CardView selectedCard;
    //BREAKFAST
    RecyclerView rv_j_mealPlan_breakfast;
    RecyclerView rv_j_mealPlan_lunch;
    RecyclerView rv_j_mealPlan_dinner;

    //LUNCH

    //DINNER


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_plan);

        //Connect JAVA variables to GUI variables
        tv_j_mealPlan_day = findViewById(R.id.tv_v_mealPlan_day);
        btn_j_mealPlan_submit = findViewById(R.id.btn_v_mealPlan_submit);
        bnv_j_mealPlan_bottomNav = findViewById(R.id.bnv_v_mealPlan_bottomNav);
        //BREAKFAST
        rv_j_mealPlan_breakfast = findViewById(R.id.rv_v_mealPlan_breakfast);
        //LUNCH
        rv_j_mealPlan_lunch = findViewById(R.id.rv_v_mealPlan_lunch);
        //DINNER
        rv_j_mealPlan_dinner = findViewById(R.id.rv_v_mealPlan_dinner);
        //DATABASE
        dbHelper = new DatabaseHelper(this);

        //Functions
        getDay();
        submitButtonOnClickListener();
        bottomNavOnNavItemSelectedListener();
        fillRecyclerView();
    }

    private void getDay()
    {
        camefrom = getIntent();
        String day = camefrom.getStringExtra("Day");
        tv_j_mealPlan_day.setText(day);
    }

    private void submitButtonOnClickListener()
    {
        btn_j_mealPlan_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MealPlan.this, HomePage.class));
            }
        });
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_mealPlan_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(MealPlan.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(MealPlan.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(MealPlan.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(MealPlan.this, GroceryList.class));
                    return true;
                }
                else if (navItem == R.id.profile)
                {
                    startActivity(new Intent(MealPlan.this, UserProfile.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void fillRecyclerView()
    {
        //Create a new recipe arraylist that only contains breakfast recipes
        ArrayList<Recipe> breakfastRecipes = RecipeList.getInstance().getRecipesByMealTime(1);
        ArrayList<Recipe> lunchRecipes = RecipeList.getInstance().getRecipesByMealTime(2);
        ArrayList<Recipe> dinnerRecipes = RecipeList.getInstance().getRecipesByMealTime(3);

        //Check if adapters are null
        if (rv_j_mealPlan_breakfast.getAdapter() == null || rv_j_mealPlan_lunch.getAdapter() == null || rv_j_mealPlan_dinner.getAdapter() == null)
        {
            //Breakfast
            if (rv_j_mealPlan_breakfast.getAdapter() == null)
            {
                MealPlanAdapter breakfastAdapter = new MealPlanAdapter(this, breakfastRecipes, dbHelper);
                rv_j_mealPlan_breakfast.setAdapter(breakfastAdapter);
                rv_j_mealPlan_breakfast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            } else
            {
                rv_j_mealPlan_breakfast.getAdapter().notifyDataSetChanged();
            }
            //Lunch
            if (rv_j_mealPlan_lunch.getAdapter() == null)
            {
                MealPlanAdapter lunchAdapter = new MealPlanAdapter(this, lunchRecipes, dbHelper);
                rv_j_mealPlan_lunch.setAdapter(lunchAdapter);
                rv_j_mealPlan_lunch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            } else
            {
                rv_j_mealPlan_lunch.getAdapter().notifyDataSetChanged();
            }
            //Dinner
            if (rv_j_mealPlan_dinner.getAdapter() == null)
            {
                MealPlanAdapter dinnerAdapter = new MealPlanAdapter(this, lunchRecipes, dbHelper);
                rv_j_mealPlan_dinner.setAdapter(dinnerAdapter);
                rv_j_mealPlan_dinner.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            } else
            {
                rv_j_mealPlan_dinner.getAdapter().notifyDataSetChanged();
            }
        }
    }
}