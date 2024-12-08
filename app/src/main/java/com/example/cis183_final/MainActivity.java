package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //Create Java variables
    EditText et_j_main_username;
    EditText et_j_main_password;
    EditText et_j_main_fName;
    EditText et_j_main_lName;
    EditText et_j_main_email;
    RadioGroup rg_j_main_pantryGroup;
    RadioButton rb_j_main_joinPantry;
    RadioButton rb_j_main_newPantry;
    EditText et_j_main_newPantryHouseName;
    Spinner sp_j_main_households;
    Button btn_j_main_signUp;
    TextView tv_j_main_signIn;
    TextView tv_j_main_allFieldsError;
    TextView tv_j_main_usernameError;
    TextView tv_j_main_emailError;
    TextView tv_j_main_houseError;
    boolean validUsername = false;
    boolean validEmail = false;
    boolean validHouseName = false;
    //Database
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Connect Java variables to GUI variables
        et_j_main_username = findViewById(R.id.et_v_main_username);
        et_j_main_password = findViewById(R.id.et_v_main_password);
        et_j_main_fName = findViewById(R.id.et_v_main_fName);
        et_j_main_lName = findViewById(R.id.et_v_main_lName);
        et_j_main_email = findViewById(R.id.et_v_main_email);
        rg_j_main_pantryGroup = findViewById(R.id.rg_v_main_pantryGroup);
        rb_j_main_joinPantry = findViewById(R.id.rb_v_main_joinPantry);
        rb_j_main_newPantry = findViewById(R.id.rb_v_main_newPantry);
        et_j_main_newPantryHouseName = findViewById(R.id.et_v_main_newPantryHouseName);
        sp_j_main_households = findViewById(R.id.sp_v_main_households);
        btn_j_main_signUp = findViewById(R.id.btn_v_main_signUp);
        tv_j_main_signIn = findViewById(R.id.tv_v_main_signIn);
        tv_j_main_allFieldsError = findViewById(R.id.tv_v_main_allFieldsError);
        tv_j_main_usernameError = findViewById(R.id.tv_v_main_usernameError);
        tv_j_main_emailError = findViewById(R.id.tv_v_main_emailError);
        tv_j_main_houseError = findViewById(R.id.tv_v_main_houseError);

        //Hide the spinner
        sp_j_main_households.setVisibility(View.INVISIBLE);
        //Hide the newHouseName
        et_j_main_newPantryHouseName.setVisibility(View.INVISIBLE);
        //Hide the error messages
        tv_j_main_allFieldsError.setVisibility(View.INVISIBLE);
        tv_j_main_usernameError.setVisibility(View.INVISIBLE);
        tv_j_main_emailError.setVisibility(View.INVISIBLE);
        tv_j_main_houseError.setVisibility(View.INVISIBLE);


        //DATABASE
        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);
        //Initialize dummy data
        dbHelper.initAllTables();

        //Functions
        fillSpinner();
        radioButtonClickListener();
        signUpButtonClickListener();
        signInTextOnClickListener();
        usernameTextChangedListener();
        emailTextChangeListener();
        houseNameTextChangedListener();
    }

    private void fillSpinner()
    {
        //Grab the household arraylist
        ArrayList<String> households = dbHelper.getHouseNames();

        //Fill the spinner
        ArrayAdapter<String> householdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, households);
        sp_j_main_households.setAdapter(householdAdapter);
    }

    private void radioButtonClickListener()
    {
        rg_j_main_pantryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if (rb_j_main_joinPantry.isChecked())
                {
                    sp_j_main_households.setVisibility(View.VISIBLE);
                    et_j_main_newPantryHouseName.setVisibility(View.INVISIBLE);
                }
                if (rb_j_main_newPantry.isChecked())
                {
                    et_j_main_newPantryHouseName.setVisibility(View.VISIBLE);
                    sp_j_main_households.setVisibility(View.INVISIBLE);
                    sp_j_main_households.equals("");
                }
            }
        });
    }

    private void signUpButtonClickListener()
    {
        btn_j_main_signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Grab the info from the textboxes
                String username = et_j_main_username.getText().toString();
                String password = et_j_main_password.getText().toString();
                String fName = et_j_main_fName.getText().toString();
                String lName = et_j_main_lName.getText().toString();
                String email = et_j_main_email.getText().toString();
                //If the user is the first in the household to make an account this will be filled out
                String newHouseName = et_j_main_newPantryHouseName.getText().toString();
                //If the user is not the first in the household to make an account the spinner will have a value
                String existingHouseName = sp_j_main_households.getSelectedItem().toString();


                if (!username.isEmpty() && !password.isEmpty() && !fName.isEmpty() && !lName.isEmpty() && !email.isEmpty())
                {
                    User user = new User();

                    user.setUsername(username);
                    user.setPassword(password);
                    user.setfName(fName);
                    user.setlName(lName);
                    user.setEmail(email);

                    //If user does not have a household we need to create one
                    if (rb_j_main_newPantry.isChecked() && !newHouseName.isEmpty())
                    {
                        //Create the new pantry
                        dbHelper.createNewPantry(newHouseName);
                        //Grab the pantryId from the pantry that was just created
                        //Set pantryId to the value that is returned
                        user.setPantryId(dbHelper.getPantryId(newHouseName));
                    }
                    if (rb_j_main_joinPantry.isChecked())
                    {
                        //Set pantryId to the value that is returned from the spinner
                        user.setPantryId(dbHelper.getPantryId(existingHouseName));
                    }

                    dbHelper.signUpUser(user);
                    SessionData.setLoggedInUser(user);
                    clearInfo();
                    Log.d("SignUpOnClickListener()","User Created");
                    Log.d("SignUpOnClickListener()","Username: " + user.getUsername());
                    Log.d("SignUpOnClickListener()","Password: " + user.getPassword());
                    Log.d("SignUpOnClickListener()","First Name: " + user.getfName());
                    Log.d("SignUpOnClickListener()","Last Name: " + user.getlName());
                    Log.d("SignUpOnClickListener()","Email: " + user.getEmail());
                    Log.d("SignUpOnClickListener()","PantryId: " + user.getPantryId());
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                }
                else
                {
                    //Make GUI error text and enable it here
                    tv_j_main_allFieldsError.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void signInTextOnClickListener()
    {
        tv_j_main_signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, SignIn.class));
            }
        });
    }

    private void usernameTextChangedListener()
    {
        et_j_main_username.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                validUsername = checkForValidUsername(et_j_main_username.getText().toString());

                if (validUsername)
                {
                    //No error
                    tv_j_main_usernameError.setVisibility(View.INVISIBLE);
                    btn_j_main_signUp.setEnabled(true);
                }
                else
                {
                    //Error
                    tv_j_main_usernameError.setVisibility(View.VISIBLE);
                    btn_j_main_signUp.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private void emailTextChangeListener()
    {
        et_j_main_email.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                {
                    validEmail = checkForValidEmail(et_j_main_email.getText().toString());

                    if (validEmail)
                    {
                        //No error
                        tv_j_main_emailError.setVisibility(View.INVISIBLE);
                        btn_j_main_signUp.setEnabled(true);
                    }
                    else
                    {
                        //Error
                        tv_j_main_emailError.setVisibility(View.VISIBLE);
                        btn_j_main_signUp.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private void houseNameTextChangedListener()
    {
        et_j_main_newPantryHouseName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                validHouseName = checkForValidHouseName(et_j_main_newPantryHouseName.getText().toString());

                if (validHouseName)
                {
                    //No error
                    tv_j_main_houseError.setVisibility(View.INVISIBLE);
                    btn_j_main_signUp.setEnabled(true);
                }
                else
                {
                    //Error
                    tv_j_main_houseError.setVisibility(View.VISIBLE);
                    btn_j_main_signUp.setEnabled(false);
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

    private boolean checkForValidEmail(String email)
    {
        ArrayList<String> listofEmails = dbHelper.getAllEmails();

        for (int i =0; i < listofEmails.size(); i++)
        {
            if (listofEmails.get(i).equals(email))
            {
                return false;
            }
        }
        return true;
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

    private void clearInfo()
    {
        et_j_main_username.setText("");
        et_j_main_password.setText("");
        et_j_main_fName.setText("");
        et_j_main_lName.setText("");
        et_j_main_email.setText("");
        rg_j_main_pantryGroup.clearCheck();
        sp_j_main_households.setVisibility(View.INVISIBLE);
        et_j_main_newPantryHouseName.setVisibility(View.INVISIBLE);
    }
}