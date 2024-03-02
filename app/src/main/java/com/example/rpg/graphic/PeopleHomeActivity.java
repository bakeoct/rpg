package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.monster_place;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.Calc.map.PersonHome1.*;
import static com.example.rpg.Calc.treasure.TreasureChestShip.treasure_chest_ship;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;
import static com.example.rpg.graphic.GameActivity.game_activity;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;
import static com.example.rpg.graphic.GameActivity.place;
import static com.example.rpg.sound.Sound.sound;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;
import java.util.ArrayList;

public class PeopleHomeActivity extends AppCompatActivity implements Serializable {
    public static PeopleHomeActivity people_home_1_activity = new PeopleHomeActivity();
    public int chenge_treasure = 0;
    public ImageView treasure_imageView = null;
    public int[] treasure_images = {R.drawable.open_treasure_chest, R.drawable.treasure_chest}; // 切り替える画像リソース
    public ArrayList<Integer> treasure_audio;
    public SoundPool sound_pool;
    public final Handler handler = new Handler();
    public final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (chenge_treasure < 2) {
                treasure_imageView.setImageResource(treasure_images[chenge_treasure]);
                if (chenge_treasure == 0) {
                    treasure_chest_ship.openTreasureChest(sound_pool,treasure_audio);
                }
                chenge_treasure++;
                handler.postDelayed(runnable, 1000); // 1秒間隔で実行
            } else {
                chenge_treasure = 0;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_home1);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                // USAGE_MEDIA
                // USAGE_GAME
                .setUsage(AudioAttributes.USAGE_GAME)
                // CONTENT_TYPE_MUSIC
                // CONTENT_TYPE_SPEECH, etc.
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();                                                                        // ストリーム数に応じて
        SoundPool sound_pool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(3).build();
        this.sound_pool = sound_pool;
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
        this.treasure_audio = audio;
        MediaPlayerManager.mediaPlayer.stop();
        MediaPlayerManager.mediaPlayer.release();
        MediaPlayerManager.mediaPlayer = MediaPlayer.create(this, R.raw.homemusic);
        MediaPlayerManager.mediaPlayer.start();
        int image_size = getResources().getDimensionPixelSize(R.dimen.image_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        ImageView right = findViewById(R.id.right_people_home1);
        ImageView over = findViewById(R.id.over_people_home1);
        ImageView under = findViewById(R.id.under_people_home1);
        ImageView left = findViewById(R.id.left_people_home1);
        ImageView setting = findViewById(R.id.setting_people_home1);
        ImageView do_button = findViewById(R.id.do_button_people_home1);
        GridLayout gridLayout = findViewById(R.id.gridLayout_people_home1);
        ImageView enemy_monster = findViewById(R.id.enemy_monster);
        ImageView yuusya = findViewById(R.id.yuusya_people_home1);
        GameActivity game_activity = new GameActivity();
        System.out.println(monster_place);
        game.get_enemey_monster = getMonsterRandomly(enemy_monster);
        //マップの画面表示
        String[][] map = people_home1;//どのマップを使うのか指定
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
                if (map[i][j].equals("treasure_chest_ship")){
                    treasure_imageView = imageView;
                }
                gridLayout.addView(imageView);
            }
        }
        yuusya.bringToFront();
        enemy_monster.bringToFront();
        //上記のマップ表示を待ち、エンティティをマップにセットする
        gridLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println(gridLayout.getX());
                System.out.println(gridLayout.getY());
                if (enemey_monster.area.equals("民家1")) {
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
                game.gameTurn(gridLayout, enemy_monster,yuusya,image_size,sound_pool,audio);
                meetEnemyMonster();
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "over";
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,sound_pool,audio);
                meetEnemyMonster();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "left";
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,sound_pool,audio);
                meetEnemyMonster();
            }
        });
        under.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "under";
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,sound_pool,audio);
                meetEnemyMonster();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWriteAndRead.write();
                finish();
            }
        });
        do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map[game.p.y][game.p.x].equals("back_world")){
                    goMainWorld(sound_pool,audio);
                }else if (map[game.p.y][game.p.x].equals("treasure_chest_ship")){
                    getTreasure(handler,runnable);
                }
            }
        });
    }
    public Drawable drawMap(String[][] map,int i,int j){
        Drawable myImageDrawable = null;
        if (map[i][j].equals("back_world")) {
            myImageDrawable = getResources().getDrawable(R.drawable.door_wood_1, null);
        } else if (map[i][j].equals("wood")) {
            myImageDrawable = getResources().getDrawable(R.drawable.wood, null);
        } else if (map[i][j].equals("errer")) {
            myImageDrawable = getResources().getDrawable(R.drawable.errerzone, null);
        } else if (map[i][j].equals("treasure_chest_ship")) {
            myImageDrawable = getResources().getDrawable(R.drawable.treasure_chest, null);
        }
        return myImageDrawable;
    }
    public void goMainWorld(SoundPool sound_pool, ArrayList<Integer> audio){
        game.p.area = "メインマップ";
        sound.startSounds("door",sound_pool,audio);
        game.p.x = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X;
        game.p.y = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y;
        game.p.serve_x = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X;
        game.p.serve_y = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y;
        Intent intent = new Intent(PeopleHomeActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity = game_activity;
    }
    public void getTreasure(Handler handler,Runnable runnable){
        handler.post(runnable);
    }
    public void meetEnemyMonster(){
        if (game.battle_manager.meet_enemy_monster){
            transition_activity = battle_manager_activity;
            save_transition_activity =people_home_1_activity;
            game.battle_manager.meet_enemy_monster = false;
            startActivity(new Intent(PeopleHomeActivity.this,TransitionActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
