package com.example.rpg.Calc;

import com.example.rpg.Calc.Entity.Player;
import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;

import java.io.Serializable;

public class Level implements Serializable {
    public void upLevel(Player p){
        while (true) {
            boolean endflg = true;
            if (p.have_experience_point >= p.need_experience_point) {
                p.lv++;
                p.have_experience_point -= p.need_experience_point;
                p.need_experience_point *= 2;
                endflg = false;
            }
            for (Monster monster2 : p.monsters2) {
                if (monster2.have_experince_point >= monster2.need_experince_point) {
                    monster2.up_leberu++;
                    monster2.leberu++;
                    monster2.have_experince_point -= monster2.need_experince_point;
                    monster2.need_experince_point *= 2;
                    endflg = false;
                }
            }
            if (endflg) {
                break;
            }
        }
        for (Monster monster2 : p.monsters2) {
            for (int i = 0; i < monster2.up_leberu; i++) {
                monster2.hp *= 1.5;
                monster2.mp *= 1.5;
                monster2.attack *= 1.3;
                monster2.defence *= 1.3;
                monster2.judge_sente *= 1.3;
            }
            monster2.up_leberu = 0;
        }
    }
}
