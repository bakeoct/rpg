package com.example.rpg.Calc.Entity.Monsters;

import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.Throw.throw_attack;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.R;

import java.io.Serializable;

public class Gorlem extends Monster implements Serializable {
    public Gorlem(){
        this.detection_distance = 300;
        this.frequency_move_bound = 2000;
        this.minimum_frequency_move = 150;
        this.limit_hp=9;
        this.limit_mp=3000;
        this.defence=0;
        this.up_leberu=0;
        this.leberu = 1;
        this.hp=9;
        this.all_skill.add(throw_attack);
        this.attack=700;
        this.mp=3000;
        this.judge_sente=1;
        this.name="golem";
        this.is_alive=true;
        this.fellow=true;
        this.can_get_experince_point = 3000;
        this.need_experince_point = 300;
        this.all_skill.add(hit_attack);
        speed = 5;
        this.monster_drawable = new int[]{R.drawable.gorlem,R.drawable.gorlem_left,R.drawable.gorlem_right,R.drawable.gorlem_under};
    }
    public static String look(Monster monster){
        return monster.name;
    }
}
