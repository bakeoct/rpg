package com.example.rpg.Calc;

import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Monsters.*;
import com.example.rpg.R;

import static com.example.rpg.Calc.Monsters.Gorlem.gorlem;
import static com.example.rpg.Calc.Monsters.MetalSlime.metal_slime;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    public ArrayList<Monster2> monsters2 =new ArrayList<>();
    public String place = "over";
    public int lv=1;
    public int have_experience_point = 0;
    public int need_experience_point = 100;
    public ArrayList<FieldItem> field_items =new ArrayList<>();
    public ArrayList<MonsterItem> monster_items =new ArrayList<>();
    public ArrayList<FightItem> fight_items =new ArrayList<>();
    public ArrayList<Item> items =new ArrayList<>();
    public boolean repeat_flg = false;
    public int money=100;
    public Item have_item = null;
    public String area = "メインマップ";
    public int mpx=12;
    public int mpy=6;
    public int serve_x = 12;
    public int serve_y = 6;
    public int x = 0;
    public int y = 0;
    public int choose_item = 0;
    public View image = null;
    public Person() {
        this.monsters2.add(metal_slime);
        this.monsters2.add(gorlem);
        this.items.addAll(field_items);
        this.items.addAll(fight_items);
        this.items.addAll(monster_items);
    }
    public void mpWalk(){
        if (place.equals("right")){
            this.mpx++;

        }else if (place.equals("left")) {
            this.mpx--;

        }else if (place.equals("under")){
            this.mpy++;

        }else if (place.equals("over")) {
            this.mpy--;

        }
    }
    public void walk(ImageView image){
        byte speed = 5;
        switch (place){
            case "right":
                this.x += speed;
                image.setImageResource(R.drawable.yuusya_right);
                break;
            case "left":
                this.x -= speed;
                image.setImageResource(R.drawable.yuusya_left);
                break;
            case "under":
                this.y += speed;
                image.setImageResource(R.drawable.yuusya);
                break;
            case "over":
                this.y -= speed;
                image.setImageResource(R.drawable.yuusya_usiro);
                break;
        }
        image.setX(this.x);
        image.setY(this.y);
    }
}
