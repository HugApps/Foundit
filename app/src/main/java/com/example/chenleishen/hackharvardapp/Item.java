package com.example.chenleishen.hackharvardapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugo on 11/14/2015.
 */
// Item attached to Dongle
public class Item {


    private String Tag,Drop,Light,Steps,Acce,Temp;
    Boolean Loss = false;

    SharedPreferences f ;


    String SHARED_PREFRENCE = "ItemDATA";


    public Item (String Name ,SharedPreferences file){

        this.Tag=Name;

        f=file;
        //Testing fake data
       this.Drop = "Dropped";
        this.Light="Bright";

        this.Temp="20";
        this.Steps ="56";
     ArrayList<String> list = new ArrayList<String>();
        list.add(Steps);

        list.add(Temp);
        list.add(Light);
        //list.add(Temp);
         //0
        list.add(Drop);

     HashSet<String> list2 = new HashSet<String>(list);
     SharedPreferences.Editor  editor = f.edit();
      editor.remove(Tag);
      editor.commit();
      editor.putStringSet(Tag, list2);
        editor.commit();
      // Set<String> set2 = f.getStringSet(Tag,null);
      //  for (String s : set2){
           // System.out.println(s);
     //   }





    }

    // Saves the data list into shared preference with the object name as the key
    public void Lost(){
        this.Loss  = !Loss;

    }

    public void updateData(Set<String> datas){

       System.out.println("WDF");
       SharedPreferences.Editor  editor = f.edit();
       editor.remove(Tag);
       editor.putStringSet(Tag,datas);
       editor.commit();


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
