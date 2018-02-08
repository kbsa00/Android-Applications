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


/**
 * This writes a file internally on the Phone!
 * NOTICE - If you want to save a file on a external storage, You will have
 * to put permission requests in the manifest.
 */
public class MainActivity extends AppCompatActivity {

    private EditText inputTxt;
    private Button saveButton;
    private Button loadButton;
    private TextView resultTxt;

    //Filename of the textfile.
    private String fileName = "hello_file.txt";
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

    /**
     * Saving the input from the application on a text file internally.
     */

    public void saveMessage(View view){
        String message = inputTxt.getText().toString();

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            Toast.makeText(this, "Message Saved", Toast.LENGTH_LONG).show();

            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                fileOutputStream = null;
            }
        }

    }

    /**
     * Reading from a file and presenting on the application
     */
    public void loadMessage(View view){

        FileInputStream fileInputStream = null;
        try {

            String message;
            fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            while ((message = bufferedReader.readLine()) != null){
                stringBuffer.append(message + "\n");
            }

            Toast.makeText(getApplicationContext(), "Loaded TextFile", Toast.LENGTH_LONG).show();
            resultTxt.setText(stringBuffer.toString());
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fileInputStream != null){
                fileInputStream = null;
            }
        }
    }

}
