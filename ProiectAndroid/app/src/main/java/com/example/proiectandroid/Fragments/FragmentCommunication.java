package com.example.proiectandroid.Fragments;

import android.app.Activity;

import com.example.proiectandroid.Database.Article;

import java.util.ArrayList;


public interface FragmentCommunication {

    public Activity getActivityOfFrag();

    public void getSearchResults(ArrayList<Article> articleList,ArrayList<String> cityList);

   // public void getArticlesOfUser(ArrayList<Article> articleList);

}
