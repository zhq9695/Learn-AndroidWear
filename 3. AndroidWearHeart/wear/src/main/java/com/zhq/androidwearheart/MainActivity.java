package com.zhq.androidwearheart;

/**
 * @author: ZhangHq
 * @blog: https://blog.csdn.net/zhq9695
 * @github: https://github.com/zhq9695
 * @date: 2018.11.16
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends WearableActivity implements SensorEventListener {

    private Button startBtn;
    private Button stopBtn;

    private TextView heartRate;
    private TextView nowDisplay;

    private SensorManager sensorManager;
    private Sensor heartRateSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dynamically request permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BODY_SENSORS}, 1);
        }

        // add all views id
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        heartRate = findViewById(R.id.heartRate);
        nowDisplay = findViewById(R.id.nowDisplay);

        // initial display
        nowDisplay.setText("now, stop");
        heartRate.setText("0");

        // create sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // create heart rate sensor
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        // set start button click listener
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if sensor is registered
                boolean sensorRegistered = sensorManager.registerListener(
                        MainActivity.this, heartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);
                if (sensorRegistered == true) {
                    nowDisplay.setText("now, start");
                }
            }
        });

        // set stop button click listener
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // unregister sensor
                sensorManager.unregisterListener(MainActivity.this);
                nowDisplay.setText("now, stop");
            }
        });

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // get heart rate
        int rate = Math.round(event.values[0]);
        heartRate.setText(Integer.toString(rate));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(MainActivity.this);
    }
}
