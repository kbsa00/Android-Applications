package com.example.eier.calcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Food;

public class MainActivity extends AppCompatActivity {
    private EditText foodname, foodcals;
    private Button submitButton;
    private DatabaseHandler dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dba = new DatabaseHandler(MainActivity.this);

        foodname = (EditText) findViewById(R.id.foodEditText);
        foodcals = (EditText) findViewById(R.id.caloriesEditText);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveDataToDB();
            }
        });
    }


    private void saveDataToDB(){
        Food food = new Food();
        String name = foodname.getText().toString().trim();
        String calsString = foodcals.getText().toString().trim();

        int cals = Integer.parseInt(calsString);

        if (name.equals("") || calsString.equals("")){
            Toast.makeText(getApplicationContext(), "No empty fields allowed", Toast.LENGTH_LONG).show();
        }else {
            food.setFoodName(name);
            food.setCalories(cals);
            dba.addFood(food);
            dba.close();

            foodname.setText("");
            foodcals.setText("");

            startActivity(new Intent(MainActivity.this, DisplayFoodsActivity.class));
        }



    }
}
