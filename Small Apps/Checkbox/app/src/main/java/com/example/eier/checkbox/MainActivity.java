package com.example.eier.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView resultText;
    private CheckBox momcheckbox;
    private CheckBox dadcheckbox;
    private CheckBox grandpacheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        momcheckbox = (CheckBox) findViewById(R.id.momID);
        dadcheckbox = (CheckBox) findViewById(R.id.dadID);
        grandpacheckbox = (CheckBox) findViewById(R.id.grandpaid);

        resultText = (TextView) findViewById(R.id.resultID);
        button = (Button) findViewById(R.id.buttonID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(momcheckbox.getText().toString() + " status is: " + momcheckbox.isChecked() + "\n");
                stringBuilder.append(dadcheckbox.getText().toString() + " status is: " + dadcheckbox.isChecked() + "\n");
                stringBuilder.append(grandpacheckbox.getText().toString() + " status is: " + grandpacheckbox.isChecked() + "\n");
                resultText.setText(stringBuilder);
                resultText.setVisibility(View.VISIBLE);


            }
        });

    }
}
