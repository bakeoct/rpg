package com.example.rpg.Calc.map.tail;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class GravelTail extends Tail {
        public GravelTail(int onIt,int need__item){
            on_it = onIt;//0=何もついてない,1=崖がついてる,2=海がついてる
            tail_id = "grave";
            need_item = need__item;
            switch (on_it) {
                case 0:
                    image.setImageDrawable(this.getResources().getDrawable(R.drawable.jyari, null));
                    break;
                case 1:
                    image.setImageDrawable(this.getResources().getDrawable(R.drawable.cliff_on_jyari, null));
                    break;
            }
            image.setLayoutParams(layout_params);
        }
}
