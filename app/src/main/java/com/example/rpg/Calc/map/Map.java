package com.example.rpg.Calc.map;

import java.io.Serializable;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.map.cave.Cave1.*;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.map.PersonHome1.*;
import static com.example.rpg.Calc.map.world_map.World_map.*;
import static com.example.rpg.Calc.treasure.TreasureChestLadder.treasure_chest_ladder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.R;

public class Map extends AppCompatActivity implements Serializable {
    public Tail tail = new Tail();
    public Tail[][] map_of_treasure;
//        public static final String TL = "treasure_chest_ladder";
//        public static final String TS = "treasure_chest_ship";
//        public static final String O = "海";//Ocean
//        public static final String M = "山";//Mounten
//        public static final String C = "崖";//Cliff
//        public static final String G = "glass";//Glass
//        public static final String E = "errer";//ErrerPoString
//        public static final String S = "store";//Store
//        public static final String W = "wood";//wood
//        public static final String SN = "stone";//Stone;
//        public static final String BCV1 = "back_cave_1";//BackCave1;
//        public static final String CG = "cliff_on_glass";
//        public static final String CJ = "cliff_on_jyari";
        public GridLayout grid_layout_map;//モンスターの動きが完成したら消す

        public Tail getMapCode(int pointx, int pointy,String area) {
            Tail map_place_code = null;
            if (area.equals("メインマップ")){
                map_place_code = world_map[pointy][pointx];
            }else if (area.equals("民家1")){
                map_place_code = people_home1[pointy][pointx];
            }else if (area.equals("洞窟1")){
                map_place_code = cave1[pointy][pointx];
            }else if (area.equals("洞窟1_1")){
                map_place_code = cave1_1[pointy][pointx];
            }
            return map_place_code;
        }
        public int[] getRange(String area){
            int[] map =new int[2];
            if (area.equals("メインマップ")){
                map[0] = 22;
                map[1] = 12;
            }else if (area.equals("民家1")){
                map[0] = 8;
                map[1] = 8;
            }else if (area.equals("洞窟1")){
                map[0] = 4;
                map[1] = 8;
            }else if (area.equals("洞窟1_1")){
                map[0] = 8;
                map[1] = 8;
            }
            return map;
        }
        public void makeMap(Tail[][] map, Context context){
            grid_layout_map.removeAllViews();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    Drawable myImageDrawable = drawMap(map,i,j,context);
                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    layoutParams.width = game.image_size;
                    layoutParams.height = game.image_size;
                    layoutParams.setMargins(game.margin, game.margin, game.margin, game.margin);
                    ImageView imageView = new ImageView(context);
                    imageView.setImageDrawable(myImageDrawable);
                    imageView.setLayoutParams(layoutParams);
                    switch (map[i][j]){
                        case "treasure_chest_ladder":
                        case "treasure_chest_ship":
                            treasure_image_view = imageView;
                    }
                    grid_layout_map.addView(imageView);
                }
            }
        }
    public Drawable drawMap(Tail[][] map, int i, int j, Context context){
        Drawable myImageDrawable = null;
        switch (map[i][j]){
            case "海":
                myImageDrawable = context.getResources().getDrawable(R.drawable.sea, null);
                break;
            case "山":
            case "glass":
            case "mountain_with_cliff":
                myImageDrawable = context.getResources().getDrawable(R.drawable.glass, null);
                break;
            case "崖":
            case "cliff_with_cliff":
                myImageDrawable = context.getResources().getDrawable(R.drawable.jyari, null);
                break;
            case "store":
                myImageDrawable = context.getResources().getDrawable(R.drawable.store, null);
                break;
            case "stone":
                myImageDrawable = context.getResources().getDrawable(R.drawable.stone, null);
                break;
            case "cave1":
            case "back_world_cave":
            case "cave1_1":
            case "back_cave_1":
                myImageDrawable = context.getResources().getDrawable(R.drawable.cave_entrance, null);
                break;
            case "back_world_home":
                myImageDrawable = context.getResources().getDrawable(R.drawable.door_wood_1, null);
                break;
            case "people_home_1":
                myImageDrawable = context.getResources().getDrawable(R.drawable.home, null);
                break;
            case "cliff_on_glass":
                myImageDrawable = context.getResources().getDrawable(R.drawable.cliff_on_glass, null);
                break;
            case "cliff_on_jyari":
                myImageDrawable = context.getResources().getDrawable(R.drawable.cliff_on_jyari, null);
                break;
            case "treasure_chest_ladder":
            case "treasure_chest_ship":
                myImageDrawable = context.getResources().getDrawable(R.drawable.treasure_chest, null);
                break;
            case "wood":
                myImageDrawable = context.getResources().getDrawable(R.drawable.wood, null);
                break;
            case "errer":
                myImageDrawable = context.getResources().getDrawable(R.drawable.errerzone, null);
                break;
        }
        return myImageDrawable;
    }
    public void setEntity(){
        game.map.grid_layout_map.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (game.enemy_monster.area.equals("メインマップ")) {
                    game.enemy_monster.image.setX(game.map.grid_layout_map.getX() + game.image_size * game.enemy_monster.x);
                    game.enemy_monster.image.setY(game.map.grid_layout_map.getY() + game.image_size * game.enemy_monster.y);
                }
                game.p.x = (int) game.map.grid_layout_map.getX() + game.image_size * game.p.mpx;
                game.p.y = (int) game.map.grid_layout_map.getY() + game.image_size * game.p.mpy;
                game.p.image.setX(game.map.grid_layout_map.getX() + game.image_size * game.p.mpx);
                game.p.image.setY(game.map.grid_layout_map.getY() + game.image_size * game.p.mpy);
                // このリスナーは一度だけ実行させたいので、直後でリスナーを削除する
                game.map.grid_layout_map.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
    }
