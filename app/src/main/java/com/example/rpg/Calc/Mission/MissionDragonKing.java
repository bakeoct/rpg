package com.example.rpg.Calc.Mission;

import java.io.Serializable;

public class MissionDragonKing extends Mission implements Serializable {
    public MissionDragonKing(){
        this.dangerous_lv=1;
        this.reward=300;
        this.name = "最強のドラゴンを討伐せよ！！";
        this.code = "killdragonking";
    }
}
