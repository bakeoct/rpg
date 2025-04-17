package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.graphic.map_activity.GameActivity.game_activity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Person;
import com.example.rpg.Calc.map.Map;
import com.example.rpg.R;
import com.example.rpg.graphic.TransitionActivity;
import com.example.rpg.graphic.map_activity.GameActivity;

import java.io.Serializable;
import java.util.Random;

public class EnemyMonster extends AppCompatActivity implements Serializable {
    public String monster_place = "over";
    public ImageView image;
    public int x=12;
    public int y=4;
    public int monster_serve_x = 12;
    public int monster_serve_y = 4;
    public String area = "メインマップ";
    public void walk(ImageView enemy_monster,Person p){
        Random random =new Random();
        int random_number = random.nextInt(5);
        if (random_number == 0) {
            this.x++;
            monster_place = "right";
            if (area.equals(p.area)) {
                setImageResource(monster_place, enemy_monster);
            }
        }else if (random_number == 1) {
            this.x--;
            monster_place = "left";
            if (area.equals(p.area)) {
                setImageResource(monster_place,enemy_monster);
            }
        }else if (random_number == 2) {
            this.y++;
            monster_place = "over";
            if (area.equals(p.area)) {
                setImageResource(monster_place, enemy_monster);
            }
        } if (random_number == 3){
            this.y--;
            monster_place = "under";
            if (area.equals(p.area)) {
                setImageResource(monster_place, enemy_monster);
            }
        }
    }
    public void randomNewEnemyMonster(){
        Random random_new_enemy_monster =new Random();
        Map map =new Map();
        int[] range = map.getRange(this.area);
        int x = random_new_enemy_monster.nextInt(range[0]);
        int y = random_new_enemy_monster.nextInt(range[1]);
        String price =map.getMapCode(x,y,this.area);
        while (price.equals(map.E)){
            x =random_new_enemy_monster.nextInt(range[0]);
            y = random_new_enemy_monster.nextInt(range[1]);
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
    public void graphic_walk(){
        if (game.enemy_monster.area.equals(game.p.area)) {
            //ここでviewを表示させる
            game.enemy_monster.image.setX(game.map.grid_layout_map.getX() + game.image_size * game.enemy_monster.x);
            game.enemy_monster.image.setY(game.map.grid_layout_map.getY() + game.image_size * game.enemy_monster.y);
        }
    }
    public void meetEnemyMonster(){
        if (game.battle_manager.meet_enemy_monster){
            transition_activity.to_activity = battle_manager_activity;
            transition_activity.save_transition_activity = game_activity;
            game.battle_manager.meet_enemy_monster = false;
            startActivity(new Intent(transition_activity.from_activity, TransitionActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}