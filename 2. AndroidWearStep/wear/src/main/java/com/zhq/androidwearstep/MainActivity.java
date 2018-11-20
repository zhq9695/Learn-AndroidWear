package com.zhq.androidwearstep;

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
import android.widget.TextView;

public class MainActivity extends WearableActivity implements SensorEventListener {

    private static final String STEP_NOW_WORD = "Current steps: ";
    private static final String STEP_TOTAL_WORD = "Total steps: ";

    private Integer stepNowNumber = 0;
    private Integer stepTotalNumber = 0;
    private TextView stepNow;
    private TextView stepTotal;

    private SensorManager sensorManager;
    private Sensor detectorSensor;
    private Sensor counterSensor;

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

        // get all views id
        stepNow = findViewById(R.id.stepNow);
        stepTotal = findViewById(R.id.stepTotal);

        // set initial display
        stepNow.setText(STEP_NOW_WORD + Integer.toString(stepNowNumber));
        stepTotal.setText(STEP_TOTAL_WORD + Integer.toString(stepTotalNumber));

        // create sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // register detector sensor
        detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sensorManager.registerListener(MainActivity.this,
                detectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
        // register counter sensor
        counterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(MainActivity.this,
                counterSensor, SensorManager.SENSOR_DELAY_FASTEST);

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0f) {
                stepNowNumber++;
            }
        } else if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepTotalNumber = Math.round(event.values[0]);
        }
        stepNow.setText(STEP_NOW_WORD + Integer.toString(stepNowNumber));
        stepTotal.setText(STEP_TOTAL_WORD + Integer.toString(stepTotalNumber));
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
