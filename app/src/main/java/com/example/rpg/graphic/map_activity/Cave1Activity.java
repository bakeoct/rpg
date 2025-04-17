package com.example.rpg.graphic.map_activity;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.Monster2.setImageResource;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
import static com.example.rpg.Calc.map.cave.Cave1.cave1;
import static com.example.rpg.Calc.map.world_map.World_map.world_map;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;
import static com.example.rpg.graphic.MainActivity.main_activity;
import static com.example.rpg.graphic.map_activity.Cave1_1Activity.cave1_1_activity;
import static com.example.rpg.graphic.map_activity.GameActivity.game_activity;

import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.ControlKey;
import com.example.rpg.R;
import com.example.rpg.graphic.MainActivity;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;
import java.util.ArrayList;

public class Cave1Activity extends AppCompatActivity implements Serializable {
    public static Cave1Activity cave_1_activity = new Cave1Activity();
    public Tail[][] map = cave1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave1);
        cave_1_activity = this;
        game.mpm.playMusic(this,R.raw.cavemusic);
        ControlKey control_key = new ControlKey(this);
        setImageResource();
        game.map.makeMap(map,this);
        game.map.setEntity();
        transition_activity.from_activity = cave_1_activity;
        game.map.map_of_treasure = map;
        game.action.setAction(control_key);
        control_key.do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map[game.p.mpy][game.p.mpx].equals("cave1_1")){
                    goCave1_1();
                }else if (map[game.p.mpy][game.p.mpx].equals("back_world_cave")){
                    goMainMap();
                }
            }
        });
    }
    public void goCave1_1(){
        game.p.area = "洞窟1_1";
        game.p.mpx = CAVE1_1_INITIAL_X;
        game.p.mpy = CAVE1_1_INITIAL_Y;
        game.p.serve_x = CAVE1_1_INITIAL_X;
        game.p.serve_y = CAVE1_1_INITIAL_Y;
        Intent intent = new Intent(Cave1Activity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = cave1_1_activity;
    }
    public void goMainMap(){
        game.p.mpx = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
        game.p.mpy = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
        game.p.serve_x = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
        game.p.serve_y = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
        game.p.area = "メインマップ";
        Intent intent = new Intent(Cave1Activity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = game_activity;
    }
}