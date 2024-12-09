package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NewRecipeInstructions extends AppCompatActivity
{
    //Create JAVA variables
    TextView tv_j_newRecipeInstructions_recipeName;
    EditText et_j_newRecipeInstructions_instructions;
    Button btn_j_newRecipeInstructions_addInstructions;
    Button btn_j_newRecipeInstructions_doNotAddInstructions;
    BottomNavigationView bnv_j_newRecipeInstructions_bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_recipe_instructions);

        //Connect JAVA variables to GUI variables
        tv_j_newRecipeInstructions_recipeName = findViewById(R.id.tv_v_newRecipeInstructions_recipeName);
        et_j_newRecipeInstructions_instructions = findViewById(R.id.et_v_newRecipeInstructions_instructions);
        btn_j_newRecipeInstructions_addInstructions = findViewById(R.id.btn_v_newRecipeInstructions_addInstructions);
        btn_j_newRecipeInstructions_doNotAddInstructions = findViewById(R.id.btn_v_newRecipeInstructions_doNotAddInstructions);
        bnv_j_newRecipeInstructions_bottomNav = findViewById(R.id.bnv_v_newRecipeInstructions_bottomNav);

        //Set the navigation bar icon
        bnv_j_newRecipeInstructions_bottomNav.setSelectedItemId(R.id.recipes);

        //Functions
        bottomNavOnNavItemSelectedListener();
        addInstructionsButtonClickListener();
        doNotAddInstructionsButtonClickListener();
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_newRecipeInstructions_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(NewRecipeInstructions.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(NewRecipeInstructions.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(NewRecipeInstructions.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(NewRecipeInstructions.this, GroceryList.class));
                    return true;
                }
                else if (navItem == R.id.profile)
                {
                    startActivity(new Intent(NewRecipeInstructions.this, UserProfile.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void addInstructionsButtonClickListener()
    {
        btn_j_newRecipeInstructions_addInstructions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(NewRecipeInstructions.this, NewRecipe.class));
            }
        });
    }

    private void doNotAddInstructionsButtonClickListener()
    {
        btn_j_newRecipeInstructions_doNotAddInstructions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(NewRecipeInstructions.this, NewRecipe.class));
            }
        });
    }
}