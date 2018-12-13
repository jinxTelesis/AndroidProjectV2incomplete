package app.calcounter.com.projectversion1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity {

    boolean timerRunning;
    private Chronometer chronometer;
    private long onPauseTime;
    private LocationManager locationManager;
    private LocationListener listener;
    private boolean startLocationSaved, atWorkZone;
    private double startLongitude, startLatitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        //NOTE load the time already spent on the task on the onPauseTime from firebase if the worker is coming back
        //onPauseTime is basically the time in milliseconds
        //NOTE may need to put the load in if (savedInstanceState == null), so it doesn't try to load from firebase on rotation
        //though it will still work if it isn't there since it will overwrite the read anyway
        onPauseTime = 0;
        timerRunning = false;
        startLocationSaved = false;
        atWorkZone = false;
        chronometer = (Chronometer) findViewById(R.id.chronometerTask);

        chronometer.setFormat("Timer: %s");
        //Chronometer basically works by the system clock's elapsed time
        //so if you want to start at 0, you set the currently elapsed time as the base and it will count the seconds from there
        //if you want to start at not 0, you subtract the number in milliseconds
        //which is basically tricking it to think the clock started x milliseconds ago
        chronometer.setBase(SystemClock.elapsedRealtime() - onPauseTime);

        //Load screen rotation stuff
        if(savedInstanceState != null){
            //Load timer info on screen rotation
            startLocationSaved = savedInstanceState.getBoolean("StartSavedBool");
            if(startLocationSaved){
                startLatitude = savedInstanceState.getDouble("StartLat");
                startLongitude = savedInstanceState.getDouble("StartLong");
                atWorkZone = savedInstanceState.getBoolean("AtWorkZone");
            }
            onPauseTime = savedInstanceState.getLong("ChronoTime");
            timerRunning = savedInstanceState.getBoolean("IsRunning");
            chronometer.setBase(SystemClock.elapsedRealtime() - onPauseTime);
        }

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(startLocationSaved){
                    //0.015 is about a mile, actual long/lat calculations are a lot more complex
                    //and unless you live in antarctica it shouldn't be an issue
                    if (!(location.getLatitude() < (startLatitude + 0.015) && location.getLatitude() > (startLatitude - 0.015)
                            && location.getLongitude() < (startLongitude + 0.015) && location.getLongitude() > (startLongitude - 0.015)) && atWorkZone){
                        atWorkZone = false;
                        stopTimer();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                TaskActivity.this);
                        alertDialog.setTitle("Task Timer Stopped");
                        alertDialog.setMessage("User has traveled over a mile away from the task starting area.");
                        alertDialog.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        alertDialog.show();
                        //NOTE upload time to firebase here, the user has left the work zone and the timer has stopped
                        //since the timer was stopped, the current time is in onPauseTime
                    } else if((location.getLatitude() < (startLatitude + 0.015) && location.getLatitude() > (startLatitude - 0.015)
                            && location.getLongitude() < (startLongitude + 0.015) && location.getLongitude() > (startLongitude - 0.015))) {
                        atWorkZone = true;
                    }
                } else {
                    //first time location is read, sets the start location.  Can delete this and set own coords if needed
                    atWorkZone = true;
                    startLatitude = location.getLatitude();
                    startLongitude = location.getLongitude();
                    startLocationSaved = true;
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        if(savedInstanceState != null) {
            if (timerRunning) {
                configure();
                chronometer.start();
            }
        }

    }

    public void openComments(View view)
    {
        startActivity(new Intent(this, CommentActivity.class));
    }

    public void openMaterials(View view)
    {
        startActivity(new Intent(this, MaterialActivity.class));
    }

    public void startButton(View view){
        if(configure()) {
            startTimer();
        }
    }

    public void stopButton(View view){
        stopTaskActivity();
    }

    public void finishButton(View view){
        finishTaskActivity();
    }

    @Override
    public void onBackPressed() {
        stopTaskActivity();
    }

    //When the user exits or leaves the activity
    public void stopTaskActivity(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                TaskActivity.this);
        alertDialog.setTitle("Leave Task?");
        alertDialog.setMessage("Do you want to leave the task?");
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        stopTimer();
                        //NOTE Upload time to firebase here
                        //Since the timer is stopped, time is saved in onPauseTime
                        //This upload should be for incomplete tasks that have been stopped
                        finish();
                    }
                });
        alertDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public void finishTaskActivity(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                TaskActivity.this);
        alertDialog.setTitle("Task Finished?");
        alertDialog.setMessage("Is the current task completed?");
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        stopTimer();
                        //NOTE Upload time to firebase here
                        //Since the timer is stopped, time is saved in onPauseTime
                        //This upload should be for finished tasks
                        finish();
                    }
                });
        alertDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public void startTimer(){
        //if the timer is not running, start the timer at the time it was paused
        //to unpause, you pretty much need to make a new timer that started onPauseTime milliseconds ago
        if(!timerRunning){
            chronometer.setBase(SystemClock.elapsedRealtime() - onPauseTime);
            chronometer.start();
            timerRunning = true;
            //NOTE Put startLocationSaved = false here if you want the start location to change when the user hits start
            //after the timer stops from walking out of range
        }
    }

    public void stopTimer(){
        //if the timer was running, stop the timer and save the current time
        //chonometer will keep counting in the background after a stop, so saving the time is needed for an unpause
        if (timerRunning) {
            chronometer.stop();
            onPauseTime = SystemClock.elapsedRealtime() - chronometer.getBase();
            timerRunning = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure();
                break;
            default:
                break;
        }
    }

    public boolean configure(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        ,10);
            }
            return false;
        }
        locationManager.requestLocationUpdates("gps", 5000, 0, listener);
        return true;
    }




    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("IsRunning", timerRunning);
        stopTimer();
        savedInstanceState.putLong("ChronoTime", onPauseTime);
        savedInstanceState.putDouble("StartLat", startLatitude);
        savedInstanceState.putDouble("StartLong", startLongitude);
        savedInstanceState.putBoolean("StartSavedBool", startLocationSaved);
        savedInstanceState.putBoolean("AtWorkZone", atWorkZone);
    }

}
