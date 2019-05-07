package com.example.proiectandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.proiectandroid.Activities.SearchPage;
import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.Database.ArticleRepository;
import com.example.proiectandroid.Database.UserRepository;
import com.example.proiectandroid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchBarFragment extends Fragment {

    private EditText ed1;
    private Button sButton;
    private ArticleRepository mArticleRepository;
    private UserRepository mUserRepository;
    FragmentCommunication fragmentCommunication;
    private ArrayList<Article> articleList  = new ArrayList<>();
    private ArrayList<String> cityList  = new ArrayList<>();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void setFragmentCommunication(FragmentCommunication fragmentCommunication) {
        this.fragmentCommunication = fragmentCommunication;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_bar, container, false);
        mArticleRepository = new ArticleRepository(this.getContext());
        mUserRepository = new UserRepository(this.getContext());

        ed1 = view.findViewById(R.id.search_text);
        sButton= view.findViewById(R.id.button_search);

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sTitle ="%" + ed1.getText().toString()+ "%";
                articleList.clear();
                cityList.clear();
                articleList.addAll(mArticleRepository.getArticlesByTitle(sTitle,(SearchPage)fragmentCommunication.getActivityOfFrag()));

                for(Article art : articleList)
                {
                    String city = mUserRepository.displayCityTask(art.getUserid(),(SearchPage)fragmentCommunication.getActivityOfFrag());
                    cityList.add(city);
                }

                fragmentCommunication.getSearchResults(articleList,cityList);


            }
        });

        return view;
    }
}
