package com.example.rpg.Calc.Monsters;

import android.widget.ImageView;

import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;
import com.example.rpg.graphic.GameActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Monster2 implements Serializable {
    public int hp;
    public int limit_hp;
    public int mp;
    public int limit_mp;
    public int attack;
    public int defence;
    public int up_leberu;
    public String name;
    public String seibetu;
    public int leberu;
    public boolean is_alive;
    public int judge_sente;
    public Boolean fellow;
    public int can_get_experince_point;
    public int have_experince_point = 0;
    public int need_experince_point = 0;
    public Skill use_skill;
    public ArrayList<Skill> all_skill =new ArrayList<>();
    public FightItem have_item =null;

    public String name() {
        return this.name;
    }

    public String seibetu() {
        return this.seibetu;
    }

    public int lv() {
        return this.leberu;
    }

    public static String look(Monster2 monster) {
        return monster.name;
    }

    public static Monster2 getMonsterRandomly(GameActivity gameActivity) {
        Random random = new Random();
        ArrayList<Monster2> monster2List = new ArrayList<>();
        monster2List.add(new DragonKing());
        monster2List.add(new MetalSlime());
        monster2List.add(new PutiSlime());
        monster2List.add(new Gorlem());
        //0~3 no random na value get
        int randomNum = random.nextInt(monster2List.size());
        setImageResource(randomNum,gameActivity);
        return monster2List.get(randomNum);
    }
    public static void setImageResource(int randomNum,GameActivity gameActivity){
        if (randomNum == 0) {
            gameActivity.enemy_monster.setImageResource(R.drawable.dragon_king);
            gameActivity.cara_now = "dragon_king";
        }else if (randomNum == 1){
            gameActivity.enemy_monster.setImageResource(R.drawable.metal_slime);
            gameActivity.cara_now = "metal_slime";
        }else if (randomNum == 2){
            gameActivity.enemy_monster.setImageResource(R.drawable.puti_slime);
            gameActivity.cara_now = "puti_slime";
        }else {
            gameActivity.enemy_monster.setImageResource(R.drawable.gorlem);
            gameActivity.cara_now = "gorlem";
        }
    }
}