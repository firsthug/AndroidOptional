package com.example.proiectandroid.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.Database.ArticleRepository;
import com.example.proiectandroid.Database.OnRepositoryActionListener;
import com.example.proiectandroid.Database.User;
import com.example.proiectandroid.Database.UserRepository;
import com.example.proiectandroid.Fragments.FragmentCommunication;
import com.example.proiectandroid.Fragments.SearchBarFragment;
import com.example.proiectandroid.Fragments.ViewEditFragment;
import com.example.proiectandroid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ViewPage extends AppCompatActivity implements FragmentCommunication, OnRepositoryActionListener {

    private FragmentManager myFragmentManager = getSupportFragmentManager();
    private List<Article> articleListRes  = new ArrayList<>();
    private  ViewEditFragment fragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_page);
        fragment = new ViewEditFragment();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String tel = pref.getString("userTel", "");

        UserRepository myrepo = new UserRepository(this);
        User myUser = myrepo.getUserByTelTask(tel,this);
        ArticleRepository articleRepository = new ArticleRepository(this);
        articleListRes = articleRepository.getArticlesByAuthor(myUser.getUid(),this);
        fragment.setArticleList((ArrayList<Article>) articleListRes);

        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.view_container, fragment);
        fragmentTransaction.commit();
       // myFragmentManager.executePendingTransactions();

    }



    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ViewEditFragment) {
            ViewEditFragment SBFrag = (ViewEditFragment) fragment;
            SBFrag.setFragmentCommunication(this);

        }

    }



    @Override
    public Activity getActivityOfFrag() {
        return this;
    }

    @Override
    public void getSearchResults(ArrayList<Article> articleList, ArrayList<String> cityList) {

    }


    @Override
    public void actionSuccess() {

    }

    @Override
    public void actionFailed() {

    }
}
