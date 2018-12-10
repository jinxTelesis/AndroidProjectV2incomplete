package app.calcounter.com.projectversion1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;


import java.util.ArrayList;
import java.util.List;

import app.calcounter.com.projectversion1.Model.RoomListItem;

public class NewLocationRoomsActivity extends AppCompatActivity implements OnItemSelectedListener{

    Spinner inputRoomNames;

    //justins
    //justins
    //justins


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<RoomListItem> listItems;
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

        String addressLineOne = getIntent().getExtras().getString("address");
        String addressLineTwo = getIntent().getExtras().getString("address2");
        String city = getIntent().getExtras().getString("city");
        String state = getIntent().getExtras().getString("state");
        String zip = getIntent().getExtras().getString("zip");
        String[] rooms = new String[listItems.size()];
        for (int i = 0; i < rooms.length; i++){
            rooms[i] = listItems.get(i).getName();
        }
        // need the adapter to update


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
