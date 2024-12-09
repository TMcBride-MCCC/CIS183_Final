package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    TextView tv_j_editProfile_houseError;
    RadioGroup rg_j_editProfile_pantryGroup;
    RadioButton rb_j_editProfile_joinPantry;
    RadioButton rb_j_editProfile_newPantry;
    Spinner sp_j_editProfile_households;
    EditText et_j_editProfile_newPantryHouseName;
    Button btn_j_editProfile_submit;
    BottomNavigationView bnv_j_editProfile_bottomNav;
    private boolean validUsername;
    private boolean validEmail;
    private boolean validHouseName;
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
        tv_j_editProfile_houseError = findViewById(R.id.tv_v_editProfile_houseError);
        rg_j_editProfile_pantryGroup = findViewById(R.id.rg_v_editProfile_pantryGroup);
        rb_j_editProfile_joinPantry = findViewById(R.id.rb_v_editProfile_joinPantry);
        rb_j_editProfile_newPantry = findViewById(R.id.rb_v_editProfile_newPantry);
        sp_j_editProfile_households = findViewById(R.id.sp_v_editProfile_households);
        et_j_editProfile_newPantryHouseName = findViewById(R.id.et_v_editProfile_newPantryHouseName);
        btn_j_editProfile_submit = findViewById(R.id.btn_v_editProfile_submit);
        bnv_j_editProfile_bottomNav = findViewById(R.id.bnv_v_editProfile_bottomNav);

        //Hide the error messages
        tv_j_editProfile_usernameError.setVisibility(View.INVISIBLE);
        tv_j_editProfile_emailError.setVisibility(View.INVISIBLE);
        tv_j_editProfile_houseError.setVisibility(View.INVISIBLE);

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
        usernameTextChangedListener();
        emailTextChangedListener();
        houseNameTextChangeListener();
    }

    private void usernameTextChangedListener()
    {
        et_j_editProfile_username.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String newUsername = et_j_editProfile_username.getText().toString();
                String oldUsername = SessionData.getLoggedInUser().getUsername();
                validUsername = checkForValidUsername(newUsername);

                if (!newUsername.equals(oldUsername))
                {
                    if (validUsername)
                    {
                        //No error
                        tv_j_editProfile_usernameError.setVisibility(View.INVISIBLE);
                        btn_j_editProfile_submit.setEnabled(true);
                    }
                    else
                    {
                        //Error
                        tv_j_editProfile_usernameError.setVisibility(View.VISIBLE);
                        btn_j_editProfile_submit.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private boolean checkForValidUsername(String username)
    {
        ArrayList<String> listOfUsernames = dbHelper.getAllUsernames();

        for (int i = 0; i < listOfUsernames.size(); i++)
        {
            if (listOfUsernames.get(i).equals(username))
            {
                return false;
            }
        }
        return true;
    }

    private void emailTextChangedListener()
    {
        et_j_editProfile_email.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String newEmail = et_j_editProfile_email.getText().toString();
                String oldEmail = SessionData.getLoggedInUser().getEmail();
                validEmail = checkForValidEmail(newEmail);

                if (!newEmail.equals(oldEmail))
                {
                    if (validEmail)
                    {
                        //No error
                        tv_j_editProfile_emailError.setVisibility(View.INVISIBLE);
                        btn_j_editProfile_submit.setEnabled(true);
                    }
                    else
                    {
                        //Error
                        tv_j_editProfile_emailError.setVisibility(View.VISIBLE);
                        btn_j_editProfile_submit.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private boolean checkForValidEmail(String email)
    {
        ArrayList<String> listOfEmails = dbHelper.getAllEmails();

        for (int i =0; i < listOfEmails.size(); i++)
        {
            if (listOfEmails.get(i).equals(email))
            {
                return false;
            }
        }
        return true;
    }

    private void houseNameTextChangeListener()
    {
        et_j_editProfile_newPantryHouseName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String newHouseName = et_j_editProfile_newPantryHouseName.getText().toString();
                String oldHouseName = dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId());
                validHouseName = checkForValidHouseName(newHouseName);

                if (!newHouseName.equals(oldHouseName))
                {
                    if (validHouseName)
                    {
                        //No error
                        tv_j_editProfile_houseError.setVisibility(View.INVISIBLE);
                        btn_j_editProfile_submit.setEnabled(true);
                    }
                    else
                    {
                        //Error
                        tv_j_editProfile_houseError.setVisibility(View.VISIBLE);
                        btn_j_editProfile_submit.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private boolean checkForValidHouseName(String houseName)
    {
        ArrayList<String> listOfHouseNames = dbHelper.getHouseNames();

        for (int i = 0; i <listOfHouseNames.size(); i++)
        {
            if (listOfHouseNames.get(i).equals(houseName))
            {
                return false;
            }
        }
        return true;
    }

    private void submitButtonClickListener()
    {
        btn_j_editProfile_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int userId = dbHelper.getUserId(SessionData.getLoggedInUser().getUsername());
                String oldUsername = SessionData.getLoggedInUser().getUsername();
                String oldPassword = SessionData.getLoggedInUser().getPassword();
                String oldFName = SessionData.getLoggedInUser().getfName();
                String oldLName = SessionData.getLoggedInUser().getlName();
                String oldEmail = SessionData.getLoggedInUser().getEmail();
                String oldHousehold = dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId());
                String newUsername = et_j_editProfile_username.getText().toString();
                String newPassword = et_j_editProfile_password.getText().toString();
                String newFName = et_j_editProfile_fName.getText().toString();
                String newLName = et_j_editProfile_lName.getText().toString();
                String newEmail = et_j_editProfile_email.getText().toString();
                String joinExistingHousehold = sp_j_editProfile_households.getSelectedItem().toString();
                String createNewHousehold = et_j_editProfile_newPantryHouseName.getText().toString();

                //If the username is different
                if (!oldUsername.equals(newUsername))
                {
                    Log.d("SessionData", "Username was: " + SessionData.getLoggedInUser().getUsername());

                    //Call dbHelper to change the username in the database
                    dbHelper.changeUserUsername(oldUsername,newUsername);
                    //Change the username in SessionData
                    SessionData.getLoggedInUser().setUsername(newUsername);

                    Log.d("SessionData", "Username is now: " + SessionData.getLoggedInUser().getUsername());
                }
                if (!oldPassword.equals(newPassword))
                {
                    Log.d("SessionData", "Password was: " + SessionData.getLoggedInUser().getPassword());

                    //Call dbHelper to change the password in the database
                    dbHelper.changeUserPassword(oldUsername, newPassword);
                    //Change the password in SessionData
                    SessionData.getLoggedInUser().setPassword(newPassword);

                    Log.d("SessionData", "Password is now: " + SessionData.getLoggedInUser().getPassword());
                }
                if (!oldFName.equals(newFName))
                {
                    Log.d("SessionData", "First name was: " + SessionData.getLoggedInUser().getfName());

                    //Call dbHelper to change the first name in the database
                    dbHelper.changeUserFName(userId, newFName);
                    //Change the first name in SessionData
                    SessionData.getLoggedInUser().setfName(newFName);

                    Log.d("SessionData", "First name is now: " + SessionData.getLoggedInUser().getfName());
                }
                if (!oldLName.equals(newLName))
                {
                    Log.d("SessionData", "Last name was: " + SessionData.getLoggedInUser().getlName());

                    //Call dbHelper to change the last name in the database
                    dbHelper.changeUserLName(userId, newLName);
                    //Change the last name in SessionData
                    SessionData.getLoggedInUser().setlName(newLName);

                    Log.d("SessionData", "Last name is now: " + SessionData.getLoggedInUser().getlName());
                }
                if (!oldEmail.equals(newEmail))
                {
                    Log.d("SessionData", "Email was: " + SessionData.getLoggedInUser().getEmail());

                    //Call dbHelper to change the email in the database
                    dbHelper.changeUserEmail(userId, newEmail);
                    //Change the email in SessionData
                    SessionData.getLoggedInUser().setEmail(newEmail);

                    Log.d("SessionData", "Email is now: " + SessionData.getLoggedInUser().getEmail());
                }
                if (rb_j_editProfile_joinPantry.isChecked())
                {
                    Log.d("SessionData", "Pantry was: " + dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId()));

                    //Call dbHelper to change the pantry in the database
                    dbHelper.changeUserPantryId(userId, dbHelper.getPantryId(joinExistingHousehold));
                    //Change the pantry in SessionData
                    SessionData.getLoggedInUser().setPantryId(dbHelper.getPantryId(joinExistingHousehold));

                    Log.d("SessionData", "Pantry is now: " + dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId()));
                }
                if (rb_j_editProfile_newPantry.isChecked())
                {
                    Log.d("SessionData", "Pantry was: " + dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId()));

                    //Call dbHelper to create a new pantry
                    dbHelper.createNewPantry(createNewHousehold);
                    //Call dbHelper to change the pantry in the database
                    dbHelper.changeUserPantryId(userId, dbHelper.getPantryId(createNewHousehold));
                    //Change the pantry in SessionData
                    SessionData.getLoggedInUser().setPantryId(dbHelper.getPantryId(createNewHousehold));

                    Log.d("SessionData", "Pantry is now: " + dbHelper.getUserPantryHouseName(SessionData.getLoggedInUser().getPantryId()));
                }

                startActivity(new Intent(EditProfile.this, UserProfile.class));

                //Log.d("submit button", "button pressed");
                //Log.d("UserId", "UserId is:" + dbHelper.getUserId(oldUsername));
            }
        });
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