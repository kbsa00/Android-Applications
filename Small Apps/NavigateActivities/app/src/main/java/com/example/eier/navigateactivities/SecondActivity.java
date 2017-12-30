package com.example.eier.navigateactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    private TextView showMessage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        showMessage = (TextView) findViewById(R.id.textView);
        button = findViewById(R.id.buttonid);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                returnIntent.putExtra("returnData", "From SecondActivity");
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });



        Bundle extras = getIntent().getExtras();

        //check if the intent is empty

        if (extras != null){

            //Retreving the Mainactivity's data and displaying it on the second activity
            String message = extras.getString("Message");
            int value = extras.getInt("Value");
            String msg = String.valueOf(value);
            showMessage.setText(msg);
        }

    }

}
