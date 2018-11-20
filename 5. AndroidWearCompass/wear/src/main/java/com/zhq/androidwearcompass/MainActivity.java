package com.zhq.androidwearcompass;

/**
 * @author: ZhangHq
 * @blog: https://blog.csdn.net/zhq9695
 * @github: https://github.com/zhq9695
 * @date: 2018.11.19
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
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends WearableActivity implements SensorEventListener {

    private ImageView compass;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor magneticSensor;

    private float[] r = new float[9];
    private float[] values = new float[3];
    private float[] gravity = null;
    private float[] geomagnetic = null;

    // current rotation degree
    private float currentDegree = 0;

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

        compass = (ImageView) findViewById(R.id.compass);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // register accelerometer sensor
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometerSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
        // register magnetic sensor
        magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(MainActivity.this, magneticSensor,
                SensorManager.SENSOR_DELAY_FASTEST);

        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // get gravity/geomagnetic values
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravity = event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagnetic = event.values;
        }
        // if get both values, rotate compass
        if (gravity != null && geomagnetic != null) {
            if (SensorManager.getRotationMatrix(r, null, gravity, geomagnetic)) {
                SensorManager.getOrientation(r, values);
                float degree = (float) ((360f + values[0] * 180f / Math.PI) % 360);

                // rotate compass
                RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setDuration(500);
                compass.startAnimation(ra);

                currentDegree = -degree;
            }
        }
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
