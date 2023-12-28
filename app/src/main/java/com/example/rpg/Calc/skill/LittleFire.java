package com.example.rpg.Calc.skill;

import com.example.rpg.R;

import java.io.Serializable;

public class LittleFire extends Skill implements Serializable {
    public static LittleFire little_fire = new LittleFire();
    public LittleFire(){
        this.name = "炎(小)";
        this.code = "little_fire";
        this.offensive_power = 40;
        this.consumption_mp = 40;
        this.long_or_short = "short";
        this.effect_drawable = new int[]{R.drawable.fire_1,R.drawable.fire_2};
    }
}
