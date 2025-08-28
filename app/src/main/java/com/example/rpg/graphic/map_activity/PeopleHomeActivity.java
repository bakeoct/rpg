package com.example.rpg.graphic.map_activity;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.map.PersonHome1.*;
import static com.example.rpg.graphic.map_activity.GameActivity.game_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.graphic.TransitionActivity.from_activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.example.rpg.Calc.controlView.ControlView;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.graphic.map_activity.super_activity.MapActivity;

import java.io.Serializable;

public class PeopleHomeActivity extends MapActivity implements Serializable {
    public static PeopleHomeActivity people_home_1_activity = new PeopleHomeActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_home1);
        people_home_1_activity = this;
        from_activity = this;
        people_home_1_activity.map = people_home1;
        game.mpm.playMusic(this,R.raw.homemusic);
        ControlView control_key = new ControlView(this);
        //マップの画面表示
        game.player.setPlayerOnMap();
        game.monster_manager.appearMonsterOnMap();
        game.action.moveMonster();
        game.action.setPersonAction(control_key);
        control_key.do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (people_home_1_activity.map[game.player.mpy][game.player.mpx].equals("back_world_home")){
                    goMainMap();
                }else if (people_home_1_activity.map[game.player.mpy][game.player.mpx].equals("treasure_chest_ship")){
                    people_home_1_activity.map[game.player.mpy][game.player.mpx].HANDLER.post(people_home_1_activity.map[game.player.mpy][game.player.mpx].OPEN_TREASURE);
                }
            }
        });
    }
    public void goMainMap(){
        game.sound.startSounds("door");
        game.player.mpx = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X;
        game.player.mpy = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y;
        game.player.serve_mpx = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X;
        game.player.serve_mpy = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y;
        Intent intent = new Intent(PeopleHomeActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = game_activity;
    }
}