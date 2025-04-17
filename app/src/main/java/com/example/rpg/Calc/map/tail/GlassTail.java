package com.example.rpg.Calc.map.tail;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class GlassTail extends Tail {
    public GlassTail(int onIt,int need__item){
        on_it = onIt;//0=何もついてない,1=崖がついてる,2=海がついてる
        tail_id = "glass";
        need_item = need__item;
        switch (on_it) {
            case 0:
                image.setImageDrawable(this.getResources().getDrawable(R.drawable.glass, null));
                break;
            case 1:
                image.setImageDrawable(this.getResources().getDrawable(R.drawable.cliff_on_glass, null));
                break;
        }
        image.setLayoutParams(layout_params);
    }
}
