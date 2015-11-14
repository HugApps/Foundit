package com.example.chenleishen.hackharvardapp;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Hugo on 11/14/2015.
 */
public class GetData extends AsyncTask<String ,Void,String> {

    String address;
    Item i;

    public GetData(String ip , Item item){
        this.i=item;
        this.address= "http://"+ ip+":80/index.html";




    }
    @Override
    protected String doInBackground(String... params) {
        String output="";
        try{
        URL url = new URL(address);
        HttpURLConnection conn =(HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        InputStreamReader in = new InputStreamReader((InputStream)conn.getContent());
        BufferedReader bf = new BufferedReader(in);

        while(bf.readLine()!=null){
            output = output+bf.readLine();}

        }

        catch(Exception e){
            System.out.println("cannot connect");

        }







        return output;
    }

    @Override
    // Parse string and upate sharepreferences file
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // Somehow parse the strings
        // update item values
        i.setAcce(s);
        i.setDrop(s);
        i.setLight(s);
        i.setSteps(s);
        // update sharedpreference for object
        i.saveData();

    }
}
