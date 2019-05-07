package com.example.proiectandroid.Activities;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.Database.ArticleRepository;
import com.example.proiectandroid.Database.OnRepositoryActionListener;
import com.example.proiectandroid.Database.User;
import com.example.proiectandroid.Database.UserRepository;
import com.example.proiectandroid.Fragments.FragmentCommunication;
import com.example.proiectandroid.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FullViewPage extends AppCompatActivity implements OnRepositoryActionListener {

    private TextView tv1,tv2,tv3,tv4,tv5;
    private ImageView img;
    private int articleID,userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        articleID = getIntent().getIntExtra("ArticleId",0);
        userID= getIntent().getIntExtra("ArticleUserId",0);

        setContentView(R.layout.article_view_full);

        tv1= findViewById(R.id.tv_title);
        tv2= findViewById(R.id.tv_author);
        tv3= findViewById(R.id.tv_price);
        tv4= findViewById(R.id.tv_telephone);
        tv5= findViewById(R.id.tv_desc);
        img = findViewById(R.id.article_image_view);

        ArticleRepository articleRepository = new ArticleRepository(this);
        UserRepository userRepository = new UserRepository(this);
        User user = userRepository.getUserById(userID,this);
        Article article = articleRepository.getArticleById(articleID,this);

        tv1.setText(article.getTitle());
        tv2.setText(user.getFirstName() + " " + user.getLastName());
        tv3.setText(String.valueOf(article.getPrice()));
        tv4.setText(user.getTelephone());
        tv5.setText(article.getDescription());
        Bitmap bitmap = BitmapFactory.decodeByteArray(article.getImage(), 0, article.getImage().length);
        img.setImageBitmap(bitmap);
        /*Bitmap bmp = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888);
ByteBuffer buffer = ByteBuffer.wrap(bitmapdata);
bmp.copyPixelsFromBuffer(buffer);*/

    }

    @Override
    public void actionSuccess() {

    }

    @Override
    public void actionFailed() {

    }
}
