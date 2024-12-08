package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class AddIngredient extends AppCompatActivity
{
    //Create JAVA variables
    EditText et_j_addIngredient_name;
    Spinner sp_j_addIngredient_category;
    Button btn_j_addIngredient_addIngredient;
    BottomNavigationView bnv_j_addIngredient_bottomNav;
    TextView tv_j_addIngredient_nameError;
    boolean validIngredientName = false;
    DatabaseHelper dbHelper;

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
        tv_j_addIngredient_nameError = findViewById(R.id.tv_v_addIngredient_nameError);

        //Hide the error message
        tv_j_addIngredient_nameError.setVisibility(View.INVISIBLE);

        //DATABASE
        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);

        //Set the navigation bar icon
        bnv_j_addIngredient_bottomNav.setSelectedItemId(R.id.pantry);

        //Functions
        ingredientNameTextChangedListener();
        fillSpinner();
        bottomNavOnNavItemSelectedListener();
        addIngredientButtonClickListener();
    }

    private void ingredientNameTextChangedListener()
    {
        et_j_addIngredient_name.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                validIngredientName = checkForValidIngredientNames(et_j_addIngredient_name.getText().toString());

                if (validIngredientName)
                {
                    //No error
                    tv_j_addIngredient_nameError.setVisibility(View.INVISIBLE);
                    btn_j_addIngredient_addIngredient.setEnabled(true);
                }
                else
                {
                    //Error
                    tv_j_addIngredient_nameError.setVisibility(View.VISIBLE);
                    btn_j_addIngredient_addIngredient.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    //Function checks against the ingredientNames in the Ingredients table to make sure the new ingredient is new
    //PROBLEM: The function does not check against capitalization or plurality...
    private boolean checkForValidIngredientNames(String username)
    {
        ArrayList<String> listOfIngredientNames = dbHelper.getAllIngredientNames();

        for (int i = 0; i < listOfIngredientNames.size(); i++)
        {
            if (listOfIngredientNames.get(i).equals(username))
            {
                return false;
            }
        }
        return true;
    }

    private void fillSpinner()
    {
        //Grab the categoryNames arraylist from database
        ArrayList<String> categoryNames = dbHelper.getAllCategoryNames();

        //Fill the spinner
        ArrayAdapter<String> categoryNamesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryNames);
        sp_j_addIngredient_category.setAdapter(categoryNamesAdapter);
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
                startActivity(new Intent(AddIngredient.this, AddIngredientToPantry.class));
            }
        });
    }
}