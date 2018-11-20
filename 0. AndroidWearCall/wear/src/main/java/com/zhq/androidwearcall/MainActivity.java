package com.zhq.androidwearcall;

/**
 * @author: ZhangHq
 * @blog: https://blog.csdn.net/zhq9695
 * @github: https://github.com/zhq9695
 * @date: 2018.11.16
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends WearableActivity {

    // edit text max length
    private static final int PHONE_MAX_LENGTH = 12;
    // the number over edit text
    private String overNumber = "";
    // the number in edit text
    private String nowNum = "";

    private EditText phoneEditText;
    private Button callBtn;
    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;
    private Button fourBtn;
    private Button fiveBtn;
    private Button sixBtn;
    private Button sevenBtn;
    private Button eightBtn;
    private Button nineBtn;
    private Button zeroBtn;
    private Button hashBtn;
    private Button asteriskBtn;
    private Button backspaceBtn;
    private Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dynamically request permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        }

        getAllID();

        addButtonsListener();

        phoneEditText.setEnabled(false);

        // Enables Always-on
        setAmbientEnabled();
    }

    /**
     * get all view id
     */
    public void getAllID() {
        phoneEditText = findViewById(R.id.phoneEditText);
        callBtn = findViewById(R.id.callBtn);
        oneBtn = findViewById(R.id.oneBtn);
        twoBtn = findViewById(R.id.twoBtn);
        threeBtn = findViewById(R.id.threeBtn);
        fourBtn = findViewById(R.id.fourBtn);
        fiveBtn = findViewById(R.id.fiveBtn);
        sixBtn = findViewById(R.id.sixBtn);
        sevenBtn = findViewById(R.id.sevenBtn);
        eightBtn = findViewById(R.id.eightBtn);
        nineBtn = findViewById(R.id.nineBtn);
        zeroBtn = findViewById(R.id.zeroBtn);
        hashBtn = findViewById(R.id.hashBtn);
        asteriskBtn = findViewById(R.id.asteriskBtn);
        backspaceBtn = findViewById(R.id.backspaceBtn);
        clearBtn = findViewById(R.id.clearBtn);
    }

    /**
     * add all buttons' click listener
     */
    public void addButtonsListener() {
        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '1');
            }
        });
        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '2');
            }
        });
        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '3');
            }
        });
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '4');
            }
        });
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '5');
            }
        });
        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '6');
            }
        });
        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '7');
            }
        });
        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '8');
            }
        });
        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '9');
            }
        });
        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '0');
            }
        });
        hashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '#');
            }
        });
        asteriskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                if (nowNum.length() >= PHONE_MAX_LENGTH) {
                    overNumber += nowNum.charAt(0);
                    nowNum = nowNum.substring(1);
                }
                phoneEditText.setText(nowNum + '*');
            }
        });
        backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();

                if (nowNum.length() == 0) return;

                if (overNumber != "") {
                    nowNum = overNumber.charAt(overNumber.length() - 1) + nowNum;
                    if (overNumber.length() == 1) {
                        overNumber = "";
                    } else {
                        overNumber = overNumber.substring(0, overNumber.length() - 1);
                    }
                }
                phoneEditText.setText(nowNum.substring(0, nowNum.length() - 1));
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overNumber = "";
                phoneEditText.setText("");
            }
        });
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowNum = phoneEditText.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + overNumber + nowNum));
                startActivity(intent);
            }
        });
    }
}
