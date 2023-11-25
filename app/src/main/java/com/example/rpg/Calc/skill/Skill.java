package com.example.rpg.Calc.skill;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public abstract class Skill {
    public int offensive_power;
    public int consumption_mp;
    public String long_or_short;
    public String name;
    public String code;
    public ArrayList<Drawable> effect_drawable = new ArrayList<>();
}
