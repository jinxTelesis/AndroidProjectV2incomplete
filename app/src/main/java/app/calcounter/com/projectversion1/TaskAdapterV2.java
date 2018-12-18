package app.calcounter.com.projectversion1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;



import java.util.List;


import app.calcounter.com.projectversion1.Model.LocationListItem;


public class TaskAdapterV2 extends RecyclerView.Adapter<TaskAdapterV2.ViewHolder> {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;
    private List<LocationListItem> listItems;

    public TaskAdapterV2(Context context, List listItem) {
        this.context = context;
        this.listItems = listItem; // need to inflate xml file and make
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override // will this inflate that same xml
    public void onBindViewHolder(TaskAdapterV2.ViewHolder holder, int position) {

        LocationListItem item = listItems.get(position); // list position
        holder.name.setText(item.getName()); // first text item
        holder.description.setText(item.getDescription()); // second text item


    }

    @Override
    public int getItemCount() { // need to return size of list items
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this); // the listener
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            LocationListItem item = listItems.get(position);


            // add a add task


            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Modify " + listItems.get(getAdapterPosition()).getName())
                    .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(context, NewLocationActivity.class);
                            intent.putExtra("isEdit", true);
                            intent.putExtra("address", listItems.get(getAdapterPosition()).getName());
                            intent.putExtra("address2", listItems.get(getAdapterPosition()).getDescription());
                            context.startActivity(intent);
                            // does not add it yet, on the close of this? might need to modify
                        }
                    })
                    .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Delete " + listItems.get(getAdapterPosition()).getName()).setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //NOTE This is where an item gets deleted from the loations list
                                            listItems.remove(getAdapterPosition());
                                            // has no listener
                                            db.collection("locations").document("loc" + getAdapterPosition()).delete();


                                            db.collection("tasks").whereEqualTo("rootloc", "loc" + getAdapterPosition()) // will iterate over the collection
                                                    .get() // this listener should be safe for activity change
                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                            if (task.isSuccessful()) {
                                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                                    Log.e("document.getId() is here ", document.getId().toString());
                                                                    db.collection("tasks").document(document.getId()).delete(); // hope this works

                                                                }

                                                            } else {
                                                                Log.e("Firebase blows", "error", task.getException());
                                                            }

                                                            //setAdapterValues();
                                                        }
                                                    });
                                            // needs to clean up all tasks also

                                            notifyDataSetChanged();// this is for the local recycler keep
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    }).setNeutralButton("View data", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(context, TaskViewer.class);
                    intent.putExtra("positonFromLocationAdapter", position); // not sure if this works correctly
                    //intent.putExtra()
                    // need to pass it the document id not the position number
                    Log.e("admin Location adapter postion", Integer.toString(position));
                    intent.putExtra("isView", true);
                    //intent.putExtra("address", listItems.get(getAdapterPosition()).getName());
                    //intent.putExtra("address2",listItems.get(getAdapterPosition()).getDescription());
                    context.startActivity(intent);
                    // does not add it yet, on the


                }
            });
            AlertDialog alert = builder.create();
            alert.show();


            Toast.makeText(context, item.getName(), Toast.LENGTH_LONG).show();
        }
    }
}