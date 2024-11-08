package com.example.cis183_final;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewRecipe extends AppCompatActivity
{
    //Create JAVA variables
    Spinner sp_j_newRecipe_mealTime;
    EditText et_j_newRecipe_recipeName;
    //Ingredient line #1
    Spinner sp_j_newRecipe_ingredient1;
    Spinner sp_j_newRecipe_quantity1;
    Spinner sp_j_newRecipe_unit1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_recipe);

        //Connect JAVA variables to GUI variables

    }
}