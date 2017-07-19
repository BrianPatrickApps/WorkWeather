package com.example.patrickc.workweather;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;


public class Menu_Buttons extends Fragment {

    Button stormy;
    Button rainy;
    Button overcast;
    Button cloudy;
    Button sunny;
    ImageView inputOverlay;
    int id;
    Database db;
    View view;
    ButtonController control;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view =  inflater.inflate(R.layout.fragment_menu__buttons, container, false);

        stormy = (Button)view.findViewById(R.id.Stormy);
        rainy = (Button)view.findViewById(R.id.Rain);
        overcast = (Button)view.findViewById(R.id.Overcast);
        cloudy = (Button)view.findViewById(R.id.Cloudy);
        sunny = (Button)view.findViewById(R.id.Sunny);
        inputOverlay = (ImageView)view.findViewById(R.id.inputWeather);
        db = new Database(getActivity().getApplicationContext());
        control = new ButtonController(stormy,rainy,overcast,cloudy,sunny,inputOverlay,getActivity().getApplicationContext());
        id = getArguments().getInt("id");
        String a= new Integer(id).toString();
        Toast.makeText(getActivity().getApplicationContext(),a,Toast.LENGTH_LONG).show();
        control.getId(a);
        return view;

    }

    public void onBackPressed() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
    }


}
