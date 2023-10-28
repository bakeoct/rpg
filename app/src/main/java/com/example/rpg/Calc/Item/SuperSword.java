package com.example.rpg.Calc.Item;

import com.example.rpg.R;

import java.io.Serializable;

public class SuperSword extends FightItem implements Serializable {
    public SuperSword(){
        this.name="すごい剣";
        this.buy_price=50;
        this.sell_price=25;
        this.code="supersword";
        this.item_lv = 1;
        this.up_attack = 10000;
        this.item_group = "attack";
        this.can_hold = false;

    }
}
