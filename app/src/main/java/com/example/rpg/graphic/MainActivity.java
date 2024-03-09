package com.example.rpg.graphic;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.GameActivity.game_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import android.media.MediaPlayer;
import com.example.rpg.Calc.Game;
import com.example.rpg.R;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button conthinew = findViewById(R.id.conthinew);
        //MediaPlayerManager.mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
       // MediaPlayerManager.mediaPlayer.start();
    conthinew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.readSave();
                Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = game_activity;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}