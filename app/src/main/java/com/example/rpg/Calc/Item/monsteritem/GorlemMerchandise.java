package com.example.rpg.Calc.Item.monsteritem;

import com.example.rpg.Calc.Item.MonsterItem;

import java.io.Serializable;

public class GorlemMerchandise  extends MonsterItem implements Serializable {
    public GorlemMerchandise(){
        this.name="ゴーレム";
        this.buy_price=200;
        this.sell_price=100;
        this.code="gorlem";
        this.item_lv = 1;
        this.item_group = "monster";
        this.can_hold = false;
    }
}
