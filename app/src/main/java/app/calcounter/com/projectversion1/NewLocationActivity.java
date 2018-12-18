package app.calcounter.com.projectversion1;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import app.calcounter.com.projectversion1.Model.LocationData;

import static app.calcounter.com.projectversion1.AdminLocationsActivity.LAST_DOCUMENT_ID;
import static app.calcounter.com.projectversion1.TaskViewer.LOCATION_COUNTER_FB;

public class NewLocationActivity extends AppCompatActivity {

    // justins
    // justins
    // justins
    // this needs logic to prevent null reads

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
                int validInput = 0;

                if(!addressLineOne.getText().toString().trim().equals(""))
                {
                    Log.e("this is the output ", addressLineOne.getText().toString());
                    validInput++;
                }

                if(!city.getText().toString().trim().equals(""))
                {
                    validInput++;
                    Log.e("this is the output ", addressLineOne.getText().toString());
                }

                if(!state.getText().toString().trim().equals(""))
                {
                    validInput++;
                    Log.e("this is the output ", addressLineOne.getText().toString());
                }

                if(!zipCode.getText().toString().trim().equals(""))
                {
                    validInput++;
                    Log.e("this is the output ", addressLineOne.getText().toString());
                }

                if(validInput == 4)
                {
                    goToAddTask();
                    Log.e("this is the output ", addressLineOne.getText().toString());
                }
                else {
                    // tells user they didn't enter enough data
                    Context context = getApplicationContext();
                    CharSequence text = "Need valid task input data!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

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
        Intent newTaskRec = new Intent(this, TaskViewerV2.class);

        // justin's code need validation
        int validInput = 0;

        if(!addressLineOne.getText().toString().trim().equals(""))
        {
            validInput++;
        }

        if(!city.getText().toString().trim().equals(""))
        {
            validInput++;
        }

        if(!state.getText().toString().trim().equals(""))
        {
            validInput++;
        }

        if(!zipCode.getText().toString().trim().equals(""))
        {
            validInput++;
        }

        if(validInput == 4)
        {
            Log.e("this is valid input " , Integer.toString(validInput));
            Intent previous = getIntent(); // for previous data
            int x = previous.getExtras().getInt("locCounter");
            String lastlocationID = previous.getExtras().getString(LAST_DOCUMENT_ID);
            // this will be missing because we didn't increment in rooms
            // this is critical coming from this window

            // be easier to make the location here rather than after a task
            Integer.parseInt(zipCode.getText().toString().trim());
            int zip =0;
            try{
               zip = Integer.parseInt(zipCode.getText().toString().trim());
            } catch (Exception e)
            {
                zip = 00000;
            }

            createLocation(x, addressLineOne.getText().toString(), addressLineTwo.getText().toString(),city.getText().toString(), state.getText().toString(), 1);



            mbundle.putString(LAST_DOCUMENT_ID,lastlocationID);
            mbundle.putInt("locCounter",x); // counter for number of locations
            mbundle.putString("address", addressLineOne.getText().toString().trim());
            mbundle.putString("address2", addressLineTwo.getText().toString().trim());
            mbundle.putString("city", city.getText().toString().trim());
            mbundle.putString("state", state.getText().toString().trim());
            mbundle.putString("zip", zipCode.getText().toString().trim());
            mbundle.putBoolean("isEdit", getIntent().getExtras().getBoolean("isEdit"));
            mbundle.putBoolean("isView", false);
            newTaskRec.putExtras(mbundle);
            startActivity(newTaskRec);
            finish();
        }
        else {
            // tells user they didn't enter enough data
            Context context = getApplicationContext();
            CharSequence text = "Need valid task input data!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        return;


    }

    public void createLocation(int locationDocID, String addressLineOne, String addressLineTwo, String city, String state, int zip)
    {

        DocumentReference mDocRef;
        int temp = locationDocID;
        temp++;


        mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + temp); // this is promoted to string

        LocationData locationLocalObject = new LocationData();

        locationLocalObject.setAddressLineOne(addressLineOne); // normal java code now
        locationLocalObject.setAddressLineTwo(addressLineTwo);
        locationLocalObject.setCity(city);
        locationLocalObject.setCity(state);
        try
        {
            locationLocalObject.setZipCode(zip);
        } catch(Exception e)
        {
            // fix this later
            locationLocalObject.setZipCode(00000);
        }

        // this saves locations to firebase --
        mDocRef.set(locationLocalObject).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


    public void goToAddRooms(View view){
        //sends all the data to rooms activity to handle everything when the user finishes
        Bundle mbundle = new Bundle();
        Intent newLocRooms = new Intent(this, NewLocationRoomsActivity.class);


        // justin's code needed validation
        int validInput = 0;

        if(!addressLineOne.getText().toString().trim().equals(""))
        {
            validInput++;
        }

        if(!city.getText().toString().trim().equals(""))
        {
            validInput++;
        }

        if(!state.getText().toString().trim().equals(""))
        {
            validInput++;
        }

        if(!zipCode.getText().toString().trim().equals(""))
        {
            validInput++;
        }

        if(validInput == 4)
        {
            Log.e("this is valid input " , Integer.toString(validInput));
            Intent previous = getIntent(); // for previous data
            int x = previous.getExtras().getInt("locCounter");
            String lastlocationID = previous.getExtras().getString(LAST_DOCUMENT_ID);

            mbundle.putString(LAST_DOCUMENT_ID,lastlocationID);
            mbundle.putInt("locCounter",x); // counter for number of locations
            mbundle.putString("address", addressLineOne.getText().toString().trim());
            mbundle.putString("address2", addressLineTwo.getText().toString().trim());
            mbundle.putString("city", city.getText().toString().trim());
            mbundle.putString("state", state.getText().toString().trim());
            mbundle.putString("zip", zipCode.getText().toString().trim());
            mbundle.putBoolean("isEdit", getIntent().getExtras().getBoolean("isEdit"));
            mbundle.putBoolean("isView",false);
            newLocRooms.putExtras(mbundle);
            startActivity(newLocRooms);
            finish();
        }
        else
        {
            // tells user they didn't enter enough data
            Context context = getApplicationContext();
            CharSequence text = "Need valid task input data!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();
        }

        return;


        // sent everything to rooms activity
    }
}

