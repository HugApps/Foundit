package com.example.chenleishen.hackharvardapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
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
import android.widget.ImageView;

import com.microsoft.band.BandClient;
import com.microsoft.band.BandClientManager;
import com.microsoft.band.BandException;
import com.microsoft.band.BandInfo;
import com.microsoft.band.BandIOException;
import com.microsoft.band.ConnectionState;
import com.microsoft.band.notifications.MessageFlags;
//import com.microsoft.band.sdk.sampleapp.notification.R;
import com.microsoft.band.sensors.BandHeartRateEvent;
import com.microsoft.band.sensors.BandHeartRateEventListener;
import com.microsoft.band.tiles.BandTile;
import com.microsoft.band.BandException;

import com.microsoft.band.sensors.BandPedometerEvent;
import com.microsoft.band.sensors.BandPedometerEventListener;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private BandClient client = null;
    private TextView smartWatchStatus;
    TableLayout serviceTable;
    //    EditText serviceName;
//    TextView serviceStatus;
    Button addService, removeService, smartWatchConnect;
    //    TableRow service;
//    ImageView statusIcon;
    Fragment datafragment;
    FrameLayout frag;
    FragmentManager fm;
    FragmentTransaction ft;
    Item node;
    protected boolean flag = false;

    private UUID tileId = UUID.fromString("aa0D508F-70A3-47D4-BBA3-812BADB1F8Aa");

    private BandPedometerEventListener mPedometerEventListener = new BandPedometerEventListener() {
        @Override
        public void onBandPedometerChanged(final BandPedometerEvent event) {
            if (event != null) {
                appendToUI(String.format("You were %d steps away from the last connectivity\n", event.getTotalSteps()));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        datafragment = new datafragment();
        SharedPreferences file = getApplicationContext().getSharedPreferences("test", Context.MODE_PRIVATE);
        frag = (FrameLayout) findViewById(R.id.datafrag);

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
        GetData fetcher = new GetData("1.1.1.1", node);

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
                GetData fetcher = new GetData("1.1.1.1", node);
                fetcher.execute("hellp");
                fm = getFragmentManager();


                ft = fm.beginTransaction();
                ft.replace(R.id.datafrag, datafragment).commit();


                EditText serviceName = new EditText(getApplication());
                serviceName.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                serviceName.setPadding(5, 5, 5, 5);

                TextView serviceStatus = new TextView(getApplication());
                serviceStatus.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                serviceStatus.setPadding(5, 5, 5, 5);

                TableRow service = new TableRow(getApplication());
                service.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                service.setPadding(5, 5, 5, 5);

                ImageView statusIcon = new ImageView(getApplication());
                statusIcon.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                statusIcon.setPadding(5, 5, 5, 5);


                statusIcon.setImageResource(R.drawable.refresh);
                serviceName.setText("NEW SERVICE");
                serviceStatus.setText("Connecting");
                service.addView(statusIcon);
                service.addView(serviceName);
                service.addView(serviceStatus);
                serviceTable.addView(service);
            }
        });

        smartWatchConnect = (Button) findViewById(R.id.smartWatchConnect);
        smartWatchStatus = (TextView) findViewById(R.id.smartWatchStatus);
        smartWatchConnect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                smartWatchStatus.setText("");
                new appTask().execute();
            }
        });

        removeService = (Button) findViewById(R.id.removeService);
        removeService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                smartWatchStatus.setText("");
                new totalSteps().execute();
            }
        });

        new vibrate().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        smartWatchStatus.setText("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (client != null) {
            try {
                client.getSensorManager().unregisterPedometerEventListener(mPedometerEventListener);
            } catch (BandIOException e) {
                appendToUI(e.getMessage());
            }
        }
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

    @Override
    protected void onDestroy() {
        if (client != null) {
            try {
                client.disconnect().await();
            } catch (InterruptedException e) {
                // Do nothing as this is happening during destroy
            } catch (BandException e) {
                // Do nothing as this is happening during destroy
            }
        }
        super.onDestroy();
    }

    private class vibrate extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                System.out.println("ping");
                if (flag != false) {
                    flag = !flag;
                    Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
            }catch(Exception e){
                System.out.println("error");
            }
            return null;
        }
    }

    private class appTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (getConnectedBandClient()) {
                    if (doesTileExist(client.getTileManager().getTiles().await(), tileId)) {
                        sendMessage("You are connected");
//                        removeFromUI();
                    } else {
                        if (addTile()) {
                            sendMessage("You are connected");
//                            removeFromUI();
                        }
                    }
                } else {
                    appendToUI("Band isn't connected. Please make sure bluetooth is on and the band is in range.\n");
                }
            } catch (BandException e) {
                String exceptionMessage = "";
                switch (e.getErrorType()) {
                    case DEVICE_ERROR:
                        exceptionMessage = "Please make sure bluetooth is on and the band is in range.\n";
                        break;
                    case UNSUPPORTED_SDK_VERSION_ERROR:
                        exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.\n";
                        break;
                    case SERVICE_ERROR:
                        exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.\n";
                        break;
                    case BAND_FULL_ERROR:
                        exceptionMessage = "Band is full. Please use Microsoft Health to remove a tile.\n";
                        break;
                    default:
                        exceptionMessage = "Unknown error occured: " + e.getMessage() + "\n";
                        break;
                }
                appendToUI(exceptionMessage);

            } catch (Exception e) {
                appendToUI(e.getMessage());
            }
            return null;
        }
    }

    private class totalSteps extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (getConnectedBandClient()) {
                        client.getSensorManager().registerPedometerEventListener(mPedometerEventListener);
                } else {
                    appendToUI("Band isn't connected. Please make sure bluetooth is on and the band is in range.\n");
                }
            } catch (BandException e) {
                String exceptionMessage="";
                switch (e.getErrorType()) {
                    case UNSUPPORTED_SDK_VERSION_ERROR:
                        exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.\n";
                        break;
                    case SERVICE_ERROR:
                        exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.\n";
                        break;
                    default:
                        exceptionMessage = "Unknown error occured: " + e.getMessage() + "\n";
                        break;
                }
                appendToUI(exceptionMessage);

            } catch (Exception e) {
                appendToUI(e.getMessage());
            }
            return null;
        }
    }

        private boolean getConnectedBandClient() throws InterruptedException, BandException {
            if (client == null) {
                BandInfo[] devices = BandClientManager.getInstance().getPairedBands();
                if (devices.length == 0) {
//                    removeFromUI();
                    appendToUI("Band isn't paired with your phone.\n");
                    return false;
                }
                client = BandClientManager.getInstance().create(getBaseContext(), devices[0]);
            } else if (ConnectionState.CONNECTED == client.getConnectionState()) {
                return true;
            }
//            removeFromUI();
            appendToUI("Band is connecting...\n");
            return ConnectionState.CONNECTED == client.connect().await();
        }

        private boolean doesTileExist(List<BandTile> tiles, UUID tileId) {
            for (BandTile tile : tiles) {
                if (tile.getTileId().equals(tileId)) {
                    return true;
                }
            }
            return false;
        }

        private void appendToUI(final String string) {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    smartWatchStatus.append(string);
                }
            });
        }

//    private void removeFromUI() {
//        this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                smartWatchStatus.clearComposingText();
//            }
//        });
//    }

        private boolean addTile() throws Exception {
        /* Set the options */
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap tileIcon = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.applogo2, options);
            Bitmap badgeIcon = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.applogo3, options);

            BandTile tile = new BandTile.Builder(tileId, "FindIt", tileIcon)
                    .setTileSmallIcon(badgeIcon).build();
            appendToUI("FindIt is being added to your Microsoft Band, please be patient ...\n");
            if (client.getTileManager().addTile(this, tile).await()) {
//                removeFromUI();
                appendToUI("FindIt is added to your Microsoft Band.\n");
                return true;
            } else {
//                removeFromUI();
                appendToUI("Unable to add FindIt to your Microsoft Band.\n");
                return false;
            }
        }

        private void sendMessage(String message) throws BandIOException {
            client.getNotificationManager().sendMessage(tileId, "", message, new Date(), MessageFlags.SHOW_DIALOG);
            appendToUI(message);
        }
    }



