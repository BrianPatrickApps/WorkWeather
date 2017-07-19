package com.example.patrickc.workweather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Menu_Login extends Fragment {

    View view;
    Database db;
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button enter;
    Button finger;
    Button cancel;
    TextView idScreen;
    int idNumber=000000;
    String idHolder = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view = inflater.inflate(R.layout.fragment_menu__login, container, false);

        db = new Database(getActivity().getApplicationContext());

        b1 = (Button)view.findViewById(R.id.number1);
        b2 = (Button)view.findViewById(R.id.number2);
        b3 = (Button)view.findViewById(R.id.number3);
        b4 = (Button)view.findViewById(R.id.number4);
        b5 = (Button)view.findViewById(R.id.number5);
        b6 = (Button)view.findViewById(R.id.number6);
        b7 = (Button)view.findViewById(R.id.number7);
        b8 = (Button)view.findViewById(R.id.number8);
        b9 = (Button)view.findViewById(R.id.number8);
        b0 = (Button)view.findViewById(R.id.number0);

        enter = (Button)view.findViewById(R.id.enter);
        finger = (Button)view.findViewById(R.id.fingerprint);
        cancel = (Button)view.findViewById(R.id.cancel);

        idScreen = (TextView)view.findViewById(R.id.idScreen);

        idScreen.setFilters(new InputFilter[] { new InputFilter.LengthFilter(6)});
        //idScreen.setInputType(InputType.TYPE_CLASS_NUMBER);
//        idScreen.setText(idNumber);

        b1.setOnClickListener(b1Click);
        b2.setOnClickListener(b2Click);
        b3.setOnClickListener(b3Click);
        b4.setOnClickListener(b4Click);
        b5.setOnClickListener(b5Click);
        b6.setOnClickListener(b6Click);
        b7.setOnClickListener(b7Click);
        b8.setOnClickListener(b8Click);
        b9.setOnClickListener(b9Click);
        b0.setOnClickListener(b0Click);

        enter.setOnClickListener(submit);
        cancel.setOnClickListener(clear);
        finger.setOnClickListener(fingerprint);

        return view;
    }

    private View.OnClickListener b1Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"1";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b2Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"2";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b3Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"3";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b4Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"4";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b5Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"5";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b6Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"6";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b7Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"7";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b8Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"8";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b9Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"9";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener b0Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = idHolder+"0";
            idScreen.setText(idHolder);
        }
    };

    private View.OnClickListener submit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = Integer.parseInt(idScreen.getText().toString());
            Toast.makeText(getActivity().getApplicationContext(),idScreen.getText().toString(),Toast.LENGTH_LONG).show();
            Bundle b = new Bundle();
            Fragment f = new Menu_Buttons();
            Bundle bdl=new Bundle();
            bdl.putInt("id",id);
            f.setArguments(bdl);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out).replace(R.id.fragment,f);
            fragmentTransaction.commit ();
        }
    };

    private View.OnClickListener clear = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            idHolder = "";
            idScreen.setText("");
            Toast.makeText(getActivity().getApplicationContext(),"Cleared",Toast.LENGTH_LONG).show();
        }
    };
    private View.OnClickListener fingerprint = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Toast.makeText(getActivity().getApplicationContext(),"Finger Print not available",Toast.LENGTH_LONG).show();
        }
    };



}
