package com.example.rpg.Calc.Item;

import android.graphics.drawable.Drawable;

import com.example.rpg.R;

import java.io.Serializable;

public class HealGlass extends FightItem implements Serializable  {
    public HealGlass(){
        this.name="薬草";
        this.buy_price=10;
        this.sell_price=5;
        this.code="healglass";
        this.item_lv = 1;
        this.heal = 500;
        this.item_group = "heal";
        this.can_hold = false;

    }
}
