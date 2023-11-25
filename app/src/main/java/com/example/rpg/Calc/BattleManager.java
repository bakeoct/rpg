package com.example.rpg.Calc;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.Calc.Monsters.Monster2;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class BattleManager implements Serializable {

    public boolean meet_enemy_monster = false;

    public int attack(Monster2 hp_monster,Monster2 attack_moster) {
        if (attack_moster.use_skill.offensive_power*attack_moster.attack - hp_monster.defence > 0) {
            if (hp_monster.hp - (attack_moster.use_skill.offensive_power*attack_moster.attack - hp_monster.defence) <= 0) {
                return 0;
            } else {
                return hp_monster.hp - (attack_moster.use_skill.offensive_power*attack_moster.attack - hp_monster.defence);
            }
        }else {
            return hp_monster.hp;
        }
    }

    public int turn(Monster2 monster,Monster2 monster2,LinearLayout battle_chat,ImageView monster_of_player,TextView battle_chat_text,int attack_margin,ImageView effect) {
        battle_chat_text.setTextColor(Color.RED);
        if (monster.is_alive) {
            if (monster.mp >= monster.use_skill.consumption_mp) {
                monster2.hp = attack(monster2,monster);
                monster.mp -= monster.use_skill.consumption_mp;
                choose_effect(monster_of_player,monster,attack_margin,effect);
                battle_chat.removeAllViews();
                battle_chat_text.setText(monster.name + "の攻撃　　ドーン！！　" + monster2.name + "の体力が" + monster2.hp + "になった。　　" + monster.name + "のmpが"+monster.use_skill.consumption_mp+"下がって" + monster.mp + "になった");
                battle_chat.addView(battle_chat_text);
            } else {
                battle_manager_activity.graphicShortageMp(battle_chat,monster_of_player);
                battle_chat.removeAllViews();
                battle_chat_text.setText(monster.name + "の攻撃　　しかしmpが足りなかった");
                battle_chat.addView(battle_chat_text);
            }
            if (monster2.hp <= 0) {
                monster2.is_alive = false;
                battle_manager_activity.graphicDie(battle_chat,monster_of_player);
                battle_chat.removeAllViews();
                battle_chat_text.setText(monster2.name + "は死んでしまった");
                battle_chat.addView(battle_chat_text);
            }
        }
        return 1;
    }
    public void choose_effect(ImageView monster_of_player,Monster2 monster,int attack_margin,ImageView effect){
        if (monster.use_skill == hit_attack){
            battle_manager_activity.graphicHitAttack(monster_of_player,monster,attack_margin,effect);
        }else if (monster.use_skill == throw_attack){

        }else if (monster.use_skill == little_fire){

        }
    }
    public void choose_skill(Monster2 monster2,LinearLayout battle_chat,ImageView monster_of_player,TextView battle_chat_text,int attack_margin,ImageView effect) {
        //敵モンスターと味方モンスターの戦い
        battle_manager_activity.graphic_skill(monster2, battle_chat);
        click_skill(monster2, battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
    }
    public void battle(Monster2 monster2,LinearLayout battle_chat,ImageView monster_of_player,TextView battle_chat_text,int attack_margin,ImageView effect) {
        boolean sente = judgeSente(monster2.judge_sente, game.get_enemey_monster.judge_sente);
        if (monster2.use_skill.long_or_short.equals(game.get_enemey_monster.use_skill.long_or_short)) {
            if (sente) {
                turn(monster2, game.get_enemey_monster,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                turn(game.get_enemey_monster, monster2,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                alive(battle_chat);
            } else {
                turn(game.get_enemey_monster, monster2,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                turn(monster2, game.get_enemey_monster,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                alive(battle_chat);
            }
        } else {
            if (monster2.use_skill.long_or_short.equals("long")) {
                turn(monster2, game.get_enemey_monster,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                turn(game.get_enemey_monster, monster2,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                alive(battle_chat);
            } else {
                turn(game.get_enemey_monster, monster2,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                turn(monster2, game.get_enemey_monster,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                alive(battle_chat);
            }
        }
    }

    public boolean judgeSente(int judgeSenteFirst, int judgeSenteSecond) {
        Random random = new Random(2);
        boolean sente = true;
        if (judgeSenteFirst < judgeSenteSecond) {
            sente = false;
        } else if (judgeSenteFirst == judgeSenteSecond) {
            sente = random.nextBoolean();
        }
        return sente;
    }

    public void alive(LinearLayout battle_chat) {
        if (game.get_enemey_monster.hp<=0) {
            TextView textView = new TextView(battle_manager_activity);
            textView.setText(game.get_enemey_monster.name + "を倒した");
            textView.setTextColor(Color.RED);
            battle_chat.addView(battle_chat);
        }
    }
    public int useItem(Monster2 my_side_monster, LinearLayout battle_chat){
        graphicItemBattleChat(battle_chat);
        for (int i=0;i<battle_chat.getChildCount();i++) {
            int battle_chat_i = i;
            battle_chat.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    battle_chat.removeAllViews();
                    TextView textView = new TextView(battle_manager_activity);
                    textView.setTextColor(Color.RED);
                    textView.setText(game.p.fight_items.get(battle_chat_i).name+"を使った！");
                    game.p.choose_item = battle_chat_i;
                    game.p.fight_items.get(game.p.choose_item).have_point--;
                    useItemMath(game.p.fight_items.get(game.p.choose_item),my_side_monster);
                    if (game.p.fight_items.get(game.p.choose_item).have_point == 0){
                        game.p.fight_items.remove(game.p.choose_item);

                    }
                }
            });
            }
        return my_side_monster.hp;
    }
    public void graphicItemBattleChat(LinearLayout battle_chat){
        for (FightItem fightItem : game.p.fight_items) {
            TextView textView = new TextView(battle_manager_activity);
            textView.setTextColor(Color.RED);
            textView.setText(fightItem.name + " " + fightItem.have_point + "個 " + "[" + fightItem.code + "]");
            battle_chat.addView(textView);
        }
    }
    public void useItemMath(FightItem fightItem,Monster2 monster2){
        if (fightItem.item_group.equals("heal")){
            if (monster2.limit_hp > monster2.hp + fightItem.heal){
                monster2.hp += fightItem.heal;
            }else {
                monster2.hp = monster2.limit_hp;
            }
            //あしたはぼうぎょちをせっていして、ダメージ軽減を行わせる。
            //具体的にはここのバトルマネージャークラスの計算箇所で割合を使って軽減させる
        }
    }

    public int click_skill(Monster2 monster2,LinearLayout battle_chat,ImageView monster_of_player,TextView battle_chat_text,int attack_margin,ImageView effect){
        for (int i=0;i<battle_chat.getChildCount();i++){
            int skill_i = i;
            battle_chat.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    battle_chat.removeAllViews();
                    monster2.use_skill = monster2.all_skill.get(skill_i);
                    chooseEnemySkill();
                    battle(monster2,battle_chat,monster_of_player,battle_chat_text,attack_margin,effect);
                }
            });
        }
        return battle_chat.getChildCount();
    }
    public void chooseEnemySkill(){
        Random random =new Random();
        game.get_enemey_monster.use_skill = game.get_enemey_monster.all_skill.get(random.nextInt(game.get_enemey_monster.all_skill.size()));
    }
}
