package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddIngredient extends AppCompatActivity
{
    //Create JAVA variables
    EditText et_j_addIngredient_name;
    Spinner sp_j_addIngredient_category;
    Button btn_j_addIngredient_addIngredient;
    BottomNavigationView bnv_j_addIngredient_bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ingredient);

        //Connect JAVA variables to GUI variables
        et_j_addIngredient_name = findViewById(R.id.et_v_addIngredient_name);
        sp_j_addIngredient_category = findViewById(R.id.sp_v_addIngredient_category);
        btn_j_addIngredient_addIngredient = findViewById(R.id.btn_v_addIngredient_addIngredient);
        bnv_j_addIngredient_bottomNav = findViewById(R.id.bnv_v_addIngredient_bottomNav);

        //Functions
        bottomNavOnNavItemSelectedListener();
        addIngredientButtonClickListener();
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_addIngredient_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(AddIngredient.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(AddIngredient.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(AddIngredient.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(AddIngredient.this, GroceryList.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void addIngredientButtonClickListener()
    {
        btn_j_addIngredient_addIngredient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AddIngredient.this, Pantry.class));
            }
        });
    }
}