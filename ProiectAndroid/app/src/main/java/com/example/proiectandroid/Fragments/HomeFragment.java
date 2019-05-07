package com.example.proiectandroid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proiectandroid.Activities.EditPage;
import com.example.proiectandroid.Activities.HomePage;
import com.example.proiectandroid.Activities.MainActivity;
import com.example.proiectandroid.Activities.NewArticlePage;
import com.example.proiectandroid.Activities.SearchPage;
import com.example.proiectandroid.Activities.ViewPage;
import com.example.proiectandroid.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements RVItemClickListener{


    private RecyclerView recyclerView;
    private HomeAdapter mAdapter;
    FragmentCommunication fragmentCommunication;
    private ArrayList<String> items = new ArrayList<>();

    public void setFragmentCommunication(FragmentCommunication fragmentCommunication) {
        this.fragmentCommunication = fragmentCommunication;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentCommunication) {
            fragmentCommunication = (FragmentCommunication) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.rv_frag, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setItems();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        mAdapter = new HomeAdapter(this.getContext(), items,this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        //        mAdapter.notifyDataSetChanged();
    }

    public void setItems()
    {
        String text = "Search-Cauta produsele care te intereseaza";
        items.add(text);

        text = "Add-Adauga anunturile tale";
        items.add(text);

        text = "Edit-Modifica sau sterge anunturile tale";
        items.add(text);

    }

    @Override
    public void onItemClick(int position) {
            switch (position)
            {
                case 0:{
                    Intent intent = new Intent(this.getActivity(), SearchPage.class);
                    startActivity(intent);
                    break;

                }
                case 1:{
                    Intent intent = new Intent(this.getActivity(), NewArticlePage.class);
                    startActivity(intent);
                    break;
                }
                case 2:{
                    Intent intent = new Intent(this.getActivity(), ViewPage.class);
                    startActivity(intent);
                    break;
                }
            }

    }

    @Override
    public void onItemClickEdit(int position, boolean isDelete, int articleID) {

    }
}
