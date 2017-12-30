package com.example.eier.navigateactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.showButtonID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This is how me create a connection between mainactivity and secondactivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                //Sending data from first activity to the second activity
                intent.putExtra("Message", "Hello from the first activity");
                intent.putExtra("SecondMessage", "Hello Again");
                intent.putExtra("Value", 1235);

                //Normally use startActivity if you just want to start the second activity
                //startActivity(intent);

                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("returnData");

                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        }

    }
}
