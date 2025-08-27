package com.example.rpg.Calc.Entity.Monsters;

import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.R;

import java.io.Serializable;

public class DragonKing extends Monster implements Serializable {
    public DragonKing() {
        this.detection_distance = 300;
        this.frequency_move_bound = 1000;
        this.minimum_frequency_move = 66;
        this.hp=2000;
        this.mp=200;
        this.limit_hp=2000;
        this.limit_mp=200;
        this.defence=0;
        this.judge_sente=7;
        this.name="dragon_king";
        this.attack=3000;
        this.up_leberu=0;
        this.leberu=1;
        this.is_alive=true;
        this.fellow=false;
        this.can_get_experince_point = 1000;
        this.need_experince_point = 300;
        this.all_skill.add(hit_attack);
        this.all_skill.add(little_fire);
        speed = 5;
        this.monster_drawable = new int[]{R.drawable.dragon_king,R.drawable.dragon_king_left,R.drawable.dragon_king_right,R.drawable.dragon_king_under};

    }
    public static String look(Monster monster){
        return monster.name;
    }
    public Boolean change(){
        return this.fellow=true;
    }
}
