package com.android.uraall.playingaudio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView playButton;
    SeekBar seekBar;
    int position;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);
        seekBar = findViewById(R.id.volumeSeekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
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



        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    pause();

                } else {
                    play();

                }
            }
        });


           new Timer().scheduleAtFixedRate(new TimerTask() {
               @Override
               public void run() {

                   if (mediaPlayer!=null){
                       position = mediaPlayer.getCurrentPosition();}
                   else
                       position = 1;
                   seekBar.setProgress(position);
               }
           },0,1000);

    }

    public void play() {
        mediaPlayer.start();
        playButton.setImageResource(R.drawable.ic_baseline_pause_24);
    }

    public void pause() {
        playButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        mediaPlayer.pause();
    }

    public void previousButton(View view) {
       pause();
       mediaPlayer.seekTo(1);
    }

    public void nextButton(View view) {
        pause();
        mediaPlayer.seekTo(mediaPlayer.getDuration());
    }
}
