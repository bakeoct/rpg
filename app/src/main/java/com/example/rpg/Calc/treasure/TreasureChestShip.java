package com.example.rpg.Calc.treasure;

import static com.example.rpg.Calc.Game.game;

import com.example.rpg.Calc.Item.Ship;

public class TreasureChestShip extends  Treasure {
    public static TreasureChestLadder treasure_chest_ship = new TreasureChestLadder();
    public TreasureChestShip(){
        treasure = game.store.ship;
    }
}
