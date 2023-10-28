package com.example.rpg.Calc.Item;

import com.example.rpg.R;

import java.io.Serializable;

public class Ladder extends FieldItem implements Serializable {
    public Ladder(){
        this.name="梯子";
        this.buy_price=200;
        this.sell_price=100;
        this.code="ladder";
        this.item_lv = 999999999;
        this.item_group = "move";
        this.can_hold = true;
    }
}
