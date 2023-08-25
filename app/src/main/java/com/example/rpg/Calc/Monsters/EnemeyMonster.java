package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.map.World_map.graphic_world_map;

import com.example.rpg.Calc.map.Map;
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
    public void walk(GameActivity game_activity){
        Random random =new Random();
        int random_number = random.nextInt(4);
        String place;
        if (random_number == 0) {
            this.x++;
            place = "right";
            setImageResource(place,game_activity);
        }else if (random_number == 1) {
            this.x--;
            place = "left";
            setImageResource(place,game_activity);
        }else if (random_number == 2){
            this.y++;
            place = "over";
            setImageResource(place,game_activity);
        }else {
            this.y--;
            place = "under";
            setImageResource(place,game_activity);
            
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
    public void setImageResource(String place,GameActivity gameActivity){
        if (gameActivity.cara_now.equals("dragon_king")){
            if (place.equals("over")){
                gameActivity.enemy_monster.setImageResource(R.drawable.dragon_king); 
            }else if (place.equals("right")) {
                gameActivity.enemy_monster.setImageResource(R.drawable.dragon_king_right);
            }else if (place.equals("left")){
                gameActivity.enemy_monster.setImageResource(R.drawable.dragon_king_left);
            }else {
                gameActivity.enemy_monster.setImageResource(R.drawable.dragon_king_under);
            }
        }else if (gameActivity.cara_now.equals("metal_slime")){
            if (place.equals("over")){
                gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime);
            }else if (place.equals("right")) {
                gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime_right);
            }else if (place.equals("left")){
                gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime_left);
            }else {
                gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime_under);
            }
        }else if (gameActivity.cara_now.equals("puti_slime")){
            if (place.equals("over")){
                gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime);
            }else if (place.equals("right")) {
                gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime_right);
            }else if (place.equals("left")){
                gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime_left);
            }else {
                gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime_under);
            }
        }else {
            if (place.equals("over")){
                gameActivity.enemy_monster.setImageResource(R.drawable.gorlem);
            }else if (place.equals("right")) {
                gameActivity.enemy_monster.setImageResource(R.drawable.gorlem_right);
            }else if (place.equals("left")){
                gameActivity.enemy_monster.setImageResource(R.drawable.gorlem_left);
            }else {
                gameActivity.enemy_monster.setImageResource(R.drawable.gorlem_under);
            }
        }
    }
    public void graphic_walk(GameActivity gameActivity){
        if (area.equals("メインマップ")){
            gameActivity.enemy_monster.setX(graphic_world_map[y][x].getX());
            gameActivity.enemy_monster.setY(graphic_world_map[y][x].getY());
        }
    }
}
