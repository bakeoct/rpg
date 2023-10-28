package com.example.rpg.Calc.treasure;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Person2.p;
import static com.example.rpg.Calc.Sound.OPEN_TREASURE_CHEST_AUDIO;
import static com.example.rpg.Calc.Sound.startAudio;
import static com.example.rpg.Calc.treasure.TreasureChestLadder.treasure_chest_ladder;

import android.os.Handler;
import android.widget.ImageView;

import com.example.rpg.Calc.Item.FieldItem;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Item.MonsterItem;
import com.example.rpg.Calc.Person2;
import com.example.rpg.R;

import java.io.File;
import java.util.Scanner;

public abstract class Treasure {
    protected Item treasure = null;
    protected boolean open_history = false;

    public void openTreasureChest(File audio_file, Person2 p) {
        if (this.open_history) {
        } else {
            this.treasure.have = true;
            this.treasure.have_point++;
            this.open_history = true;
            startAudio(audio_file);
            if (this.treasure instanceof FightItem) {
                p.fight_items.add((FightItem) this.treasure);
            } else if (this.treasure instanceof FieldItem) {
                p.field_items.add((FieldItem) this.treasure);
            } else {
                p.monster_items.add((MonsterItem) this.treasure);
            }
            p.items.add(this.treasure);
        }
    }
}
