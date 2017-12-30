package Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eier.recyclerviewproject.DetailsActivity;
import com.example.eier.recyclerviewproject.R;

import java.util.List;

import Model.ListItem;

/**
 * Created by Eier on 12/28/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> listItems;


    public MyAdapter(Context context, List listItem){
        this.context = context;
        this.listItems = listItem;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        ListItem item = listItems.get(position);

        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.rating.setText(item.getRating());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private TextView description;
        private TextView rating;


        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            rating = (TextView) itemView.findViewById(R.id.rating);


        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            ListItem item = listItems.get(position);

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", item.getName());
            intent.putExtra("Description", item.getDescription());
            intent.putExtra("Rating", item.getRating());

            context.startActivity(intent);



        }
    }
}
