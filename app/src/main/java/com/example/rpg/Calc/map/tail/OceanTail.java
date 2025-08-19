package com.example.rpg.Calc.map.tail;

import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class OceanTail extends Tail {
    public OceanTail(int onIt, int flooR,boolean need__ship){
        on_it = onIt;//0=何もついてない,1=崖がついてる,2=海がついてる
        tail_id = "ocean";
        floor = flooR;
        need_ship = need__ship;
        switch (on_it) {
            case 0:
                drawable = from_activity.getResources().getDrawable(R.drawable.sea, null);
                break;
        }
    }
}
