package com.example.rpg.Calc.Monsters;

import static com.example.rpg.graphic.GameActivity.game_activity;

import android.app.Person;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.rpg.Calc.Person2;
import com.example.rpg.Calc.map.Map;
import com.example.rpg.Calc.map.World_map;
import com.example.rpg.R;
import com.example.rpg.graphic.GameActivity;
import com.example.rpg.graphic.GameActivity;

import java.io.Serializable;
import java.util.Random;

public class EnemeyMonster implements Serializable {
    public static String monster_place = "over";
    public static EnemeyMonster enemey_monster =new EnemeyMonster();
    public int x=12;
    public int y=3;
    public int monster_serve_x = 12;
    public int monster_serve_y = 3;
    public String area = "メインマップ";
    public void walk(ImageView enemy_monster,Person2 p){
        Random random =new Random();
        int random_number = random.nextInt(5);
        if (random_number == 0) {
            this.x++;
            monster_place = "right";
            if (enemey_monster.area.equals(p.area)) {
                setImageResource(monster_place, enemy_monster);
            }
        }else if (random_number == 1) {
            this.x--;
            monster_place = "left";
            if (enemey_monster.area.equals(p.area)) {
                setImageResource(monster_place,enemy_monster);
            }
        }else if (random_number == 2){
            this.y++;
            monster_place = "over";
            if (enemey_monster.area.equals(p.area)) {
                setImageResource(monster_place, enemy_monster);
            }
        }else if (random_number == 3){
            this.y--;
            monster_place = "under";
            if (enemey_monster.area.equals(p.area)) {
                setImageResource(monster_place, enemy_monster);
            }
        }
    }
    public void randomNewEnemeyMonster(){
        Random random_new_enemey_monster =new Random();
        Map map =new Map();
        int[] range = map.getRange(this.area);
        int x = random_new_enemey_monster.nextInt(range[0]);
        int y = random_new_enemey_monster.nextInt(range[1]);
        String price =map.getMapCode(x,y,this.area);
        while (price.equals(map.E)){
            x =random_new_enemey_monster.nextInt(range[0]);
            y = random_new_enemey_monster.nextInt(range[1]);
            price =map.getMapCode(x,y,this.area);
        }
        this.x = x;
        this.y = y;
    }
    public void setImageResource(String monster_place,ImageView enemy_monster){
            if (game_activity.monster_cara_now.equals("dragon_king")) {
                if (monster_place.equals("over")) {
                    enemy_monster.setImageResource(R.drawable.dragon_king);
                } else if (monster_place.equals("right")) {
                    enemy_monster.setImageResource(R.drawable.dragon_king_right);
                } else if (monster_place.equals("left")) {
                    enemy_monster.setImageResource(R.drawable.dragon_king_left);
                } else {
                    enemy_monster.setImageResource(R.drawable.dragon_king_under);
                }
            } else if (game_activity.monster_cara_now.equals("metal_slime")) {
                if (monster_place.equals("over")) {
                    enemy_monster.setImageResource(R.drawable.metal_slime);
                } else if (monster_place.equals("right")) {
                    enemy_monster.setImageResource(R.drawable.metal_slime_right);
                } else if (monster_place.equals("left")) {
                    enemy_monster.setImageResource(R.drawable.metal_slime_left);
                } else {
                    enemy_monster.setImageResource(R.drawable.metal_slime_under);
                }
            } else if (game_activity.monster_cara_now.equals("puti_slime")) {
                if (monster_place.equals("over")) {
                    enemy_monster.setImageResource(R.drawable.puti_slime);
                } else if (monster_place.equals("right")) {
                    enemy_monster.setImageResource(R.drawable.puti_slime_right);
                } else if (monster_place.equals("left")) {
                    enemy_monster.setImageResource(R.drawable.puti_slime_left);
                } else {
                    enemy_monster.setImageResource(R.drawable.puti_slime_under);
                }
            } else {
                if (monster_place.equals("over")) {
                    enemy_monster.setImageResource(R.drawable.gorlem);
                } else if (monster_place.equals("right")) {
                    enemy_monster.setImageResource(R.drawable.gorlem_right);
                } else if (monster_place.equals("left")) {
                    enemy_monster.setImageResource(R.drawable.gorlem_left);
                } else {
                    enemy_monster.setImageResource(R.drawable.gorlem_under);
                }
            }
    }
    public void graphic_walk(GridLayout gridLayout, ImageView enemy_monster,EnemeyMonster enemey_monster, int image_size,Person2 p){
        if (enemey_monster.area.equals(p.area)) {
            //ここでviewを表示させる
            enemy_monster.setX(gridLayout.getX() + image_size * enemey_monster.x);
            enemy_monster.setY(gridLayout.getY() + image_size * enemey_monster.y);
        }
    }
}
