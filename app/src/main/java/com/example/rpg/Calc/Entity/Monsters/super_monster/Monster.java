package com.example.rpg.Calc.Entity.Monsters.super_monster;

import android.widget.TextView;

import com.example.rpg.Calc.Entity.Entity;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.skill.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Monster extends Entity implements Serializable {
    public float detection_distance;
    public float distance_from_player;
    public long minimum_frequency_move;
    public int frequency_move_bound;
    public int mp;
    public int limit_mp;
    public int defence;
    public int up_leberu;
    public String name;
    public int leberu;
    public boolean is_alive;
    public int judge_sente;
    public Boolean fellow;
    public int can_get_experince_point;
    public int have_experince_point;
    public int need_experince_point;
    public Skill use_skill;
    public ArrayList<Skill> all_skill =new ArrayList<>();
    public FightItem have_item =null;
    public ArrayList<TextView> display_skill =new ArrayList<>();
    public int[] monster_drawable;
    public long getFrequencyMove() {return minimum_frequency_move + new Random().nextInt(frequency_move_bound);}
}