package com.example.rpg.Calc;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.InventoryActivity.inventory_activity;
import static com.example.rpg.graphic.MainActivity.main_activity;

import static com.example.rpg.graphic.TransitionActivity.from_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.controlKey.ControlKey;
import com.example.rpg.Calc.controlKey.JoyStickView;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;
import java.util.ArrayList;

public class Action extends AppCompatActivity implements Serializable, View.OnTouchListener {
    //PlayerAction
    private boolean repeat_flg_player = false;
    private float moveX = 0, moveY = 0;
    private MotionEvent e = null;
    public void setPersonAction(ControlKey control_key) {
        control_key.joy_stick.setJoystickListener(new JoyStickView.JoystickListener() {
            @Override
            public void onJoystickMoved(float xPercent, float yPercent, MotionEvent event) {
                moveX = xPercent;
                moveY = yPercent;
                e = event;
                movePlayer();
            }
        });
        control_key.setting.setOnTouchListener(this);
        control_key.inventory_button.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.setting:
                setting(event);
                break;
            case R.id.inventory_button:
                inventory_button();
                break;
        }
        return true;
    }

    private void setting(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                MediaPlayerManager.media_player.stop();
                MediaPlayerManager.media_player.release();
                saveWriteAndRead.write();
                Intent intent = new Intent(transition_activity.from_activity, TransitionActivity.class);
                transition_activity.from_activity.startActivity(intent);
                transition_activity.from_activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity.to_activity = main_activity;
        }
    }

    private void inventory_button() {
        Intent intent = new Intent(transition_activity.from_activity, TransitionActivity.class);
        transition_activity.from_activity.startActivity(intent);
        transition_activity.from_activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = inventory_activity;
        transition_activity.save_transition_activity = transition_activity.from_activity;
    }

    private void movePlayer() {
        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                repeat_flg_player = false;
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                
                repeat_flg_player = true;
                int stop_time = 15;
                int stop_time_amount = 0;
                int walk_texture_number = 1;
                try {
                    Thread.sleep(stop_time); //0.015秒イベント中断。値が小さいほど、高速で連続する
                } catch (InterruptedException e) {
                }
                stop_time_amount += stop_time;
                game.player.walk(walk_texture_number, moveX, moveY);
                if (stop_time_amount > 500) {
                    if (walk_texture_number == 1) {
                        walk_texture_number = 2;
                    } else if (walk_texture_number == 2) {
                        walk_texture_number = 1;
                    }
                    stop_time_amount = 0;
                    }
                break;
        }
        if (repeat_flg_player){
            new Handler().postDelayed(this::movePlayer,16);
        }
    }
    //MonsterAction
    public void moveMonster() {
        repeat_flg_monster.clear();
        for (int i = 0; i < from_activity.monster_on_map.size(); i++) {
            int which_monster = i;
            repeat_flg_monster.add(true);
            Thread thread;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //モンスターの通常のマップ徘徊
                    while (true) {//死んだらfalse
                        if (repeat_flg_monster.get(which_monster)) {
                            from_activity.monster_on_map.get(which_monster).direction = game.navmesh.directionMonster();
                            int j = 0;
                            while (j < 50 && repeat_flg_monster.get(which_monster)) {//モンスターのspeed * 50 の値分上記のdirectionの方向に動く
                                from_activity.monster_on_map.get(which_monster).distance_from_player = game.navmesh.distanceFromPlayer(game.player.image, from_activity.monster_on_map.get(which_monster).image);
                                if (from_activity.monster_on_map.get(which_monster).detection_distance > from_activity.monster_on_map.get(which_monster).distance_from_player) {
                                    repeat_flg_monster.set(which_monster, false);
                                } else {
                                    from_activity.monster_on_map.set(which_monster, game.navmesh.wandering(which_monster));

                                }
                                j++;
                            }
                            try {
                                if (repeat_flg_monster.get(which_monster)) {
                                    Thread.sleep(from_activity.monster_on_map.get(which_monster).getFrequencyMove());
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            from_activity.monster_on_map.get(which_monster).distance_from_player = game.navmesh.distanceFromPlayer(game.player.image, from_activity.monster_on_map.get(which_monster).image);
                            if (from_activity.monster_on_map.get(which_monster).detection_distance < from_activity.monster_on_map.get(which_monster).distance_from_player) {
                                repeat_flg_monster.set(which_monster, true);
                            } else {
                                game.navmesh.tracking(from_activity.monster_on_map.get(which_monster));
                            }
                        }
                    }
                    //モンスターのプレイヤー追跡
                }

            };
            thread = new Thread(runnable);
            thread.start();
        }
    }

    private ArrayList<Boolean> repeat_flg_monster = new ArrayList<>();

}