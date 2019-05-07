package com.example.proiectandroid.Activities;

import android.os.Bundle;

import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.Database.OnRepositoryActionListener;
import com.example.proiectandroid.Fragments.FragmentCommunication;
import com.example.proiectandroid.Fragments.SearchBarFragment;
import com.example.proiectandroid.Fragments.SearchResultFragment;
import com.example.proiectandroid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SearchPage extends AppCompatActivity implements FragmentCommunication, OnRepositoryActionListener {

    private FragmentManager myFragmentManager = getSupportFragmentManager();
    private List<Article> articleListRes  = new ArrayList<>();
    private SearchResultFragment fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragment2 = new SearchResultFragment();
        SearchBarFragment fragment1 = new SearchBarFragment();
        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.search_box, fragment1);
        fragmentTransaction.add(R.id.search_result, fragment2,"result_frag");
        fragmentTransaction.commit();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {

        if (fragment instanceof SearchBarFragment) {
            SearchBarFragment SBFrag = (SearchBarFragment) fragment;
            SBFrag.setFragmentCommunication(this);

        }

        if (fragment instanceof  SearchResultFragment) {
            SearchResultFragment SRFrag = ( SearchResultFragment) fragment;
            SRFrag.setFragmentCommunication(this);

        }

    }


    @Override
    public SearchPage getActivityOfFrag() {
        return this;
    }

    @Override
    public void getSearchResults(ArrayList<Article> articleList,ArrayList<String> cityList) {

        fragment2.setArticleList(articleList);
        fragment2.setCityList(cityList);
       fragment2.updateDataRV();
        /*
        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.search_result, fragment2,"result_frag");
        fragmentTransaction.commit();
        */



    }


    @Override
    public void actionSuccess() {

    }

    @Override
    public void actionFailed() {

    }
}
