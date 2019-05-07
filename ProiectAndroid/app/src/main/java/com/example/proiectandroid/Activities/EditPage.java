package com.example.proiectandroid.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.Database.ArticleRepository;
import com.example.proiectandroid.Database.OnRepositoryActionListener;
import com.example.proiectandroid.Fragments.FragmentCommunication;
import com.example.proiectandroid.Fragments.ViewEditFragment;
import com.example.proiectandroid.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class EditPage extends AppCompatActivity implements FragmentCommunication , OnRepositoryActionListener
{

    private EditText ed1,ed2,ed3;
    private int articleID;
    private Button button1;
    private ArticleRepository articleRepository;
    private Article article;
    //private FragmentManager myFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleID = getIntent().getIntExtra("ArticleId",0);
        setContentView(R.layout.article_edit);
        articleRepository = new ArticleRepository(this);
        article= articleRepository.getArticleById(articleID,this);

        ed1= findViewById(R.id.title_input);
        ed2= findViewById(R.id.desc_input);
        ed3= findViewById(R.id.price_input);

        button1= findViewById(R.id.button_new);
        ed1.setText(article.getTitle());
        ed2.setText(article.getDescription());
        ed3.setText(String.valueOf(article.getPrice()));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titlu,desc;
                Float price;
                titlu=ed1.getText().toString();
                desc = ed2.getText().toString();
                price = Float.valueOf(ed3.getText().toString());
                if (!titlu.isEmpty() && !ed3.getText().toString().isEmpty() && !desc.isEmpty())
                {article.setTitle(titlu);
                article.setDescription(desc);
                article.setPrice(price);
                articleRepository.updateArticle(article, EditPage.this);
                EditPage.this.finish();}

                else
                {
                    Toast.makeText(EditPage.this,"Toate campurile trebuie completate!", Toast.LENGTH_SHORT).show();
                }

            }
        });

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
