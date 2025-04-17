package com.example.rpg.graphic.map_activity;

import static com.example.rpg.Calc.Monsters.Monster2.setImageResource;
import static com.example.rpg.Calc.map.PersonHome1.PERSON_HOME1_INITIAL_X;
import static com.example.rpg.Calc.map.PersonHome1.PERSON_HOME1_INITIAL_Y;
import static com.example.rpg.graphic.InventoryActivity.inventory_activity;
import static com.example.rpg.graphic.MainActivity.main_activity;
import static com.example.rpg.graphic.map_activity.PeopleHomeActivity.people_home_1_activity;
import static com.example.rpg.Calc.map.world_map.World_map.world_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_INITIAL_Y;
import static com.example.rpg.graphic.map_activity.Cave1Activity.cave_1_activity;
import static com.example.rpg.graphic.StoreActivity.store_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.rpg.Calc.Game.game;

import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.ControlKey;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;

public class GameActivity extends AppCompatActivity implements Serializable {
    public static String monster_cara_now = null;
    public static GameActivity game_activity = new GameActivity();
    public Tail[][] map = world_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game_activity = this;
        game.mpm.playMusic(this, R.raw.bgmusic);
        ControlKey control_key = new ControlKey(this);
        setImageResource();
        game.map.makeMap(map, this);
        game.map.setEntity();
        game.map.map_of_treasure = map;
        transition_activity.from_activity = game_activity;
        game.action.setAction(control_key);
        control_key.do_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map[game.p.mpy][game.p.mpx].equals("store")) {
                    goStore();
                } else if (map[game.p.mpy][game.p.mpx].equals("cave1")) {
                    goCave1();
                } else if (map[game.p.mpy][game.p.mpx].equals("people_home_1")) {
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
        game.p.area = "洞窟1";
        game.p.mpx = CAVE1_INITIAL_X;
        game.p.mpy = CAVE1_INITIAL_Y;
        game.p.serve_x = CAVE1_INITIAL_X;
        game.p.serve_y = CAVE1_INITIAL_Y;
        Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = cave_1_activity;

    }

    public void goPeople_home_1() {
        game.p.area = "民家1";
        game.sound.startSounds("door");
        game.p.mpx = PERSON_HOME1_INITIAL_X;
        game.p.mpy = PERSON_HOME1_INITIAL_Y;
        game.p.serve_x = PERSON_HOME1_INITIAL_X;
        game.p.serve_y = PERSON_HOME1_INITIAL_Y;
        Intent intent = new Intent(GameActivity.this, TransitionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = people_home_1_activity;
    }
}
