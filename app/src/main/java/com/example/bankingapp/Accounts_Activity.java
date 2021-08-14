package com.example.bankingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Accounts_Activity extends AppCompatActivity {
    private DataBaseHelpher myDB;
    EditText AccountNoAdd,zAmount;
    Button Add_Account ;
    TextView Back;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounts_activity);

        AccountNoAdd = findViewById(R.id.accountnoadd);
        zAmount = findViewById(R.id.amount_add);
        Add_Account = findViewById(R.id.addAccount);


        Back = findViewById(R.id.backtttxt);
        Back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent = new Intent(Accounts_Activity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


        myDB = new DataBaseHelpher(this);



        Add_Account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String CurrentCardNumber = users.getCARDNUMBER();
                String Accountno = AccountNoAdd.getText().toString();
                String tmoney = zAmount.getText().toString();


                int tmoney1 = Integer.parseInt(tmoney);
                String username = users.getUSERNAME();
                String getaccountno = users.getAccountNo();
                String accountemail = myDB.getTransferMoneyAccountEmail(getaccountno);
                boolean var = myDB.addAccounts(username,CurrentCardNumber,Accountno,tmoney1,accountemail);
                if (var) {
                    Toast.makeText(Accounts_Activity.this, "new Account Created Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Accounts_Activity.this,HomeActivity.class);

                    startActivity(intent);
                } else {
                    Toast.makeText(Accounts_Activity.this, "Error during account create ", Toast.LENGTH_SHORT).show();

                }
            }
        });




    }
    }
