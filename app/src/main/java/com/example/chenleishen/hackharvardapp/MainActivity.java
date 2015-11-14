package com.example.chenleishen.hackharvardapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    TableLayout serviceTable;
//    EditText serviceName;
//    TextView serviceStatus;
    Button addService, removeService;
//    TableRow service;
//    ImageView statusIcon;
    Fragment datafragment ;
    FrameLayout frag ;
    FragmentManager fm;
    FragmentTransaction ft;
    Item node;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        datafragment = new datafragment();
        SharedPreferences file = getApplicationContext().getSharedPreferences("test", Context.MODE_PRIVATE);
        frag = (FrameLayout)findViewById(R.id.datafrag);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
         node = new Item("ITEMS",
                file);
        GetData fetcher = new GetData("1.1.1.1",node);

        serviceTable = (TableLayout) findViewById(R.id.serviceTable);
//        serviceTable.removeAllViews();
//        service = (TableRow) findViewById(R.id.newRow);
//        statusIcon = (ImageView) findViewById(R.id.statusIcon);
        addService = (Button) findViewById(R.id.addService);
//        serviceName = (EditText) findViewById(R.id.serviceName);
//        serviceStatus = (TextView) findViewById(R.id.serviceStatus);

        addService.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


               // GetData fetcher = new GetData("1.1.1.1",node);
               // fetcher.execute("hellp");
               // fm = getFragmentManager();


               // ft = fm.beginTransaction();
               // ft.replace(R.id.datafrag,datafragment).commit();

               EditText serviceName = new EditText(getApplication());
                serviceName.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                TextView serviceStatus = new TextView(getApplication());
                serviceStatus.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                TableRow service = new TableRow(getApplication());
                service.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                ImageView statusIcon = new ImageView(getApplication());
                statusIcon.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));

                statusIcon.setImageResource(R.drawable.refresh);
                serviceName.setText("NEW SERVICE");
                serviceStatus.setText("Connecting");
                service.addView(statusIcon);
                service.addView(serviceName);
                service.addView(serviceStatus);
                serviceTable.addView(service);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
