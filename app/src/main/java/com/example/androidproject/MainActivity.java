package com.example.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
//Ref: Richvale Consulting https://www.youtube.com/watch?v=nnfHZgqx4F4, https://developer.android.com/guide/topics/sensors/sensors_overview

    private Accelerometer accelerometer; // Used to store the accelerometer value to use it in the set listener.

    private Button button; // Used for the button that navigates to the second page.
    private ImageView moveObject;

    private float moveObjectx;

    private Handler handeler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = new Accelerometer(this);


        button = (Button) findViewById(R.id.howTobutton); // Fetching the button on xml page.

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        }); // onClickListener that calls the function openActivity2.

        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if(tx > 1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(32, 99, 155)); // Turns the background color of the xml file to this color
                }
                else if(tx < -1.0f){
                    getWindow().getDecorView().setBackgroundColor(Color.rgb(60, 174, 163)); // Turns the background color into #3caea3
                }
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.rgb( 	238	,238	,238)); // Get back to normal color
                }


            }
        });

    }
    public void openActivity2(){ //Function to take user to Main2Activity class.
        Intent intent = new Intent(this, Main2Activity.class); // Requests the main2activity using an intent functionality wich calls the android component.
        startActivity(intent); // Starts the Main2activity by calling startActivity and intent. Ref: (Coding in Flow) https://www.youtube.com/watch?v=bgIUdb-7Rqo
    // Ref https://hv.instructure.com/courses/2278/files/133982?module_item_id=50943
    }
    @Override
    protected void onResume(){ //When app restarts the accelerometer registers
        super.onResume();

        accelerometer.register();

    }
    @Override
    protected void onPause(){ // Unregisters accelerometer when the app is paused. (Ui is partially visable for the user)
        super.onPause();

        accelerometer.unregister();

    }

}
