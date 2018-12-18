package app.calcounter.com.projectversion1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.calcounter.com.projectversion1.Model.LocationData;
import app.calcounter.com.projectversion1.Model.LocationListItem;




public class TaskViewerV2 extends AppCompatActivity {

    AlertDialog.Builder builder;

    private int locationCounter = 0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LocationListItem> listItems;
    private String lastDocumentID;
    private int captureCounter =0;
    private int adapterPosition = 0;
    private LocationData locationData = new LocationData();
    private String m_TextTask = "";
    private String m_TextPriority = "";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference mDocRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_viewer_v2);

        // need to iterate over the list the position number of times
        // then capture that document
        Intent previousIntent = getIntent();
        adapterPosition = previousIntent.getExtras().getInt("locCounter");



        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdre);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<LocationListItem>();

        // the locations object was not created yet, it needs to be created first


        mDocRef = mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + (adapterPosition));

        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    //locationData = mDocRef.get().getResult().toObject(LocationData.class); this won't work does not thread properly
                    locationData = documentSnapshot.toObject(LocationData.class);
                }
            }
        });



        int tempPri = 0;
        String tempPriStr = "";

        for(int i = 0; i < 12; i++)
        {
            if(locationData.task1.getTaskDetails1() != null) // locationData.task1 != null
            {
                tempPri = Integer.parseInt(String.valueOf(locationData.task1.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task1.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task2.getTaskDetails1() != null)
            {
                tempPri = Integer.parseInt(String.valueOf(locationData.task2.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task2.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);

            }

            if(locationData.task3.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task3.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task3.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task4.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task4.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task4.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task5.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task5.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task5.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task6.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task6.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task6.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task7.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task7.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task7.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task8.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task8.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task8.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task9.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task9.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task9.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task10.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task10.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task10.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task11.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task11.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task11.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }

            if(locationData.task12.getTaskDetails1() != null)
            {

                tempPri = Integer.parseInt(String.valueOf(locationData.task12.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task12.getTaskDetails1(),Integer.toString(tempPri));
                listItems.add(item);
            }
        }

        adapter = new TaskAdapterV2(TaskViewerV2.this,listItems);
        recyclerView.setAdapter(adapter);

        //Todo make function
        // this should be a function
        // this should be a function




        // this does not work good try
//        db.collection("locations") // will iterate over the collection
//                .get() // this listener should be safe for activity change
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful())
//                        {
//                            for(DocumentSnapshot document : task.getResult())
//                            {
//                                captureCounter++;
//                                if(captureCounter == adapterPosition) // this should leave the data object in the correct spot
//                                {
//                                    locationData = document.toObject(LocationData.class);
//                                    Log.e("this fired!", "we need this to fire");
//                                }
//                            }
//
//                        }else
//                        {
//                            //Log.e("Firebase blows", "error", task.getException());
//                        }
//                    }
//                });


        // need to add tasks before we can read

        //crash point didn't add floating action button? xml not there?
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                builder = new AlertDialog.Builder(TaskViewerV2.this);
                builder.setTitle("Enter Task Name & priority");

                Context context = TaskViewerV2.this;
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);


                final EditText inputTask = new EditText(context);
                inputTask.setHint("Task Name");
                layout.addView(inputTask);


                final EditText inputPriority = new EditText(context);
                inputPriority.setHint("Priority 1-5");
                layout.addView(inputPriority);

                builder.setView(layout);

                builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_TextTask = inputTask.getText().toString();
                        m_TextPriority = inputPriority.getText().toString();
                        int temp =0;
                        boolean pass = false;

                        try{
                            temp = Integer.parseInt(m_TextPriority);
                        }
                        catch(Exception e) {
                            dialog.cancel();
                        };

                        if(temp < 0 || temp > 6)
                        {
                            Context context = getApplicationContext();
                            CharSequence text = "Priority out of range";
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context,text,duration);
                            toast.show();
                            dialog.cancel();
                        }
                        else
                        {
                            pass = true;
                        }

                        if(m_TextTask != "" && pass == true)
                        {
                            pass = false;
                            if(m_TextTask.length() > 15)
                            {
                                m_TextTask = m_TextTask.substring(0,20);
                            }

                            // read current objects here and create new task object
                            // alright


                            // wrote to task one not task 2 unfortunately
                            locationData.task1.setTaskDetails1(m_TextTask);
                            locationData.task1.setPriority(Integer.parseInt(m_TextPriority));
                            Log.e("this is the data being uploaded", locationData.task1.getTaskDetails1());
                            Log.e("this is the data being uploaded", Integer.toString(locationData.task1.getPriority()));

                            mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + (adapterPosition + 1));
                            mDocRef.set(locationData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.e("new task saved", "task saved!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Task didn't save, ", "didn't save");
                                }
                            });

                            LocationListItem item = new LocationListItem(locationData.task1.getTaskDetails1(),Integer.toString(locationData.task1.getPriority()));
                            listItems.add(item);

                            listItems.clear();
                            recyclerView.removeAllViewsInLayout();

                            //gonna need weird if statements
                            // because this is not a


                        }
                        else
                        {
                            dialog.cancel();
                        }


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
        // have it pass in the position
        // capture the document id at that position?



    protected  void onStart(){
        super.onStart();
    }

    public void onResume() {

        super.onResume();
        locationCounter = 0;

        // needed it will reload all of them
        listItems.clear();
        recyclerView.removeAllViewsInLayout();

        Intent previousIntent = getIntent();
        adapterPosition = previousIntent.getExtras().getInt("locCounter");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdre);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<LocationListItem>();

        captureCounter = 0; // need this reset in resume

        mDocRef = mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + (adapterPosition -1));
        Log.e("this is the positon passed in ", Integer.toString(adapterPosition));

        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    //locationData = mDocRef.get().getResult().toObject(LocationData.class); this won't work does not thread properly
                    locationData = documentSnapshot.toObject(LocationData.class);

                    Log.e("we have data",locationData.task1.getTaskDetails1());
                }
            }
        });


        int tempPri = 0;
        String tempPriStr = "";

        for (int i = 0; i < 12; i++) {
            if (locationData.task1.getTaskDetails1() != null) // locationData.task1 != null
            {
                Log.e("first task had data","data");
                tempPri = Integer.parseInt(String.valueOf(locationData.task1.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task1.getTaskDetails1(), Integer.toString(tempPri));
                Log.e("data ",locationData.task1.getTaskDetails1());
                listItems.add(item);
            }

            if (locationData.task2.getTaskDetails1() != null) {
                tempPri = Integer.parseInt(String.valueOf(locationData.task2.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task2.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);

            }

            if (locationData.task3.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task3.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task3.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task4.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task4.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task4.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task5.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task5.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task5.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task6.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task6.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task6.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task7.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task7.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task7.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task8.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task8.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task8.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task9.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task9.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task9.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task10.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task10.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task10.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task11.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task11.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task11.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task12.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task12.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task12.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }
        }

        adapter = new TaskAdapterV2(TaskViewerV2.this, listItems);
        recyclerView.setAdapter(adapter);
    }

    public void onRestart() {
        super.onRestart();

        locationCounter = 0;

        // needed it will reload all of them
        listItems.clear();
        recyclerView.removeAllViewsInLayout();

        Intent previousIntent = getIntent();
        adapterPosition = previousIntent.getExtras().getInt("locCounter");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdre);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<LocationListItem>();

        captureCounter = 0; // need this reset in resume

        mDocRef = mDocRef = FirebaseFirestore.getInstance().collection("locations").document("loc" + (adapterPosition -1));
        Log.e("this is the positon passed in ", Integer.toString(adapterPosition));

        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    //locationData = mDocRef.get().getResult().toObject(LocationData.class); this won't work does not thread properly
                    locationData = documentSnapshot.toObject(LocationData.class);
                    Log.e("we have data",locationData.task1.getTaskDetails1());
                }
            }
        });


        int tempPri = 0;
        String tempPriStr = "";

        for (int i = 0; i < 12; i++) {
            if (locationData.task1.getTaskDetails1() != null) // locationData.task1 != null
            {
                Log.e("first task had data","data");
                tempPri = Integer.parseInt(String.valueOf(locationData.task1.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task1.getTaskDetails1(), Integer.toString(tempPri));
                Log.e("data ",locationData.task1.getTaskDetails1());
                listItems.add(item);
            }

            if (locationData.task2.getTaskDetails1() != null) {
                tempPri = Integer.parseInt(String.valueOf(locationData.task2.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task2.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);

            }

            if (locationData.task3.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task3.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task3.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task4.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task4.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task4.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task5.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task5.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task5.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task6.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task6.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task6.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task7.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task7.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task7.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task8.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task8.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task8.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task9.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task9.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task9.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task10.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task10.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task10.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task11.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task11.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task11.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }

            if (locationData.task12.getTaskDetails1() != null) {

                tempPri = Integer.parseInt(String.valueOf(locationData.task12.getPriority()));
                LocationListItem item = new LocationListItem(locationData.task12.getTaskDetails1(), Integer.toString(tempPri));
                listItems.add(item);
            }
        }

        adapter = new TaskAdapterV2(TaskViewerV2.this, listItems);
        recyclerView.setAdapter(adapter);

    }


}
