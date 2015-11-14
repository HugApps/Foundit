package com.example.chenleishen.hackharvardapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Created by Hugo on 11/14/2015.
 */
// Item attached to Dongle
public class Item {


    private String Tag,Drop,Light,Steps,Acce;

    Set<String> datavalues;
    Context c;
    SharedPreferences file;

    String SHARED_PREFRENCE = "ItemDATA";

    public Item (String Name ,Context context){

        this.Tag=Name;
        this.c=context;
        //Testing fake data
        Drop = "test";
        Light ="test";
        Steps ="test";
        Acce="test";
        SharedPreferences.Editor  editor = file.edit();
        datavalues.add(Drop);
        datavalues.add(Light);
        datavalues.add(Steps);
        datavalues.add(Acce);
        editor.putStringSet(Tag,datavalues);

    }

    // Saves the data list into shared preference with the object name as the key
    public void saveData (){
        file =c.getSharedPreferences(SHARED_PREFRENCE,0);





    }



    public String getDrop() {
        return Drop;
    }

    public void setDrop(String drop) {
        Drop = drop;
    }

    public String getLight() {
        return Light;
    }

    public void setLight(String light) {
        Light = light;
    }

    public String getSteps() {
        return Steps;
    }

    public void setSteps(String steps) {
        Steps = steps;
    }

    public String getAcce() {
        return Acce;
    }

    public void setAcce(String acce) {
        Acce = acce;
    }
}
