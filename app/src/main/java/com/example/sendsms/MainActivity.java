package com.example.sendsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btnSendSms;
    private EditText edtPhoneNumber, edtMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendSms = findViewById(R.id.btnSendSms);
        edtMessage = findViewById(R.id.edtMessage);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);


        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtMessage.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter message", Toast.LENGTH_SHORT).show();
                } else if (edtPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Phone number", Toast.LENGTH_SHORT).show();
                } else {
                    sendSmsMsgFnc(edtPhoneNumber.getText().toString(), edtMessage.getText().toString());
                }
            }
        });


    }

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    void sendSmsMsgFnc(String mblNumVar, String smsMsgVar) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            try {
                SmsManager smsMgrVar = SmsManager.getDefault();
                smsMgrVar.sendTextMessage(mblNumVar, null, smsMsgVar, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            } catch (Exception ErrVar) {
                Toast.makeText(getApplicationContext(), ErrVar.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
                ErrVar.printStackTrace();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);
            }
        }

    }
}