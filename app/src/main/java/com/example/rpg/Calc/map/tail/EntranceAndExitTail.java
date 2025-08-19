package com.example.rpg.Calc.map.tail;

import static com.example.rpg.graphic.TransitionActivity.from_activity;
import com.example.rpg.R;

public class EntranceAndExitTail extends Tail {
    public EntranceAndExitTail(int what_kind_of, String d,int flooR) {
        wko = what_kind_of;//EntranceAndExitの例:wko=what_kind_of(何の種類の入口？),0=洞窟,1=民家,2=店
        distinction = d;//例:"to_WorldMap","to_cave1"
        tail_id = "entrance_exit";
        floor = flooR;
        switch (wko) {
            case 0:
                drawable = from_activity.getResources().getDrawable(R.drawable.cave_entrance, null);
                break;
            case 1:
                drawable = from_activity.getResources().getDrawable(R.drawable.door_wood_1, null);
                break;
            case 2:
                drawable = from_activity.getResources().getDrawable(R.drawable.store, null);
        }
    }
}