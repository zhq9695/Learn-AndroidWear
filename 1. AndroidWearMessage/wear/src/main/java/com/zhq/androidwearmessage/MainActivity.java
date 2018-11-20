package com.zhq.androidwearmessage;

/**
 * @author: ZhangHq
 * @blog: https://blog.csdn.net/zhq9695
 * @github: https://github.com/zhq9695
 * @date: 2018.11.16
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.wearable.activity.WearableActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends WearableActivity {

    private String number;
    private String content;
    private EditText phoneEditText;
    private EditText contentEditText;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dynamically request permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS,
                            Manifest.permission.READ_PHONE_STATE}, 1);
        }

        // get all view id
        phoneEditText = findViewById(R.id.phoneEditText);
        contentEditText = findViewById(R.id.contentEditText);
        sendBtn = findViewById(R.id.sendBtn);

        // add click listener
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = phoneEditText.getText().toString();
                content = contentEditText.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();
                List<String> divideContents = smsManager.divideMessage(content);
                for (String text : divideContents) {
                    smsManager.sendTextMessage(number, null, text, null, null);
                }
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }

}
