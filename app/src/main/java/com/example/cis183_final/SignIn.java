package com.example.cis183_final;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignIn extends AppCompatActivity
{
    //Create Java variables
    EditText et_j_signIn_username;
    EditText et_j_signIn_password;
    Button btn_j_signIn_signIn;
    TextView tv_j_signIn_signUp;
    TextView tv_j_signIn_signInError;
    TextView tv_j_signIn_allFieldsError;
    boolean validLogin = false;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);

        //Connect Java variables to GUI variables
        et_j_signIn_username = findViewById(R.id.et_v_signIn_username);
        et_j_signIn_password = findViewById(R.id.et_v_signIn_password);
        btn_j_signIn_signIn = findViewById(R.id.btn_v_signIn_signIn);
        tv_j_signIn_signUp = findViewById(R.id.tv_v_signIn_signUp);
        tv_j_signIn_signInError = findViewById(R.id.tv_v_signIn_signInError);
        tv_j_signIn_allFieldsError = findViewById(R.id.tv_v_signIn_allFieldsError);

        //Hide the error messages
        tv_j_signIn_signInError.setVisibility(View.INVISIBLE);
        tv_j_signIn_allFieldsError.setVisibility(View.INVISIBLE);

        //DATABASE
        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);

        //Functions
        signInButtonClickListener();
        signUpTextOnClickListener();
        usernameTextChangeListener();
        passwordTextChangedListener();
    }

    private void usernameTextChangeListener()
    {
        et_j_signIn_username.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                tv_j_signIn_signInError.setVisibility(View.INVISIBLE);
                tv_j_signIn_allFieldsError.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private void passwordTextChangedListener()
    {
        et_j_signIn_password.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                tv_j_signIn_signInError.setVisibility(View.INVISIBLE);
                tv_j_signIn_allFieldsError.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private void signInButtonClickListener()
    {
        btn_j_signIn_signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String username = et_j_signIn_username.getText().toString();
                String password = et_j_signIn_password.getText().toString();

                if (!username.isEmpty() && !password.isEmpty())
                {
                    if (password.equals(dbHelper.verifyUsersPassword(username,password)))
                    {
                        tv_j_signIn_signInError.setVisibility(View.INVISIBLE);
                        Log.d("SUCCESS","VALID LOG IN");

                        //Create a new user memory chunk
                        User user = new User();

                        //Set all of the user variables
                        user.setPantryId(dbHelper.getUserPantryId(username));
                        user.setUsername(username);
                        user.setPassword(password);
                        user.setfName(dbHelper.getUserFName(username));
                        user.setlName(dbHelper.getUserLName(username));
                        user.setEmail(dbHelper.getUserEmail(username));

                        //Debugging
                        //Log.d("SignInOnClickListener()","User Created");
                        //Log.d("SignInOnClickListener()","PantryId: " + user.getPantryId());
                        //Log.d("SignInOnClickListener()","Username: " + user.getUsername());
                        //Log.d("SignInOnClickListener()","Password: " + user.getPassword());
                        //Log.d("SignInOnClickListener()","First Name: " + user.getfName());
                        //Log.d("SignInOnClickListener()","Last Name: " + user.getlName());
                        //Log.d("SignInOnClickListener()","Email: " + user.getEmail());

                        //Set the logged in user to this user
                        SessionData.setLoggedInUser(user);

                        startActivity(new Intent(SignIn.this, HomePage.class));
                    }
                    else
                    {
                        tv_j_signIn_signInError.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    tv_j_signIn_allFieldsError.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void signUpTextOnClickListener()
    {
        tv_j_signIn_signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SignIn.this, MainActivity.class));
            }
        });
    }

}