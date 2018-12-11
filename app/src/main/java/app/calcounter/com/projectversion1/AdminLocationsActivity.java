package app.calcounter.com.projectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



import java.util.ArrayList;
import java.util.List;

import app.calcounter.com.projectversion1.Model.LocationListItem;

public class AdminLocationsActivity extends Activity {
    // justins
    // justins
    // justins

    // these should be moved
    public static final String ADDRESS = "address";
    public static final String ADDRESS_2 = "address2";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String ZIP = "zip";

    private int locationCounter = 0; // added
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LocationListItem> listItems;
    private int dynamicCount = 0;

    // firebase code
    //private

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private DocumentReference mDocRef  = FirebaseFirestore.getInstance().collection("locations").document("loc10");
    //firebase code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_location3);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdre);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
        listItems = new ArrayList<LocationListItem>();

        // this gets all docsthe collection
        // don't move this to on create please
        db.collection("locations") // will iterate over the collection
                .get() // this listener should be safe for activity change
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot document : task.getResult())
                            {
                                locationCounter++;
                                LocationListItem item = new LocationListItem((String)document.getId().toString(), (String)document.get(ADDRESS).toString());
                                listItems.add(item);
                                Log.e("it worked like a charm", document.getId() + " => " + document.getData());
                            }

                        }else
                        {
                            Log.e("Firebase blows", "error", task.getException());
                        }

                        Log.e("readout", String.valueOf(listItems.size()));
                        adapter = new AdminLocationsAdapter(AdminLocationsActivity.this,listItems); // object not the class
                        recyclerView.setAdapter(adapter);

                    }
                });



    }

    @Override // all has to be on start
    protected  void onStart(){
        super.onStart();







        // this is the event listener and this will crash it if not detached?
        // this will grab the data when it updates
        // need to turn off this listener

        // this is a different from that if you pass the activity and the activity stops you can
        // stop this listener

        // this is for the metadata
        // this uses more data?

        //DocumentListenOptions verboseOptions = new DocumentListenOptions();
        //verboseOptions.includeMetadataChanges();

       // recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdre);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //adapter = new AdminLocationsAdapter(this,listItems); // object not the class
        //recyclerView.setAdapter(adapter);

        // add verbose options here if you want that included

    }

    public void onResume() {

        super.onResume();
    }

    public void onRestart(){
        super.onRestart();

        // needed it will reload all of them
        listItems.clear();
        //adapter.notifyDataSetChanged();
        recyclerView.removeAllViewsInLayout();

        //recyclerView.setAdapter(adapter);
        db.collection("locations") // will iterate over the collection
                .get() // this listener should be safe for activity change
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot document : task.getResult())
                            {
                                //locationCounter++;
                                LocationListItem item = new LocationListItem((String)document.getId().toString(), (String)document.get(ADDRESS).toString());
                                listItems.add(item);
                                Log.e("it worked like a charm", document.getId() + " => " + document.getData());
                            }

                        }else
                        {
                            Log.e("Firebase blows", "error", task.getException());
                        }

                        Log.e("readout", String.valueOf(listItems.size()));
                        adapter = new AdminLocationsAdapter(AdminLocationsActivity.this,listItems); // object not the class
                        recyclerView.setAdapter(adapter);

                    }
                });
    }




    public void addLocation(View view){
        Intent addLocation = new Intent(this, NewLocationActivity.class);
        addLocation.putExtra("isEdit", false);
        addLocation.putExtra("locCounter", locationCounter); // passing in current number to increment
        // when this activity closes it will lose this number or inner won't know how many
        // on outside, we can't create the number here because they might cancel
        startActivity(addLocation);
    }

}
