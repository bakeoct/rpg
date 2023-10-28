package com.example.rpg.Calc.treasure;

import static com.example.rpg.Calc.Game.game;

public class TreasureChestLadder extends Treasure{
    public static TreasureChestLadder treasure_chest_ladder = new TreasureChestLadder();
    public TreasureChestLadder(){
        treasure = game.store.ladder;
    }
}
