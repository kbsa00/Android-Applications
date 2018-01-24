package com.example.eier.grocerylist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eier.grocerylist.Data.DatabaseHandler;
import com.example.eier.grocerylist.Model.Grocery;
import com.example.eier.grocerylist.R;
import com.example.eier.grocerylist.UI.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Grocery> groceryList;
    private List<Grocery> listItems;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private EditText groceryItem;
    private EditText quantity;
    private Button saveButton;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder = new AlertDialog.Builder(ListActivity.this);
                View view = getLayoutInflater().inflate(R.layout.popup, null);
                groceryItem = (EditText) v.findViewById(R.id.groceryItem);
                quantity = (EditText) v.findViewById(R.id.groceryQt);
                saveButton = (Button) v.findViewById(R.id.saveButton);

                dialogBuilder.setView(view);
                dialog = dialogBuilder.create();
                dialog.show();

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!groceryItem.getText().toString().isEmpty() && !quantity.getText().toString().isEmpty()) {
                            saveGroceryToDB(v);
                        }

                    }
                });

            }
        });

        db = new DatabaseHandler(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groceryList = new ArrayList<>();
        listItems = new ArrayList<>();

        groceryList = db.getAllGroceries();

        for (Grocery c : groceryList){
            Grocery grocery = new Grocery();
            grocery.setName(c.getName());
            grocery.setQuantity("Qty: " + c.getQuantity());
            grocery.setId(c.getId());
            grocery.setDateItemAdded("Added on: " + c.getDateItemAdded());

            listItems.add(grocery);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(this, listItems);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();


    }

    private void saveGroceryToDB(View v){
        Grocery grocery = new Grocery();
        String newGrocery = groceryItem.getText().toString();
        String newGroceryQuantity = quantity.getText().toString();

        grocery.setName(newGrocery);
        grocery.setQuantity(newGroceryQuantity);

        //Save to DB
        db.addGrocery(grocery);

        Snackbar.make(v, "Item Saved!", Snackbar.LENGTH_LONG).show();

        // Log.d("Item Added ID:", String.valueOf(db.getGorceriesCount()));

        dialog.dismiss();

    }

}
