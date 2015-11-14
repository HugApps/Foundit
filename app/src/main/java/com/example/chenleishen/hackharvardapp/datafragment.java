package com.example.chenleishen.hackharvardapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenleishen.hackharvardapp.R;

/**
 * Created by Hugo on 11/13/2015.
 */
public class datafragment extends Fragment {
    TextView Acce ;
    TextView Temp;
    TextView Light;
    TextView Steps;
    View frag;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences shared = getActivity().getSharedPreferences("Test",0);

        Acce = (TextView)getView().findViewById(R.id.DropStatus);
        Temp =(TextView)getView().findViewById(R.id.TempDisplay);
        Light=(TextView)getView().findViewById(R.id.LightDisplay);
        Steps = (TextView)getView().findViewById(R.id.StepsDrop);




    }





    @Override
    public View onCreateView(LayoutInflater inflater ,ViewGroup container,Bundle savedInstanceState){
        frag=inflater.inflate(R.layout.datafraglayout,container,false);
        return frag;

    }



}
