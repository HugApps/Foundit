package com.example.chenleishen.hackharvardapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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



      /*
      this.Drop = "Dropped";
       this.Light ="Bright";
        this.Steps ="56";
        this.Temp="20";
       */


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
        TextView dropdisplay = (TextView)getActivity().findViewById(R.id.DropStatus);
        if(!DisplayList[0].equals("Dropped")) {
            Acce.setText("Static");
            Acce.setTextColor(Color.GREEN);

        }else{
            Acce.setText("Dropped");
            Acce.setTextColor(Color.RED);
        }

        ImageView Bulb = (ImageView)frag.findViewById(R.id.Bulb);
        if(DisplayList[1].equals("Bright")){
            Bulb.setBackgroundResource(R.drawable.brightness);

        }else{
            Bulb.setBackgroundResource(R.drawable.brightness1);}

        TextView temperature = (TextView)frag.findViewById(R.id.TempDisplay);
        temperature.setText(DisplayList[3]);
        if(Integer.parseInt(DisplayList[3]) <20 ){

            temperature.setTextColor(Color.BLUE);

        }else{
            temperature.setTextColor(Color.RED);
        }

        TextView steps = (TextView)frag.findViewById(R.id.StepsDrop);
        steps.setText(DisplayList[2])   ;
       // Acce.setText(DisplayList[0]);
       // Temp.setText(DisplayList[1]);
      //  Light.setText(DisplayList[2]);
       // Steps.setText(DisplayList[3]);
        return frag;

    }


}
