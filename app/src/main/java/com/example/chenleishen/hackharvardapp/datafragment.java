package com.example.chenleishen.hackharvardapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenleishen.hackharvardapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
    String [] DisplayList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        SharedPreferences shared = getActivity().getSharedPreferences("test", Context.MODE_PRIVATE);


       Set<String> DisplaySet= shared.getStringSet("ITEMS", null);
        DisplayList = DisplaySet.toArray(new String[DisplaySet.size()]);



       for(String s : DisplayList){

           System.out.println(s);

       }



     //  Temp.setText(temp.get(1));
     //  Light.setText(temp.get(2));
     //  Steps.setText(temp.get(3));

    }





    @Override
    public View onCreateView(LayoutInflater inflater ,ViewGroup container,Bundle savedInstanceState){
        frag=inflater.inflate(R.layout.datafraglayout,container,false);
        Acce = (TextView)frag.findViewById(R.id.DropStatus);
        Temp =(TextView)frag.findViewById(R.id.TempDisplay);
        Light=(TextView)frag.findViewById(R.id.LightDisplay);
        Steps = (TextView)frag.findViewById(R.id.StepsDrop);

        Acce.setText(DisplayList[0]);
        Temp.setText(DisplayList[1]);
//        Light.setText(DisplayList[2]);
        //Steps.setText(DisplayList[3]);
        return frag;

    }


}
