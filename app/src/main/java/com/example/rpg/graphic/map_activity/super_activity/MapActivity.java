package com.example.rpg.graphic.map_activity.super_activity;

import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.Calc.controlView.DrawView;
import com.example.rpg.Calc.controlView.JoyStickView;
import com.example.rpg.Calc.map.tail.Tail;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class MapActivity extends AppCompatActivity implements Serializable {
    public ArrayList<Monster> monster_on_map = new ArrayList<>();
    public FrameLayout entity_map;
    public int max_monster_num;
    public JoyStickView joy_stick;
    public DrawView draw_map_view;
    public Tail[][] map;
}
