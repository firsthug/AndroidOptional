package com.example.proiectandroid.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proiectandroid.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private Context context;
    private ArrayList<String> items;
    private RVItemClickListener mrvItemClickListener;

  public HomeAdapter (Context context, ArrayList<String> items,RVItemClickListener mrvItemClickListener ) {
        this.context = context;
        this.items = items;
        this.mrvItemClickListener=mrvItemClickListener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View hview = inflater.inflate(R.layout.home_item, parent, false);

        //RecyclerView.ViewHolder viewHolder = new HomeAdapter.HomeViewHolder(hview);

       // return viewHolder;
        return new HomeViewHolder(hview);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

      String text= items.get(position);
       String[] res = text.split("-");
        holder.title.setText(res[0]);
        holder.desc.setText(res[1]);
        holder.position=position;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public class HomeViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public int position;

        public HomeViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.hitem_heading);
            desc = (TextView)itemView.findViewById(R.id.hitem_desc);


            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mrvItemClickListener.onItemClick(position);
                }
            });

        }


    }
}
