package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewRecipeInstructions extends AppCompatActivity
{
    //Create JAVA variables
    TextView tv_j_newRecipeInstructions_recipeName;
    EditText et_j_newRecipeInstructions_instructions;
    Button btn_j_newRecipeInstructions_addInstructions;
    Button btn_j_newRecipeInstructions_doNotAddInstructions;

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

        //Functions
        addInstructionsButtonClickListener();
        doNotAddInstructionsButtonClickListener();
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