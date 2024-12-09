package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class EditProfile extends AppCompatActivity
{
    //Create JAVA variables
    EditText et_j_editProfile_username;
    EditText et_j_editProfile_password;
    EditText et_j_editProfile_fName;
    EditText et_j_editProfile_lName;
    EditText et_j_editProfile_email;
    TextView tv_j_editProfile_householdName;
    TextView tv_j_editProfile_usernameError;
    TextView tv_j_editProfile_emailError;
    RadioGroup rg_j_editProfile_pantryGroup;
    RadioButton rb_j_editProfile_joinPantry;
    RadioButton rb_j_editProfile_newPantry;
    Spinner sp_j_editProfile_households;
    EditText et_j_editProfile_newPantryHouseName;
    Button btn_v_editProfile_submit;
    BottomNavigationView bnv_j_editProfile_bottomNav;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        //Connect JAVA variables to GUI variables
        et_j_editProfile_username = findViewById(R.id.et_v_editProfile_username);
        et_j_editProfile_password = findViewById(R.id.et_v_editProfile_password);
        et_j_editProfile_fName = findViewById(R.id.et_v_editProfile_fName);
        et_j_editProfile_lName = findViewById(R.id.et_v_editProfile_lName);
        et_j_editProfile_email = findViewById(R.id.et_v_editProfile_email);
        tv_j_editProfile_householdName = findViewById(R.id.tv_v_editProfile_householdName);
        tv_j_editProfile_usernameError = findViewById(R.id.tv_v_editProfile_usernameError);
        tv_j_editProfile_emailError = findViewById(R.id.tv_v_editProfile_emailError);
        rg_j_editProfile_pantryGroup = findViewById(R.id.rg_v_editProfile_pantryGroup);
        rb_j_editProfile_joinPantry = findViewById(R.id.rb_v_editProfile_joinPantry);
        rb_j_editProfile_newPantry = findViewById(R.id.rb_v_editProfile_newPantry);
        sp_j_editProfile_households = findViewById(R.id.sp_v_editProfile_households);
        et_j_editProfile_newPantryHouseName = findViewById(R.id.et_v_editProfile_newPantryHouseName);
        btn_v_editProfile_submit = findViewById(R.id.btn_v_editProfile_submit);
        bnv_j_editProfile_bottomNav = findViewById(R.id.bnv_v_editProfile_bottomNav);

        //Hide the error messages
        tv_j_editProfile_usernameError.setVisibility(View.INVISIBLE);
        tv_j_editProfile_emailError.setVisibility(View.INVISIBLE);

        //Hide household spinner and textbox
        sp_j_editProfile_households.setVisibility(View.INVISIBLE);
        et_j_editProfile_newPantryHouseName.setVisibility(View.INVISIBLE);

        //Set the navigation bar icon
        bnv_j_editProfile_bottomNav.setSelectedItemId(R.id.profile);

        //DATABASE
        dbHelper = new DatabaseHelper(this);

        //Functions
        bottomNavOnNavItemSelectedListener();
        fillEditTextsWithUserInfo();
        fillSpinner();
        radioButtonClickListener();
        submitButtonClickListener();
    }

    private void fillEditTextsWithUserInfo()
    {
        et_j_editProfile_username.setText(SessionData.getLoggedInUser().getUsername());
        et_j_editProfile_password.setText(SessionData.getLoggedInUser().getPassword());
        et_j_editProfile_fName.setText(SessionData.getLoggedInUser().getfName());
        et_j_editProfile_lName.setText(SessionData.getLoggedInUser().getlName());
        et_j_editProfile_email.setText(SessionData.getLoggedInUser().getEmail());
        tv_j_editProfile_householdName.setText(dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId()));
    }

    private void fillSpinner()
    {
        //Grab the household arraylist
        ArrayList<String> households = dbHelper.getHouseNames();

        //Fill the spinner
        ArrayAdapter<String> householdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, households);
        sp_j_editProfile_households.setAdapter(householdAdapter);
    }

    private void radioButtonClickListener()
    {
        rg_j_editProfile_pantryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if (rb_j_editProfile_joinPantry.isChecked())
                {
                    sp_j_editProfile_households.setVisibility(View.VISIBLE);
                    et_j_editProfile_newPantryHouseName.setVisibility(View.INVISIBLE);
                }
                if (rb_j_editProfile_newPantry.isChecked())
                {
                    et_j_editProfile_newPantryHouseName.setVisibility(View.VISIBLE);
                    sp_j_editProfile_households.setVisibility(View.INVISIBLE);
                    sp_j_editProfile_households.equals("");
                }
            }
        });
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_editProfile_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(EditProfile.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(EditProfile.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(EditProfile.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(EditProfile.this, GroceryList.class));
                    return true;
                }
                else if (navItem == R.id.profile)
                {
                    startActivity(new Intent(EditProfile.this, UserProfile.class));
                    return true;
                }

                return false;
            }
        });
    }
}