package com.example.rpg.Calc.Item;

import java.io.Serializable;

public abstract class Item implements Serializable {
    public String item_group;
    public int item_lv;
    public Boolean have = false;
    public String name;
    public int have_number = 0;
    public int buy_price;
    public int sell_price;
    public String code;
    public int have_point = 0;
    public boolean can_hold;
}
