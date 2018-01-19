package com.example.eier.petsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView catview;
    private ImageView dogview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catview = (ImageView) findViewById(R.id.catId);
        dogview = (ImageView) findViewById(R.id.dogid);

        catview.setOnClickListener(this);
        dogview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.catId:
                //Toast.makeText(MainActivity.this, "Cat is touched ", Toast.LENGTH_LONG).show();
                Intent catIntent = new Intent(MainActivity.this, BioActivity.class);
                catIntent.putExtra("name", "Jarvis");
                catIntent.putExtra("bio", "Great cat, Loves people and mjaus a lot ");
                startActivity(catIntent);

            break;

            case R.id.dogid:
                //Toast.makeText(MainActivity.this, "Dog is touched", Toast.LENGTH_LONG).show();

                Intent dogIntent = new Intent(MainActivity.this, BioActivity.class);
                dogIntent.putExtra("name", "Dufus");
                dogIntent.putExtra("bio", "Great dog, Loves people and barks a lot ");
                startActivity(dogIntent);
            break;
        }


    }
}
