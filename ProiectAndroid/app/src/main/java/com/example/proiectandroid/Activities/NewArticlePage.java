package com.example.proiectandroid.Activities;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.example.proiectandroid.Database.Article;
import com.example.proiectandroid.Database.ArticleRepository;
import com.example.proiectandroid.Database.OnRepositoryActionListener;
import com.example.proiectandroid.Database.User;
import com.example.proiectandroid.Database.UserRepository;
import com.example.proiectandroid.Fragments.FragmentCommunication;
import com.example.proiectandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewArticlePage extends AppCompatActivity implements FragmentCommunication, OnRepositoryActionListener
{

    private EditText ed1,ed2,ed3;

    private ImageView avatar;
    private Button button1;
    private String titlu,desc,imageURL;
    private float price = 1;
    private int userId;

    private ArticleRepository myRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_edit);

        myRepository = new ArticleRepository(this);
        avatar = findViewById(R.id.article_image_edit);
        setImageView(this);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String tel = pref.getString("userTel", "");

        UserRepository myrepo = new UserRepository(this);
        User myUser = myrepo.getUserByTelTask(tel,this);
        userId=myUser.getUid();

        ed1= findViewById(R.id.title_input);
        ed2= findViewById(R.id.desc_input);
        ed3= findViewById(R.id.price_input);

        button1= findViewById(R.id.button_new); // edit / new laypout

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                titlu=ed1.getText().toString();
                desc = ed2.getText().toString();

                Bitmap bitmap = ((BitmapDrawable)avatar.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                if (!titlu.isEmpty() && !ed3.getText().toString().isEmpty() && !desc.isEmpty())
                {  price = Float.valueOf(ed3.getText().toString());
                    Article myArticle = new Article(userId,titlu,desc,price,byteArray);
                myRepository.insertTask(myArticle,NewArticlePage.this);
                NewArticlePage.this.finish();
                }

                else
                {
                    Toast.makeText(NewArticlePage.this,"Toate campurile trebuie completate!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    private void setImageView(NewArticlePage newArticlePage) {

        String url = "https://loremflickr.com/json/120/120";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            imageURL =  response.getString("file");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");

                    }
                });


        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(jsonObjectRequest);


        /*ImageLoader imageLoader=MySingleton.getInstance(this.getApplicationContext()).getImageLoader();
        avatar.setImageUrl(imageFile[0],imageLoader);*/


        String imageurl = "http://thecatsbreeds.net/gallery/train-your-cat/thumbs/thumbs_train_your_cat.jpg";
        //http://www.oasisanimalclinic.com/wp-content/uploads/2014/07/outdoor-cats-180x180.jpg
        //"https:\\/\\/loremflickr.com\\/cache\\/resized\\/1908_45320279051_68a105315b_320_240_g.jpg";
        ImageRequest imageRequest = new ImageRequest(imageurl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                if (response != null) {
                    avatar.setImageBitmap(response);
                }

            }
        }, 200, 200, null , Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley","Error");
            }
        });

        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(imageRequest);

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
