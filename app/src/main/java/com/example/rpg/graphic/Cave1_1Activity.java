package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Game.get_enemey_monster;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemeyMonster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.Calc.Person2.p;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.map.cave.Cave1.cave1;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;
import static com.example.rpg.graphic.GameActivity.place;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

import java.io.Serializable;

public class Cave1_1Activity extends AppCompatActivity implements Serializable {
    private int chenge_treasure = 0;
    private ImageView treasure_imageView;
    private int[] treasure_images = {R.drawable.treasure_chest_open, R.drawable.treasure_chest}; // 切り替える画像リソース
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave1_1);
        int image_size = getResources().getDimensionPixelSize(R.dimen.image_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        ImageView right = findViewById(R.id.right_cave1_1);
        ImageView over = findViewById(R.id.over_cave1_1);
        ImageView under = findViewById(R.id.under_cave1_1);
        ImageView left = findViewById(R.id.left_cave1_1);
        ImageView setting = findViewById(R.id.setting_cave1_1);
        ImageView do_button = findViewById(R.id.do_button_cave1_1);
        GridLayout gridLayout = findViewById(R.id.gridLayout_cave1_1);
        ImageView enemy_monster = findViewById(R.id.enemy_monster_cave1_1);
        ImageView yuusya = findViewById(R.id.yuusya_cave1_1);
        GameActivity game_activity = new GameActivity();
        get_enemey_monster = getMonsterRandomly(enemy_monster);
        String[][] map = cave1_1;
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
                if (myImageDrawable == getResources().getDrawable(R.drawable.treasure_chest, null)){
                    treasure_imageView = imageView;
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
                if (enemeyMonster.area.equals("洞窟1_1")) {
                    enemy_monster.setX(gridLayout.getX() + image_size * enemeyMonster.x);
                    enemy_monster.setY(gridLayout.getY() + image_size * enemeyMonster.y);
                }
                yuusya.setX(gridLayout.getX() + image_size * p.x);
                yuusya.setY(gridLayout.getY() + image_size * p.y);

                // このリスナーは一度だけ実行させたいので、直後でリスナーを削除する
                gridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (chenge_treasure < 2) {
                    treasure_imageView.setImageResource(treasure_images[chenge_treasure]);
                    chenge_treasure++;
                    handler.postDelayed(this, 1000); // 1秒間隔で実行
                }
        }
    };

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "right";
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "left";
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
            }
        });
        under.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "under";
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "over";
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
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
                if (map[p.y][p.x].equals("back_cave_1")){
                    goCave1();
                }else if (map[p.y][p.x].equals("treasure_chest_ladder")){
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
        p.x = CAVE1_1_BACK_CAVE1_INITIAL_X;
        p.y = CAVE1_1_BACK_CAVE1_INITIAL_Y;
        p.serve_x = CAVE1_1_BACK_CAVE1_INITIAL_X;
        p.serve_y = CAVE1_1_BACK_CAVE1_INITIAL_Y;
        p.area = "洞窟1";
        Intent intent = new Intent(Cave1_1Activity.this, Cave1Activity.class);
        startActivity(intent);
    }
    public void getTreasure(Handler handler,Runnable runnable){
        handler.post(runnable);
    }
}
