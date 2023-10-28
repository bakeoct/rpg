package com.example.rpg.Calc.Item.monsteritem;

import com.example.rpg.Calc.Item.MonsterItem;
import com.example.rpg.R;

import java.io.Serializable;

public class DragonKingMerchandise  extends MonsterItem implements Serializable {
    public DragonKingMerchandise(){
        this.name="竜王";
        this.buy_price=1000;
        this.sell_price=500;
        this.code="dragonking";
        this.item_lv = 1;
        this.item_group = "monster";
        this.can_hold = false;

    }
}
