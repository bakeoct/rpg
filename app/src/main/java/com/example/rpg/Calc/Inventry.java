package com.example.rpg.Calc;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Monsters.Monster2;
import static com.example.rpg.Calc.Game.game;
import java.io.Serializable;
import java.util.Scanner;

public class Inventry implements Serializable {
    public static void takeItemOfThing(Item item) {
        if (item.can_hold) {
            game.p.have_item = item;
        }else {
            System.out.println("このアイテムは持てません");
        }
    }
    public static void takeItemOfMonster(Item item,Monster2 monster){
        if (item instanceof FightItem){
            monster.have_item = (FightItem) item;
        }else {
            System.out.println("そのアイテムはモンスタ－に持たせることはできません。");
        }
    }
    public static void disposeItem(Item item){
        game.p.items.remove(item);
    }
    public static void haveNoThing(){
        game.p.have_item = null;
    }
}
