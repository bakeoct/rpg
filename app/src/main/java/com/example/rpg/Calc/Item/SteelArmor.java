package com.example.rpg.Calc.Item;

import com.example.rpg.R;

import java.io.Serializable;

public class SteelArmor extends FightItem implements Serializable {
    public SteelArmor(){
        this.name="鋼の防具";
        this.buy_price=50;
        this.sell_price=25;
        this.code="steelarmor";
        this.item_lv = 1;
        this.up_defence = 60000;
        this.item_group = "armor";
        this.can_hold = false;

    }
}
