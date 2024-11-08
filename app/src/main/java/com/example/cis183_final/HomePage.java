package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity
{
    //Create JAVA Variables
    Button btn_j_home_monday;
    Button btn_j_home_tuesday;
    Button btn_j_home_wednesday;
    Button btn_j_home_thursday;
    Button btn_j_home_friday;
    Button btn_j_home_saturday;
    Button btn_j_home_sunday;
    BottomNavigationView bnv_j_home_bottomNav;

    Intent intent_j_main_mealPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        //Connect JAVA variable to GUI Variables
        btn_j_home_monday = findViewById(R.id.btn_v_home_monday);
        btn_j_home_tuesday = findViewById(R.id.btn_v_home_tuesday);
        btn_j_home_wednesday = findViewById(R.id.btn_v_home_wednesday);
        btn_j_home_thursday = findViewById(R.id.btn_v_home_thursday);
        btn_j_home_friday = findViewById(R.id.btn_v_home_friday);
        btn_j_home_saturday = findViewById(R.id.btn_v_home_saturday);
        btn_j_home_sunday = findViewById(R.id.btn_v_home_sunday);
        bnv_j_home_bottomNav = findViewById(R.id.bnv_v_main_bottomNav);

        //Functions
        dayButtonListeners();
        bottomNavOnNavItemSelectedListener();
    }

    private void dayButtonListeners()
    {
        //MONDAY
        btn_j_home_monday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent_j_main_mealPlan = new Intent(HomePage.this, MealPlan.class);
                intent_j_main_mealPlan.putExtra("Day", "Monday");
                startActivity(intent_j_main_mealPlan);
            }
        });

        //TUESDAY
        btn_j_home_tuesday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent_j_main_mealPlan = new Intent(HomePage.this, MealPlan.class);
                intent_j_main_mealPlan.putExtra("Day", "Tuesday");
                startActivity(intent_j_main_mealPlan);
            }
        });

        //WEDNESDAY
        btn_j_home_wednesday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent_j_main_mealPlan = new Intent(HomePage.this, MealPlan.class);
                intent_j_main_mealPlan.putExtra("Day", "Wednesday");
                startActivity(intent_j_main_mealPlan);
            }
        });

        //THURSDAY
        btn_j_home_thursday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent_j_main_mealPlan = new Intent(HomePage.this, MealPlan.class);
                intent_j_main_mealPlan.putExtra("Day", "Thursday");
                startActivity(intent_j_main_mealPlan);
            }
        });

        //FRIDAY
        btn_j_home_friday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent_j_main_mealPlan = new Intent(HomePage.this, MealPlan.class);
                intent_j_main_mealPlan.putExtra("Day", "Friday");
                startActivity(intent_j_main_mealPlan);
            }
        });

        //SATURDAY
        btn_j_home_saturday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent_j_main_mealPlan = new Intent(HomePage.this, MealPlan.class);
                intent_j_main_mealPlan.putExtra("Day", "Saturday");
                startActivity(intent_j_main_mealPlan);
            }
        });

        //SUNDAY
        btn_j_home_sunday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent_j_main_mealPlan = new Intent(HomePage.this, MealPlan.class);
                intent_j_main_mealPlan.putExtra("Day", "Sunday");
                startActivity(intent_j_main_mealPlan);
            }
        });
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_home_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(HomePage.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(HomePage.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    //Put Pantry code here
                }
                else if (navItem == R.id.groceryList)
                {
                    //Put GroceryList code here
                }

                return false;
            }
        });
    }
}