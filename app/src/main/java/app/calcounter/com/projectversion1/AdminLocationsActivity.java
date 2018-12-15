package app.calcounter.com.projectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import app.calcounter.com.projectversion1.Model.LocationData;
import app.calcounter.com.projectversion1.Model.LocationListItem;
import app.calcounter.com.projectversion1.Model.TaskData;

public class AdminLocationsActivity extends Activity {
    // justins
    // justins
    // justins

    // locations recyclerviewer

    // these should be moved to another file
    public static final String ADDRESS = "address";
    public static final String ADDRESS_2 = "address2";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String ZIP = "zip";
    public static final String LAST_DOCUMENT_ID = "LastDocumentID";

    private int locationCounter = 0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LocationListItem> listItems;
    private int dynamicCount = 0;
    private String lastDocumentID;
    private int newLocationCounter =0;


    private int innerI =0;

    //LocationData[] locationData = new LocationData[12];

    LocationData locationData = new LocationData();




    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private DocumentReference mDocRef  = FirebaseFirestore.getInstance().collection("locations").document("loc10");

    // just a counter so the adapter won't overwrite projects on delete, could just read the last location in the adapter
    // this seemed to be the least brittle solution
    private DocumentReference mLocalTotal = FirebaseFirestore.getInstance().collection("appSetup").document("initFile");

    //private DocumentReference newDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc10");
    //firebase code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_location3);



//        for(int i = 0; i < 12; i++)
//        {
//            locationData[i] = new LocationData();
//        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdre);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
        listItems = new ArrayList<LocationListItem>();



//        db.collection("locations") // will iterate over the collection
//                .get() // this listener should be safe for activity change
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful())
//                        {
//                            for(QueryDocumentSnapshot document : task.getResult())
//                            {
//                                newLocationCounter++;
//                            }
//
//                        }else
//                        {
//                        }
//                    }
//                });
//
//        for(innerI =0; innerI < newLocationCounter; innerI++)
//        {
//            mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + newLocationCounter);
//
//            mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                @Override
//                public void onSuccess(DocumentSnapshot documentSnapshot) {
//                    // this had to be final lol
//                    locationData[innerI] = documentSnapshot.toObject(LocationData.class);
//                }
//            });
//        }
//        innerI = 0; // reset innerI
//
//        for(int i =0; i < newLocationCounter; i++)
//        {
//            LocationListItem item = new LocationListItem(locationData[i].getAddressLineOne().toString(),locationData[i].getCity().toString());
//            listItems.add(item);
//        }
//
//        adapter = new AdminLocationsAdapter(AdminLocationsActivity.this,listItems); // object not the class
//        recyclerView.setAdapter(adapter);


        db.collection("locations") // will iterate over the collection
                .get() // this listener should be safe for activity change
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot document : task.getResult())
                            {

                                LocationData newTestLoc = document.toObject(LocationData.class);

                                newTestLoc.getAddressLineOne();

                                //Log.e("document id is ", document.getId());
                                lastDocumentID = document.getId();


                                locationCounter++;

                                LocationListItem item = new LocationListItem(newTestLoc.getAddressLineOne(),newTestLoc.getCity());
                                listItems.add(item);
                                //Log.e("it worked like a charm", document.getId() + " => " + document.getData());
                                //}

                            }

                        }else
                        {
                            //Log.e("Firebase blows", "error", task.getException());
                        }

                        //Log.e("readout", String.valueOf(listItems.size()));
                        adapter = new AdminLocationsAdapter(AdminLocationsActivity.this,listItems); // object not the class
                        recyclerView.setAdapter(adapter);

                    }
                });






        // this gets all docsthe collection
        // don't move this to on create please
//        db.collection("locations") // will iterate over the collection
//                .get() // this listener should be safe for activity change
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful())
//                        {
//                            for(QueryDocumentSnapshot document : task.getResult())
//                            {
//
//                                //Todo this breaks if it returns from the view
//                                // change it so null subfields don't trip this up
//                                // need a real solution to this not hack
//
//                                //if(document.getData() != null && document.get(ADDRESS) != null) // prevents crash if null data
//                                //{
//
//                                Log.e("document id is ", document.getId());
//                                lastDocumentID = document.getId();
//
//
//                                    locationCounter++;
//                                    mDocRef = FirebaseFirestore.getInstance().collection("locations").document(lastDocumentID);
//
//                                    mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                        @Override
//                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                            locationData = documentSnapshot.toObject(LocationData.class);
//                                        }
//                                    });
//
//
//
//
//
//
//                                    //LocationListItem item = new LocationListItem((String)document.getId().toString(),
//                                    //        document.getData());
//                                    //listItems.add(item);
//                                    //Log.e("it worked like a charm", document.getId() + " => " + document.getData());
//                                //}
//
//                            }
//
//                        }else
//                        {
//                            //Log.e("Firebase blows", "error", task.getException());
//                        }
//
//                        //Log.e("readout", String.valueOf(listItems.size()));
//                        adapter = new AdminLocationsAdapter(AdminLocationsActivity.this,listItems); // object not the class
//                        recyclerView.setAdapter(adapter);
//
//                    }
//                });



    }

    @Override // all has to be on start
    protected  void onStart(){
        super.onStart();



    }

    public void onResume() {

        super.onResume();
    }

    public void onRestart(){
        super.onRestart();
        locationCounter = 0;

        // needed it will reload all of them
        listItems.clear();
        //adapter.notifyDataSetChanged();
        recyclerView.removeAllViewsInLayout();

//        db.collection("locations") // will iterate over the collection
//                .get() // this listener should be safe for activity change
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful())
//                        {
//                            for(QueryDocumentSnapshot document : task.getResult())
//                            {
//                                newLocationCounter++;
//                            }
//
//                        }else
//                        {
//                        }
//                    }
//                });
//
//        for(innerI =0; innerI < newLocationCounter; innerI++)
//        {
//            mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + newLocationCounter);
//
//            mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                @Override
//                public void onSuccess(DocumentSnapshot documentSnapshot) {
//                    // this had to be final lol
//                    locationData[innerI] = documentSnapshot.toObject(LocationData.class);
//                }
//            });
//        }
//        innerI = 0; // reset innerI
//
//        for(int i =0; i < newLocationCounter; i++)
//        {
//            LocationListItem item = new LocationListItem(locationData[i].getAddressLineOne().toString(),locationData[i].getCity());
//            listItems.add(item);
//        }
//
//        adapter = new AdminLocationsAdapter(AdminLocationsActivity.this,listItems); // object not the class
//        recyclerView.setAdapter(adapter);

        db.collection("locations") // will iterate over the collection
                .get() // this listener should be safe for activity change
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot document : task.getResult())
                            {

                                LocationData newTestLoc = document.toObject(LocationData.class);

                                newTestLoc.getAddressLineOne();

                                //Log.e("document id is ", document.getId());
                                lastDocumentID = document.getId();


                                locationCounter++;

                                LocationListItem item = new LocationListItem("Location " + locationCounter,
                                        " Address: " + newTestLoc.getAddressLineOne());
                                listItems.add(item);
                                //Log.e("it worked like a charm", document.getId() + " => " + document.getData());
                                //}

                            }

                        }else
                        {
                            //Log.e("Firebase blows", "error", task.getException());
                        }

                        //Log.e("readout", String.valueOf(listItems.size()));
                        adapter = new AdminLocationsAdapter(AdminLocationsActivity.this,listItems); // object not the class
                        recyclerView.setAdapter(adapter);

                    }
                });





        //recyclerView.setAdapter(adapter);
//        db.collection("locations") // will iterate over the collection
//                .get() // this listener should be safe for activity change
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful())
//                        {
//                            for(QueryDocumentSnapshot document : task.getResult())
//                            {
//
//                                //Todo this breaks if it returns from the view
//                                // change it so null subfields don't trip this up
//                                // need a real solution to this not hack
//
//                                //if(document.getData() != null && document.get(ADDRESS) != null) // prevents crash if null data
//                                //{
//
//                                Log.e("document id is ", document.getId());
//                                lastDocumentID = document.getId();
//
//                                locationCounter++;
//                                mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + locationCounter);
//
//                                mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                    @Override
//                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                        locationData = documentSnapshot.toObject(LocationData.class);
//
//                                    }
//                                });
//
//                                // this part of the data times out or something
//
//                                LocationListItem item = new LocationListItem((String)document.getId().toString(),
//                                        locationData.getAddressLineOne().toString());
//                                listItems.add(item);
//                                //Log.e("it worked like a charm", document.getId() + " => " + document.getData());
//                                //}
//
//                            }
//
//                        }else
//                        {
//                            //Log.e("Firebase blows", "error", task.getException());
//                        }
//
//                        //Log.e("readout", String.valueOf(listItems.size()));
//                        adapter = new AdminLocationsAdapter(AdminLocationsActivity.this,listItems); // object not the class
//                        recyclerView.setAdapter(adapter);
//
//                    }
//                });
    }




    public void addLocation(View view){ // goes to NewLocationActivity
        Intent addLocation = new Intent(this, NewLocationActivity.class);
        addLocation.putExtra(LAST_DOCUMENT_ID, lastDocumentID);
        addLocation.putExtra("isEdit", false);
        addLocation.putExtra("locCounter", locationCounter); // passing in current number to increment
        // when this activity closes it will lose this number or inner won't know how many
        // on outside, we can't create the number here because they might cancel
        startActivity(addLocation);
    }

}
