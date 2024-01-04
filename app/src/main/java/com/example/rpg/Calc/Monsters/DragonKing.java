package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;

import com.example.rpg.Calc.skill.Hit;
import com.example.rpg.Calc.skill.LittleFire;

import java.io.Serializable;

public class DragonKing extends Monster2 implements Serializable {
    public static DragonKing dragon_king = new DragonKing();
    public DragonKing() {
        this.hp=20000000;
        this.mp=200;
        this.limit_hp=20000000;
        this.limit_mp=200;
        this.defence=0;
        this.judge_sente=70000;
        this.name="竜王";
        this.attack=3;
        this.seibetu="男性";
        this.up_leberu=0;
        this.leberu=1;
        this.is_alive=true;
        this.fellow=false;
        this.can_get_experince_point = 1000;
        this.need_experince_point = 300;
        this.all_skill.add(hit_attack);
        this.all_skill.add(little_fire);
    }
    public static String look(Monster2 monster){
        return monster.name;
    }
    public Boolean change(){
        return this.fellow=true;
    }
}
