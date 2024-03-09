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
    public ArrayList<MediaPlayer> audio = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button conthinew = findViewById(R.id.conthinew);
        //MediaPlayerManager.mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
       // MediaPlayerManager.mediaPlayer.start();
        final MediaPlayer ON_STONE_AUDIO = MediaPlayer.create(this, R.raw.stone);//stone
        final MediaPlayer ON_WOOD_AUDIO = MediaPlayer.create(this, R.raw.wood);//wood
        final MediaPlayer IN_SEA_AUDIO = MediaPlayer.create(this, R.raw.sea);//海
        final MediaPlayer ON_GRAVEL_AUDIO = MediaPlayer.create(this, R.raw.cliff);//崖
        final MediaPlayer ON_GLASS_AUDIO = MediaPlayer.create(this, R.raw.glass);//glass
        final MediaPlayer ON_FALLEN_LEAVES_AUDIO = MediaPlayer.create(this, R.raw.leaves);//山
        final MediaPlayer OPEN_DOOR_AUDIO = MediaPlayer.create(this, R.raw.door);//
        final MediaPlayer OPEN_TREASURE_CHEST_AUDIO = MediaPlayer.create(this, R.raw.treasure_chest);//treasure_chest
        audio.add(ON_STONE_AUDIO);
        audio.add(ON_WOOD_AUDIO);
        audio.add(IN_SEA_AUDIO);
        audio.add(ON_GRAVEL_AUDIO);
        audio.add(ON_GLASS_AUDIO);
        audio.add(ON_FALLEN_LEAVES_AUDIO);
        audio.add(OPEN_DOOR_AUDIO);
        audio.add(OPEN_TREASURE_CHEST_AUDIO);
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
}