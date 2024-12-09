package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserProfile extends AppCompatActivity
{
    //Create JAVA variables
    TextView tv_j_userProfile_username;
    TextView tv_j_userProfile_password;
    TextView tv_j_userProfile_fName;
    TextView tv_j_userProfile_lName;
    TextView tv_j_userProfile_email;
    TextView tv_j_userProfile_household;
    Button btn_j_userProfile_edit;
    Button btn_j_userProfile_delete;
    TextView tv_j_userProfile_deletePrompt;
    Button btn_j_userProfile_deletePromptYes;
    Button btn_j_userProfile_deletePromptNo;
    BottomNavigationView bnv_j_userProfile_bottomNav;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);

        //Connect JAVA variables to GUI variables
        tv_j_userProfile_username = findViewById(R.id.tv_v_userProfile_username);
        tv_j_userProfile_password = findViewById(R.id.tv_v_userProfile_pasword);
        tv_j_userProfile_fName = findViewById(R.id.tv_v_userProfile_fName);
        tv_j_userProfile_lName = findViewById(R.id.tv_v_userProfile_lName);
        tv_j_userProfile_email = findViewById(R.id.tv_v_userProfile_email);
        btn_j_userProfile_edit = findViewById(R.id.btn_v_userProfile_edit);
        tv_j_userProfile_household = findViewById(R.id.tv_v_userProfile_household);
        btn_j_userProfile_delete = findViewById(R.id.btn_v_userProfile_delete);
        tv_j_userProfile_deletePrompt = findViewById(R.id.tv_v_userProfile_deletePrompt);
        btn_j_userProfile_deletePromptYes = findViewById(R.id.btn_v_userProfile_deletePromptYes);
        btn_j_userProfile_deletePromptNo = findViewById(R.id.btn_v_userProfile_deletePromptNo);
        bnv_j_userProfile_bottomNav = findViewById(R.id.bnv_v_userProfile_bottomNav);

        //Hide the delete GUI
        tv_j_userProfile_deletePrompt.setVisibility(View.INVISIBLE);
        btn_j_userProfile_deletePromptYes.setVisibility(View.INVISIBLE);
        btn_j_userProfile_deletePromptNo.setVisibility(View.INVISIBLE);

        //Set the navigation bar icon
        bnv_j_userProfile_bottomNav.setSelectedItemId(R.id.profile);

        //DATABASE
        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);

        //Functions
        bottomNavOnNavItemSelectedListener();
        fillUserInfo();
        editButtonClickListener();
        deleteButtonClickListener();
        deletePromptNoButtonClickListener();
        deletePromptYesButtonClickListener();
    }

    private void fillUserInfo()
    {
        tv_j_userProfile_username.setText(SessionData.getLoggedInUser().getUsername());
        tv_j_userProfile_password.setText(SessionData.getLoggedInUser().getPassword());
        tv_j_userProfile_fName.setText(SessionData.getLoggedInUser().getfName());
        tv_j_userProfile_lName.setText(SessionData.getLoggedInUser().getlName());
        tv_j_userProfile_email.setText(SessionData.getLoggedInUser().getEmail());
        tv_j_userProfile_household.setText(dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId()));
    }

    private void editButtonClickListener()
    {
        btn_j_userProfile_edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Go to Edit Page
                startActivity(new Intent(UserProfile.this, EditProfile.class));
            }
        });
    }

    private void deleteButtonClickListener()
    {
        btn_j_userProfile_delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Hide Edit & Delete Button
                btn_j_userProfile_edit.setVisibility(View.INVISIBLE);
                btn_j_userProfile_delete.setVisibility(View.INVISIBLE);
                //Show Delete GUI
                tv_j_userProfile_deletePrompt.setVisibility(View.VISIBLE);
                btn_j_userProfile_deletePromptYes.setVisibility(View.VISIBLE);
                btn_j_userProfile_deletePromptNo.setVisibility(View.VISIBLE);
            }
        });
    }

    private void deletePromptNoButtonClickListener()
    {
        btn_j_userProfile_deletePromptNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Hide the DeleteGUI
                tv_j_userProfile_deletePrompt.setVisibility(View.INVISIBLE);
                btn_j_userProfile_deletePromptYes.setVisibility(View.INVISIBLE);
                btn_j_userProfile_deletePromptNo.setVisibility(View.INVISIBLE);
                //Show edit & Delete Button
                btn_j_userProfile_edit.setVisibility(View.VISIBLE);
                btn_j_userProfile_delete.setVisibility(View.VISIBLE);
            }
        });
    }

    private void deletePromptYesButtonClickListener()
    {
        btn_j_userProfile_deletePromptYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dbHelper.deleteUser(SessionData.getLoggedInUser());
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });
    }

    private void bottomNavOnNavItemSelectedListener()
    {
        bnv_j_userProfile_bottomNav.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int navItem = item.getItemId();

                if (navItem == R.id.home)
                {
                    startActivity(new Intent(UserProfile.this, HomePage.class));
                    return true;
                }
                else if (navItem == R.id.recipes)
                {
                    startActivity(new Intent(UserProfile.this, Recipes.class));
                    return true;
                }
                else if (navItem == R.id.pantry)
                {
                    startActivity(new Intent(UserProfile.this, Pantry.class));
                    return true;
                }
                else if (navItem == R.id.groceryList)
                {
                    startActivity(new Intent(UserProfile.this, GroceryList.class));
                    return true;
                }
                else if (navItem == R.id.profile)
                {
                    startActivity(new Intent(UserProfile.this, UserProfile.class));
                    return true;
                }

                return false;
            }
        });
    }
}