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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //Create Java variables
    EditText et_j_main_username;
    EditText et_j_main_password;
    EditText et_j_main_fName;
    EditText et_j_main_lName;
    EditText et_j_main_email;
    Button btn_j_main_signUp;
    TextView tv_j_main_signIn;
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
        btn_j_main_signUp = findViewById(R.id.btn_v_main_signUp);
        tv_j_main_signIn = findViewById(R.id.tv_v_main_signIn);

        //DATABASE
        //Make a new instance of the database
        dbHelper = new DatabaseHelper(this);
        //Initialize dummy data
        dbHelper.initAllTables();

        //Functions
        signUpButtonClickListener();
        signInTextOnClickListener();
    }

    private void signUpButtonClickListener()
    {
        btn_j_main_signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, HomePage.class));
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
}