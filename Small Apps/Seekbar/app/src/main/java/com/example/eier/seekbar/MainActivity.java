package com.example.eier.seekbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView resulTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulTextView = (TextView) findViewById(R.id.resultID);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        resulTextView.setText("Pain level " + seekBar.getProgress() + " of " + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                resulTextView.setText("Pain level " + seekBar.getProgress() + " of " + seekBar.getMax());


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "OnStartTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() >= 7){
                    resulTextView.setTextColor(Color.RED);
                }
                else {
                    resulTextView.setTextColor(Color.DKGRAY);
                }
            }
        });
    }
}
