package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;

import com.example.rpg.Calc.skill.Hit;
import com.example.rpg.Calc.skill.LittleFire;
import com.example.rpg.Calc.skill.Throw;

import java.io.Serializable;

public class PutiSlime extends Monster2 implements Serializable {
    public static PutiSlime puti_slime = new PutiSlime();
    public PutiSlime(){
        this.limit_hp=398;
        this.limit_mp=7;
        this.defence=0;
        this.up_leberu=0;
        this.leberu = 1;
        this.hp = 398;
        this.attack=50000000;
        this.mp=7;
        this.judge_sente=7;
        this.name="プチスライム";
        this.seibetu="?";
        this.is_alive=true;
        this.fellow=false;
        this.can_get_experince_point = 2000;
        this.need_experince_point = 50;
        this.all_skill.add(hit_attack);
        this.all_skill.add(throw_attack);
        this.all_skill.add(little_fire);
    }
    public static String look(Monster2 monster){
        return monster.name;
    }
}
