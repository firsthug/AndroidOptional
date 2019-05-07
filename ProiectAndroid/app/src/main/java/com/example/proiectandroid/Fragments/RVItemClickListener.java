package com.example.proiectandroid.Fragments;

public interface RVItemClickListener {

    void onItemClick(int position);

    void onItemClickEdit(int position,boolean isDelete, int articleID);
}
