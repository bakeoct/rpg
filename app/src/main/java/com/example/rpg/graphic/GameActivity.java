package com.example.rpg.graphic;

import static com.example.rpg.Calc.Monsters.EnemeyMonster.monster_place;
import static com.example.rpg.Calc.map.World_map.world_map;
import static com.example.rpg.graphic.MainActivity.*;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemeyMonster;
import static com.example.rpg.Calc.Game.get_enemey_monster;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_Y;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import com.example.rpg.R;
import static com.example.rpg.Calc.Person2.p;
import java.io.Serializable;

public class GameActivity extends AppCompatActivity implements Serializable {
    public static String monster_cara_now = null;
    public static String place = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        int image_size = getResources().getDimensionPixelSize(R.dimen.image_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        ImageView right = findViewById(R.id.right);
        ImageView over = findViewById(R.id.over);
        ImageView under = findViewById(R.id.under);
        ImageView left = findViewById(R.id.left);
        ImageView setting = findViewById(R.id.setting);
        ImageView do_button = findViewById(R.id.do_button);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        ImageView enemy_monster = findViewById(R.id.enemy_monster);
        ImageView yuusya = findViewById(R.id.yuusya);
        GameActivity game_activity = new GameActivity();
        System.out.println(monster_place);
        get_enemey_monster = getMonsterRandomly(enemy_monster);
        String[][] map = world_map;
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
                if (enemeyMonster.area.equals("メインマップ")) {
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
                game.gameTurn(game_activity,gridLayout, enemy_monster,yuusya,image_size);
            }
        });
        over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place = "over";
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
                if (map[p.y][p.x].equals("store")){
                    goStore();
                }else if (map[p.y][p.x].equals("cave1")){
                    goCave_1();
                }
            }
        });
    }
    public Drawable drawMap(String[][] map,int i,int j){
        Drawable myImageDrawable = null;
        if (map[i][j].equals("海")) {
            myImageDrawable = getResources().getDrawable(R.drawable.sea, null);
        } else if (map[i][j].equals("山")) {
            myImageDrawable = getResources().getDrawable(R.drawable.glass, null);
        } else if (map[i][j].equals("崖")) {
            myImageDrawable = getResources().getDrawable(R.drawable.jyari, null);
        } else if (map[i][j].equals("glass")) {
            myImageDrawable = getResources().getDrawable(R.drawable.glass, null);
        } else if (map[i][j].equals("errer")) {
            myImageDrawable = getResources().getDrawable(R.drawable.errerzone, null);
        } else if (map[i][j].equals("store")) {
            myImageDrawable = getResources().getDrawable(R.drawable.store, null);
        } else if (map[i][j].equals("stone")) {
            myImageDrawable = getResources().getDrawable(R.drawable.stone, null);
        } else if (map[i][j].equals("cave1")) {
            myImageDrawable = getResources().getDrawable(R.drawable.cave_entrance, null);
        } else if (map[i][j].equals("people_home_1")) {
            myImageDrawable = getResources().getDrawable(R.drawable.home, null);
        }
        return myImageDrawable;
    }
    public void goStore(){
            Intent intent = new Intent(GameActivity.this, StoreActivity.class);
            startActivity(intent);
    }
    public void goCave_1(){
        p.area = "洞窟1";
        p.x = CAVE1_INITIAL_X;
        p.y = CAVE1_INITIAL_Y;
        p.serve_x = CAVE1_INITIAL_X;
        p.serve_y = CAVE1_INITIAL_Y;
        Intent intent = new Intent(GameActivity.this, Cave1Activity.class);
        startActivity(intent);
    }
}
