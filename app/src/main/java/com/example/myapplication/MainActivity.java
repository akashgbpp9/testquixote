package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView img;
    private SensorManager sensorManager;
    private Sensor accel;
    Button button1,button2,button3,button4;
    int i=0;
    RotateAnimation rotateAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        img = (ImageView)findViewById(R.id.imgvw);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
        //Animation aniRotateClk = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
         rotateAnimation = new RotateAnimation(0,3600,RotateAnimation.RELATIVE_TO_SELF,.5f,RotateAnimation.RELATIVE_TO_SELF,.5f);
        rotateAnimation.setDuration(50000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        img.startAnimation(rotateAnimation);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] g = new float[3];
        g = event.values.clone();

        double norm_Of_g = Math.sqrt(g[0] * g[0] + g[1] * g[1] + g[2] * g[2]);

// Normalize the accelerometer vector
        g[0] = (float) (g[0] / norm_Of_g);
        g[1] = (float) (g[1] / norm_Of_g);
        g[2] = (float) (g[2] / norm_Of_g);
        int inclination = (int) Math.round(Math.toDegrees(Math.acos(g[2])));

        if (inclination < 25 || inclination > 155)
        {

            //device flat-horizontal

            button1.setOnTouchListener((v, event1) -> {
                if (event1.getAction() == MotionEvent.ACTION_DOWN) {
                    i++;
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        if (i==1){
                            img.clearAnimation();
                            img.setRotation(225);
                        }else if(i==2){
                            img.clearAnimation();
                            img.setRotation(45);
                        }
                        i=0;
                    },200);
                }

                else if (event1.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    img.startAnimation(rotateAnimation);
                }
                return true;
            });


            button2.setOnTouchListener((v, event1) -> {
                if (event1.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    if (event1.getAction() == MotionEvent.ACTION_DOWN) {
                        i++;
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            if (i==1){
                                img.clearAnimation();
                                img.setRotation(315);
                            }else if(i==2){
                                img.clearAnimation();
                                img.setRotation(135);
                            }
                            i=0;
                        },200);
                    }

                } else if (event1.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    img.startAnimation(rotateAnimation);
                }
                return true;
            });
            button3.setOnTouchListener((v, event1) -> {
                if (event1.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    i++;
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        if (i==1){
                            img.clearAnimation();
                            img.setRotation(135);
                        }else if(i==2){
                            img.clearAnimation();
                            img.setRotation(315);
                        }
                        i=0;
                    },200);


                } else if (event1.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    img.startAnimation(rotateAnimation);
                }
                return true;
            });
            button4.setOnTouchListener((v, event1) -> {
                if (event1.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    i++;
                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        if (i==1){
                            img.clearAnimation();
                            img.setRotation(45);
                        }else if(i==2){
                            img.clearAnimation();
                            img.setRotation(225);
                        }
                        i=0;
                    },150);
                } else if (event1.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    img.startAnimation(rotateAnimation);
                }
                return true;
            });


        }
        else
        {
            //device vertical
            img.clearAnimation();
            img.setRotation(270);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}