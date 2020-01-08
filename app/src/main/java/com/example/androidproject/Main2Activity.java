package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private Gyroscope gyroscope;
    public int xOffset;
    public int yOffset;
    public String toastString = "Tilt me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gyroscope = new Gyroscope(this);

        gyroscope.setLister(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {



                if(rx > 1.0f){
                    yOffset = 1000;
                    toastString = "You tilted me forward";
                }
                else if(rx < -1.0f){
                    yOffset = -1000;
                    toastString = "You tilted me backwards";

                }



            }
        });
    }
    //Ref Coding in flow https://www.youtube.com/watch?v=aKGeCpJy_AA
    public void showToast(View v) {
        Toast toast = Toast.makeText(this, toastString, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, xOffset, yOffset); // sets gravity of toast element. this method uses the xOffset, yOffsets values that is set in the gyroscope listner
        toast.show(); // Shows the toast on the screen
    }
    @Override
    protected void onResume(){ //When app restarts the gyroscope registers
        super.onResume();


        gyroscope.register();
    }
    @Override
    protected void onPause(){ // Unregisters gyroscope when the app is paused. (Ui is partially visable for the user)
        super.onPause();


        gyroscope.unregister();
    }

}
