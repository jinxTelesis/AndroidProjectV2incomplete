package app.calcounter.com.projectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UserActivityRuss extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userruss);
    }

    public void openNewTask(View view)
    {
        startActivity(new Intent(this, NewTaskActivityRuss.class));
    }

    public void openInspection(View view) {startActivity(new Intent(this, InspectionActivityRuss.class)); }

    public void openLocations(View view) {startActivity(new Intent(this, AdminLocationsActivity.class));
    }
}
