package com.example.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp_Activity extends AppCompatActivity {
private EditText UserName,CardNumber,PinNumber,AccountNo,EmailAddress;
 Button signupButton;
 ImageView BackButton;
private DataBaseHelpher myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        EmailAddress = findViewById(R.id.TextEmailAddress);

        UserName = findViewById(R.id.username);
        CardNumber = findViewById(R.id.CardNumberSignup);

        PinNumber = findViewById(R.id.PinNumberSignUp);
        BackButton = findViewById(R.id.backbutton);
        AccountNo = findViewById(R.id.AccountNoSignup);

        signupButton = findViewById(R.id.ssbutton);
        myDB = new DataBaseHelpher(this);

            signupButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                     int amount = 100;
                    String Account_No = AccountNo.getText().toString();

                    String CARDNUM =   CardNumber.getText().toString();
                    String pinno =PinNumber.getText().toString();
                    String email = EmailAddress.getText().toString();
                    boolean var = myDB.registerUser(CARDNUM,pinno);
                    boolean var1 = myDB.addAccounts(UserName.getText().toString(),CARDNUM,Account_No,amount,email);
                    if(var && var1){
                        Toast.makeText(SignUp_Activity.this,"user Registered Successfully ",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUp_Activity.this,Login_Activity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignUp_Activity.this,"Registration Error ",Toast.LENGTH_SHORT).show();

                    }

                }
            });

        BackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
           Intent intent = new Intent(SignUp_Activity.this,MainActivity.class);
           startActivity(intent);

            }
        });

    }

}
