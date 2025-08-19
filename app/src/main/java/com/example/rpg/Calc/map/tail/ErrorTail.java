package com.example.rpg.Calc.map.tail;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class ErrorTail extends Tail {
    public ErrorTail(){
        tail_id = "error";
        drawable = from_activity.getResources().getDrawable(R.drawable.errerzone, null);
    }
}
