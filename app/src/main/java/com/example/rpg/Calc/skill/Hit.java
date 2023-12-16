package com.example.rpg.Calc.skill;

import com.example.rpg.R;

import java.io.Serializable;

public class Hit extends Skill implements Serializable {
    public static Hit hit_attack = new Hit();
    public Hit(){
        this.name = "殴る";
        this.code = "hit";
        this.offensive_power = 20;
        this.consumption_mp = 10;
        this.long_or_short = "short";
        this.effect_drawable = new int[]{R.drawable.slashing_1,R.drawable.slashing_2,R.drawable.slashing_3,R.drawable.brack};
    }
}
