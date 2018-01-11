package com.example.eier.dbintro;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import Data.DatabaseHandler;
import Model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);


        //Insert contacts
        Log.d("Insert: ", "Inserting..");
        db.addContact(new Contact("Paul", "43939211"));
        db.addContact(new Contact("John", "53134321"));
        db.addContact(new Contact("Rose", "666666"));
        db.addContact(new Contact("Bella", "77777"));

        //Read them back
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contactList = db.getAllContacts();

        //Get a contact
        Contact oneContact = db.getContact(1);

        //Update contact
        int newContact = db.updateContact(oneContact);
        Log.d("One contact: ", "Updated row" +  oneContact.getName() + String.valueOf(newContact) + " Phone number: " + oneContact.getPhoneNumber());


        for (Contact c : contactList){
            String log = "ID" + c.getId() + ", Name: " + c.getName() + ", Phone: " + c.getPhoneNumber();
            Log.d("Name: ", log);
        }
    }
}
