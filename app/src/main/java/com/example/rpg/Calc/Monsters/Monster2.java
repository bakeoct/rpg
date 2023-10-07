package com.example.rpg.Calc.Monsters;

import android.widget.ImageView;
import static com.example.rpg.graphic.GameActivity.monster_cara_now;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import static com.example.rpg.graphic.GameActivity.place;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.monster_place;
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

    public static Monster2 getMonsterRandomly(ImageView enemy_monster) {
        Random random = new Random();
        ArrayList<Monster2> monster2List = new ArrayList<>();
        monster2List.add(new DragonKing());
        monster2List.add(new MetalSlime());
        monster2List.add(new PutiSlime());
        monster2List.add(new Gorlem());
        //0~3 no random na value get
        int randomNum = random.nextInt(monster2List.size());
        setImageResource(randomNum,enemy_monster);
        return monster2List.get(randomNum);
    }
    public static void setImageResource(int randomNum,ImageView enemy_monster){
        if (monster_cara_now == null) {
            if (randomNum == 0) {
                enemy_monster.setImageResource(R.drawable.dragon_king);
                monster_cara_now = "dragon_king";
            } else if (randomNum == 1) {
                enemy_monster.setImageResource(R.drawable.metal_slime);
                monster_cara_now = "metal_slime";
            } else if (randomNum == 2) {
                enemy_monster.setImageResource(R.drawable.puti_slime);
                monster_cara_now = "puti_slime";
            } else {
                enemy_monster.setImageResource(R.drawable.gorlem);
                monster_cara_now = "gorlem";
            }
        }else if (monster_cara_now.equals("dragon_king")){
            if (monster_place.equals("over")) {
                enemy_monster.setImageResource(R.drawable.dragon_king);
            } else if (monster_place.equals("right")) {
                enemy_monster.setImageResource(R.drawable.dragon_king_right);
            } else if (monster_place.equals("left")) {
                enemy_monster.setImageResource(R.drawable.dragon_king_left);
            } else {
                enemy_monster.setImageResource(R.drawable.dragon_king_under);
            }
        }else if (monster_cara_now.equals("metal_slime")){
            if (monster_place.equals("over")) {
                enemy_monster.setImageResource(R.drawable.metal_slime);
            } else if (monster_place.equals("right")) {
                enemy_monster.setImageResource(R.drawable.metal_slime_right);
            } else if (monster_place.equals("left")) {
                enemy_monster.setImageResource(R.drawable.metal_slime_left);
            } else {
                enemy_monster.setImageResource(R.drawable.metal_slime_under);
            }
        }else if (monster_cara_now.equals("puti_slime")){
            if (monster_place.equals("over")) {
                enemy_monster.setImageResource(R.drawable.puti_slime);
            } else if (monster_place.equals("right")) {
                enemy_monster.setImageResource(R.drawable.puti_slime_right);
            } else if (monster_place.equals("left")) {
                enemy_monster.setImageResource(R.drawable.puti_slime_left);
            } else {
                enemy_monster.setImageResource(R.drawable.puti_slime_under);
            }
        }else {
            if (monster_place.equals("over")) {
                enemy_monster.setImageResource(R.drawable.gorlem);
            } else if (monster_place.equals("right")) {
                enemy_monster.setImageResource(R.drawable.gorlem_right);
            } else if (monster_place.equals("left")) {
                enemy_monster.setImageResource(R.drawable.gorlem_left);
            } else {
                enemy_monster.setImageResource(R.drawable.gorlem_under);
            }
        }
    }
}