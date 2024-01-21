package com.example.rpg.Calc.skill;

import com.example.rpg.R;

public class ShortageMP extends Skill {
    public static ShortageMP shortage_mp = new ShortageMP();
    public ShortageMP(){
        this.effect_drawable = new int[]{R.drawable.shortage_mp1,R.drawable.shortage_mp2};
    }
}
