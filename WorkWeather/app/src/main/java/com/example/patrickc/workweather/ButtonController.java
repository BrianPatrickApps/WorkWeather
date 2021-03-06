package com.example.patrickc.workweather;

/**
 * Created by patrickc on 06-Jul-17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class ButtonController {
    Database db;
    Button stormy;
    Button rainy;
    Button overcast;
    Button cloudy;
    Button sunny;
    ImageView weatherOverlay;
    Context context;
    String id;
    Double mood;
    protected ButtonController(Button stormy, Button rainy, Button overcast, Button cloudy, Button sunny, ImageView weatherOverlay, Context context) {

        this.stormy = stormy;
        this.rainy = rainy;
        this.overcast = overcast;
        this.cloudy = cloudy;
        this.sunny = sunny;
        this.weatherOverlay = weatherOverlay;
        this.context = context;
        db = new Database(context);
        stormy.setOnClickListener(stormyClicked);
        rainy.setOnClickListener(rainyClicked);
        overcast.setOnClickListener(overcastClicked);
        cloudy.setOnClickListener(cloudyClicked);
        sunny.setOnClickListener(sunnyClicked);

    }

    //sets all buttons visible
    protected void setViewable() {
        stormy.setVisibility(View.VISIBLE);
        rainy.setVisibility(View.VISIBLE);
        overcast.setVisibility(View.VISIBLE);
        cloudy.setVisibility(View.VISIBLE);
        sunny.setVisibility(View.VISIBLE);
    }
    //sets all buttons invisible
    protected void setInvisible() {
        stormy.setVisibility(View.GONE);
        rainy.setVisibility(View.GONE);
        overcast.setVisibility(View.GONE);
        cloudy.setVisibility(View.GONE);
        sunny.setVisibility(View.GONE);
    }

    protected View.OnClickListener stormyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 1.0;

            weatherOverlay.setImageResource(R.drawable.input_thunderstorm);
            select();
        }
    };
    protected View.OnClickListener rainyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 2.0;
            weatherOverlay.setImageResource(R.drawable.input2_rainy);

            select();
        }
    };
    protected View.OnClickListener overcastClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 3.0;
            weatherOverlay.setImageResource(R.drawable.input3_clouded);

            select();
        }
    };
    protected View.OnClickListener cloudyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 4.0;
            weatherOverlay.setImageResource(R.drawable.input4_half_clouded);

            select();
        }
    };
    protected View.OnClickListener sunnyClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mood = 5.0;
            weatherOverlay.setImageResource(R.drawable.input5_sunny);
            Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show();
            select();
        }
    };
        public void select() {
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            Double avg = db.getAverage(mood);
            String query = "INSERT into nurses(`id`,`input`,`average`,`date`,`shift_id`)" +
                    "VALUES('" + id + "','"+ mood +"','"+ avg +"','"+ currentDateTimeString +"','"+db.getShiftNumber() +"');";
            db.addMedian(avg,currentDateTimeString,db.getShiftNumber());
            db.execSQL(query);
            //setInvisible();
            Log.d("BB",query);
            //Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    //Not in use
    protected void getId(String id) {
        this.id = id;
    }

    public String tester() {
        //setInvisible();
        String a = "it worked\n id: " + id.toString();
        return a;

    }

}
