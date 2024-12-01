package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        //LUNCH

        //DINNER

        //DATABASE
        dbHelper = new DatabaseHelper(this);


        //Functions
        getDay();
        submitButtonOnClickListener();
        bottomNavOnNavItemSelectedListener();
    }

    private void getDay()
    {
        camefrom = getIntent();
        String day = camefrom.getStringExtra("Day");
        tv_j_mealPlan_day.setText(day);
    }

    private void loadBreakfastRecipes()
    {
        //String recipeName = reci
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

                return false;
            }
        });
    }
}