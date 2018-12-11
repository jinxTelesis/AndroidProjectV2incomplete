package app.calcounter.com.projectversion1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewLocationActivity extends AppCompatActivity {

    // justins
    // justins
    // justins

    private Button addTaskbtn;


    EditText addressLineOne, addressLineTwo, city, state, zipCode;
    Button cLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);


        // add task needs to fire add location code also
        addTaskbtn = (Button) findViewById(R.id.addTaskBtn);
        addTaskbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this needs the code to create location
                goToAddTask();
            }
        });

        addressLineOne = (EditText) findViewById(R.id.editTextLineOne);
        addressLineTwo = (EditText) findViewById(R.id.editTextLineTwo);
        city = (EditText) findViewById(R.id.editTextCity);
        state = (EditText) findViewById(R.id.editTextState);
        zipCode = (EditText) findViewById(R.id.editTextZip);
        cLocation = (Button) findViewById(R.id.goToCreateRooms);

        if (getIntent().getExtras().getBoolean("isEdit")){
            cLocation.setText(R.string.location_edit);
            addressLineOne.setText(getIntent().getExtras().getString("address"));
            addressLineTwo.setText(getIntent().getExtras().getString("address2"));
            //NOTE Should load city, state and zip this is an edit // is this incomplete?
        }
    }

    public void goToAddTask()
    {
        Bundle mbundle = new Bundle();
        Intent newTaskRec = new Intent(this, TaskViewer.class);

        Intent previous = getIntent(); // for previous data
        int x = previous.getExtras().getInt("locCounter");
        mbundle.putInt("locCounter",x); // counter for number of locations
        mbundle.putString("address", addressLineOne.getText().toString().trim());
        mbundle.putString("address2", addressLineTwo.getText().toString().trim());
        mbundle.putString("city", city.getText().toString().trim());
        mbundle.putString("state", state.getText().toString().trim());
        mbundle.putString("zip", zipCode.getText().toString().trim());
        mbundle.putBoolean("isEdit", getIntent().getExtras().getBoolean("isEdit"));
        newTaskRec.putExtras(mbundle);
        startActivity(newTaskRec);
        finish();

    }

    public void goToAddRooms(View view){
        //sends all the data to rooms activity to handle everything when the user finishes
        Bundle mbundle = new Bundle();
        Intent newLocRooms = new Intent(this, NewLocationRoomsActivity.class);

        Intent previous = getIntent(); // for previous data
        int x = previous.getExtras().getInt("locCounter");
        mbundle.putInt("locCounter",x); // counter for number of locations
        mbundle.putString("address", addressLineOne.getText().toString().trim());
        mbundle.putString("address2", addressLineTwo.getText().toString().trim());
        mbundle.putString("city", city.getText().toString().trim());
        mbundle.putString("state", state.getText().toString().trim());
        mbundle.putString("zip", zipCode.getText().toString().trim());
        mbundle.putBoolean("isEdit", getIntent().getExtras().getBoolean("isEdit"));
        newLocRooms.putExtras(mbundle);
        startActivity(newLocRooms);
        finish();
        // sent everything to rooms activity
    }
}

