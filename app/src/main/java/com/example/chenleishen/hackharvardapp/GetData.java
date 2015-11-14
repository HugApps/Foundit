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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        URL url = new URL("http://yahoo.com");
        HttpURLConnection conn =(HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        InputStreamReader in = new InputStreamReader((InputStream)conn.getContent());
        BufferedReader bf = new BufferedReader(in);

        System.out.println("Rope");
        while(bf.readLine()!=null){
            output = output+bf.readLine();}

        }

        catch(Exception e){
            System.out.println(e.toString());

        }






        System.out.println("Rope");
        return output;
    }

    @Override
    // Parse string and upate sharepreferences file
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        list.add(s);
        list.add(s);
        list.add(s);
        HashSet<String> list2 = new HashSet<String>(list);
        Set<String> dataset= list2;
        i.updateData(dataset);

        // Somehow parse the strings
        // update item values


    }
}
