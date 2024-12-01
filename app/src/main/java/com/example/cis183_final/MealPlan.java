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
    HorizontalScrollView hsv_j_mealPlan_breakfast;
    CardView cv_j_mealPlan_breakfast1;
    TextView tv_j_mealPlan_breakfast1;
    CardView cv_j_mealPlan_breakfast2;
    TextView tv_j_mealPlan_breakfast2;
    CardView cv_j_mealPlan_breakfast3;
    TextView tv_j_mealPlan_breakfast3;
    CardView cv_j_mealPlan_breakfast4;
    TextView tv_j_mealPlan_breakfast4;
    CardView cv_j_mealPlan_breakfast5;
    TextView tv_j_mealPlan_breakfast5;
    //LUNCH
    HorizontalScrollView hsv_j_mealPlan_lunch;
    CardView cv_j_mealPlan_lunch1;
    TextView tv_j_mealPlan_lunch1;
    CardView cv_j_mealPlan_lunch2;
    TextView tv_j_mealPlan_lunch2;
    CardView cv_j_mealPlan_lunch3;
    TextView tv_j_mealPlan_lunch3;
    CardView cv_j_mealPlan_lunch4;
    TextView tv_j_mealPlan_lunch4;
    CardView cv_j_mealPlan_lunch5;
    TextView tv_j_mealPlan_lunch5;
    //DINNER
    HorizontalScrollView hsv_j_mealPlan_dinner;
    CardView cv_j_mealPlan_dinner1;
    TextView tv_j_mealPlan_dinner1;
    CardView cv_j_mealPlan_dinner2;
    TextView tv_j_mealPlan_dinner2;
    CardView cv_j_mealPlan_dinner3;
    TextView tv_j_mealPlan_dinner3;
    CardView cv_j_mealPlan_dinner4;
    TextView tv_j_mealPlan_dinner4;
    CardView cv_j_mealPlan_dinner5;
    TextView tv_j_mealPlan_dinner5;

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
        hsv_j_mealPlan_breakfast = findViewById(R.id.hsv_v_mealPlan_breakfast);
        cv_j_mealPlan_breakfast1 = findViewById(R.id.cv_v_mealPlan_breakfast1);
        tv_j_mealPlan_breakfast1 = findViewById(R.id.tv_v_mealPlan_breakfast1);
        cv_j_mealPlan_breakfast2 = findViewById(R.id.cv_v_mealPlan_breakfast2);
        tv_j_mealPlan_breakfast2 = findViewById(R.id.tv_v_mealPlan_breakfast2);
        cv_j_mealPlan_breakfast3 = findViewById(R.id.cv_v_mealPlan_breakfast3);
        tv_j_mealPlan_breakfast3 = findViewById(R.id.tv_v_mealPlan_breakfast3);
        cv_j_mealPlan_breakfast4 = findViewById(R.id.cv_v_mealPlan_breakfast4);
        tv_j_mealPlan_breakfast4 = findViewById(R.id.tv_v_mealPlan_breakfast4);
        cv_j_mealPlan_breakfast5 = findViewById(R.id.cv_v_mealPlan_breakfast5);
        tv_j_mealPlan_breakfast5 = findViewById(R.id.tv_v_mealPlan_breakfast5);
        //LUNCH
        hsv_j_mealPlan_lunch = findViewById(R.id.hsv_v_mealPlan_lunch);
        cv_j_mealPlan_lunch1 = findViewById(R.id.cv_v_mealPlan_lunch1);
        tv_j_mealPlan_lunch1 = findViewById(R.id.tv_v_mealPlan_lunch1);
        cv_j_mealPlan_lunch2 = findViewById(R.id.cv_v_mealPlan_lunch2);
        tv_j_mealPlan_lunch2 = findViewById(R.id.tv_v_mealPlan_lunch2);
        cv_j_mealPlan_lunch3 = findViewById(R.id.cv_v_mealPlan_lunch3);
        tv_j_mealPlan_lunch3 = findViewById(R.id.tv_v_mealPlan_lunch3);
        cv_j_mealPlan_lunch4 = findViewById(R.id.cv_v_mealPlan_lunch4);
        tv_j_mealPlan_lunch4 = findViewById(R.id.tv_v_mealPlan_lunch4);
        cv_j_mealPlan_lunch5 = findViewById(R.id.cv_v_mealPlan_lunch5);
        tv_j_mealPlan_lunch5 = findViewById(R.id.tv_v_mealPlan_lunch5);
        //DINNER
        hsv_j_mealPlan_dinner = findViewById(R.id.hsv_v_mealPlan_dinner);
        cv_j_mealPlan_dinner1 = findViewById(R.id.cv_v_mealPlan_dinner1);
        tv_j_mealPlan_dinner1 = findViewById(R.id.tv_v_mealPlan_dinner1);
        cv_j_mealPlan_dinner2 = findViewById(R.id.cv_v_mealPlan_dinner2);
        tv_j_mealPlan_dinner2 = findViewById(R.id.tv_v_mealPlan_dinner2);
        cv_j_mealPlan_dinner3 = findViewById(R.id.cv_v_mealPlan_dinner3);
        tv_j_mealPlan_dinner3 = findViewById(R.id.tv_v_mealPlan_dinner3);
        cv_j_mealPlan_dinner4 = findViewById(R.id.cv_v_mealPlan_dinner4);
        tv_j_mealPlan_dinner4 = findViewById(R.id.tv_v_mealPlan_dinner4);
        cv_j_mealPlan_dinner5 = findViewById(R.id.cv_v_mealPlan_dinner5);
        tv_j_mealPlan_dinner5 = findViewById(R.id.tv_v_mealPlan_dinner5);
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