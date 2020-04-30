package com.girlesc.hearme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girlesc.hearme.R;

public class SignUpActivity extends AppCompatActivity {

    private LinearLayout backFromSignUpBtn;
    private Button signUpToSetUpBtn;
    private TextView logInFromSignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backFromSignUpBtn = findViewById(R.id.backFromSignUpBtn);
        backFromSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUpToSetUpBtn = findViewById(R.id.signUpToSetUpBtn);
        signUpToSetUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, AccountSetUpActivity.class);
                startActivity(intent);
            }
        });

        logInFromSignUpBtn = findViewById(R.id.logInFromSignUpBtn);
        logInFromSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
