package app.calcounter.com.projectversion1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private Context context;
    private List<ListItem> listItems;

    public TaskAdapter(Context context, List listItem)
    {
        this.context = context;
        this.listItems = listItem; // need to inflate xml file and make
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder holder, int position) {

        ListItem item = listItems.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription() + Integer.toString(item.getPriority()));
        //holder.rating.setText(item.getRating());
    }

    @Override
    public int getItemCount() { // need to return size of list items
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public TextView description;
        //public TextView rating;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            //rating = (TextView) itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            ListItem item = listItems.get(position);
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name",item.getName());
            intent.putExtra("description",item.getDescription());
            //intent.putExtra("rating", item.getRating());

            context.startActivity(intent); // this is not an activity need to use a context

            Toast.makeText(context, item.getName(), Toast.LENGTH_LONG).show();
        }
    }
}
