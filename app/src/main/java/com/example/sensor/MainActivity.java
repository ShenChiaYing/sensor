package com.example.sensor;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;





public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView acceleration;
    private TextView gyro;
    private TextView magnetic;
    private Sensor mAccelerometer;
    private Sensor mGyroscope;
    private Sensor mMagnetometer;
    private SensorManager SM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("A");
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivityB.class);
                startActivity(intent);

            }
        });

        // Create Sensor Manager
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        mAccelerometer = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Gyroscope
        mGyroscope = SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Magnetometer
        mMagnetometer = SM.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        //TextView
        acceleration = (TextView) findViewById(R.id.acceleration);
        gyro = (TextView) findViewById(R.id.gyro);
        magnetic = (TextView) findViewById(R.id.magnetic);

    }
    @Override
    public void onBackPressed(){

        moveTaskToBack(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SM.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        SM.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        SM.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SM.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String values = "X:" + String.valueOf(event.values[0]) + "\n"
                + "Y:" + String.valueOf(event.values[1]) + "\n"
                + "Z:" + String.valueOf(event.values[2]);
        if (event.sensor.equals(mAccelerometer))
            acceleration.setText(values);
        if (event.sensor.equals(mGyroscope))
            gyro.setText(values);
        if (event.sensor.equals(mMagnetometer))
            magnetic.setText(values);


    }

}