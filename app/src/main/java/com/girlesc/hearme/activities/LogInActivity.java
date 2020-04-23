package com.girlesc.hearme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girlesc.hearme.R;

public class LogInActivity extends AppCompatActivity {
    private LinearLayout backFromLogInBtn;
    private TextView signUpFromLogInBtn;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        backFromLogInBtn=findViewById(R.id.backFromLogInBtn);
        backFromLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        signUpFromLogInBtn=findViewById(R.id.signUpFromLogInBtn);
        signUpFromLogInBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Intent intent=new Intent(LogInActivity.this,SignUpActivity.class);
            startActivity(intent);
            finish();
           }
        });


    }
}
