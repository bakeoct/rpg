package com.example.rpg.Calc.map.tail;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class ErrorTail extends Tail {
    public ErrorTail(){
        tail_id = "error";
        image.setImageDrawable(this.getResources().getDrawable(R.drawable.errerzone, null));
        image.setLayoutParams(layout_params);
    }
}
