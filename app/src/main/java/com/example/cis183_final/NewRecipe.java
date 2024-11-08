package com.example.cis183_final;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    //Ingredient line #2
    private boolean line2IsShowing;
    Button btn_j_newRecipe_deleteIngredientLine2;
    Button btn_j_newRecipe_addIngredientLine2;
    Spinner sp_j_newRecipe_ingredient2;
    Spinner sp_j_newRecipe_quantity2;
    Spinner sp_j_newRecipe_unit2;
    //Ingredient line #3
    Button btn_j_newRecipe_deleteIngredientLine3;
    Button btn_j_newRecipe_addIngredientLine3;
    Spinner sp_j_newRecipe_ingredient3;
    Spinner sp_j_newRecipe_quantity3;
    Spinner sp_j_newRecipe_unit3;
    //Ingredient line #4
    Button btn_j_newRecipe_deleteIngredientLine4;
    Button btn_j_newRecipe_addIngredientLine4;
    Spinner sp_j_newRecipe_ingredient4;
    Spinner sp_j_newRecipe_quantity4;
    Spinner sp_j_newRecipe_unit4;
    //Ingredient line #5
    Button btn_j_newRecipe_deleteIngredientLine5;
    Button btn_j_newRecipe_addIngredientLine5;
    Spinner sp_j_newRecipe_ingredient5;
    Spinner sp_j_newRecipe_quantity5;
    Spinner sp_j_newRecipe_unit5;
    //Ingredient line #6
    Button btn_j_newRecipe_deleteIngredientLine6;
    Button btn_j_newRecipe_addIngredientLine6;
    Spinner sp_j_newRecipe_ingredient6;
    Spinner sp_j_newRecipe_quantity6;
    Spinner sp_j_newRecipe_unit6;
    //Ingredient line #7
    Button btn_j_newRecipe_deleteIngredientLine7;
    Button btn_j_newRecipe_addIngredientLine7;
    Spinner sp_j_newRecipe_ingredient7;
    Spinner sp_j_newRecipe_quantity7;
    Spinner sp_j_newRecipe_unit7;
    //Ingredient line #8
    Button btn_j_newRecipe_deleteIngredientLine8;
    Button btn_j_newRecipe_addIngredientLine8;
    Spinner sp_j_newRecipe_ingredient8;
    Spinner sp_j_newRecipe_quantity8;
    Spinner sp_j_newRecipe_unit8;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_recipe);

        //Connect JAVA variables to GUI variables
        sp_j_newRecipe_mealTime = findViewById(R.id.sp_v_newRecipe_mealTime);
        et_j_newRecipe_recipeName = findViewById(R.id.et_v_newRecipe_recipeName);
        //Ingredient line #1
        sp_j_newRecipe_ingredient1 = findViewById(R.id.sp_v_newRecipe_ingredient1);
        sp_j_newRecipe_quantity1 = findViewById(R.id.sp_v_newRecipe_quantity1);
        sp_j_newRecipe_unit1 = findViewById(R.id.sp_v_newRecipe_unit1);
        //Ingredient line #2
        btn_j_newRecipe_deleteIngredientLine2 = findViewById(R.id.btn_v_newRecipe_deleteIngredientLine2);
        btn_j_newRecipe_addIngredientLine2 = findViewById(R.id.btn_v_newRecipe_addIngredientLine2);
        sp_j_newRecipe_ingredient2 = findViewById(R.id.sp_v_newRecipe_ingredient2);
        sp_j_newRecipe_quantity2 = findViewById(R.id.sp_v_newRecipe_quantity2);
        sp_j_newRecipe_unit2 = findViewById(R.id.sp_v_newRecipe_unit2);
        //Ingredient line #3
        btn_j_newRecipe_deleteIngredientLine3 = findViewById(R.id.btn_v_newRecipe_deleteIngredientLine3);
        btn_j_newRecipe_addIngredientLine3 = findViewById(R.id.btn_v_newRecipe_addIngredientLine3);
        sp_j_newRecipe_ingredient3 = findViewById(R.id.sp_v_newRecipe_ingredient3);
        sp_j_newRecipe_quantity3 = findViewById(R.id.sp_v_newRecipe_quantity3);
        sp_j_newRecipe_unit3 = findViewById(R.id.sp_v_newRecipe_unit3);
        //Ingredient line #4
        btn_j_newRecipe_deleteIngredientLine4 = findViewById(R.id.btn_v_newRecipe_deleteIngredientLine4);
        btn_j_newRecipe_addIngredientLine4 = findViewById(R.id.btn_v_newRecipe_addIngredientLine4);
        sp_j_newRecipe_ingredient4 = findViewById(R.id.sp_v_newRecipe_ingredient4);
        sp_j_newRecipe_quantity4 = findViewById(R.id.sp_v_newRecipe_quantity4);
        sp_j_newRecipe_unit4 = findViewById(R.id.sp_v_newRecipe_unit4);
        //Ingredient line #5
        btn_j_newRecipe_deleteIngredientLine5 = findViewById(R.id.btn_v_newRecipe_deleteIngredientLine5);
        btn_j_newRecipe_addIngredientLine5 = findViewById(R.id.btn_v_newRecipe_addIngredientLine5);
        sp_j_newRecipe_ingredient5 = findViewById(R.id.sp_v_newRecipe_ingredient5);
        sp_j_newRecipe_quantity5 = findViewById(R.id.sp_v_newRecipe_quantity5);
        sp_j_newRecipe_unit5 = findViewById(R.id.sp_v_newRecipe_unit5);
        //Ingredient line #6
        btn_j_newRecipe_deleteIngredientLine6 = findViewById(R.id.btn_v_newRecipe_deleteIngredientLine6);
        btn_j_newRecipe_addIngredientLine6 = findViewById(R.id.btn_v_newRecipe_addIngredientLine6);
        sp_j_newRecipe_ingredient6 = findViewById(R.id.sp_v_newRecipe_ingredient6);
        sp_j_newRecipe_quantity6 = findViewById(R.id.sp_v_newRecipe_quantity6);
        sp_j_newRecipe_unit6 = findViewById(R.id.sp_v_newRecipe_unit6);
        //Ingredient line #7
        btn_j_newRecipe_deleteIngredientLine7 = findViewById(R.id.btn_v_newRecipe_deleteIngredientLine7);
        btn_j_newRecipe_addIngredientLine7 = findViewById(R.id.btn_v_newRecipe_addIngredientLine7);
        sp_j_newRecipe_ingredient7 = findViewById(R.id.sp_v_newRecipe_ingredient7);
        sp_j_newRecipe_quantity7 = findViewById(R.id.sp_v_newRecipe_quantity7);
        sp_j_newRecipe_unit7 = findViewById(R.id.sp_v_newRecipe_unit7);
        //Ingredient line #8
        btn_j_newRecipe_deleteIngredientLine8 = findViewById(R.id.btn_v_newRecipe_deleteIngredientLine8);
        btn_j_newRecipe_addIngredientLine8 = findViewById(R.id.btn_v_newRecipe_addIngredientLine8);
        sp_j_newRecipe_ingredient8 = findViewById(R.id.sp_v_newRecipe_ingredient8);
        sp_j_newRecipe_quantity8 = findViewById(R.id.sp_v_newRecipe_quantity8);
        sp_j_newRecipe_unit8 = findViewById(R.id.sp_v_newRecipe_unit8);

        //Functions
        onStartSetButtonsInvisible();
        onStartSetSpinnersInvisible();
        line2ButtonClickListener();
    }

    private void onStartSetButtonsInvisible()
    {
        //Ingredient line #3
        btn_j_newRecipe_deleteIngredientLine3.setVisibility(View.INVISIBLE);
        btn_j_newRecipe_addIngredientLine3.setVisibility(View.INVISIBLE);
        //Ingredient line #4
        btn_j_newRecipe_deleteIngredientLine4.setVisibility(View.INVISIBLE);
        btn_j_newRecipe_addIngredientLine4.setVisibility(View.INVISIBLE);
        //Ingredient line #5
        btn_j_newRecipe_deleteIngredientLine5.setVisibility(View.INVISIBLE);
        btn_j_newRecipe_addIngredientLine5.setVisibility(View.INVISIBLE);
        //Ingredient line #6
        btn_j_newRecipe_deleteIngredientLine6.setVisibility(View.INVISIBLE);
        btn_j_newRecipe_addIngredientLine6.setVisibility(View.INVISIBLE);
        //Ingredient line #7
        btn_j_newRecipe_deleteIngredientLine7.setVisibility(View.INVISIBLE);
        btn_j_newRecipe_addIngredientLine7.setVisibility(View.INVISIBLE);
        //Ingredient line #8
        btn_j_newRecipe_deleteIngredientLine8.setVisibility(View.INVISIBLE);
        btn_j_newRecipe_addIngredientLine8.setVisibility(View.INVISIBLE);
    }

    private void onStartSetSpinnersInvisible()
    {
        //Ingredient line #2
        sp_j_newRecipe_ingredient2.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_quantity2.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_unit2.setVisibility(View.INVISIBLE);
        //Ingredient line #3
        sp_j_newRecipe_ingredient3.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_quantity3.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_unit3.setVisibility(View.INVISIBLE);
        //Ingredient line #4
        sp_j_newRecipe_ingredient4.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_quantity4.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_unit4.setVisibility(View.INVISIBLE);
        //Ingredient line #5
        sp_j_newRecipe_ingredient5.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_quantity5.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_unit5.setVisibility(View.INVISIBLE);
        //Ingredient line #6
        sp_j_newRecipe_ingredient6.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_quantity6.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_unit6.setVisibility(View.INVISIBLE);
        //Ingredient line #7
        sp_j_newRecipe_ingredient7.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_quantity7.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_unit7.setVisibility(View.INVISIBLE);
        //Ingredient line #8
        sp_j_newRecipe_ingredient8.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_quantity8.setVisibility(View.INVISIBLE);
        sp_j_newRecipe_unit8.setVisibility(View.INVISIBLE);
    }

    private void line2ButtonClickListener()
    {
        btn_j_newRecipe_deleteIngredientLine2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (line2IsShowing)
                {

                }
            }
        });
    }
}