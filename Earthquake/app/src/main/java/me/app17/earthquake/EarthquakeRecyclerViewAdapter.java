package me.app17.earthquake;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EarthquakeRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

    private final List<Earthquake> earthquakeList;

    public EarthquakeRecyclerViewAdapter(List<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    TextView detailView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        detailView = itemView.findViewById(R.id.list_item_earthquake_details);

    }

    @Override
    public String toString() {
        return "ViewHolder{" +
                "detailView=" + detailView.getText() +
                '}';
    }
}
