package com.example.rpg.Calc.Monsters;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.Monsters.DragonKing.dragon_king;
import static com.example.rpg.Calc.Monsters.Gorlem.gorlem;
import static com.example.rpg.Calc.Monsters.MetalSlime.metal_slime;
import static com.example.rpg.Calc.Monsters.PutiSlime.puti_slime;
import static com.example.rpg.graphic.GameActivity.monster_cara_now;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.monster_place;

import androidx.appcompat.app.AppCompatActivity;

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
    public ArrayList<TextView> display_skill =new ArrayList<>();

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
        setNewMonster();
        monster2List = addMonster2List(monster2List);
        //0~3 no random na value get
        int randomNum = 3 /*random.nextInt(monster2List.size())*/;
        setImageResource(randomNum,enemy_monster);
        return monster2List.get(0/*randomNum*/);
    }
    public static void setNewMonster(){
        dragon_king = new DragonKing();
        metal_slime = new MetalSlime();
        puti_slime = new PutiSlime();
        gorlem = new Gorlem();
    }
    public static ArrayList<Monster2> addMonster2List(ArrayList<Monster2> monster2List){
        /*monster2List.add(dragon_king);
        monster2List.add(metal_slime);
        monster2List.add(puti_slime);*/
        monster2List.add(gorlem);
        return monster2List;
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
    public int getAttack(){
        int attack = this.attack;
        try{
            if (this.have_item.name.equals("attack")){
                attack += this.have_item.up_attack;
            }
        }catch (NullPointerException e){}
        return attack;
    }
    public int getDefence(){
        int defence = this.defence;
        try{
            if (this.have_item.name.equals("armor")){
                defence += this.have_item.up_defence;
            }
        }catch (NullPointerException e){}
        return defence;
    }
}