package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AddIngredientToPantry extends AppCompatActivity
{
    //Create JAVA variables
    TextView tv_j_addIngredientToPantry_houseName;
    Spinner sp_j_addIngredientToPantry_ingredients;
    TextView tv_j_addIngredientToPantry_addIngredientToIngredientTable;
    EditText et_j_addIngredientToPantry_quantity;
    Spinner sp_j_addIngredientToPantry_units;
    EditText et_j_addIngredientToPantry_buyTrigger;
    Button btn_j_addIngredientToPantry_addIngredient;
    BottomNavigationView bnv_j_addIngredientToPantry_bottomNav;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ingredient_to_pantry);

        //Connect JAVA variables to GUI variables
        tv_j_addIngredientToPantry_houseName = findViewById(R.id.tv_v_addIngredientToPantry_houseName);
        sp_j_addIngredientToPantry_ingredients = findViewById(R.id.sp_v_addIngredientToPantry_ingredients);
        tv_j_addIngredientToPantry_addIngredientToIngredientTable = findViewById(R.id.tv_v_addIngredientToPantry_addIngredientToIngredientTable);
        et_j_addIngredientToPantry_quantity = findViewById(R.id.et_v_addIngredientToPantry_quantity);
        sp_j_addIngredientToPantry_units = findViewById(R.id.sp_v_addIngredientToPantry_units);
        et_j_addIngredientToPantry_buyTrigger = findViewById(R.id.et_v_addIngredientToPantry_buyTrigger);
        btn_j_addIngredientToPantry_addIngredient = findViewById(R.id.btn_v_addIngredientToPantry_addIngredient);
        bnv_j_addIngredientToPantry_bottomNav = findViewById(R.id.bnv_v_addIngredientToPantry_bottomNav);

        //Set the navigation bar icon
        bnv_j_addIngredientToPantry_bottomNav.setSelectedItemId(R.id.pantry);

        //DATABASE
        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);

        //Functions
        changeHouseNameBasedOnUser();
        fillSpinners();
        bottomNavOnNavItemSelectedListener();
        addToIngredientTableClickListener();

    }

    private void changeHouseNameBasedOnUser()
    {
        tv_j_addIngredientToPantry_houseName.setText("To the " + dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId()) + " house");
    }

    private void fillSpinners()
    {
        //Fill Ingredient spinner
        ArrayList<String> ingredientNames = dbHelper.getAllIngredientNames();
        ArrayAdapter<String> ingredientNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredientNames);
        sp_j_addIngredientToPantry_ingredients.setAdapter(ingredientNamesAdapter);

        //Fill Unit spinner
        ArrayList<String> unitNames = dbHelper.getAllUnitNames();
        ArrayAdapter<String> unitNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitNames);
        sp_j_addIngredientToPantry_units.setAdapter(unitNamesAdapter);
    }

    private void addToIngredientTableClickListener()
    {
        tv_j_addIngredientToPantry_addIngredientToIngredientTable.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AddIngredientToPantry.this, AddIngredient.class));
            }
        });
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_addIngredientToPantry_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(AddIngredientToPantry.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(AddIngredientToPantry.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(AddIngredientToPantry.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(AddIngredientToPantry.this, GroceryList.class));
                    return true;
                }

                return false;
            }
        });
    }
}