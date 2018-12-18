package app.calcounter.com.projectversion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdministrativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrative);
    }

//    public void openNewTask(View view)
//    {
//        startActivity(new Intent(this, NewTaskActivity.class));
//    }
//
//    public void openAdminLocations(View view){
//        startActivity(new Intent(this, AdminLocationsActivity.class));
//    }
//
//    public void openLocations(View view) {startActivity(new Intent(this, AdminLocationsActivity.class));
//    }
//
//    // need to add inspections activity
//    public void openInspection(View view) {}

    public void openNewTask(View view)
    {
        startActivity(new Intent(this, NewTaskActivity.class));
    }

    public void openAdminLocations(View view){
        startActivity(new Intent(this, AdminLocationsActivity.class));
    }

    public void openLocations(View view) {startActivity(new Intent(this, AdminLocationsActivity.class));
    }
    //public void openInspection(View view) {startActivity(new Intent(this, InspectionActivity.class)); }
}
