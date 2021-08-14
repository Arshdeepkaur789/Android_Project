package com.example.bankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Utilitybill_water_save_activity extends AppCompatActivity {
    private DataBaseHelpher myDB;
    Button WaterActivity ;
    ArrayList<String> listItem;
    ArrayAdapter adaptor;
    ListView userlist;
    TextView back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utilitybill_water_save);
        myDB = new DataBaseHelpher(this);
        listItem = new ArrayList<>();
        userlist = findViewById(R.id.users_list);
        viewData();
        back = findViewById(R.id.backtxt);
        WaterActivity = findViewById(R.id.water_activity);
        WaterActivity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Utilitybill_water_save_activity.this,Utilitybill_water_activity.class);
                startActivity(intent);
            }
        });
        back = findViewById(R.id.backtxt);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Utilitybill_water_save_activity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
private void viewData(){
        String accnumber =  users.getAccountNo();
    String cardnumber =  users.getCARDNUMBER();
    Cursor cursor = myDB.viewdataaterbill(accnumber);

    if(cursor.getCount()==0){
        Toast.makeText(this,"No data avaliable",Toast.LENGTH_SHORT).show();
    }else{
        while(cursor.moveToNext()){
            listItem.add("Subscriptionno " +cursor.getString(1)+" Amount " +cursor.getString(2));
        }
        adaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listItem);
    userlist.setAdapter(adaptor);
    }
}
}

