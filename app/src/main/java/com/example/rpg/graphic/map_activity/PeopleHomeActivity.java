package com.example.rpg.graphic.map_activity;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.Monster2.setImageResource;
import static com.example.rpg.Calc.map.PersonHome1.*;
import static com.example.rpg.graphic.MainActivity.main_activity;
import static com.example.rpg.graphic.map_activity.GameActivity.game_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.ControlKey;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;

public class PeopleHomeActivity extends AppCompatActivity implements Serializable {
    public static PeopleHomeActivity people_home_1_activity = new PeopleHomeActivity();
    public Tail[][] map = people_home1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_home1);
        people_home_1_activity = this;
        game.mpm.playMusic(this,R.raw.homemusic);
        ControlKey control_key = new ControlKey(this);
        setImageResource();
        //マップの画面表示
        game.map.makeMap(map,this);
        game.map.setEntity();
        game.map.map_of_treasure = map;
        transition_activity.from_activity = people_home_1_activity;
        game.action.setAction(control_key);
        control_key.do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map[game.p.mpy][game.p.mpx].equals("back_world_home")){
                    goMainMap();
                }else if (map[game.p.mpy][game.p.mpx].equals("treasure_chest_ship")){
                    map[game.p.mpy][game.p.mpx].HANDLER.post(map[game.p.mpy][game.p.mpx].OPEN_TREASURE);
                }
            }
        });
    }
    public void goMainMap(){
        game.p.area = "メインマップ";
        game.sound.startSounds("door");
        game.p.mpx = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X;
        game.p.mpy = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y;
        game.p.serve_x = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X;
        game.p.serve_y = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y;
        Intent intent = new Intent(PeopleHomeActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = game_activity;
    }
}