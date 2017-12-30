package com.example.eier.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder alertdialog;
    private Button showDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialog = (Button) findViewById(R.id.buttonID);

        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show the actual dialog (alert dialog)
                alertdialog = new AlertDialog.Builder(MainActivity.this);
                //set things up - SETUP TITLE
                alertdialog.setTitle(R.string.title);
                alertdialog.setIcon(android.R.drawable.star_big_on);
                

                //set  Message
                alertdialog.setMessage(getResources().getString(R.string.message));


                //set Cancelable
                alertdialog.setCancelable(false);

                //set positive button
                alertdialog.setPositiveButton(getResources().getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Exit our window activity
                                MainActivity.this.finish();

                            }
                        });

                //set Negative button

                alertdialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                //create at the actual dialog

                AlertDialog dialog = alertdialog.create();

                //show the dialog
                dialog.show();


            }
        });


    }
}
