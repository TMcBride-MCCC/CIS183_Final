package com.example.cis183_final;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Pantry extends AppCompatActivity
{
    //Create JAVA variables
    ListView lv_j_pantry_listOfIngredients;
    Button btn_j_pantry_addIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantry);

        //Connect JAVA variables to GUI variables
        lv_j_pantry_listOfIngredients = findViewById(R.id.lv_v_pantry_listOfIngredients);
        btn_j_pantry_addIngredient = findViewById(R.id.btn_v_pantry_addIngredient);

        //Functions
        addIngredientButtonClickListener();
    }

    private void addIngredientButtonClickListener()
    {
        btn_j_pantry_addIngredient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }
}