package com.example.rpg.Calc.Monsters;

import android.widget.TextView;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.DragonKing.dragon_king;
import static com.example.rpg.Calc.Monsters.Gorlem.gorlem;
import static com.example.rpg.Calc.Monsters.MetalSlime.metal_slime;
import static com.example.rpg.Calc.Monsters.PutiSlime.puti_slime;
import static com.example.rpg.graphic.map_activity.GameActivity.monster_cara_now;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Monster2 implements Serializable {
    public int hp;
    public int limit_hp;
    public int mp;
    public int limit_mp;
    public int attack;
    public int defence;
    public int up_leberu;
    public String name;
    public int leberu;
    public boolean is_alive;
    public int judge_sente;
    public Boolean fellow;
    public int can_get_experince_point;
    public int have_experince_point = 0;
    public int need_experince_point = 0;
    public Skill use_skill;
    public ArrayList<Skill> all_skill =new ArrayList<>();
    public FightItem have_item =null;
    public ArrayList<TextView> display_skill =new ArrayList<>();
    public int[] monster_drawable_usually;
    public int[] monster_drawable_damage_enemy;
    public int[] monster_drawable_damage_ally;

    public static Monster2 getMonsterRandomly() {
        Random random = new Random();
        ArrayList<Monster2> monster2List = new ArrayList<>();
        setNewMonster(monster2List);
        monster2List = addMonster2List(monster2List);
        int randomNum = random.nextInt(monster2List.size());
        decideFirstMonster(randomNum);
        return monster2List.get(randomNum);
    }
    public static void setNewMonster(ArrayList<Monster2> monster2List){
        dragon_king = new DragonKing();
        metal_slime = new MetalSlime();
        puti_slime = new PutiSlime();
        gorlem = new Gorlem();
        monster2List.add(dragon_king);
        monster2List.add(metal_slime);
        monster2List.add(puti_slime);
        monster2List.add(gorlem);
    }
    public static ArrayList<Monster2> addMonster2List(ArrayList<Monster2> monster2List){
        monster2List.add(dragon_king);
        monster2List.add(metal_slime);
        monster2List.add(puti_slime);
        monster2List.add(gorlem);
        return monster2List;
    }
    public static void setImageResource(){
        if (monster_cara_now.equals("dragon_king")){
            if (game.enemy_monster.monster_place.equals("over")) {
                game.enemy_monster.image.setImageResource(R.drawable.dragon_king);
            } else if (game.enemy_monster.monster_place.equals("right")) {
                game.enemy_monster.image.setImageResource(R.drawable.dragon_king_right);
            } else if (game.enemy_monster.monster_place.equals("left")) {
                game.enemy_monster.image.setImageResource(R.drawable.dragon_king_left);
            } else {
                game.enemy_monster.image.setImageResource(R.drawable.dragon_king_under);
            }
        }else if (monster_cara_now.equals("metal_slime")){
            if (game.enemy_monster.monster_place.equals("over")) {
                game.enemy_monster.image.setImageResource(R.drawable.metal_slime);
            } else if (game.enemy_monster.monster_place.equals("right")) {
                game.enemy_monster.image.setImageResource(R.drawable.metal_slime_right);
            } else if (game.enemy_monster.monster_place.equals("left")) {
                game.enemy_monster.image.setImageResource(R.drawable.metal_slime_left);
            } else {
                game.enemy_monster.image.setImageResource(R.drawable.metal_slime_under);
            }
        }else if (monster_cara_now.equals("puti_slime")){
            if (game.enemy_monster.monster_place.equals("over")) {
                game.enemy_monster.image.setImageResource(R.drawable.puti_slime);
            } else if (game.enemy_monster.monster_place.equals("right")) {
                game.enemy_monster.image.setImageResource(R.drawable.puti_slime_right);
            } else if (game.enemy_monster.monster_place.equals("left")) {
                game.enemy_monster.image.setImageResource(R.drawable.puti_slime_left);
            } else {
                game.enemy_monster.image.setImageResource(R.drawable.puti_slime_under);
            }
        }else {
            if (game.enemy_monster.monster_place.equals("over")) {
                game.enemy_monster.image.setImageResource(R.drawable.gorlem);
            } else if (game.enemy_monster.monster_place.equals("right")) {
                game.enemy_monster.image.setImageResource(R.drawable.gorlem_right);
            } else if (game.enemy_monster.monster_place.equals("left")) {
                game.enemy_monster.image.setImageResource(R.drawable.gorlem_left);
            } else {
                game.enemy_monster.image.setImageResource(R.drawable.gorlem_under);
            }
        }
    }
    public static void decideFirstMonster(int randomNum){
        if (monster_cara_now == null) {
            if (randomNum == 0) {
                monster_cara_now = "dragon_king";
            } else if (randomNum == 1) {
                monster_cara_now = "metal_slime";
            } else if (randomNum == 2) {
                monster_cara_now = "puti_slime";
            } else {
                monster_cara_now = "gorlem";
            }
        }
    }
}