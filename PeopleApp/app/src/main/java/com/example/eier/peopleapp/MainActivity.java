package com.example.eier.peopleapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import Data.DatabaseHandler;
import Model.Person;

public class MainActivity extends AppCompatActivity {
    private EditText nameInput, ageInput, adressInput;
    private Button sendButton;
    private DatabaseHandler dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba = new DatabaseHandler(this);
        nameInput = (EditText) findViewById(R.id.inputName);
        ageInput = (EditText) findViewById(R.id.Inputage);
        sendButton = (Button) findViewById(R.id.sendbtn);
        adressInput = (EditText) findViewById(R.id.inputAdress);
    }

    public void sendInformation(View view){
        Person person = new Person();
        ArrayList<Person> list = new ArrayList<>();

        String name = nameInput.getText().toString().trim();
        String age = ageInput.getText().toString().trim();
        String adress = adressInput.getText().toString().trim();


        int pAge = Integer.parseInt(age);

        if (name.equals("") || age.equals("") || adress.equals("")){

            Toast.makeText(this, "No empty fields please", Toast.LENGTH_LONG).show();
        }else {

            person.setPersonName(name);
            person.setPersonAge(pAge);
            person.setPersAdress(adress);
            dba.addPerson(person);
            dba.close();
            nameInput.setText("");
            ageInput.setText("");
            adressInput.setText("");

            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), DisplayPersonActivity.class);
                    startActivity(intent);

                }
            },1000);
        }

    }
}
