package com.example.rpg.Calc.Monsters;

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
    public static EnemeyMonster enemeyMonster =new EnemeyMonster();
    public int x=12;
    public int y=3;
    public int monster_serve_x = 12;
    public int monster_serve_y = 3;
    public String area = "メインマップ";
    public void walk(GameActivity game_activity, ImageView enemy_monster){
        Random random =new Random();
        int random_number = random.nextInt(5);
        String place;
        if (random_number == 0) {
            this.x++;
            place = "right";
            setImageResource(place,game_activity,enemy_monster);
        }else if (random_number == 1) {
            this.x--;
            place = "left";
            setImageResource(place,game_activity,enemy_monster);
        }else if (random_number == 2){
            this.y++;
            place = "over";
            setImageResource(place,game_activity,enemy_monster);
        }else if (random_number == 3){
            this.y--;
            place = "under";
            setImageResource(place,game_activity,enemy_monster);
        }
    }
    public void randomNewEnemeyMonster(World_map world_map){
        Random random_new_enemey_monster =new Random();
        Map map =new Map();
        int[] range = map.getRange(this.area);
        int x = random_new_enemey_monster.nextInt(range[0]);
        int y = random_new_enemey_monster.nextInt(range[1]);
        String price =map.getMapCode(x,y,this.area,world_map);
        while (price.equals(map.E)){
            x =random_new_enemey_monster.nextInt(range[0]);
            y = random_new_enemey_monster.nextInt(range[1]);
            price =map.getMapCode(x,y,this.area,world_map);
        }
        this.x = x;
        this.y = y;
    }
    public void setImageResource(String place,GameActivity gameActivity,ImageView enemy_monster){
        if (gameActivity.cara_now.equals("dragon_king")){
            if (place.equals("over")){
                enemy_monster.setImageResource(R.drawable.dragon_king); 
            }else if (place.equals("right")) { 
                enemy_monster.setImageResource(R.drawable.dragon_king_right);
            }else if (place.equals("left")){
                enemy_monster.setImageResource(R.drawable.dragon_king_left);
            }else {
                enemy_monster.setImageResource(R.drawable.dragon_king_under);
            }
        }else if (gameActivity.cara_now.equals("metal_slime")){
            if (place.equals("over")){
                enemy_monster.setImageResource(R.drawable.metal_slime);
            }else if (place.equals("right")) {
                enemy_monster.setImageResource(R.drawable.metal_slime_right);
            }else if (place.equals("left")){
                enemy_monster.setImageResource(R.drawable.metal_slime_left);
            }else {
                enemy_monster.setImageResource(R.drawable.metal_slime_under);
            }
        }else if (gameActivity.cara_now.equals("puti_slime")){
            if (place.equals("over")){
                enemy_monster.setImageResource(R.drawable.puti_slime);
            }else if (place.equals("right")) {
                enemy_monster.setImageResource(R.drawable.puti_slime_right);
            }else if (place.equals("left")){
                enemy_monster.setImageResource(R.drawable.puti_slime_left);
            }else {
                enemy_monster.setImageResource(R.drawable.puti_slime_under);
            }
        }else {
            if (place.equals("over")){
                enemy_monster.setImageResource(R.drawable.gorlem);
            }else if (place.equals("right")) {
                enemy_monster.setImageResource(R.drawable.gorlem_right);
            }else if (place.equals("left")){
                enemy_monster.setImageResource(R.drawable.gorlem_left);
            }else {
                enemy_monster.setImageResource(R.drawable.gorlem_under);
            }
        }
    }
    public void graphic_walk(GridLayout gridLayout, ImageView enemy_monster,EnemeyMonster enemey_monster, int image_size){
        if (area.equals("メインマップ")){
            System.out.println(gridLayout.getX());
            System.out.println(gridLayout.getY());
            enemy_monster.setX(gridLayout.getX() + image_size * enemey_monster.x);
            enemy_monster.setY(gridLayout.getY() + image_size * enemey_monster.y);
        }
    }
}
