package com.example.eier.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button button;
    private SeekBar seekBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Creating a Mediaplayer object and locate the song of your choice in the RAW directory that you created..
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.lala);

        seekBar = (SeekBar) findViewById(R.id.seekBarID);
        seekBar.setMax(mediaPlayer.getDuration());


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration();
                System.out.println(duration);

            }
        });

        button = (Button) findViewById(R.id.playID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    pauseMusic();
                }
                else{
                    startMusic();
                }
            }
        });

    }


    public void pauseMusic(){

        if (mediaPlayer != null){
            mediaPlayer.pause();
            button.setText("Play");

        }

    }

    public void startMusic(){

        if(mediaPlayer != null){
            mediaPlayer.start();
            button.setText("Pause");
        }

    }

    @Override
    protected void onDestroy() {


        /*
            Overwriting the OnDestroy method, so that when the app is in destroy state it will make the Mediaplayer object to NULL again..

         */
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;


        }

        super.onDestroy();
    }
}
