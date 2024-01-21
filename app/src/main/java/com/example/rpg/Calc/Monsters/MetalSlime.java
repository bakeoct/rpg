package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;

import android.icu.text.RelativeDateTimeFormatter;

import com.example.rpg.Calc.skill.Hit;
import com.example.rpg.Calc.skill.LittleFire;
import com.example.rpg.Calc.skill.Throw;
import com.example.rpg.R;

import java.io.Serializable;

public class MetalSlime extends Monster2  implements Serializable{
    public static MetalSlime metal_slime = new MetalSlime();
    public MetalSlime(){
        this.limit_hp=200;
        this.limit_mp=80000;
        this.defence=0;
        this.up_leberu=0;
        this.hp = 200;
        this.leberu = 1;
        this.attack=6;
        this.mp=80000;
        this.judge_sente=20000000;
        this.name="メタルスライム";
        this.seibetu="?";
        this.is_alive=true;
        this.fellow=true;
        this.can_get_experince_point = 500;
        this.need_experince_point = 100;
        this.all_skill.add(hit_attack);
        this.all_skill.add(throw_attack);
        this.all_skill.add(little_fire);
        this.monster_drawable_usually = new int[]{R.drawable.metal_slime,R.drawable.metal_slime_left,R.drawable.metal_slime_right,R.drawable.metal_slime_under};
        this.monster_drawable_damage_enemy = new int[]{R.drawable.metal_slime_left_damage};
        this.monster_drawable_damage_ally = new int[]{R.drawable.metal_slime_right_damage};
    }
    public static String look(Monster2 monster){
        return monster.name;
    }
}
