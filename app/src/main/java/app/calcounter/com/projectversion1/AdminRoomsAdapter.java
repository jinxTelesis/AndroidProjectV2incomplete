package app.calcounter.com.projectversion1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import app.calcounter.com.projectversion1.Model.RoomListItem;


public class AdminRoomsAdapter extends RecyclerView.Adapter<AdminRoomsAdapter.ViewHolder>{

    // justins
    // justins
    // justins


    private Context context;
    private List<RoomListItem> listItems;

    public AdminRoomsAdapter(Context context, List listItem)
    {
        this.context = context;
        this.listItems = listItem; // need to inflate xml file and make
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminRoomsAdapter.ViewHolder holder, int position) {

        RoomListItem item = listItems.get(position); // index
        holder.name.setText(item.getName()); // name
    }

    @Override
    public int getItemCount() { // need to return size of list items
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public TextView description;



        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this); // listener
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Delete " + listItems.get(getAdapterPosition()).getName()).setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            listItems.remove(getAdapterPosition()); // removes item
                            notifyDataSetChanged();
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


    }
}

