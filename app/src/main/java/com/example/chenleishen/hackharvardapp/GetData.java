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
    ArrayList<String> list = new ArrayList<String>();
    int TIMEOUT = 5000;

    public GetData(String ip , Item item){
        this.i=item;
        this.address= "http://"+ ip+":80/index.html";




    }
    @Override
    protected String doInBackground(String... params) {

        try{
        URL url = new URL("http://192.168.4.1:80/index.html");
        HttpURLConnection conn =(HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(50);
        conn.setRequestMethod("GET");
        InputStreamReader in = new InputStreamReader((InputStream)conn.getContent());
        BufferedReader bf = new BufferedReader(in);
        String output;
        System.out.println("Rope");
        while((output=bf.readLine())!=null){
            list.add(output);

            }
        for (String s:list){System.out.println(s);}
        }

        catch(Exception e){
            System.out.println(e.toString());

        }







        return "";
    }

    @Override
    // Parse string and upate sharepreferences file
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        HashSet<String> list2 = new HashSet<String>(list);
        Set<String> dataset= list2;
        i.updateData(dataset);

        // Somehow parse the strings
        // update item values


    }
}
