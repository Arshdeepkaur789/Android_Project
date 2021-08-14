package com.example.bankingapp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
TextView CardNumber ,Amount,cardtxt ,Welcometxt,uName,AccountsButton,SendMoney,waterBill;
TextView All_Accounts;
Spinner sSpinner;
Button EmailButton;
    private DataBaseHelpher myDB;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        myDB = new DataBaseHelpher(this);
        cardtxt =findViewById(R.id.cardNotxt);
        String cardnum = users.getCARDNUMBER();
        cardtxt.setText(cardnum);
        CardNumber =findViewById(R.id.CardNumbertxt);
        Welcometxt =findViewById(R.id.welcome);
        All_Accounts=findViewById(R.id.account_detail);
        Amount =findViewById(R.id.Amounttxt);
        uName =findViewById(R.id.usernametxt);
        waterBill =findViewById(R.id.saverecords);
        String cardNum =users.getAccountNo();
        CardNumber.setText(cardNum);
        myDB.getAccountData(cardNum);
        String accountno = users.getAccountNo();
        String x = CardNumber.getText().toString();
        String data = myDB.getData(accountno);
        Amount.setText("$"+data);
        sSpinner =(Spinner)findViewById(R.id.spinner1);
        loadSpinnerData();
        sSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(view!=null)
                {
                    String label = parent.getItemAtPosition(position).toString();
                        users.setAccountNo(label);

                    CardNumber.setText(label);
                    String data = myDB.getAccountMoney(label);
                    String result = myDB.FindUserName(label);
                    uName.setText(result);
                    Welcometxt.setText("Welcome , "+result);
                    Amount.setText("$"+data);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        All_Accounts.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this,Bank_Accounts.class);
                startActivity(intent);
            }
        });

        SendMoney =findViewById(R.id.sendmoney);


        waterBill.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this,Utilitybill_water_save_activity.class);
                startActivity(intent);
            }
        });

        SendMoney.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this,Send_Money_activity.class);
                startActivity(intent);
            }
        });
    }
    private void loadSpinnerData() {
String cardNumber = users.getCARDNUMBER();
        List<String> labels = myDB.getAllLabels(cardNumber);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSpinner.setAdapter(dataAdapter);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }
}
