package com.ahmedmakramallah.musicalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Podcasts extends AppCompatActivity {

    Button back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcasts);

        // to play a music, you should use Media Player class.

        MediaPlayer mediaPlayer = new MediaPlayer();

        back =(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Podcasts.this, Playlists.class);
                startActivity(i);
            }
        });
    }
}
