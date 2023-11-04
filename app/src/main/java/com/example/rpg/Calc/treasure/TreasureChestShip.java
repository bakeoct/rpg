package com.example.rpg.Calc.treasure;

import static com.example.rpg.Calc.Game.game;

public class TreasureChestShip extends  Treasure {
    public static TreasureChestShip treasure_chest_ship = new TreasureChestShip();
    public TreasureChestShip(){
        treasure = game.store.ship;
    }
}
