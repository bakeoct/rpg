package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;

import com.example.rpg.Calc.skill.Hit;
import com.example.rpg.Calc.skill.LittleFire;
import com.example.rpg.Calc.skill.Throw;
import com.example.rpg.R;

import java.io.Serializable;

public class PutiSlime extends Monster2 implements Serializable {
    public static PutiSlime puti_slime = new PutiSlime();
    public PutiSlime(){
        this.limit_hp=3980;
        this.limit_mp=700;
        this.defence=0;
        this.up_leberu=0;
        this.leberu = 1;
        this.hp = 3980;
        this.attack=500000;
        this.mp=700;
        this.judge_sente=7;
        this.name="プチスライム";
        this.is_alive=true;
        this.fellow=false;
        this.can_get_experince_point = 2000;
        this.need_experince_point = 50;
        this.all_skill.add(hit_attack);
        this.all_skill.add(throw_attack);
        this.all_skill.add(little_fire);
        this.monster_drawable_usually = new int[]{R.drawable.puti_slime,R.drawable.puti_slime_left,R.drawable.puti_slime_right,R.drawable.puti_slime_under};
        this.monster_drawable_damage_enemy = new int[]{R.drawable.puti_slime_left_damage};
        this.monster_drawable_damage_ally = new int[]{R.drawable.puti_slime_right_damage};
    }
    public static String look(Monster2 monster){
        return monster.name;
    }
}
