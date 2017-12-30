package com.example.eier.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private TextView seektext;
    private EditText editText;
    private SeekBar seekBar;
    private Button button;

    private int seekbarPercentage;
    private float enteredBillFloat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = (TextView) findViewById(R.id.resultID);
        seektext = (TextView) findViewById(R.id.seektext);
        button = (Button) findViewById(R.id.buttonID);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        editText = (EditText) findViewById(R.id.billAmountID);

        Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seektext.setText(String.valueOf(progress) + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarPercentage = seekBar.getProgress();

            }
        });

    }


    public void calculate(){
        float result = 0.0f;

        if (!editText.getText().toString().equals(" ")) {
            enteredBillFloat = Float.parseFloat(editText.getText().toString());
            result = enteredBillFloat * seekbarPercentage / 100;
            resultText.setText("Your tip will be: " + String.valueOf(result));

        }
        else{
            Toast.makeText(this, "Please enter a bill amount", Toast.LENGTH_SHORT).show();
        }

    }
}
