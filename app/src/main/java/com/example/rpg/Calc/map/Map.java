package com.example.rpg.Calc.map;

import java.io.Serializable;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.map.cave.Cave1.*;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.map.PersonHome1.*;
import static com.example.rpg.Calc.map.world_map.World_map.*;
import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.content.Context;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.R;
import com.example.rpg.graphic.map_activity.super_activity.MapActivity;

public class Map extends AppCompatActivity implements Serializable {
        public GridLayout grid_layout_map;//モンスターの動きが完成したら消す

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
        public void makeMap(){
            grid_layout_map.removeAllViews();
            for (int i = 0; i < from_activity.map.length; i++) {
                for (int j = 0; j < from_activity.map[i].length; j++) {
                    from_activity.map[i][j].image = new ImageView(from_activity);
                    from_activity.map[i][j].image.setImageDrawable(from_activity.map[i][j].drawable);
//                    from_activity.map[i][j].image.setImageDrawable(from_activity.getDrawable(R.drawable.debug));デバックしたい時だけ使う
                    from_activity.map[i][j].image.setLayoutParams(from_activity.map[i][j].layout_params);
                    grid_layout_map.addView(from_activity.map[i][j].image);
                }
            }
        }

    public void setEntity(){
        game.map.grid_layout_map.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setCoordinate();
                game.player.x = (int) game.map.grid_layout_map.getX() + game.image_size * game.player.mpx;
                game.player.y = (int) game.map.grid_layout_map.getY() + game.image_size * game.player.mpy;
                game.player.image.setX(game.map.grid_layout_map.getX() + game.image_size * game.player.mpx);
                game.player.image.setY(game.map.grid_layout_map.getY() + game.image_size * game.player.mpy);
                game.monster_manager.appearMonsterOnMap();
                game.action.moveMonster();
                // このリスナーは一度だけ実行させたいので、直後でリスナーを削除する
                game.map.grid_layout_map.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
    private void setCoordinate(){
        for (int i = 0; i < from_activity.map.length; i++) {
            for (int j = 0; j < from_activity.map[i].length; j++) {
                from_activity.map[i][j].x_start = from_activity.map[i][j].image.getX() + game.map.grid_layout_map.getX();
                from_activity.map[i][j].y_start = from_activity.map[i][j].image.getY() + game.map.grid_layout_map.getY();
                from_activity.map[i][j].x_end = from_activity.map[i][j].image.getX() + game.image_size + game.map.grid_layout_map.getX();
                from_activity.map[i][j].y_end = from_activity.map[i][j].image.getY() + game.image_size + game.map.grid_layout_map.getY();
            }
        }
    }
    }
