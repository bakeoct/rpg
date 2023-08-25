package com.example.rpg.Calc.Item.monsteritem;

import com.example.rpg.Calc.Item.MonsterItem;

import java.io.Serializable;

public class PutiSlimeMerchandise extends MonsterItem implements Serializable {
    public PutiSlimeMerchandise(){
        this.name="プチスライム";
        this.buy_price=100;
        this.sell_price=50;
        this.code="putislime";
        this.item_lv = 1;
        this.item_group = "monster";
        this.can_hold = false;

    }
}
