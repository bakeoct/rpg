package com.example.rpg.graphic.map_activity;

import static com.example.rpg.Calc.map.PersonHome1.PERSON_HOME1_INITIAL_X;
import static com.example.rpg.Calc.map.PersonHome1.PERSON_HOME1_INITIAL_Y;
import static com.example.rpg.graphic.map_activity.PeopleHomeActivity.people_home_1_activity;
import static com.example.rpg.Calc.map.world_map.World_map.world_map;
import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.GridLayout;

import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_Y;
import static com.example.rpg.graphic.map_activity.Cave1Activity.cave_1_activity;
import static com.example.rpg.graphic.StoreActivity.store_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import static com.example.rpg.Calc.Game.game;

import androidx.annotation.RequiresApi;

import com.example.rpg.Calc.controlView.ControlView;
import com.example.rpg.Calc.controlView.JoyStickView;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.graphic.map_activity.super_activity.MapActivity;

import java.io.Serializable;

public class GameActivity extends MapActivity implements Serializable {
    public static GameActivity game_activity = new GameActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game.player.world_x = game.player.mpx * game.image_size;
        game.player.world_y = game.player.mpy * game.image_size;
        from_activity = this;
        max_monster_num = 10;
        this.map = world_map;
//        game.mpm.playMusic(this, R.raw.bgmusic);
        control_key = new ControlView(this);
        from_activity.control_key.joy_stick.setJoystickListener(new JoyStickView.JoystickListener() {
            @Override
            public void onJoystickMoved(float xPercent, float yPercent, MotionEvent event) {
                game.action.movePlayer(event,xPercent,yPercent);
            }
        });
        game.player.setPlayerOnMap();
        game.monster_manager.appearMonsterOnMap();
        game.action.moveMonster();
        game.action.setPersonAction(control_key);
        control_key.do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (from_activity.map[game.player.mpy][game.player.mpx].equals("store")) {
                    goStore();
                } else if (from_activity.map[game.player.mpy][game.player.mpx].equals("cave1")) {
                    goCave1();
                } else if (from_activity.map[game.player.mpy][game.player.mpx].equals("people_home_1")) {
                    goPeople_home_1();
                }
            }
        });
    }

    public void goStore() {
        Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = store_activity;
    }

    public void goCave1() {
        game.player.mpx = CAVE1_INITIAL_X;
        game.player.mpy = CAVE1_INITIAL_Y;
        game.player.serve_mpx = CAVE1_INITIAL_X;
        game.player.serve_mpy = CAVE1_INITIAL_Y;
        Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = cave_1_activity;

    }

    public void goPeople_home_1() {
        game.sound.startSounds("door");
        game.player.mpx = PERSON_HOME1_INITIAL_X;
        game.player.mpy = PERSON_HOME1_INITIAL_Y;
        game.player.serve_mpx = PERSON_HOME1_INITIAL_X;
        game.player.serve_mpy = PERSON_HOME1_INITIAL_Y;
        Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = people_home_1_activity;
    }
}
