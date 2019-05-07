package com.example.proiectandroid.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private Context context;
    private ArrayList<Article> articleItems;
    private ArrayList<String> cityItems;
    private RVItemClickListener mrvItemClickListener;
    private boolean isSearch;

  public ArticleAdapter(Context context, ArrayList<Article> items,ArrayList<String> cityItems, RVItemClickListener mrvItemClickListener, boolean isSearch ) {
        this.context = context;
        this.isSearch= isSearch;
        this.articleItems = items;
        if(isSearch == true)
        {this.cityItems=cityItems;}

        this.mrvItemClickListener=mrvItemClickListener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View hview;
        if (isSearch == true)
        {hview = inflater.inflate(R.layout.search_item, parent, false);}
        else
        {hview = inflater.inflate(R.layout.edit_item, parent, false);}

        //RecyclerView.ViewHolder viewHolder = new HomeAdapter.HomeViewHolder(hview);

       // return viewHolder;
        return new ArticleViewHolder(hview, isSearch);

    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleItems.get(position);
        holder.position=position;
        holder.aID=article.getAid();
        holder.title.setText(article.getTitle());
        holder.price.setText(String.valueOf(article.getPrice()));
        if( isSearch == true)
        {holder.city.setText(cityItems.get(position));}
    }



    @Override
    public int getItemCount() {
        return articleItems.size();
    }



    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView price;
        public TextView city;
        public Button editButton;
        public Button deleteButton;
        public int position;
        public int aID;

        public ArticleViewHolder(View itemView, boolean isSearch) {
            super(itemView);
            if (isSearch == true) {
                title = (TextView) itemView.findViewById(R.id.sitem_title);
                price = (TextView) itemView.findViewById(R.id.sitem_price);
                city = (TextView) itemView.findViewById(R.id.sitem_city);

                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mrvItemClickListener.onItemClick(position);
                    }
                });

            }
            else
            {
                title = (TextView) itemView.findViewById(R.id.eitem_title);
                price = (TextView) itemView.findViewById(R.id.eitem_price);
                editButton = itemView.findViewById(R.id.edit_button);
                deleteButton = itemView.findViewById(R.id.delete_button);


                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mrvItemClickListener.onItemClickEdit(position,false,aID);
                    }
                });

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mrvItemClickListener.onItemClickEdit(position,true,aID);
                    }
                });

            }

        }


    }
}
