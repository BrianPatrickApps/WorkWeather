package com.example.patrickc.workweather;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.Serializable;

/**
 * Created by patrickc on 12-Jul-17.
 */

public class ViewController implements Serializable{

    private RelativeLayout mainScreen;
    private RelativeLayout nurse;
    private RelativeLayout inputScreen;
    private ImageView rainOverlay;
    private ImageView weatherOverlay;

    protected ViewController(ImageView rainOverlay, ImageView weatherOverlay,RelativeLayout mainScreen,RelativeLayout nurse){
        this.mainScreen = mainScreen;
        this.nurse = nurse;
        this.inputScreen = inputScreen;
        this.rainOverlay = rainOverlay;
        this.weatherOverlay = weatherOverlay;
    }

    protected void viewNurses() {
        nurse.setVisibility(View.VISIBLE);
        inputScreen.setVisibility(View.GONE);
    }

    protected void viewInput(){
        inputScreen.setVisibility(View.VISIBLE);
        nurse.setVisibility(View.GONE);
    }

    protected void setRain(){
        rainOverlay.setVisibility(View.VISIBLE);
    }

    protected void stopRain(){
        rainOverlay.setVisibility(View.GONE);
    }

    protected void showSun(){
        nurse.setBackgroundResource(R.drawable.weather_sun);
    }

    protected void showClouds(){
        //weatherOverlay.setImageResource();
        mainScreen.setBackgroundResource(R.drawable.background4_semi_clouded);
        nurse.setBackgroundResource(R.drawable.weather_halfclouds);
    }

    protected void showOvercast(){
        //weatherOverlay.setImageResource();
        mainScreen.setBackgroundResource(R.drawable.background3_clouded);
        nurse.setBackgroundResource(R.drawable.weather_halfclouds);
    }

    protected void showRainMood(){
        //weatherOverlay.setImageResource();
        setRain();
        mainScreen.setBackgroundResource(R.drawable.background2_rain);
        nurse.setBackgroundResource(R.drawable.weather_rain);
    }

    protected void showThunder(){
        //weatherOverlay.setImageResource();
        setRain();
        nurse.setBackgroundResource(R.drawable.weather_rain);
        mainScreen.setBackgroundResource(R.drawable.background1_thunderstorm);
    }

    protected void startUp(){
        viewNurses();
        weatherOverlay.setImageResource(0);
        mainScreen.setBackgroundResource(R.drawable.background3_clouded);
        stopRain();
    }


}
