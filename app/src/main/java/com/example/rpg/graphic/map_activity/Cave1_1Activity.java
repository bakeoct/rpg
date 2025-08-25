package com.example.rpg.graphic.map_activity;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.from_activity;
import static com.example.rpg.graphic.map_activity.Cave1Activity.cave_1_activity;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.example.rpg.Calc.controlView.ControlView;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.graphic.map_activity.super_activity.MapActivity;

import java.io.Serializable;

public class Cave1_1Activity extends MapActivity implements Serializable {
    public static Cave1_1Activity cave1_1_activity = new Cave1_1Activity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave1_1);
        cave1_1_activity = this;
        from_activity = this;
        cave1_1_activity.map = cave1_1;
        game.mpm.playMusic(this,R.raw.cavemusic);
        ControlView control_key = new ControlView(this);
        game.player.setPlayerOnMap();
        game.monster_manager.appearMonsterOnMap();
        game.action.moveMonster();
        game.action.setPersonAction(control_key);
        control_key.do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cave1_1_activity.map[game.player.mpy][game.player.mpx].equals("back_cave_1")){
                    goCave1();
                }else if (cave1_1_activity.map[game.player.mpy][game.player.mpx].equals("treasure_chest_ladder")){
                    cave1_1_activity.map[game.player.mpy][game.player.mpx].HANDLER.post(cave1_1_activity.map[game.player.mpy][game.player.mpx].OPEN_TREASURE);
                }
            }
        });

    }
    public void goCave1(){
        game.player.mpx = CAVE1_1_BACK_CAVE1_INITIAL_X;
        game.player.mpy = CAVE1_1_BACK_CAVE1_INITIAL_Y;
        game.player.serve_mpx = CAVE1_1_BACK_CAVE1_INITIAL_X;
        game.player.serve_mpy = CAVE1_1_BACK_CAVE1_INITIAL_Y;
        Intent intent = new Intent(this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = cave_1_activity;
    }
}