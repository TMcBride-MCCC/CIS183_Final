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

public class SignIn extends AppCompatActivity
{
    //Create Java variables
    EditText et_j_signIn_username;
    EditText et_j_signIn_password;
    Button btn_j_signIn_signIn;
    TextView tv_j_signIn_signUp;

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

        //Functions
        signInButtonClickListener();
        signUpTextOnClickListener();
    }

    private void signInButtonClickListener()
    {
        btn_j_signIn_signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SignIn.this, HomePage.class));
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