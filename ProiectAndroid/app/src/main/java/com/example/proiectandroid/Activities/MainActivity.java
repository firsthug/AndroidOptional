package com.example.proiectandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proiectandroid.Database.OnRepositoryActionListener;
import com.example.proiectandroid.Database.User;
import com.example.proiectandroid.Database.UserRepository;
import com.example.proiectandroid.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements OnRepositoryActionListener {

    private UserRepository myRepository;
    private EditText ed1;
    private EditText ed2;
    private  EditText ed3;
    private  EditText ed4;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();

        ed1=findViewById(R.id.nume_fp);
        ed2=findViewById(R.id.prenume_fp);
        ed3=findViewById(R.id.oras_fp);
        ed4=findViewById(R.id.tel_fp);

        myRepository = new UserRepository(this);

        Button registerButton = findViewById(R.id.enter_fp);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nume= ed1.getText().toString();
                String prenume = ed2.getText().toString();
                String oras = ed3.getText().toString();
                String telefon = ed4.getText().toString();

                if(!nume.isEmpty() && !prenume.isEmpty() && !oras.isEmpty() && !telefon.isEmpty())
                {
                    User user = new User(nume,prenume,oras,telefon);
                    editor.putString("userTel", telefon);
                    editor.commit();

                    myRepository.insertTask(user, MainActivity.this);
                    Intent intent = new Intent(MainActivity.this,HomePage.class);
                    finish();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Toate campurile trebuie completate!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
    if(!previouslyStarted) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
        edit.commit();
        showHelp();
    }

      SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
    if(pref.getBoolean("activity_executed", false)){
        Intent intent = new Intent(this, TutorialOne.class);
        startActivity(intent);
        finish();
    } else {
        Editor ed = pref.edit();
        ed.putBoolean("activity_executed", true);
        ed.commit();
    }
    */
    }


    @Override
    public void actionSuccess() {

    }

    @Override
    public void actionFailed() {

    }
}
