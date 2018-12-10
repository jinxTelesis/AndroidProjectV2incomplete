package app.calcounter.com.projectversion1;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskViewer extends AppCompatActivity {

    //Recycler
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    AlertDialog.Builder builder;
    //
    private List<ListItem> listItems;
    private int counter = 0;
    private String m_TextTask = "";
    private String m_TextPriority = "";


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
            ListItem item = new ListItem("Task " + (counter + 1), "Priority", counter%5);

            listItems.add(item);
        }

        setAdapterValues();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                builder = new AlertDialog.Builder(TaskViewer.this);
                builder.setTitle("Enter Task Name & priority");
//
//                final EditText inputTask = new EditText(TaskViewer.this);
//                final EditText inputPriority = new EditText(TaskViewer.this);
//
//                inputTask.setInputType(InputType.TYPE_CLASS_TEXT);
//                inputPriority.setInputType(InputType.TYPE_CLASS_NUMBER);
//                builder.setView(inputTask);
//                builder.setView(inputPriority);

                Context context = TaskViewer.this;
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

// Add a TextView here for the "Title" label, as noted in the comments
                final EditText inputTask = new EditText(context);
                inputTask.setHint("Task Name");
                layout.addView(inputTask); // Notice this is an add method

// Add another TextView here for the "Description" label
                final EditText inputPriority = new EditText(context);
                inputPriority.setHint("Priority 1-5");
                layout.addView(inputPriority); // Another add method

                builder.setView(layout); // Again this is a set method, not add

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
                                m_TextTask = m_TextTask.substring(0,15);
                            }

                            counter++;
                            ListItem item = new ListItem(m_TextTask, "Priority ", temp);
                            listItems.add(item);
                            setAdapterValues();
                            prioritize();
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

    void setAdapterValues ()
    {
        adapter = new TaskAdapter(this,listItems); // object not the class
        recyclerView.setAdapter(adapter);
    }

    void prioritize()
    {

        listItems.sort(new Comparator<ListItem>() {
            @Override
            public int compare(ListItem t1, ListItem t2) {

                if(t1.getPriority() == t2.getPriority())
                {
                    return 0;
                }

                if(t1.getPriority() > t2.getPriority())
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
        });

        setAdapterValues();

    }




//    private static void order(List<ListItem> listItems) {
//
//        Collections.sort(listItems, new Comparator() {
//
//            public int compare(Object o1, Object o2) {
//
//                String x1 = ((Person) o1).getName();
//                String x2 = ((Person) o2).getName();
//                int sComp = x1.compareTo(x2);
//
//                if (sComp != 0) {
//                    return sComp;
//                }
//
//                Integer x1 = ((Person) o1).getAge();
//                Integer x2 = ((Person) o2).getAge();
//                return x1.compareTo(x2);
//            }});
//    }

}
