package com.example.rpg.Calc.Entity.Monsters;

import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.R;

import java.io.Serializable;

public class PutiSlime extends Monster implements Serializable {
    public PutiSlime(){
        this.detection_distance = 300;
        this.frequency_move_bound = 2000;
        this.minimum_frequency_move = 66;
        this.limit_hp=3980;
        this.limit_mp=700;
        this.defence=0;
        this.up_leberu=0;
        this.leberu = 1;
        this.hp = 3980;
        this.attack=5000;
        this.mp=700;
        this.judge_sente=7;
        this.name="puti_slime";
        this.is_alive=true;
        this.fellow=false;
        this.can_get_experince_point = 2000;
        this.need_experince_point = 50;
        this.all_skill.add(hit_attack);
        this.all_skill.add(throw_attack);
        this.all_skill.add(little_fire);
        speed = 5;
        this.monster_drawable = new int[]{R.drawable.puti_slime,R.drawable.puti_slime_left,R.drawable.puti_slime_right,R.drawable.puti_slime_under};
    }
    public static String look(Monster monster){
        return monster.name;
    }
}
