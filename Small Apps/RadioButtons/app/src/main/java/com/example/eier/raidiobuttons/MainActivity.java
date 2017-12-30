package com.example.eier.raidiobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroupID);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) findViewById(checkedId);

                switch (radioButton.getId()){
                    case R.id.yesID:
                        Log.d("RD", "yes");
                        Log.d("ses", String.valueOf(radioButton.getId()));
                       break;
                    case R.id.noID:
                        Log.d("RD", "no");
                        break;

                    case R.id.maybeID:
                        Log.d("RD", "maybe");
                        break;

                }
            }
        });

    }
}
