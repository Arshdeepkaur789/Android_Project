package com.example.bankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Bank_Accounts extends AppCompatActivity {
    ArrayList<String> listItem;

    ArrayAdapter adaptor ;
    ListView userlist ;
    private DataBaseHelpher myDB;
    TextView backclick;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_accounts);
        myDB = new DataBaseHelpher(this);
        listItem = new ArrayList<>();

        userlist = findViewById(R.id.accounts_list);
        viewData();


        backclick = findViewById(R.id.back);
        backclick.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Bank_Accounts.this,HomeActivity.class);
                startActivity(intent);
            }
        });



    }

    private void viewData() {
        myDB = new DataBaseHelpher(this);
        Cursor cursor = myDB.viewallaccounts();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data avaliable", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(1)+" : " +cursor.getString(3)+":   $"+cursor.getString(4));

            }
            adaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listItem);
            userlist.setAdapter(adaptor);
        }
    }




}
