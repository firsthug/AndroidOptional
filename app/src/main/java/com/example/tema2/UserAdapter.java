package com.example.tema2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import UserDB.User;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> usersList;
    private Context context;

    public UserAdapter(List<User> usersList, Context context)
    {this.usersList=usersList;
    this.context=context;}


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.user_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = usersList.get(position);

        holder.lName.setText(user.getLastName());
        holder.fName.setText(user.getFirstName());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lName, fName;

        public MyViewHolder(View view) {
            super(view);
            fName = (TextView) view.findViewById(R.id.list_fname);
            lName = (TextView) view.findViewById(R.id.list_lname);

        }
    }
}
