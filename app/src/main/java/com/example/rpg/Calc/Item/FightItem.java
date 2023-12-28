package com.example.rpg.Calc.Item;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class FightItem extends Item implements Serializable  {
    public int up_attack = 0;
    public int up_defence = 0;
    public int heal = 0;
    public int[] effect_drawable;
}
