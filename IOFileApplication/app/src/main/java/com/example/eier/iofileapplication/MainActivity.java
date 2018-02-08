package com.example.eier.iofileapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText inputTxt;
    private Button saveButton;
    private Button loadButton;
    private TextView resultTxt;
    private String pathname = "hello_file";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initilize variables

        saveButton = (Button) findViewById(R.id.saveButton);
        loadButton = (Button) findViewById(R.id.loadButton);
        resultTxt = (TextView) findViewById(R.id.textView2);
        inputTxt = (EditText) findViewById(R.id.inputTxt);

    }


    public void saveMessage(View view){
        String message = inputTxt.getText().toString();


        try {
            FileOutputStream fileOutputStream = openFileOutput(pathname, MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            Toast.makeText(this, "Message Saved", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMessage(View view){
        try {

            String message;
            FileInputStream fileInputStream = openFileInput(pathname);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while ((message = bufferedReader.readLine()) != null){
                stringBuffer.append(message + "\n");
            }

            resultTxt.setText(stringBuffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
