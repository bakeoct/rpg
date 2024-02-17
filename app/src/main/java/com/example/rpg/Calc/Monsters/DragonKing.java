package com.example.rpg.Calc.Monsters;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;

import com.example.rpg.Calc.skill.Hit;
import com.example.rpg.Calc.skill.LittleFire;
import com.example.rpg.R;

import java.io.Serializable;

public class DragonKing extends Monster2 implements Serializable {
    public static DragonKing dragon_king = new DragonKing();
    public DragonKing() {
        this.hp=2000;
        this.mp=200;
        this.limit_hp=2000;
        this.limit_mp=200;
        this.defence=0;
        this.judge_sente=7;
        this.name="竜王";
        this.attack=3000000;
        this.up_leberu=0;
        this.leberu=1;
        this.is_alive=true;
        this.fellow=false;
        this.can_get_experince_point = 1000;
        this.need_experince_point = 300;
        this.all_skill.add(hit_attack);
        this.all_skill.add(little_fire);
        this.monster_drawable_usually = new int[]{R.drawable.dragon_king,R.drawable.dragon_king_left,R.drawable.dragon_king_right,R.drawable.dragon_king_under};
        this.monster_drawable_damage_enemy = new int[]{R.drawable.dragon_king_left_damage};
        this.monster_drawable_damage_ally = new int[]{R.drawable.dragon_king_right_damage};
    }
    public static String look(Monster2 monster){
        return monster.name;
    }
    public Boolean change(){
        return this.fellow=true;
    }
}
