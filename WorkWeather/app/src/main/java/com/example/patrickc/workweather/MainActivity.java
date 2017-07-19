package com.example.patrickc.workweather;

import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView nurse1;
    ImageView nurse2;
    ImageView nurse3;
    ImageView nurse4;
    ImageView nurse5;
    ImageView nurse6;
    ImageView nurse7;
    ImageView rainOverlay;
    ImageView weather;
    ViewController viewController;
    Database db;
    ArrayList<ImageView>nurseArray;
    Counter counter;
    boolean sub;
    RelativeLayout nurseLayout;
    RelativeLayout backLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        db = new Database(this);

        //Button but = (Button)findViewById(R.id.a1);
        final Button but2 = (Button)findViewById(R.id.loginButton);
        changetoNurses();
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changetoNurses();
                but2.setEnabled(false);
                but2.setVisibility(View.GONE);
            }
        });
        db = new Database(this);

        //Nurse Images
        nurse1 = (ImageView)findViewById(R.id.nurse1);
        nurse2 = (ImageView)findViewById(R.id.nurse2);
        nurse3 = (ImageView)findViewById(R.id.nurse3);
        nurse4 = (ImageView)findViewById(R.id.nurse4);
        nurse5 = (ImageView)findViewById(R.id.nurse5);
        nurse6 = (ImageView)findViewById(R.id.nurse6);
        nurse7 = (ImageView)findViewById(R.id.nurse7);
        weather = (ImageView)findViewById(R.id.weatherOverlay);
        rainOverlay = (ImageView)findViewById(R.id.rainOverlay);

        nurseLayout =(RelativeLayout)findViewById(R.id.nurseLayout);
        nurseLayout =(RelativeLayout)findViewById(R.id.backgroundLayout);

        nurseArray = new ArrayList<>();
        nurseArray.add(nurse1);
        nurseArray.add(nurse2);
        nurseArray.add(nurse3);
        nurseArray.add(nurse4);
        nurseArray.add(nurse5);
        nurseArray.add(nurse6);
        nurseArray.add(nurse7);

        counter = new Counter();

        viewController = new ViewController(rainOverlay,weather,nurseLayout,backLayout);
        checkWeather();

    }

    protected void changetoMenu(){
        Fragment frag;
        frag = new Menu_Login();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment,frag);
        ft.commit();

    }

    protected void changetoNurses(){
        Fragment frag;
        FragmentManager fm = getSupportFragmentManager();
        frag = fm.findFragmentById(R.id.fragment);

        /*FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment,frag);
        ft.commit();*/

        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);

        if (frag.isHidden()) {
            fragTransaction.show(frag);
            Log.d("hidden","Show");
        } else {
            fragTransaction.hide(frag);
            Log.d("Shown","Hide");
        }

        fragTransaction.commit();
    }

    private void checkWeather(){

        Glide.with(this).load(R.drawable.animation_rain).into(rainOverlay);
        Double x = db.getMedian();
        if(x==0){
//            viewController.startUp();
        }
        else if(x == 1)
        {
            viewController.showThunder();
        }
        else if(x ==2 || x ==1.5){
            viewController.showRainMood();
        }
        else if(x ==3||x ==2.5)
        {
            viewController.stopRain();
            viewController.showOvercast();
        }
        else if(x ==4||x ==3.5){
            viewController.stopRain();
            viewController.showClouds();
        }
        else if(x==5||x ==4.5) {
            viewController.stopRain();
            viewController.showSun();
        }

    }

    public void showNurses(){
        //Available nurse image chosen by counter.
        ImageView iv = nurseArray.get(counter.getCount());
        Random r = new Random();
        int Low = 0;
        int High = 9;
        int Result = r.nextInt(High-Low) + Low;
        if(counter.getCount() > nurseArray.size())
            counter.resetCount();
        if (!sub) { //boolean check to see if mx number of nurses already visible
            iv.setVisibility(View.VISIBLE);
            nurseTimeout(nurseArray.get(counter.getCount()));//calls the nurse timeout method with the imageview of the nurse that just went visible.
            counter.setCount();
            if (counter.getCount() == nurseArray.size()) {
                counter.removeCount();
                sub = true;
            }
        }
        else{ //starts setting nurses invisible manually if the max is visible.
            iv.setVisibility(View.GONE);
            counter.removeCount();
            if(counter.getCount() == -1){
                counter.setCount();
                sub = false;
            }
        }
    }

    public void nurseTimeout(View v){
        final ImageView iv = (ImageView) v;
        new CountDownTimer((1000 * 60 * 120), (1000 * 60 * 120)) { //timer set to be 3 seconds long and tick once every 3 seconds. Will be 2 hours each for final app

            public void onTick(long millisUntilFinished) { //nothing needed here as we only disable an image after the full time
            }

            public void onFinish() {
                iv.setVisibility(View.GONE);
            }

        }.start();
    }
}
