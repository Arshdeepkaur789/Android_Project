package com.example.bankingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Send_Money_activity extends AppCompatActivity {
    EditText AccountNo,Money,UserName ,TransferAmount;
    Button Send_Money_Button ,Pesonal_Account;
    private DataBaseHelpher myDB;
    TextView back;
    Spinner sSpinner,sSpinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_activity);
        myDB = new DataBaseHelpher(this);
        Money = findViewById(R.id.money);
        AccountNo = findViewById(R.id.AccountNumber);
        Pesonal_Account =findViewById(R.id.pesonal_account);
        sSpinner =(Spinner)findViewById(R.id.spinner2);
        loadSpinnerData();
        sSpinner3 =(Spinner)findViewById(R.id.spinner3);
        loadSpinnerData3();
        sSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(view!=null)
                {
                    String label = parent.getItemAtPosition(position).toString();
                    users.setFromAccountNo(label);
                    String data = myDB.getAccountMoney(label);
                    users.setFromAccountNoBalance(data);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(view!=null)
                {
                    String label = parent.getItemAtPosition(position).toString();
                    users.setToAccountNo(label);
                    String data = myDB.getAccountMoney(label);
                    users.setToAccountNoBalance(data);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Pesonal_Account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String fromaccount = users.getFromAccountNo();
                String toaccount = users.getToAccountNo();

                if (fromaccount.equals(toaccount)) {
                    Toast.makeText(Send_Money_activity.this, "Please Select Different Account", Toast.LENGTH_SHORT).show();

                } else{
                    int tmoney = Integer.parseInt(TransferAmount.getText().toString());

                int CurrentAmount = Integer.parseInt(users.getFromAccountNoBalance());
                int tbalance = Integer.parseInt(users.getToAccountNoBalance());

                if (CurrentAmount > tmoney || CurrentAmount == tmoney) {
                    int result = users.currentbalance(CurrentAmount, tmoney);

                    int mon = users.transferaccountcurrentbalance(tbalance, tmoney);
                    boolean data1 = myDB.sendmoney(users.getFromAccountNo(), result); //update from account money
                    boolean data2 = myDB.sendmoney(users.getToAccountNo(), mon); //Updated to account money
                    if (data1 == true && data2) {
                        Toast.makeText(Send_Money_activity.this, "Amount transfer Successfully ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Send_Money_activity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Send_Money_activity.this, "ERROR AMOUNT TRANSFER ", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(Send_Money_activity.this, "insifficuent balance in your account ", Toast.LENGTH_SHORT).show();

                }
            }
        }
        });
        UserName = findViewById(R.id.Send_Money_UserName);
        TransferAmount =findViewById(R.id.tamount);
        Send_Money_Button = findViewById(R.id.send_money_button);
        Send_Money_Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                boolean x = myDB.checkAccountnoUser(UserName.getText().toString(),AccountNo.getText().toString());
                if (x == false) {
                    Toast.makeText(Send_Money_activity.this, "Account number not found ", Toast.LENGTH_SHORT).show();
                }else{
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        connected = true;
                        Toast.makeText(getApplicationContext(), "please wait .", Toast.LENGTH_SHORT).show();
                        int tmoney = Integer.parseInt(Money.getText().toString());
                        int CurrentAmount = users.getAmount();
                        if (CurrentAmount > tmoney || CurrentAmount == tmoney ) {
                            int result = users.currentbalance(CurrentAmount, tmoney);
                            String transeraccountBalance = myDB.getAccountMoney(AccountNo.getText().toString());
                            String transferaccountemail = myDB.getTransferMoneyAccountEmail(AccountNo.getText().toString());
                            Toast.makeText(Send_Money_activity.this, "transferaccountemail "+transferaccountemail, Toast.LENGTH_SHORT).show();

                            int tbalance = Integer.parseInt(transeraccountBalance.toString());
                            int mon = users.transferaccountcurrentbalance(tbalance, tmoney);
                            boolean data = myDB.sendmoney(AccountNo.getText().toString(), mon);

                            if (data == true) {

                                String CardNo = users.getCARDNUMBER();
                                String AccountNumber = users.getAccountNo();
                                myDB.updatedata(CardNo,AccountNumber,result);
                                String transfermoney = Integer.toString(tmoney);
                                //////////////////////////////////////////////////////////////
                                ///////////////////Email Implementation//////////////////////
                                /////////////////////////////////////////////////////////////
                                /////////////////////////////////////////////////////////////
                                final String username = "hblbankapp@gmail.com";
                                final String password = "Bank123456789";
                                String messagetoSend = "fund Transfer of $" +transfermoney + " was made from Account No " + AccountNumber + " to your Account No  " +AccountNo.getText().toString();
                                Properties props = new Properties();
                                props.put("mail.smtp.auth","true");
                                props.put("mail.smtp.starttls.enable","true");
                                props.put("mail.smtp.host","smtp.gmail.com");
                                props.put("mail.smtp.starttls.port","587");
                                Session session = Session.getInstance(props,
                                        new javax.mail.Authenticator(){
                                            protected PasswordAuthentication getPasswordAuthentication() {
                                                return new PasswordAuthentication(username, password);
                                            }
                                        });

                                try {
                                    Toast.makeText(getApplicationContext(), "please wait ..", Toast.LENGTH_SHORT).show();
                                    Message message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress(username));
                                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(transferaccountemail));
                                    message.setSubject("Fund Received");
                                    message.setText(messagetoSend);
                                    Transport.send(message);
                                    Toast.makeText(getApplicationContext(), "email send successfully", Toast.LENGTH_SHORT).show();
                                }catch(MessagingException e){
                                    throw new RuntimeException(e);
                                }
                                Toast.makeText(Send_Money_activity.this, "Updated Successfully ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Send_Money_activity.this,HomeActivity.class);
                                startActivity(intent);
                                /////////////////////////////////////////////////////////////
                                /////////////////////////////////////////////////////////////
                                /////////////////////////////////////////////////////////////
                                ///////////////Email Implementation End//////////////////////
                            } else if (data == false) {
                                Toast.makeText(Send_Money_activity.this, "UserName / Accountno is Wrong ", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Send_Money_activity.this, "Error during update ", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(Send_Money_activity.this, "Insufficent Account balance ", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(Send_Money_activity.this,"Please check internet connection ",Toast.LENGTH_SHORT).show();
                        connected = false;
                    }
            }
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        back = findViewById(R.id.backttxt);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Send_Money_activity.this,HomeActivity.class);
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
    private void loadSpinnerData3() {
        String cardNumber = users.getCARDNUMBER();
        List<String> labels = myDB.getAllLabels(cardNumber);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSpinner3.setAdapter(dataAdapter);

    }

}
