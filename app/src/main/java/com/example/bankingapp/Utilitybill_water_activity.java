package com.example.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Utilitybill_water_activity extends AppCompatActivity {

EditText SubscriptionNo,Amount;
    CheckBox savechekbox;
Button WaterBillPay;
TextView Back;
    private DataBaseHelpher myDB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utilitybill_water_activity);
        SubscriptionNo = (EditText) findViewById(R.id.subscriptionno);
        Amount = (EditText) findViewById(R.id.amount);
        WaterBillPay = findViewById(R.id.waterbllpay);

        myDB = new DataBaseHelpher(this);

        Back = findViewById(R.id.backtxtt);
        Back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent = new Intent(Utilitybill_water_activity .this,Utilitybill_water_save_activity.class);
                startActivity(intent);
            }
        });
        savechekbox = findViewById(R.id.checkbox_cheese);
        savechekbox.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                if (savechekbox.isChecked()==true) {


                    WaterBillPay.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {

                            int WaterBillAmount = Integer.parseInt(Amount.getText().toString());
                            String AccountNo = users.getAccountNo();

                            int CurrentAmount = users.getAmount();
                            if (CurrentAmount > WaterBillAmount || CurrentAmount == WaterBillAmount) {
                                int result = users.currentbalance(CurrentAmount, WaterBillAmount);

                                boolean var = myDB.utilitybillWater(SubscriptionNo.getText().toString(), WaterBillAmount, AccountNo, "true");
                                if (var) {
                                    String CardNo = users.getCARDNUMBER();
                                    String AccountNumber = users.getAccountNo();
                                    myDB.updatedata(CardNo,AccountNumber,result);
                                    users.setAmount(result);
                                   Toast.makeText(Utilitybill_water_activity.this,"Paid Successfully ",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Utilitybill_water_activity.this,Utilitybill_water_save_activity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Utilitybill_water_activity.this, "Error during water bill ", Toast.LENGTH_SHORT).show();

                                }

                            } else {
                                Toast.makeText(Utilitybill_water_activity.this, "Insufficient Balance ", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
                }

            }
        });


         if (savechekbox.isChecked()==false){
            WaterBillPay.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v){

                    int WaterBillAmount = Integer.parseInt(Amount.getText().toString());
                    String AccountNo = users.getAccountNo();

                    int CurrentAmount = users.getAmount();
                    if (CurrentAmount > WaterBillAmount || CurrentAmount == WaterBillAmount) {
                        int result = users.currentbalance(CurrentAmount, WaterBillAmount);//Apna account ma - hua

                        boolean var = myDB.utilitybillWater( SubscriptionNo.getText().toString(),WaterBillAmount,AccountNo,"false");
                        if(var){
                            String CardNo = users.getCARDNUMBER();
                            String AccountNumber = users.getAccountNo();
                            myDB.updatedata(CardNo,AccountNumber,result);
                            users.setAmount(result);
                            Toast.makeText(Utilitybill_water_activity.this,"Paid Successfully ",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Utilitybill_water_activity.this,Utilitybill_water_save_activity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Utilitybill_water_activity.this,"Error during water bill ",Toast.LENGTH_SHORT).show();

                        }

                    }else{
                        Toast.makeText(Utilitybill_water_activity.this,"Insufficient Balance ",Toast.LENGTH_SHORT).show();

                    }


                }
            });
        }





    }
}
