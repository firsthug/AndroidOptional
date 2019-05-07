package com.example.proiectandroid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proiectandroid.Activities.EditPage;
import com.example.proiectandroid.Activities.NewArticlePage;
import com.example.proiectandroid.Activities.ViewPage;
import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.Database.ArticleRepository;
import com.example.proiectandroid.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewEditFragment extends Fragment implements RVItemClickListener {

    private RecyclerView recyclerView;
    private ArticleAdapter myadapter;
   private FragmentCommunication fragmentCommunication;
    private ArrayList<Article> items = new ArrayList<>();

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
    public void setArticleList(ArrayList<Article> articleList) {
        this.items = articleList;
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


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        myadapter = new ArticleAdapter(this.getContext(),items,null,this,false);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(myadapter);

    }

    public void updateData()
    {
        myadapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemClickEdit(int position, boolean isDelete, int articleID) {
        if (isDelete == true)
        {

            ArticleRepository articleRepository = new ArticleRepository(this.getContext());
            Article article = articleRepository.getArticleById(articleID,(ViewPage)fragmentCommunication.getActivityOfFrag());
            articleRepository.removeTask(article,(ViewPage)fragmentCommunication.getActivityOfFrag());
            items.remove(position);
            myadapter.notifyItemRemoved(position);
            myadapter.notifyDataSetChanged();
        }
        else
        {
            Intent intent = new Intent(fragmentCommunication.getActivityOfFrag(), EditPage.class);
            intent.putExtra("ArticleId",articleID);
            startActivity(intent);
            myadapter.notifyDataSetChanged();
        }
    }
}
