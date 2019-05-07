package com.example.proiectandroid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proiectandroid.Activities.EditPage;
import com.example.proiectandroid.Activities.FullViewPage;
import com.example.proiectandroid.Activities.SearchPage;
import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultFragment extends Fragment implements RVItemClickListener {

    FragmentCommunication fragmentCommunication;
    private ArrayList<Article> articleList  = new ArrayList<>();
    private ArrayList<String> cityList  = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArticleAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //if (context instanceof FragmentCommunication) {
            //fragmentCommunication = (FragmentCommunication) context; }

    }

    public void setFragmentCommunication(FragmentCommunication fragmentCommunication) {
        this.fragmentCommunication = fragmentCommunication;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rv_frag, container, false);
        return view;
    }

    public void setArticleList(ArrayList<Article> articleList) {
        this.articleList=articleList;

    }

    public void setCityList(ArrayList<String> cityList) {
        this.cityList=cityList;
    }

    public void updateDataRV()
    {
        //mAdapter.notifyDataSetChanged();
        mAdapter = new ArticleAdapter(this.getContext(), articleList,cityList, this,true);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mAdapter = new ArticleAdapter(this.getContext(), articleList,cityList, this,true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onItemClick(int position) {

        Article article = articleList.get(position);
        Intent intent = new Intent(fragmentCommunication.getActivityOfFrag(), FullViewPage.class);
        intent.putExtra("ArticleId",article.getAid());
        intent.putExtra("ArticleUserId",article.getUserid());
        startActivity(intent);

    }

    @Override
    public void onItemClickEdit(int position, boolean isDelete, int articleID) {

    }


}

