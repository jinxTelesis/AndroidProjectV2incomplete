package app.calcounter.com.projectversion1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TaskViewer extends AppCompatActivity {

    //Recycler
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    //
    private List<ListItem> listItems;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_viewer);
       // getActionBar().setTitle("Tasks");
        getSupportActionBar().setTitle("Tasks");


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdre);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for(int i = 0; i < 10; i++)
        {
            counter++;
            ListItem item = new ListItem("Item " + (counter + 1), "Description", "Excellent");

            listItems.add(item);
        }

        setAdapterValues();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                ListItem item = new ListItem( "Item " + (counter + 1), "Description", "Excellent");
                listItems.add(item);
                setAdapterValues();
            }
        });
    }

    void setAdapterValues ()
    {
        adapter = new TaskAdapter(this,listItems); // object not the class
        recyclerView.setAdapter(adapter);
    }
}
