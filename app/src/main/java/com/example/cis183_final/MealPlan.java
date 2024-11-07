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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MealPlan extends AppCompatActivity
{
    //Create JAVA variables
    TextView tv_j_mealPlan_day;
    HorizontalScrollView hsv_j_mealPlan_breakfast;
    HorizontalScrollView hsv_j_mealPlan_lunch;
    HorizontalScrollView hsv_j_mealPlan_dinner;
    Button btn_j_mealPlan_submit;
    BottomNavigationView bnv_j_mealPlan_bottomNav;

    Intent camefrom;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_plan);

        //Connect JAVA variables to GUI variables
        tv_j_mealPlan_day = findViewById(R.id.tv_v_mealPlan_day);
        hsv_j_mealPlan_breakfast = findViewById(R.id.hsv_v_mealPlan_breakfast);
        hsv_j_mealPlan_lunch = findViewById(R.id.hsv_v_mealPlan_lunch);
        hsv_j_mealPlan_dinner = findViewById(R.id.hsv_v_mealPlan_dinner);
        btn_j_mealPlan_submit = findViewById(R.id.btn_v_mealPlan_submit);
        bnv_j_mealPlan_bottomNav = findViewById(R.id.bnv_v_mealPlan_bottomNav);

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
                    //Put Recipe code here
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