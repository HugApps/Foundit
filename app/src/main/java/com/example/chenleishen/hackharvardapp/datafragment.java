package com.example.chenleishen.hackharvardapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenleishen.hackharvardapp.R;

import java.util.Set;

/**
 * Created by Hugo on 11/13/2015.
 */
public class datafragment extends Fragment {
    TextView Acce ;
    TextView Temp;
    TextView Light;
    TextView Steps;
    View frag;
    Item obj;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        SharedPreferences shared = getActivity().getSharedPreferences("ItemDATA",0);


        Set<String> list = shared.getStringSet("test",null);
        String[] displayvalues  =(String[])list.toArray();

        Acce = (TextView)getView().findViewById(R.id.DropStatus);
        Temp =(TextView)getView().findViewById(R.id.TempDisplay);
        Light=(TextView)getView().findViewById(R.id.LightDisplay);
        Steps = (TextView)getView().findViewById(R.id.StepsDrop);

        Acce.setText(displayvalues[0]);
        Temp.setText(displayvalues[0]);
        Light.setText(displayvalues[0]);
        Steps.setText(displayvalues[0]);




    }





    @Override
    public View onCreateView(LayoutInflater inflater ,ViewGroup container,Bundle savedInstanceState){
        frag=inflater.inflate(R.layout.datafraglayout,container,false);
        return frag;

    }



}
