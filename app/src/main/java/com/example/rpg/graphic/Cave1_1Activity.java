package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;
import static com.example.rpg.graphic.Cave1Activity.cave_1_activity;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.treasure.TreasureChestLadder.treasure_chest_ladder;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;
import static com.example.rpg.graphic.GameActivity.place;

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

public class Cave1_1Activity extends MainActivity implements Serializable {
    public static Cave1_1Activity cave1_1_activity = new Cave1_1Activity();
    public int chenge_treasure = 0;
    public ImageView treasure_imageView = null;
    public int[] treasure_images = {R.drawable.open_treasure_chest, R.drawable.treasure_chest}; // 切り替える画像リソース
    public final Handler handler = new Handler();
    public final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (chenge_treasure < 2) {
                treasure_imageView.setImageResource(treasure_images[chenge_treasure]);
                if (chenge_treasure == 0) {
                    treasure_chest_ladder.openTreasureChest(audio);
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
        setContentView(R.layout.activity_cave1_1);
       /* MediaPlayerManager.mediaPlayer.stop();
        MediaPlayerManager.mediaPlayer.release();
        MediaPlayerManager.mediaPlayer = MediaPlayer.create(this, R.raw.cavemusic);
        MediaPlayerManager.mediaPlayer.start();*/
        int image_size = getResources().getDimensionPixelSize(R.dimen.image_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        ImageView right = findViewById(R.id.right_cave1_1);
        ImageView over = findViewById(R.id.over_cave1_1);
        ImageView under = findViewById(R.id.under_cave1_1);
        ImageView left = findViewById(R.id.left_cave1_1);
        ImageView setting = findViewById(R.id.setting_cave1_1);
        ImageView do_button = findViewById(R.id.do_button_cave1_1);
        GridLayout gridLayout = findViewById(R.id.gridLayout_cave1_1);
        ImageView enemy_monster = findViewById(R.id.enemy_monster);
        ImageView yuusya = findViewById(R.id.yuusya_cave1_1);
        game.get_enemey_monster = getMonsterRandomly(enemy_monster);
        String[][] map = cave1_1;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                ImageView imageView = new ImageView(this);
                Drawable myImageDrawable = drawMap(map,i,j);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.width = image_size;
                layoutParams.height = image_size;
                layoutParams.setMargins(margin, margin, margin, margin);
                imageView.setImageDrawable(myImageDrawable);
                imageView.setLayoutParams(layoutParams);
                if (map[i][j].equals("treasure_chest_ladder")){
                    treasure_imageView = imageView;
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
                gridLayout.addView(imageView);
            }
        }
        yuusya.bringToFront();
        enemy_monster.bringToFront();
        gridLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println(gridLayout.getX());
                System.out.println(gridLayout.getY());
                if (enemey_monster.area.equals("洞窟1_1")) {
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
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,audio);
                meetEnemyMonster();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "left";
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,audio);
                meetEnemyMonster();
            }
        });
        under.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "under";
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,audio);
                meetEnemyMonster();
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "over";
                game.gameTurn(gridLayout,enemy_monster,yuusya,image_size,audio);
                meetEnemyMonster();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWriteAndRead.write();
                Intent intent = new Intent(Cave1_1Activity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = new MainActivity();
            }
        });
        do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map[game.p.y][game.p.x].equals("back_cave_1")){
                    goCave1();
                }else if (map[game.p.y][game.p.x].equals("treasure_chest_ladder")){
                    getTreasure(handler,runnable);
                }
            }
        });

    }
    public Drawable drawMap(String[][] map,int i,int j){
        Drawable myImageDrawable = null;
        if (map[i][j].equals("stone")) {
            myImageDrawable = getResources().getDrawable(R.drawable.stone, null);
        } else if (map[i][j].equals("errer")) {
            myImageDrawable = getResources().getDrawable(R.drawable.errerzone, null);
        } else if (map[i][j].equals("back_cave_1")){
            myImageDrawable = getResources().getDrawable(R.drawable.cave_entrance, null);
        } else if (map[i][j].equals("treasure_chest_ladder")) {
            myImageDrawable = getResources().getDrawable(R.drawable.treasure_chest, null);
        }
        return myImageDrawable;
    }
    public void goCave1(){
        game.p.x = CAVE1_1_BACK_CAVE1_INITIAL_X;
        game.p.y = CAVE1_1_BACK_CAVE1_INITIAL_Y;
        game.p.serve_x = CAVE1_1_BACK_CAVE1_INITIAL_X;
        game.p.serve_y = CAVE1_1_BACK_CAVE1_INITIAL_Y;
        game.p.area = "洞窟1";
        Intent intent = new Intent(this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity = cave_1_activity;
    }
    public void getTreasure(Handler handler,Runnable runnable){
       handler.post(runnable);
    }
    public void meetEnemyMonster(){
        if (game.battle_manager.meet_enemy_monster){
            transition_activity = battle_manager_activity;
            save_transition_activity = cave1_1_activity;
            game.battle_manager.meet_enemy_monster = false;
            startActivity(new Intent(Cave1_1Activity.this,TransitionActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
