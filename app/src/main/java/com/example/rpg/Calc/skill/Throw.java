package com.example.rpg.Calc.skill;

import java.io.Serializable;

public class Throw extends Skill implements Serializable {
    public static Throw throw_attack = new Throw();
    public Throw(){
        this.name = "投げる";
        this.code = "throw";
        this.offensive_power = 50;
        this.consumption_mp = 20;
        this.long_or_short = "short";
    }
}
