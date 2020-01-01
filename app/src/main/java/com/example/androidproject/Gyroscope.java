package com.example.androidproject;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscope {

    public interface Listener
    {
        void onRotation(float rx, float ry, float rz); // these are the rotation about the x,y and z axes


    }

    private Listener listener;

    public void setLister(Listener l) // method to send listener
    {
        listener = l;
    }

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    Gyroscope(Context context)
    {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEventevent) {
                if(listener != null) // we make sure that the listener is different from null
                {
                    listener.onRotation(sensorEventevent.values[0], sensorEventevent.values[1], sensorEventevent.values[2]);

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    public void register ()
    {
        sensorManager.registerListener(sensorEventListener, sensor, sensorManager.SENSOR_DELAY_NORMAL);


    }

    public void unregister ()
    {
        sensorManager.unregisterListener(sensorEventListener);
    }


}

