package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    Button btn_j_addIngredientToPantry_addIngredientToPantry;
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
        btn_j_addIngredientToPantry_addIngredientToPantry = findViewById(R.id.btn_v_addIngredientToPantry_addIngredientToPantry);
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
        ArrayList<String> ingredientNamesNotInPantry = dbHelper.getIngredientsNotInPantry(SessionData.getLoggedInUser().getPantryId());
        ArrayAdapter<String> ingredientNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ingredientNamesNotInPantry);
        sp_j_addIngredientToPantry_ingredients.setAdapter(ingredientNamesAdapter);

        //Fill Unit spinner
        ArrayList<String> unitNames = dbHelper.getAllUnitNames();
        ArrayAdapter<String> unitNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitNames);
        sp_j_addIngredientToPantry_units.setAdapter(unitNamesAdapter);
    }

    private void addToIngredientTableClickListener()
    {
        btn_j_addIngredientToPantry_addIngredientToPantry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Grab the data
                String ingredientName = sp_j_addIngredientToPantry_ingredients.getSelectedItem().toString();
                int quantityToAdd = Integer.parseInt(et_j_addIngredientToPantry_quantity.getText().toString());
                int buyTrigger = Integer.parseInt(et_j_addIngredientToPantry_buyTrigger.getText().toString());
                String unitName = sp_j_addIngredientToPantry_units.getSelectedItem().toString();

                //Create a new pantry ingredient
                PantryIngredient ingredientToAdd = new PantryIngredient();

                //Set the pantryId from the logged in user's pantryId
                ingredientToAdd.setPantryId(SessionData.getLoggedInUser().getPantryId());
                //Set the IngredientId with dbHelper using the ingredient's name
                ingredientToAdd.setIngredientId(dbHelper.getIngredientId(ingredientName));
                ingredientToAdd.setPantryIngredientStock(quantityToAdd);
                ingredientToAdd.setPantryIngredientBuyTrigger(buyTrigger);
                ingredientToAdd.setPantryIngredientUnitId(dbHelper.getIngredientId(ingredientName));
                //Add the ingredient to the pantry ingredient list
                PantryIngredientList.getInstance().addPantryIngredient(ingredientToAdd);
                //Add the ingredient to the database
                //(pantryId, ingredientId, stock, buytrigger, unitId)
                dbHelper.createPantryIngredient(SessionData.getLoggedInUser().getPantryId(), dbHelper.getIngredientId(ingredientName), quantityToAdd, buyTrigger, dbHelper.getIngredientId(ingredientName));

                startActivity(new Intent(AddIngredientToPantry.this, Pantry.class));
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
                else if (navItem == R.id.profile)
                {
                    startActivity(new Intent(AddIngredientToPantry.this, UserProfile.class));
                    return true;
                }

                return false;
            }
        });
    }
}