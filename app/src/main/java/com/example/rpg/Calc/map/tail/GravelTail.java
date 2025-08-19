package com.example.rpg.Calc.map.tail;

import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class GravelTail extends Tail {
        public GravelTail(int onIt,int flooR){
            on_it = onIt;//0=何もついてない,1=崖がついてる,2=海がついてる
            tail_id = "grave";
            floor = flooR;
            switch (on_it) {
                case 0:
                    drawable = from_activity.getResources().getDrawable(R.drawable.jyari, null);
                    break;
                case 1:
                    drawable = from_activity.getResources().getDrawable(R.drawable.cliff_on_jyari, null);
                    break;
            }
        }
}
