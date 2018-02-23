package com.example.eier.calcsfileio;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eier.iowithsdcard.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Write to a SD-Card and read From SD-Card.
 */
public class MainActivity extends AppCompatActivity{
    private Button plusButton, minusButton, divideButton, timesButton, writeBtn, readBtn;
    private EditText editNumberTextOne, editNumberTextTwo;
    private int sum;
    private TextView textView;
    private final String fileName = "hellotext.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

    }


    public void initWidgets(){
        editNumberTextOne = (EditText) findViewById(R.id.editText);
        editNumberTextTwo = (EditText) findViewById(R.id.editText2);
        plusButton = (Button) findViewById(R.id.plussBtn);
        minusButton = (Button) findViewById(R.id.minusBtn);
        divideButton = (Button) findViewById(R.id.divideBtn);
        timesButton = (Button) findViewById(R.id.timesBtn);
        textView = (TextView) findViewById(R.id.fileTextView);
        writeBtn = (Button) findViewById(R.id.writeBtn);
        readBtn = (Button) findViewById(R.id.readBtn);

    }



    public void OnClick(View view){

        switch (view.getId()){
            case R.id.plussBtn:

                if (!editNumberTextOne.getText().toString().equals("") || !editNumberTextTwo.getText().toString().equals("")) {
                    int num1 = Integer.parseInt(editNumberTextOne.getText().toString());
                    int num2 = Integer.parseInt(editNumberTextTwo.getText().toString());
                    sum = num1 + num2;
                    Toast.makeText(this, "OPERATION IS DONE", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Inputs are not complete", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.minusBtn:

                if (!editNumberTextOne.getText().toString().equals("") || !editNumberTextTwo.getText().toString().equals("")) {
                    int num1 = Integer.parseInt(editNumberTextOne.getText().toString());
                    int num2 = Integer.parseInt(editNumberTextTwo.getText().toString());
                    sum = num1 - num2;
                    Toast.makeText(this, "OPERATION IS DONE", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Inputs are not complete", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.divideBtn:

                if (!editNumberTextOne.getText().toString().equals("") || !editNumberTextTwo.getText().toString().equals("")) {
                    int num1 = Integer.parseInt(editNumberTextOne.getText().toString());
                    int num2 = Integer.parseInt(editNumberTextTwo.getText().toString());
                    sum = num1 / num2;
                    Toast.makeText(this, "OPERATION IS DONE", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Inputs are not complete", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.timesBtn:
                if (!editNumberTextOne.getText().toString().equals("") || !editNumberTextTwo.getText().toString().equals("")) {
                    int num1 = Integer.parseInt(editNumberTextOne.getText().toString());
                    int num2 = Integer.parseInt(editNumberTextTwo.getText().toString());
                    sum = num1 * num2;
                    Toast.makeText(this, "OPERATION IS DONE", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, "Inputs are not complete", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }



    public void writeToFile(View view) {


        FileOutputStream fileOutputStream = null;
        String msg = String.valueOf(sum);

        if (isExternalStorageWritable()) {

            try {

                File sdCard = Environment.getExternalStorageDirectory();
                File ddir = new File(sdCard.getAbsolutePath() + "/foldernavn");

                if (!ddir.exists())  ddir.mkdirs();


                File file = new File(ddir, fileName);


                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(msg.getBytes());

                Toast.makeText(this, "Message Saved", Toast.LENGTH_LONG).show();


                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    fileOutputStream = null;
                }
            }
        }else {
            Toast.makeText(this, "Please input your SD-Card", Toast.LENGTH_LONG).show();
        }
    }


    public void readFromFile(View view){
        String msg;

        FileInputStream inputStream = null;

        try{

            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/foldernavn");

            Log.d("PATH", root.getAbsolutePath());

            File file = new File(dir, fileName);

            inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder builder = new StringBuilder();

            while ((msg = bufferedReader.readLine()) != null){
                builder.append(msg);
            }

            inputStream.close();
            Toast.makeText(this, "Presenting from textFile", Toast.LENGTH_LONG).show();
            textView.setText(builder.toString());

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }



    /* Checks if external storage is available for read and write */

    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }


   /* public File createFolderOnSDCARD(){

        try{

            File myfolder = new File(Environment.getExternalStorageDirectory() +"/testfolder");

            if (!myfolder.exists()) {
                myfolder.mkdir();
                return myfolder;
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }*/
}

