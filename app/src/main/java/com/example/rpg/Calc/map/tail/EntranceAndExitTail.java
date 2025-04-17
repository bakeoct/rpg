package com.example.rpg.Calc.map.tail;

import static com.example.rpg.Calc.Game.game;

import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class EntranceAndExitTail extends Tail {
    public EntranceAndExitTail(int what_kind_of, String d) {
        wko = what_kind_of;//EntranceAndExitの例:wko=what_kind_of(何の種類の入口？),0=洞窟,1=民家,2=店
        distinction = d;//例:"to_WorldMap","to_cave1"
        tail_id = "entrance_exit";
        switch (wko) {
            case 0:
                image.setImageDrawable(this.getResources().getDrawable(R.drawable.cave_entrance, null));
                break;
            case 1:
                image.setImageDrawable(this.getResources().getDrawable(R.drawable.door_wood_1, null));
                break;
            case 2:
                image.setImageDrawable(this.getResources().getDrawable(R.drawable.store, null));
        }
        image.setLayoutParams(layout_params);
    }
}