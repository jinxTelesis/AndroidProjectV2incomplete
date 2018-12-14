package app.calcounter.com.projectversion1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.calcounter.com.projectversion1.Model.LocationData;
import app.calcounter.com.projectversion1.Model.RoomListItem;

import static app.calcounter.com.projectversion1.TaskViewer.LOCATION_COUNTER_FB;

public class NewLocationRoomsActivity extends AppCompatActivity implements OnItemSelectedListener{


    public static final String ADDRESS = "address";
    public static final String ADDRESS_2 = "address2";
    public static final String CITY = "city";
    public static final String LAST_DOCUMENT_ID = "LastDocumentID";
    public static final String STATE = "state";
    public static final String ZIP = "zip";
    private DocumentReference mDocRef;
    Spinner inputRoomNames;

    //justins
    //justins
    //justins


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<RoomListItem> listItems;
    private DocumentReference mLocalTotal = FirebaseFirestore.getInstance().collection("appSetup").document("initFile");
    String selectedSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_rooms);

        recyclerView = (RecyclerView) findViewById(R.id.created_rooms_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inputRoomNames = (Spinner) findViewById(R.id.input_room);

        listItems = new ArrayList<>();

        if(getIntent().getExtras().getBoolean("isEdit")){
            //NOTE Code for rooms to load goes here if the room is being edited
        }

        //dummy values can delete this for loop and everything in it
        for(int i = 0; i < 10; i++) {
            RoomListItem item = new RoomListItem("Item " + (i + 1));
            listItems.add(item);
        }


        adapter = new AdminRoomsAdapter(this,listItems); // object not the class
        recyclerView.setAdapter(adapter);

        ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(this,
                R.array.room_choices, android.R.layout.simple_spinner_item);
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputRoomNames.setAdapter(sAdapter);
        inputRoomNames.setOnItemSelectedListener(this);


    }

    public void addRoom(View view){
        int counter = 1;
        boolean notFound = true;
        while(notFound) {
            notFound = false;
            for (int i = 0; i < listItems.size(); i++) {
                if (listItems.get(i).getName().equals(selectedSpin + " " + counter)) {
                    counter++;
                    notFound = true;
                }
            }
        }
        listItems.add(new RoomListItem(selectedSpin + " " + counter));
        adapter = new AdminRoomsAdapter(this,listItems); // object not the class
        recyclerView.setAdapter(adapter);
    }
    public void finishAddingRooms(View view){


        // locations so it saves it as the correct number for firebase

        String addressLineOne = getIntent().getExtras().getString(ADDRESS);
        String addressLineTwo = getIntent().getExtras().getString(ADDRESS_2);
        String city = getIntent().getExtras().getString(CITY);
        String state = getIntent().getExtras().getString(STATE);
        String zip = getIntent().getExtras().getString(ZIP);
        String locationDocID = getIntent().getExtras().getString(LAST_DOCUMENT_ID);
        String[] rooms = new String[listItems.size()];
        for (int i = 0; i < rooms.length; i++){
            rooms[i] = listItems.get(i).getName();
        }

        // need the adapter to update

        // this is gonna use the address data for the location name

        //EditText quoteView = (EditText) findViewById(R.id.editTextQuote);
        //EditText authorView = (EditText) findViewById(R.id.editText2);

        //String quoteText = quoteView.getText().toString();
        //String authorText = authorView.getText().toString();

        //if(quoteText.isEmpty() || authorText.isEmpty()) {return;} // odd statement but works

        // this should still work
        // this should still work

        int locationCounter = getIntent().getExtras().getInt("locCounter");// keep track out outside
        locationCounter++;
        // this an index problem or a viewing problem?



        // this is the other area that needs that fb counter

        // the adapter needs to pull in the documents id not its position

        // have the view pass in the document id rather than position?

        // old location saver

        //mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + locationCounter);

        // need to increment id one lol


        if(locationDocID == null)
        {
            locationDocID = "loc0";
        }

        if(locationDocID.length() == 4)
        {
            locationDocID = locationDocID.substring(3);
            Log.e("test",locationDocID);
        }

        if(locationDocID.length() == 5)
        {
            locationDocID = locationDocID.substring(3,5);
            Log.e("test",locationDocID);
        }

        if(locationDocID.length() == 6)
        {
            locationDocID = locationDocID.substring(3,6);
            Log.e("test",locationDocID);
        }


        Log.e("this is the string value", locationDocID);
        int temp = Integer.parseInt(locationDocID);
        temp++;


        mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + temp); // this is promoted to string

        LocationData locationLocalObject = new LocationData();

        locationLocalObject.setAddressLineOne(addressLineOne); // normal java code now
        locationLocalObject.setAddressLineTwo(addressLineTwo);
        locationLocalObject.setCity(city);
        locationLocalObject.setCity(state);
        try
        {
            locationLocalObject.setZipCode(Integer.parseInt(zip));
        } catch(Exception e)
        {
            // fix this later
            locationLocalObject.setZipCode(00000);
        }

        // this saves locations to firebase --
        mDocRef.set(locationLocalObject).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("HOLY MOTHER OF GOD", "Document has been saved!");

                // this is to store it as a hashmap can it just be stored as the boxed type?
                String localLocationCounter = "";
                mLocalTotal.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
                            String localLocationCounter = documentSnapshot.getString(LOCATION_COUNTER_FB);
                        }
                        else
                        {
                            String localLocationCounter = "0";
                        }
                    }
                });
                int temp =0;
                try{
                    temp = Integer.parseInt(localLocationCounter);
                }catch(Exception e)
                {

                }
                temp++;
                // this is overly complicated to store as a hashmap
                Map<String,Object> locationCounterOnFireBase = new HashMap<String, Object>();
                locationCounterOnFireBase.put(LOCATION_COUNTER_FB,Integer.toString(temp));

                mLocalTotal.set(locationCounterOnFireBase).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // actually puts the value up
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("WE CANT DO IT CAPTAIN", "didn't save document");
            }
        });



//        OBJECTTESTREF.set(CASTLEBRAVO).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.d("HOLY MOTHER OF GOD", "Document has been saved!");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("WE CANT DO IT CAPTAIN", "didn't save document");
//            }
//        });


        // replace this with your new object
        // old code
        // old code
        // old code
//        Map<String,Object> dataToSave = new HashMap<String,Object>();
//
//        // don't need to add counter because it is save on doc name?
//        dataToSave.put(ADDRESS,addressLineOne);
//        dataToSave.put(ADDRESS_2,addressLineTwo);
//        dataToSave.put(CITY,city);
//        dataToSave.put(STATE,state);
//        dataToSave.put(ZIP,zip);
        // old code
        // old code
        // old code

//
//        mDocRef.set((dataToSave)).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.d("Location saved", "Location saved");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("Loc didn't save", "didn't save loc");
//            }
//        });


        //NOTE code to send data to firebase when user is finished adding/removing rooms
        //NOTE can use getIntent().getExtras().getBoolean("isEdit") to return true/false whether it is an edit or not
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        selectedSpin = parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
