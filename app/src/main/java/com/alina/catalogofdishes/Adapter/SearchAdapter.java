package com.alina.catalogofdishes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alina.catalogofdishes.Dishes;
import com.alina.catalogofdishes.R;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder{

    public TextView name,country,components;

    public SearchViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        country = (TextView)itemView.findViewById(R.id.county);
        components = (TextView)itemView.findViewById(R.id.components);

    }
}
public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Dishes> dishes;

    public SearchAdapter(Context context, List<Dishes> dishes) {
        this.context = context;
        this.dishes = dishes;
    }

    @Override
    public SearchViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( SearchViewHolder holder, int position) {
        holder.name.setText(dishes.get(position).getName());
        holder.country.setText(dishes.get(position).getCountry());
        holder.components.setText(dishes.get(position).getComponents());
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}
