package com.example.eier.petsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BioActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView petName;
    private TextView petBio;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);


        imageView  = (ImageView) findViewById(R.id.imageID);
        petBio = (TextView) findViewById(R.id.BioID);
        petName = (TextView) findViewById(R.id.nameID);


        extras = getIntent().getExtras();

        if (extras != null){
            String name = extras.getString("name");
            String bio = extras.getString("bio");
            setUp(name, bio);
        }


    }

    public void setUp(String name, String bio){

        if (name.equals("Dufus")){
            petBio.setText(bio);
            petName.setText(name);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ab));

        }
        else if (name.equals("Jarvis")){
            petBio.setText(bio);
            petName.setText(name);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ba));
        }

    }
}
