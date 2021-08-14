package com.example.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class Email_Activity extends AppCompatActivity {
    EditText _txtEmail,_txtMessage ;
    Button _btnSend;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_activity);

        _txtEmail =findViewById(R.id.txtemail);
        _txtMessage = findViewById(R.id.txtmessage);
        _btnSend = findViewById(R.id.sendbutton);

        _btnSend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

              final String username = "hblbankapp@gmail.com";
              final String password = "Bank123456789";

              String messagetoSend = _txtMessage.getText().toString();
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
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(_txtEmail.getText().toString()));
                    message.setSubject("Transaction");
                    message.setText(messagetoSend);
                    Transport.send(message);
                    Toast.makeText(getApplicationContext(), "email send successfully", Toast.LENGTH_SHORT).show();
                }catch(MessagingException e){
                    throw new RuntimeException(e);

                }

            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }

}
