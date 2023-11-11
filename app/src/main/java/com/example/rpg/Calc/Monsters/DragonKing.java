package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.Game.game;

import com.example.rpg.Calc.skill.Hit;
import com.example.rpg.Calc.skill.LittleFire;

import java.io.Serializable;

public class DragonKing extends Monster2 implements Serializable {

    public DragonKing() {
        this.hp=20000000;
        this.mp=2;
        this.limit_hp=20000000;
        this.limit_mp=2;
        this.defence=0;
        this.judge_sente=7000;
        this.name="竜王";
        this.attack=300000000;
        this.seibetu="男性";
        this.up_leberu=0;
        this.leberu=1;
        this.is_alive=true;
        this.fellow=false;
        this.can_get_experince_point = 1000;
        this.need_experince_point = 300;
        this.all_skill.add(game.hit_attack);
        this.all_skill.add(game.little_fire);
    }
    public static String look(Monster2 monster){
        return monster.name;
    }
    public Boolean change(){
        return this.fellow=true;
    }
}
