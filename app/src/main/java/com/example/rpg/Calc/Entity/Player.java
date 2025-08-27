package com.example.rpg.Calc.Entity;

import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.graphic.map_activity.GameActivity;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.MainActivity.main_activity;
import static com.example.rpg.graphic.TransitionActivity.from_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Entity implements Serializable {
    public ArrayList<Monster> monsters2 = new ArrayList<>();
    public int lv = 1;
    public int have_experience_point = 0;
    public int need_experience_point = 100;
    public int screen_x;
    public int screen_y;
    public ArrayList<FieldItem> field_items = new ArrayList<>();
    public ArrayList<MonsterItem> monster_items = new ArrayList<>();
    public ArrayList<FightItem> fight_items = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();
    public int money = 100;
    public Item have_item = null;
    public int choose_item = 0;

    public Player() {
        mpx = 25;
        mpy = 25;
        serve_mpx = 25;
        serve_mpy = 25;
//        mpx = 4;//デバッグ用
//        mpy = 6;
//        serve_mpx = 4;
//        serve_mpy = 6;
        hp = 20000;
        attack = 25;
        limit_hp = 20000;
        limit_attack = 25;
        speed = 5;
        direction = "over";
        this.items.addAll(field_items);
        this.items.addAll(fight_items);
        this.items.addAll(monster_items);
    }
    public void setPlayerOnMap(){
        this.screen_x = from_activity.getResources().getSystem().getDisplayMetrics().widthPixels / 2 - game.image_size / 2;
        this.screen_y = from_activity.getResources().getSystem().getDisplayMetrics().heightPixels / 2 - game.image_size / 2;
        game.player.image.setX(screen_x);
        game.player.image.setY(screen_y);
    }

    public void walk(int walk_texture_number,float xPercent,float yPercent,boolean repeat_flg) {
        if (repeat_flg) {
            knowWhereTail();
            direction(walk_texture_number, xPercent, yPercent);
            //x移動
            if (game.event.notPlayerEnter(this.world_x + speed * xPercent, this.world_y)) {
                System.out.println("再度選んでくださいp");
            } else {
                this.world_x += speed * xPercent;
            }
            //y移動
            if (game.event.notPlayerEnter(this.world_x, this.world_y + speed * yPercent)) {
                System.out.println("再度選んでくださいp");
            } else {
                this.world_y += speed * yPercent;
            }
            //上の二つの移動で斜め移動を表現
            serve_mpx = mpx;
            serve_mpy = mpy;
        }
    }
    private String direction(int walk_texture_number,float xPercent,float yPercent){
        String dire_x;
        String dire_y;
        if (0 < xPercent) {
            dire_x = "right";
        } else {
            dire_x = "left";
        }
        if (0 < yPercent) {
            dire_y = "behind";
        } else {
            dire_y = "over";
        }
        String d;
        if (Math.abs(xPercent) < Math.abs(yPercent)){
            d = dire_y;
        }else {
            d = dire_x;
        }
        switch (d){
            case "over":
                if (walk_texture_number == 1) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_over_walk_1));
                } else if (walk_texture_number == 2) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_over_walk_2));
                }
                break;
            case "behind":
                if (walk_texture_number == 1) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_under_walk_1));
                } else if (walk_texture_number == 2) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_under_walk_2));
                }
                break;
            case "right":
                if (walk_texture_number == 1) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_right_walk));
                } else if (walk_texture_number == 2) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_right));
                }
                break;
            case "left":
                if (walk_texture_number == 1) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_left));
                } else if (walk_texture_number == 2) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_left_walk));
                }
                break;
        }
        return d;
    }

    private void knowWhereTail() {
        for (int i = 0; i < from_activity.map.length; i++) {
            for (int j = 0; j < from_activity.map[i].length; j++) {
                if (from_activity.map[i][j].x_start <= game.player.screen_x
                    && from_activity.map[i][j].x_end >= game.player.screen_x
                    && from_activity.map[i][j].y_start <= game.player.screen_y
                    && from_activity.map[i][j].y_end >= game.player.screen_y) {
                    this.mpx = j;
                    this.mpy = i;
                }
            }
        }
    }
    public void knockBack(){

    }
    public void changeHpBarColor(){
        if ((double)this.hp / this.limit_hp <= 0.25){
            from_activity.control_key.hp_bar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        }else if ((double)this.hp / this.limit_hp <= 0.5){
            from_activity.control_key.hp_bar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        }else {
            from_activity.control_key.hp_bar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        }
    }
    public void gameOver(){

    }
}
