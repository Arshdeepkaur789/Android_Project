package com.example.bankingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {
    private EditText CardNumber ,PinNumber;
    private Button loginButton;
    private DataBaseHelpher myDB;
    ImageView BackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);




        myDB = new DataBaseHelpher(this);
        boolean result =myDB.checkUser("5391600000551295","12345");
        if(!result)
           {
            myDB.registerUser("5391600000551295","12345");
            myDB.addAccounts("gurwinder singh","5391600000551295","05967901154404",1000,"gurwinder1singh1@gmail.com");
               myDB.addAccounts("gurwinder singh","5391600000551295","05967901154405",2770,"gurwinder1singh1@gmail.com");
               myDB.registerUser("5391600000551296","12345");
               myDB.addAccounts("Arshdeep kaur","5391600000551296","05967901154406",1600,"arshdeep03647@gmail.com");
               myDB.addAccounts("Arshdeep kaur","5391600000551296","05967901154407",8127,"arshdeep03647@gmail.com");

           }
        loginButton = findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CardNumber = findViewById(R.id.CardNumberLogin);
                PinNumber = findViewById(R.id.PinNumberLogin);
                boolean var = myDB.checkUser(CardNumber.getText().toString(),PinNumber.getText().toString());
                if(var){
                    users.setCARDNUMBER(CardNumber.getText().toString());
                    Toast.makeText(Login_Activity.this,"Login Successfully ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login_Activity.this,HomeActivity.class);
                    intent.putExtra("CardNumber", CardNumber.getText());
                    startActivity(intent);
                }else{
                    Toast.makeText(Login_Activity.this,"Wrong Cardno / Pinno ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
