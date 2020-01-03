package com.example.androidproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class Accelerometer {
    // Ref: Richvale Consulting https://www.youtube.com/watch?v=OPsVr44uCb8.
    //Ref:  https://developer.android.com/guide/topics/sensors/sensors_overview.html
    public interface Listener{ // Listener for accelerometer.
        void onTranslation(float tx, float ty, float tz);
    }
    private Listener listener;

    public void setListener(Listener l)
    {
        listener = l;
    }

    private SensorManager androidSensorManager; // Binds the sensor
    private Sensor androidSensor;
    private SensorEventListener sensorEventListener;



    Accelerometer(Context context){

        androidSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        androidSensor = androidSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if(listener != null){ // Listener for the accelerometer.
                    listener.onTranslation(event.values[0], event.values[1], event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


    }
    public void register(){ // Registers the accelerometer and the sensormanger is set to delay normal 20,000 ms.
        androidSensorManager.registerListener(sensorEventListener, androidSensor, androidSensorManager.SENSOR_DELAY_NORMAL);
    }
    public void unregister(){
        androidSensorManager.unregisterListener(sensorEventListener);
    }
}
