package com.example.call_message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_Num, et_Mess;
    Button btn_call, btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_call = findViewById(R.id.btn_call);
        btn_send = findViewById(R.id.btn_message);
        et_Num= findViewById(R.id.phoneNum);
        et_Mess= findViewById(R.id.et_Message);
        btn_call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String number = et_Num.getText().toString();
                if(number.isEmpty()|| number.length()!=10)
                {
                    Toast.makeText(MainActivity.this,"Vous devez saisir un nombre de 10 chiffre!",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent call =new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                {
                    startActivity(call);
                }
                else{
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},123);
                }


            }
        });

        btn_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String number = et_Num.getText().toString();
                String Message = et_Mess.getText().toString();
                if(number.isEmpty()|| number.length()!=10)
                {
                    Toast.makeText(MainActivity.this,"Vous devez saisir un nombre de 10 chiffre!",Toast.LENGTH_LONG).show();
                    return;
                }
               SmsManager manager =SmsManager.getDefault();
                Intent sendMes =new Intent(Intent.ACTION_SENDTO, Uri.parse("tel:"+number));
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                {
                   manager.sendTextMessage(number,null,Message,null,null);
                    //startActivity(sendMes);
                    //sendMes.putExtra("sms_body",Message);
                }
                else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS},123);
                }

            }
        });

    }
}