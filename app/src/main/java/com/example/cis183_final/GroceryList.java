package com.example.cis183_final;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GroceryList extends AppCompatActivity
{
    //Create JAVA variables
    ListView lv_j_groceryList_listOfGroceries;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grocery_list);

        //Connect Java variables to GUI variables
        lv_j_groceryList_listOfGroceries = findViewById(R.id.lv_v_groceryList_listOfGroceries);

        //Functions
    }
}