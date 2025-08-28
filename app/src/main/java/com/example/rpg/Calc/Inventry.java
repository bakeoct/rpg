package com.example.rpg.Calc;

import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import static com.example.rpg.Calc.Game.game;

import java.io.Serializable;

public class Inventry implements Serializable {
    public static void takeItemOfThing(Item item, boolean when_click_player, Monster select_monster_now) {
        if (when_click_player) {
            if (item.can_hold) {
                game.player.have_item = item;
            }
        }else if (item instanceof FightItem && select_monster_now != null){
            select_monster_now.have_item = (FightItem) item;
        }
    }
    public static void disposeItem(Item item){
        game.player.items.remove(item);
    }
    public static void haveNoThing(){
        game.player.have_item = null;
    }
}