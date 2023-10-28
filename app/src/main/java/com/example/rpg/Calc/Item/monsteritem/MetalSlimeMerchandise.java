package com.example.rpg.Calc.Item.monsteritem;

import com.example.rpg.Calc.Item.MonsterItem;
import com.example.rpg.R;

import java.io.Serializable;

public class MetalSlimeMerchandise  extends MonsterItem implements Serializable {
    public MetalSlimeMerchandise(){
        this.name="メタルスライム";
        this.buy_price=300;
        this.sell_price=150;
        this.code="metalslime";
        this.item_lv = 1;
        this.item_group = "monster";
        this.can_hold = false;
    }
}
