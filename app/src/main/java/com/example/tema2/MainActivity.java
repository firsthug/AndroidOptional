package com.example.tema2;

import UserDB.OnUserRepositoryActionListener;
import UserDB.User;
import UserDB.UserRepository;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements OnUserRepositoryActionListener,FragmentCommunication {


    private RecyclerView rvList;
    private ArrayList<String> mArray;
    private FragmentManager myFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();

        FragmentOne fragment = new FragmentOne();
        fragmentTransaction.add(R.id.fragments_container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof FragmentOne) {
            FragmentOne fragmentOne = (FragmentOne) fragment;
            fragmentOne.setFragmentCommunication(this);
        }

    }

    @Override
    public void actionSuccess() {

    }

    @Override
    public void actionFailed() {

    }

    @Override
    public MainActivity getActivityOfFrag() {
        return this;
    }
}
