package com.example.eier.recyclerviewproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView descText;
    private TextView ratingText;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        nameText = (TextView) findViewById(R.id.dNameId);
        descText = (TextView) findViewById(R.id.DescID);
        ratingText = (TextView) findViewById(R.id.ratingId);

        extras = getIntent().getExtras();

        if (extras != null){

            nameText.setText(extras.getString("name"));
            descText.setText(extras.getString("Description"));
            ratingText.setText(extras.getString("rating"));

        }
    }
}
