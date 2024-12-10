package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Pantry extends AppCompatActivity
{
    //Create JAVA variables
    ListView lv_j_pantry_listOfIngredients;
    Button btn_j_pantry_addIngredient;
    BottomNavigationView bnv_j_pantry_bottomNav;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantry);

        //Connect JAVA variables to GUI variables
        lv_j_pantry_listOfIngredients = findViewById(R.id.lv_v_pantry_listOfIngredients);
        btn_j_pantry_addIngredient = findViewById(R.id.btn_v_pantry_addIngredientToPantry);
        bnv_j_pantry_bottomNav = findViewById(R.id.bnv_v_pantry_bottomNav);

        //Set the navigation bar icon
        bnv_j_pantry_bottomNav.setSelectedItemId(R.id.pantry);

        //DATABASE
        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);

        //Functions
        bottomNavOnNavItemSelectedListener();
        addIngredientButtonClickListener();
        pantryIngredientListViewOnItemClickListener();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        dbHelper.fillPantryIngredientsArrayListGivenUserPantryId(SessionData.getLoggedInUser().getPantryId());
        fillListView();
    }

    private void pantryIngredientListViewOnItemClickListener()
    {
        lv_j_pantry_listOfIngredients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //Remove ingredient from database
                PantryIngredient ingredientToDelete = PantryIngredientList.getInstance().getPantryIngredients().get(i);
                dbHelper.deletePantryIngredient(SessionData.getLoggedInUser().getPantryId(), ingredientToDelete.getIngredientId());

                //Remove ingredient from list
                PantryIngredientList.getInstance().getPantryIngredients().remove(i);
                PantryIngredientList.getInstance().getPantryListAdapter().notifyDataSetChanged();

                return true;
            }
        });
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_pantry_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(Pantry.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(Pantry.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(Pantry.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(Pantry.this, GroceryList.class));
                    return true;
                }
                else if (navItem == R.id.profile)
                {
                    startActivity(new Intent(Pantry.this, UserProfile.class));
                    return true;
                }

                return false;
            }
        });
    }

    private void addIngredientButtonClickListener()
    {
        btn_j_pantry_addIngredient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Pantry.this, AddIngredientToPantry.class));
            }
        });
    }

    private void fillListView()
    {
        if (PantryIngredientList.getInstance().getPantryListAdapter() == null)
        {
            PantryListAdapter adapter = new PantryListAdapter(this, PantryIngredientList.getInstance().getPantryIngredients(), dbHelper);
            PantryIngredientList.getInstance().setPantryListAdapter(adapter);
            lv_j_pantry_listOfIngredients.setAdapter(adapter);
        }
        else
        {
            lv_j_pantry_listOfIngredients.setAdapter(PantryIngredientList.getInstance().getPantryListAdapter());
        }

        Log.d("Pantry filllistview()", "listview filled with " + PantryIngredientList.getInstance().getPantryIngredients().size() + " ingredients");
    }
}