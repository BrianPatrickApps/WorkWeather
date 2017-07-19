package com.example.patrickc.workweather;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;


public class Menu_Nurses extends Fragment {

    View view;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_menu__nurses, container, false);

        nurse1 = (ImageView)view.findViewById(R.id.nurse1);
        nurse2 = (ImageView)view.findViewById(R.id.nurse2);
        nurse3 = (ImageView)view.findViewById(R.id.nurse3);
        nurse4 = (ImageView)view.findViewById(R.id.nurse4);
        nurse5 = (ImageView)view.findViewById(R.id.nurse5);
        nurse6 = (ImageView)view.findViewById(R.id.nurse6);
        nurse7 = (ImageView)view.findViewById(R.id.nurse7);
        weather = (ImageView)view.findViewById(R.id.weatherOverlay);
        rainOverlay = (ImageView)view.findViewById(R.id.rainOverlay);
        db = new Database(getActivity().getApplicationContext());

        nurseArray = new ArrayList<>();
        nurseArray.add(nurse1);
        nurseArray.add(nurse2);
        nurseArray.add(nurse3);
        nurseArray.add(nurse4);
        nurseArray.add(nurse5);
        nurseArray.add(nurse6);
        nurseArray.add(nurse7);

        counter = new Counter();

        viewController = new ViewController(rainOverlay,weather);

        return view;
    }

    private void checkWeather(){

        Glide.with(getActivity().getApplicationContext()).load(R.drawable.animation_rain).into(rainOverlay);
        Double x = db.getMedian();
        if(x==0){
            viewController.startUp();
        }
        else if(x == 1)
        {
            viewController.showThunder();
        }
        else if(x ==2){
            viewController.showRainMood();
        }
        else if(x ==3)
        {
            viewController.stopRain();
            viewController.showOvercast();
        }
        else if(x ==4){
            viewController.stopRain();
            viewController.showClouds();
        }
        else if(x==5) {
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
