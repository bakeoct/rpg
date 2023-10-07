package com.example.rpg.graphic;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Game.get_enemey_monster;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_Y;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemeyMonster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.Calc.Person2.p;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
import static com.example.rpg.Calc.map.cave.Cave1.cave1;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;
import static com.example.rpg.graphic.GameActivity.place;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.map.cave.Cave1;
import com.example.rpg.Calc.map.cave.Cave1_1;
import com.example.rpg.R;

import java.io.Serializable;

public class Cave1Activity extends AppCompatActivity implements Serializable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave1);
        int image_size = getResources().getDimensionPixelSize(R.dimen.image_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        ImageView right = findViewById(R.id.right_cave1);
        ImageView over = findViewById(R.id.over_cave1);
        ImageView under = findViewById(R.id.under_cave1);
        ImageView left = findViewById(R.id.left_cave1);
        ImageView setting = findViewById(R.id.setting_cave1);
        ImageView do_button = findViewById(R.id.do_button_cave1);
        GridLayout gridLayout = findViewById(R.id.gridLayout_cave1);
        ImageView enemy_monster = findViewById(R.id.enemy_monster_cave1);
        ImageView yuusya = findViewById(R.id.yuusya_cave1);
        GameActivity game_activity = new GameActivity();
        yuusya.bringToFront();
        enemy_monster.bringToFront();
        get_enemey_monster = getMonsterRandomly(enemy_monster);
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
                if (enemeyMonster.area.equals("洞窟1")) {
                    enemy_monster.setX(gridLayout.getX() + image_size * enemeyMonster.x);
                    enemy_monster.setY(gridLayout.getY() + image_size * enemeyMonster.y);
                }
                yuusya.setX(gridLayout.getX() + image_size * p.x);
                yuusya.setY(gridLayout.getY() + image_size * p.y);

                // このリスナーは一度だけ実行させたいので、直後でリスナーを削除する
                gridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "right";
                System.out.println("あああああああああああああああ");
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "left";
                System.out.println("あああああああああああああああ");
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
            }
        });
        under.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "under";
                System.out.println("あああああああああああああああ");
                game.gameTurn(game_activity,gridLayout,enemy_monster,yuusya,image_size);
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "over";
                System.out.println("あああああああああああああああ");
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
                if (map[p.y][p.x].equals("cave1_1")){
                    goCave1_1();
                }else if (map[p.y][p.x].equals("back_world")){
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
        p.area = "洞窟1_1";
        p.x = CAVE1_1_INITIAL_X;
        p.y = CAVE1_1_INITIAL_Y;
        p.serve_x = CAVE1_1_INITIAL_X;
        p.serve_y = CAVE1_1_INITIAL_Y;
        Intent intent = new Intent(Cave1Activity.this, Cave1_1Activity.class);
        startActivity(intent);
    }
    public void goMainMap(){
        p.x = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
        p.y = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
        p.serve_x = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
        p.serve_y = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
        p.area = "メインマップ";
        Intent intent = new Intent(Cave1Activity.this, GameActivity.class);
        startActivity(intent);
    }
}
