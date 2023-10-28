package com.example.rpg.Calc;

import com.example.rpg.Calc.Item.HealGlass;
import com.example.rpg.Calc.Item.Ladder;
import com.example.rpg.Calc.Item.Ship;
import com.example.rpg.Calc.Item.SteelArmor;
import com.example.rpg.Calc.Item.SuperSword;
import com.example.rpg.Calc.Item.monsteritem.DragonKingMerchandise;
import com.example.rpg.Calc.Item.monsteritem.GorlemMerchandise;
import com.example.rpg.Calc.Item.monsteritem.MetalSlimeMerchandise;
import com.example.rpg.Calc.Item.monsteritem.PutiSlimeMerchandise;

import junit.framework.TestCase;

public class StoreTest extends TestCase {
    public void testGetAfterMoney(){
        HealGlass healGlass = new HealGlass();
        Ship ship =new Ship();
        SteelArmor steelArmor = new SteelArmor();
        SuperSword superSword = new SuperSword();
        Ladder ladder = new Ladder();
        DragonKingMerchandise dragonKingMerchandise = new DragonKingMerchandise();
        GorlemMerchandise gorlemMerchandise = new GorlemMerchandise();
        PutiSlimeMerchandise putiSlimeMerchandise = new PutiSlimeMerchandise();
        MetalSlimeMerchandise metalSlimeMerchandise = new MetalSlimeMerchandise();
        assertTrue(Store.getAfterMoney(healGlass,1));
        assertFalse(Store.getAfterMoney(ship,1));
        assertTrue(Store.getAfterMoney(steelArmor,1));
        assertFalse(Store.getAfterMoney(superSword,1));
        assertFalse(Store.getAfterMoney(ladder,1));
        assertFalse(Store.getAfterMoney(dragonKingMerchandise,1));
        assertFalse(Store.getAfterMoney(gorlemMerchandise,1));
        assertFalse(Store.getAfterMoney(putiSlimeMerchandise,1));
        assertFalse(Store.getAfterMoney(metalSlimeMerchandise,1));
    }
    public void testBuyMessage(){
        HealGlass healGlass = new HealGlass();
        Ship ship =new Ship();
        SteelArmor steelArmor = new SteelArmor();
        SuperSword superSword = new SuperSword();
        Ladder ladder = new Ladder();
        DragonKingMerchandise dragonKingMerchandise = new DragonKingMerchandise();
        GorlemMerchandise gorlemMerchandise = new GorlemMerchandise();
        PutiSlimeMerchandise putiSlimeMerchandise = new PutiSlimeMerchandise();
        MetalSlimeMerchandise metalSlimeMerchandise = new MetalSlimeMerchandise();
        assertEquals(Store.getItems(healGlass,1),1);
        assertEquals(Store.getItems(ship,1),1);
        assertEquals(Store.getItems(steelArmor,1),1);
        assertEquals(Store.getItems(superSword,1),1);
        assertEquals(Store.getItems(ladder,1),1);
        assertEquals(Store.getItems(dragonKingMerchandise,1),1);
        assertEquals(Store.getItems(gorlemMerchandise,1),1);
        assertEquals(Store.getItems(putiSlimeMerchandise,1),1);
        assertEquals(Store.getItems(metalSlimeMerchandise,1),1);
    }
}