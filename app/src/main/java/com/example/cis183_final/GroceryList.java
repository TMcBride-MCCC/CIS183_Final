package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GroceryList extends AppCompatActivity
{
    //Create JAVA variables
    ListView lv_j_groceryList_listOfGroceries;
    BottomNavigationView bnv_j_groceryList_bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grocery_list);

        //Connect Java variables to GUI variables
        lv_j_groceryList_listOfGroceries = findViewById(R.id.lv_v_groceryList_listOfGroceries);
        bnv_j_groceryList_bottomNav = findViewById(R.id.bnv_v_groceryList_bottomNav);

        //Functions
        bottomNavOnNavItemSelectedListener();
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_groceryList_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(GroceryList.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(GroceryList.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(GroceryList.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(GroceryList.this, GroceryList.class));
                    return true;
                }

                return false;
            }
        });
    }
}