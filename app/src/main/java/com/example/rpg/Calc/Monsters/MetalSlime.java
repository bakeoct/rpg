package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.Game.game;

import com.example.rpg.Calc.skill.Hit;
import com.example.rpg.Calc.skill.LittleFire;
import com.example.rpg.Calc.skill.Throw;

import java.io.Serializable;

public class MetalSlime extends Monster2  implements Serializable{

    public MetalSlime(){
        this.limit_hp=50;
        this.limit_mp=80000;
        this.defence=0;
        this.up_leberu=0;
        this.hp = 50;
        this.leberu = 1;
        this.attack=6000;
        this.mp=80000;
        this.judge_sente=20;
        this.name="メタルスライム";
        this.seibetu="?";
        this.is_alive=true;
        this.fellow=true;
        this.can_get_experince_point = 500;
        this.need_experince_point = 100;
        this.all_skill.add(game.hit_attack);
        this.all_skill.add(game.throw_attack);
        this.all_skill.add(game.little_fire);
    }
    public static String look(Monster2 monster){
        return monster.name;
    }
}
