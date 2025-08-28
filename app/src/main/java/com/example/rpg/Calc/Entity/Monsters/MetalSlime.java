package com.example.rpg.Calc.Entity.Monsters;

import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.R;

import java.io.Serializable;

public class MetalSlime extends Monster implements Serializable{
    public MetalSlime(){
        this.detection_distance = 300;
        this.frequency_move_bound = 2000;
        this.minimum_frequency_move = 100;
        this.limit_hp=2;
        this.limit_mp=8000;
        this.defence=0;
        this.up_leberu=0;
        this.hp = 2;
        this.leberu = 1;
        this.attack=6000;
        this.mp=8000;
        this.judge_sente=20;
        this.name="metal_slime";
        this.is_alive=true;
        this.fellow=true;
        this.can_get_experince_point = 500;
        this.need_experince_point = 100;
        this.all_skill.add(hit_attack);
        this.all_skill.add(throw_attack);
        this.all_skill.add(little_fire);
        speed = 5;
        this.monster_drawable = new int[]{R.drawable.metal_slime,R.drawable.metal_slime_left,R.drawable.metal_slime_right,R.drawable.metal_slime_under};
    }
    public static String look(Monster monster){
        return monster.name;
    }
}
