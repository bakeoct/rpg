package com.example.rpg.Calc;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Monsters.Monster2;
import static com.example.rpg.Calc.Game.game;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Scanner;

public class Inventry implements Serializable {
    public static void takeItemOfThing(Item item, boolean when_click_player,Monster2 select_monster_now) {
        if (when_click_player) {
            if (item.can_hold) {
                game.p.have_item = item;
            }
        }else if (item instanceof FightItem && select_monster_now != null){
            select_monster_now.have_item = (FightItem) item;
        }
    }
    public static void disposeItem(Item item){
        game.p.items.remove(item);
    }
    public static void haveNoThing(){
        game.p.have_item = null;
    }
}