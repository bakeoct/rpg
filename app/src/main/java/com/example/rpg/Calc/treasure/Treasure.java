package com.example.rpg.Calc.treasure;

import static com.example.rpg.Calc.Game.game;

import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.rpg.Calc.Item.FieldItem;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Item.MonsterItem;

import java.io.File;
import java.util.ArrayList;

public abstract class Treasure {
    protected Item treasure = null;
    protected boolean open_history = false;

    public void openTreasureChest() {
        if (this.open_history) {
        } else {
            this.treasure.have = true;
            this.treasure.have_point++;
            this.open_history = true;
            game.sound.startSounds("treasure_chest");
            if (this.treasure instanceof FightItem) {
                game.player.fight_items.add((FightItem) this.treasure);
            } else if (this.treasure instanceof FieldItem) {
                game.player.field_items.add((FieldItem) this.treasure);
            } else {
                game.player.monster_items.add((MonsterItem) this.treasure);
            }
            game.player.items.add(this.treasure);
        }
    }
}
