package com.example.rpg.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.map_activity.GameActivity.game_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.sound.MediaPlayerManager.media_player;

import android.media.MediaPlayer;

import com.example.rpg.R;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    public static MainActivity main_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_activity = this;
        setMediaPlayer();
        setTail();
        assignmentItemDrawable();
        ImageView continue_button = findViewById(R.id.continue_button);
//        media_player = MediaPlayer.create(this, R.raw.bgmusic);
//        media_player.start();
    continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.readSave();
                Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity.to_activity = game_activity;
            }
        });
    }
    private ArrayList<MediaPlayer> setMediaPlayer(){
        final MediaPlayer ON_STONE_AUDIO = MediaPlayer.create(this, R.raw.stone);//stone
        final MediaPlayer ON_WOOD_AUDIO = MediaPlayer.create(this, R.raw.wood);//wood
        final MediaPlayer IN_SEA_AUDIO = MediaPlayer.create(this, R.raw.sea);//海
        final MediaPlayer ON_GRAVEL_AUDIO = MediaPlayer.create(this, R.raw.cliff);//崖
        final MediaPlayer ON_GLASS_AUDIO = MediaPlayer.create(this, R.raw.glass);//glass
        final MediaPlayer ON_FALLEN_LEAVES_AUDIO = MediaPlayer.create(this, R.raw.leaves);//山
        final MediaPlayer OPEN_DOOR_AUDIO = MediaPlayer.create(this, R.raw.door);//
        final MediaPlayer OPEN_TREASURE_CHEST_AUDIO = MediaPlayer.create(this, R.raw.treasure_chest);//treasure_chest
        game.mpm.audio.add(ON_STONE_AUDIO);
        game.mpm.audio.add(ON_WOOD_AUDIO);
        game.mpm.audio.add(IN_SEA_AUDIO);
        game.mpm.audio.add(ON_GRAVEL_AUDIO);
        game.mpm.audio.add(ON_GLASS_AUDIO);
        game.mpm.audio.add(ON_FALLEN_LEAVES_AUDIO);
        game.mpm.audio.add(OPEN_DOOR_AUDIO);
        game.mpm.audio.add(OPEN_TREASURE_CHEST_AUDIO);
        return game.mpm.audio;
    }
    private void setTail(){
        game.image_size = getResources().getDimensionPixelSize(R.dimen.image_size);
        game.margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
    }
    public void assignmentItemDrawable(){
        game.store.ladder.item_drawable = getResources().getDrawable(R.drawable.ladder);
        game.store.ship.item_drawable = getResources().getDrawable(R.drawable.ship);
        game.store.steel_armor.item_drawable = getResources().getDrawable(R.drawable.steel_armer);
        game.store.super_sword.item_drawable = getResources().getDrawable(R.drawable.super_sword);
        game.store.heal_glass.item_drawable = getResources().getDrawable(R.drawable.heal_glass);
        game.store.metal_slime_merchandise.item_drawable = getResources().getDrawable(R.drawable.metal_slime);
        game.store.puti_slime_merchandise.item_drawable = getResources().getDrawable(R.drawable.puti_slime);
        game.store.dragon_king_merchandise.item_drawable = getResources().getDrawable(R.drawable.dragon_king);
        game.store.gorlem_merchandise.item_drawable = getResources().getDrawable(R.drawable.gorlem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (media_player != null) {
            media_player.stop();
            media_player.release();
            media_player = null;
        }
    }
}