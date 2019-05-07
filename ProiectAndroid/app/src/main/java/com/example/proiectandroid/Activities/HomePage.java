package com.example.proiectandroid.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.Fragments.FragmentCommunication;
import com.example.proiectandroid.Fragments.HomeFragment;
import com.example.proiectandroid.R;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements FragmentCommunication {

    private FragmentManager myFragmentManager = getSupportFragmentManager();
    private Button mybutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();

        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.home_container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            HomeFragment homeFrag = (HomeFragment) fragment;
            homeFrag.setFragmentCommunication(this);

        }

    }

    @Override
    public Activity getActivityOfFrag() {
        return this;
    }

    @Override
    public void getSearchResults(ArrayList<Article> articleList, ArrayList<String> cityList) {

    }




}
