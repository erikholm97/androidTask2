package com.example.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);

        button = (Button) findViewById(R.id.howTobutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if(tx > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 0, 0)); // Turns the background color of the xml file to this color
                }
                else if(tx < -1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 254, 0));
                }

            }
        });
        gyroscope.setLister(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                if(rx > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(235, 254, 0));
                }
                else if(rx < -1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(25, 1, 252));

                }
                /*
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(246, 213, 92));
                }
                 */

            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume(){
        super.onResume();

        accelerometer.register();
        gyroscope.register();
    }
    @Override
    protected void onPause(){
        super.onPause();

        accelerometer.unregister();
        gyroscope.unregister();
    }

}
