package com.example.eier.calcounter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.Food;

public class FoodItemDetailsActivity extends AppCompatActivity {
    private TextView foodname, calories, dateTaken;
    private Button shareButton;
    private int foodId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_details);

        foodname = (TextView) findViewById(R.id.detsFoodName);
        calories = (TextView) findViewById(R.id.detCaloriesTitle);
        dateTaken = (TextView) findViewById(R.id.detsDateText);
        shareButton = (Button) findViewById(R.id.detsShareButton);

        Food food = (Food) getIntent().getSerializableExtra("userObj");

        foodname.setText(food.getFoodName());
        calories.setText(String.valueOf(food.getCalories()));
        dateTaken.setText(food.getRecordDate());

        foodId = food.getFoodId();

        calories.setTextSize(34.9f);
        calories.setTextColor(Color.RED);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCals();
            }
        });

    }

    public void shareCals(){

        StringBuilder dataString = new StringBuilder();
        String name = foodname.getText().toString();
        String cals = calories.getText().toString();
        String date = dateTaken.getText().toString();

        dataString.append(" Food: " + name + "\n" );
        dataString.append(" Calories: " + cals + "\n" );
        dataString.append(" Eaten on: " + date + "\n" );

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT, "My Caloric Intake");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_TEXT, dataString.toString());

        try{
            startActivity(Intent.createChooser(i, "Send mail..."));

        }catch (ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(), "Please install email client before sending",
            Toast.LENGTH_LONG).show();
        }
    }
}
