package com.example.rpg.Calc.Item;

import java.io.Serializable;

public class Ship extends FieldItem implements Serializable {
    public Ship(){
        this.name="船";
        this.buy_price=200;
        this.sell_price=100;
        this.code="ship";
        this.item_lv = 999999999;
        this.item_group = "move";
        this.can_hold = true;
    }
}
