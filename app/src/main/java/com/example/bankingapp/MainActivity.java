package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button SignUpButton , LoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignUpButton = findViewById(R.id.signup);

        LoginButton = findViewById(R.id.login);

        SignUpButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                        startActivity(new Intent(MainActivity.this,SignUp_Activity.class));
                        finish();
                }
            });
        LoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,Login_Activity.class));
                finish();
            }
        });

    }
}