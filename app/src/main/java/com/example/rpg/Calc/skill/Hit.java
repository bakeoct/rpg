package com.example.rpg.Calc.skill;

import java.io.Serializable;

public class Hit extends Skill implements Serializable {
    public Hit(){
        this.name = "殴る";
        this.code = "hit";
        this.offensive_power = 20;
        this.consumption_mp = 10;
        this.long_or_short = "short";
    }
}
