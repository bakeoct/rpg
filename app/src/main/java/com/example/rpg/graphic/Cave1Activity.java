package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_Y;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
import static com.example.rpg.Calc.map.cave.Cave1.cave1;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;
import static com.example.rpg.graphic.Cave1_1Activity.cave1_1_activity;
import static com.example.rpg.graphic.GameActivity.game_activity;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;
import static com.example.rpg.graphic.GameActivity.place;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.map.cave.Cave1;
import com.example.rpg.Calc.map.cave.Cave1_1;
import com.example.rpg.R;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;
import java.util.ArrayList;

public class Cave1Activity extends AppCompatActivity implements Serializable {
    public static Cave1Activity cave_1_activity = new Cave1Activity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave1);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                // USAGE_MEDIA
                // USAGE_GAME
                .setUsage(AudioAttributes.USAGE_GAME)
                // CONTENT_TYPE_MUSIC
                // CONTENT_TYPE_SPEECH, etc.
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();                                                                        // ストリーム数に応じて
        SoundPool sound_pool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(3).build();
        final int ON_STONE_AUDIO = sound_pool.load(this, R.raw.stone,1);//stone
        final int ON_WOOD_AUDIO = sound_pool.load(this,R.raw.wood,1);//wood
        final int IN_SEA_AUDIO = sound_pool.load(this,R.raw.sea,1);//海
        final int ON_GRAVEL_AUDIO = sound_pool.load(this,R.raw.cliff,1);//崖
        final int ON_GLASS_AUDIO = sound_pool.load(this,R.raw.glass,1);//glass
        final int ON_FALLEN_LEAVES_AUDIO = sound_pool.load(this,R.raw.leaves,1);//山
        final int OPEN_DOOR_AUDIO = sound_pool.load(this,R.raw.door,1);//
        final int OPEN_TREASURE_CHEST_AUDIO = sound_pool.load(this,R.raw.treasure_chest,1);//treasure_chest
        ArrayList<Integer> audio = new ArrayList<>();
        audio.add(ON_STONE_AUDIO);
        audio.add(ON_WOOD_AUDIO);
        audio.add(IN_SEA_AUDIO);
        audio.add(ON_GRAVEL_AUDIO);
        audio.add(ON_GLASS_AUDIO);
        audio.add(ON_FALLEN_LEAVES_AUDIO);
        audio.add(OPEN_DOOR_AUDIO);
        audio.add(OPEN_TREASURE_CHEST_AUDIO);
        MediaPlayerManager.mediaPlayer.stop();
        MediaPlayerManager.mediaPlayer.release();
        MediaPlayerManager.mediaPlayer = MediaPlayer.create(this, R.raw.cavemusic);
        MediaPlayerManager.mediaPlayer.start();
        int image_size = getResources().getDimensionPixelSize(R.dimen.image_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        ImageView right = findViewById(R.id.right_cave1);
        ImageView over = findViewById(R.id.over_cave1);
        ImageView under = findViewById(R.id.under_cave1);
        ImageView left = findViewById(R.id.left_cave1);
        ImageView setting = findViewById(R.id.setting_cave1);
        ImageView do_button = findViewById(R.id.do_button_cave1);
        GridLayout gridLayout = findViewById(R.id.gridLayout_cave1);
        ImageView enemy_monster = findViewById(R.id.enemy_monster);
        ImageView yuusya = findViewById(R.id.yuusya_cave1);
        GameActivity game_activity = new GameActivity();
        yuusya.bringToFront();
        enemy_monster.bringToFront();
        game.get_enemey_monster = getMonsterRandomly(enemy_monster);
        String[][] map = cave1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Drawable myImageDrawable = drawMap(map,i,j);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = image_size;
                layoutParams.height = image_size;
                layoutParams.setMargins(margin, margin, margin, margin);
                ImageView imageView = new ImageView(this);
                imageView.setImageDrawable(myImageDrawable);
                imageView.setLayoutParams(layoutParams);
                gridLayout.addView(imageView);
            }
        }
        gridLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println(gridLayout.getX());
                System.out.println(gridLayout.getY());
                if (enemey_monster.area.equals("洞窟1")) {
                    enemy_monster.setX(gridLayout.getX() + image_size * enemey_monster.x);
                    enemy_monster.setY(gridLayout.getY() + image_size * enemey_monster.y);
                }
                yuusya.setX(gridLayout.getX() + image_size * game.p.x);
                yuusya.setY(gridLayout.getY() + image_size * game.p.y);

                // このリスナーは一度だけ実行させたいので、直後でリスナーを削除する
                gridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "right";
                System.out.println("あああああああああああああああ");
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,sound_pool,audio);
                meetEnemyMonster();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "left";
                System.out.println("あああああああああああああああ");
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,sound_pool,audio);
                meetEnemyMonster();
            }
        });
        under.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "under";
                System.out.println("あああああああああああああああ");
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,sound_pool,audio);
                meetEnemyMonster();
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "over";
                System.out.println("あああああああああああああああ");
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,sound_pool,audio);
                meetEnemyMonster();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWriteAndRead.write();
                Intent intent = new Intent(Cave1Activity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = new MainActivity();
            }
        });
        do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map[game.p.y][game.p.x].equals("cave1_1")){
                    goCave1_1();
                }else if (map[game.p.y][game.p.x].equals("back_world")){
                    goMainMap();
                }
            }
        });
    }
    public Drawable drawMap(String[][] map,int i,int j){
        Drawable myImageDrawable;
        if (map[i][j].equals("stone")) {
            myImageDrawable = getResources().getDrawable(R.drawable.stone, null);
        } else if (map[i][j].equals("errer")) {
            myImageDrawable = getResources().getDrawable(R.drawable.errerzone, null);
        } else {
            myImageDrawable = getResources().getDrawable(R.drawable.cave_entrance, null);
        }
        return myImageDrawable;
    }
    public void goCave1_1(){
        game.p.area = "洞窟1_1";
        game.p.x = CAVE1_1_INITIAL_X;
        game.p.y = CAVE1_1_INITIAL_Y;
        game.p.serve_x = CAVE1_1_INITIAL_X;
        game.p.serve_y = CAVE1_1_INITIAL_Y;
        Intent intent = new Intent(Cave1Activity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity = cave1_1_activity;
    }
    public void goMainMap(){
        game.p.x = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
        game.p.y = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
        game.p.serve_x = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
        game.p.serve_y = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
        game.p.area = "メインマップ";
        Intent intent = new Intent(Cave1Activity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity = game_activity;
    }
    public void meetEnemyMonster(){
        if (game.battle_manager.meet_enemy_monster){
            transition_activity = battle_manager_activity;
            save_transition_activity = cave_1_activity;
            game.battle_manager.meet_enemy_monster = false;
            startActivity(new Intent(Cave1Activity.this,TransitionActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
