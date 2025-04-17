package com.example.rpg.Calc.map.tail;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.treasure.TreasureChestLadder.treasure_chest_ladder;
import static com.example.rpg.Calc.treasure.TreasureChestShip.treasure_chest_ship;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class TreasureBoxTail extends Tail {
    public int[] treasure_images = {R.drawable.open_treasure_chest, R.drawable.treasure_chest}; // 切り替える画像リソース
    public int chenge_treasure = 0;
    public final Handler HANDLER = new Handler();
    public final Runnable OPEN_TREASURE = new Runnable() {
        @Override
        public void run() {
            if (chenge_treasure < 2) {
                image.setImageResource(treasure_images[chenge_treasure]);
                if (chenge_treasure == 0) {
                    try {
                        if (game.map.map_of_treasure[game.p.mpy][game.p.mpx].distinction.equals("people_home1_1")) {
                            treasure_chest_ship.openTreasureChest();
                        }
                    }catch (ArrayIndexOutOfBoundsException e){}
                    try {
                        if (game.map.map_of_treasure[game.p.mpy][game.p.mpx].distinction.equals("cave1_1_1")) {
                            treasure_chest_ladder.openTreasureChest();
                        }
                    }catch (ArrayIndexOutOfBoundsException e){}
                }
                chenge_treasure++;
                HANDLER.postDelayed(OPEN_TREASURE, 1000); // 1秒間隔で実行
            } else {
                chenge_treasure = 0;
            }
        }
    };
    public TreasureBoxTail(int what_kind_of, String d){
        wko = what_kind_of;//treasureBoxの例:wko=what_kind_of(何の種類の入口？),例(実装するかは別)：0=普通の宝箱,1=ミミック,2=高級宝箱
        distinction = d;//例:"people_home1","cave1_1"
        tail_id = "treasure_box";
        switch (wko) {
            case 0:
                image.setImageDrawable(this.getResources().getDrawable(R.drawable.treasure_chest, null));
                break;
        }
        image.setLayoutParams(layout_params);
    }
}
