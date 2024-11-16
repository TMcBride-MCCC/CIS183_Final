package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Recipes extends AppCompatActivity
{
    //Create JAVA variables
    Button btn_j_recipes_filter;
    ListView lv_j_recipes_recipeList;
    Button btn_j_recipes_newRecipe;
    BottomNavigationView bnv_j_recipes_bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipes);

        //Connect JAVA variables to GUI variables
        btn_j_recipes_filter = findViewById(R.id.btn_v_recipes_filter);
        lv_j_recipes_recipeList = findViewById(R.id.lv_v_recipes_recipeList);
        btn_j_recipes_newRecipe = findViewById(R.id.btn_v_recipes_newRecipe);
        bnv_j_recipes_bottomNav = findViewById(R.id.bnv_v_recipes_bottomNav);

        //Set the navigation bar icon
        bnv_j_recipes_bottomNav.setSelectedItemId(R.id.recipes);

        //Functions
        bottomNavOnNavItemSelectedListener();
        newRecipeButtonClickListener();
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_recipes_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(Recipes.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(Recipes.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(Recipes.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(Recipes.this, GroceryList.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void newRecipeButtonClickListener()
    {
        btn_j_recipes_newRecipe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Recipes.this, NewRecipe.class));
            }
        });
    }
}