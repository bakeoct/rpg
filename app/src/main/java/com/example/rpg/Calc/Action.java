package com.example.rpg.Calc;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.InventoryActivity.inventory_activity;
import static com.example.rpg.graphic.MainActivity.main_activity;

import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.ControlKey;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.graphic.map_activity.GameActivity;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;

public class Action extends AppCompatActivity implements Serializable, View.OnTouchListener {
    public Thread thread;
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (game.p.repeat_flg) {
                try {
                    Thread.sleep(25); //0.1秒イベント中断。値が小さいほど、高速で連続する
                } catch (InterruptedException e) {
                }
                event();
            }
        }
    };

    public void setAction(ControlKey control_key) {
        control_key.right.setOnTouchListener(this);
        control_key.left.setOnTouchListener(this);
        control_key.under.setOnTouchListener(this);
        control_key.over.setOnTouchListener(this);
        control_key.setting.setOnTouchListener(this);
        control_key.inventory_button.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.right:
            case R.id.left:
            case R.id.under:
            case R.id.over:
                move(v, event);
                break;
            case R.id.setting:
                setting(event);
                break;
            case R.id.inventory_button:
                inventory_button();
                break;
        }
        return true;
    }

    public void setting(MotionEvent event) {
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

    public void inventory_button() {
        Intent intent = new Intent(transition_activity.from_activity, TransitionActivity.class);
        transition_activity.from_activity.startActivity(intent);
        transition_activity.from_activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        transition_activity.to_activity = inventory_activity;
        transition_activity.save_transition_activity = transition_activity.from_activity;
    }

    public void move(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                game.p.repeat_flg = false;
                break;
            case MotionEvent.ACTION_DOWN:
                direction(v);
                event();
                game.p.repeat_flg = true;
                thread = new Thread(runnable);
                thread.start();
                break;
        }
    }

    public void event() {
        game.p.walk((ImageView) game.p.image);
        game.gameTurn();
        game.enemy_monster.meetEnemyMonster();
    }

    public void direction(View v) {
        switch (v.getId()) {
            case R.id.right:
                game.p.place = "right";
                break;
            case R.id.left:
                game.p.place = "left";
                break;
            case R.id.under:
                game.p.place = "under";
                break;
            case R.id.over:
                game.p.place = "over";
        }
    }
}