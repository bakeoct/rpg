package com.example.rpg.Calc.controlView;

import static com.example.rpg.Calc.Game.game;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;
import com.example.rpg.graphic.map_activity.super_activity.MapActivity;

import java.io.Serializable;

public class ControlView extends AppCompatActivity implements Serializable {

    public JoyStickView joy_stick;
    public ImageView setting;
    public ImageView do_button;
    public ImageView inventory_button;
    public ControlView(MapActivity activity){
        joy_stick = activity.findViewById(R.id.joystickView);
        setting = activity.findViewById(R.id.setting);
        do_button = activity.findViewById(R.id.do_button);
        inventory_button = activity.findViewById(R.id.inventory_button);
        game.player.image = activity.findViewById(R.id.hero);
        activity.entity_map = activity.findViewById(R.id.entityMap);
        activity.draw_map_view = activity.findViewById(R.id.drawMap);
    }
}
