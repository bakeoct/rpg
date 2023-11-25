package com.example.rpg.Calc;

import static com.example.rpg.Calc.Game.game;
import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.Mission.MissionSab;
import com.example.rpg.Calc.Monsters.*;
import com.example.rpg.Calc.map.World_map;
import com.example.rpg.R;

import static com.example.rpg.Calc.Monsters.Gorlem.gorlem;
import static com.example.rpg.Calc.Monsters.MetalSlime.metal_slime;
import static com.example.rpg.graphic.GameActivity.place;

import android.widget.GridLayout;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class Person2 implements Serializable {
    public ArrayList<Monster2> monsters2 =new ArrayList<Monster2>();
    public int lv=1;
    public int have_experince_point = 0;
    public int need_experince_point = 100;
    public ArrayList<FieldItem> field_items =new ArrayList<FieldItem>();
    public ArrayList<MonsterItem> monster_items =new ArrayList<MonsterItem>();
    public ArrayList<FightItem> fight_items =new ArrayList<FightItem>();
    public ArrayList<Item> items =new ArrayList<>();
    public int money=100;
    public Item have_item = null;
    public String area = "メインマップ";
    public int x=12;
    public int y=6;
    public int serve_x = 12;
    public int serve_y = 6;
    public int choose_item;
    public Person2() {
        this.monsters2.add(metal_slime);
        this.monsters2.add(gorlem);
        items.addAll(field_items);
        items.addAll(fight_items);
        items.addAll(monster_items);
    }
    public void walk(ImageView yuusya){
        if (place.equals("right")){
            this.x++;
            yuusya.setImageResource(R.drawable.yuusya_right);
        }else if (place.equals("left")) {
            this.x--;
            yuusya.setImageResource(R.drawable.yuusya_left);
        }else if (place.equals("under")){
            this.y++;
            yuusya.setImageResource(R.drawable.yuusya);
        }else if (place.equals("over")) {
            this.y--;
            yuusya.setImageResource(R.drawable.yuusya_usiro);
        }
    }
    public void graphic_walk(GridLayout gridLayout,ImageView yuusya,Person2 p,int image_size){
            yuusya.setX(gridLayout.getX() + image_size * p.x);
            yuusya.setY(gridLayout.getY() + image_size * p.y);
    }
}
