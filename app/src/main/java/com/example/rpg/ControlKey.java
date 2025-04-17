package com.example.rpg;

import static com.example.rpg.Calc.Game.game;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ControlKey extends AppCompatActivity implements Serializable {
    public Button right;
    public Button left;
    public Button over;
    public Button under;
    public ImageView setting;
    public ImageView do_button;
    public ImageView inventory_button;
    public ControlKey(Activity activity){
        right = activity.findViewById(R.id.right);
        over = activity.findViewById(R.id.over);
        under = activity.findViewById(R.id.under);
        left = activity.findViewById(R.id.left);
        setting = activity.findViewById(R.id.setting);
        do_button = activity.findViewById(R.id.do_button);
        inventory_button = activity.findViewById(R.id.inventory_button);
        game.p.image = activity.findViewById(R.id.hero);
        game.enemy_monster.image = activity.findViewById(R.id.enemy_monster);
        game.map.grid_layout_map = activity.findViewById(R.id.map);
    }
}
