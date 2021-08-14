package com.example.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Deposit_Amount_Activity extends AppCompatActivity {
    TextView CardNumber;
    EditText DepositAmount;
    Button DepositButton;
    private DataBaseHelpher myDB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deposit_amount);
        CardNumber = findViewById(R.id.CardNumber);
        DepositAmount = findViewById(R.id.depositAmount);
        DepositButton = findViewById(R.id.deposit_button);
        myDB = new DataBaseHelpher(this);

        String id =  users.getCARDNUMBER();
        CardNumber.setText(id);
        DepositButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                int Depositamountnew = Integer.parseInt(DepositAmount.getText().toString());
              int aamount =users.getAmount();
                int result = users.Deposit(aamount,Depositamountnew);
                String AccountNumber = users.getAccountNo();

                boolean isupdate = myDB.updatedata((String) CardNumber.getText(),AccountNumber,result);
                if(isupdate ==true){
                    Toast.makeText(Deposit_Amount_Activity.this,"Amount Updated ",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Deposit_Amount_Activity.this,HomeActivity.class);

                    startActivity(intent);
                }else{
                    Toast.makeText(Deposit_Amount_Activity.this,"error ",Toast.LENGTH_SHORT).show();

                }



            }
        });


    }
}
