package com.example.rpg.Calc.Entity;

import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.R;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Entity implements Serializable {
    public ArrayList<Monster> monsters2 = new ArrayList<>();
    public int lv = 1;
    public int have_experience_point = 0;
    public int need_experience_point = 100;
    public ArrayList<FieldItem> field_items = new ArrayList<>();
    public ArrayList<MonsterItem> monster_items = new ArrayList<>();
    public ArrayList<FightItem> fight_items = new ArrayList<>();
    public ArrayList<Item> items = new ArrayList<>();
    public int money = 100;
    public Item have_item = null;
    public int choose_item = 0;

    public Player() {
        mpx = 12;
        mpy = 6;
        serve_mpx = 12;
        serve_mpy = 6;
        x = 0;
        y = 0;
        speed = 5;
        direction = "over";
        this.items.addAll(field_items);
        this.items.addAll(fight_items);
        this.items.addAll(monster_items);
    }

    public void walk(int walk_texture_number) {
        knowWhereTail();
        switch (direction) {
            case "right":
                if (walk_texture_number == 1) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_right_walk));
                } else if (walk_texture_number == 2) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_right));
                }
                if (game.event.notPlayerEnter(this.x + speed,this.y)) {
                    System.out.println("再度選んでくださいp");
                } else {
                    this.x += speed;
                }
                break;
            case "left":
                if (walk_texture_number == 1) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_left));
                } else if (walk_texture_number == 2) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_left_walk));
                }
                if (game.event.notPlayerEnter(this.x - speed,this.y)) {
                    System.out.println("再度選んでくださいp");
                } else {
                    this.x -= speed;
                }
                break;
            case "under":
                if (walk_texture_number == 1) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_under_walk_1));
                } else if (walk_texture_number == 2) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_under_walk_2));
                }
                if (game.event.notPlayerEnter(this.x,this.y + speed)) {
                    System.out.println("再度選んでくださいp");
                } else {
                    this.y += speed;
                }
                break;
            case "over":
                if (walk_texture_number == 1) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_over_walk_1));
                } else if (walk_texture_number == 2) {
                    game.player.image.setImageDrawable(from_activity.getDrawable(R.drawable.player_over_walk_2));
                }
                if (game.event.notPlayerEnter(this.x,this.y - speed)) {
                    System.out.println("再度選んでくださいp");
                } else {
                    this.y -= speed;
                }
                break;
        }
        image.setX(this.x);
        image.setY(this.y);
        serve_mpx = mpx;
        serve_mpy = mpy;
    }

    private void knowWhereTail() {
        for (int i = 0; i < from_activity.map.length; i++) {
            for (int j = 0; j < from_activity.map[i].length; j++) {
                if (from_activity.map[i][j].x_start <= game.player.image.getX() + game.image_size / 2 && from_activity.map[i][j].x_end >= game.player.image.getX() + game.image_size / 2 && from_activity.map[i][j].y_start <= game.player.image.getY() + game.image_size / 2 && from_activity.map[i][j].y_end >= game.player.image.getY() + game.image_size / 2) {
                    this.mpx = j;
                    this.mpy = i;
                }
            }
        }
    }
}
