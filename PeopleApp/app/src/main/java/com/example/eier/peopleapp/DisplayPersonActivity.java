package com.example.eier.peopleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Data.CustomListViewAdapter;
import Data.DatabaseHandler;
import Model.Person;

public class DisplayPersonActivity extends AppCompatActivity {
    private CustomListViewAdapter adapter;
    private ListView listView;
    private DatabaseHandler dba;
    private Person person;
    private ArrayList<Person> persList = new ArrayList<>();

    private TextView numberOfPeopleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_person);
        listView = (ListView) findViewById(R.id.list);
        numberOfPeopleText = (TextView) findViewById(R.id.numberOfPpltxt);

        refresh();
    }

    private void refresh() {
        persList.clear();
        dba = new DatabaseHandler(getApplicationContext());

        ArrayList<Person> personFromDatabase = dba.getListOfPerson();
        numberOfPeopleText.setText(String.valueOf(personFromDatabase.size()));


        for (int i = 0; i < personFromDatabase.size(); i++){
            String name = personFromDatabase.get(i).getPersonName();
            int age = personFromDatabase.get(i).getPersonAge();
            String adress = personFromDatabase.get(i).getPersAdress();

            person = new Person();
            person.setPersAdress(adress);
            person.setPersonName(name);
            person.setPersonAge(age);
            persList.add(person);
        }

        dba.close();

        adapter = new CustomListViewAdapter(this, R.layout.list_row, persList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
