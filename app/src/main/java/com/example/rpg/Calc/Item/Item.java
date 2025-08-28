package com.example.rpg.Calc.Item;

import static com.example.rpg.Calc.Game.game;

import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

import java.io.Serializable;

public abstract class Item extends AppCompatActivity implements Serializable {
    public String item_group;
    public int item_lv;
    public Boolean have = false;
    public String name;
    public int buy_price;
    public int sell_price;
    public String code;
    public int have_point = 0;
    public boolean can_hold;
    public Drawable item_drawable;
}
