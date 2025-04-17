package com.example.rpg.Calc;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.graphic.AnimationQueue;
import com.example.rpg.graphic.MonsterTask;
import com.example.rpg.graphic.PlayerTask;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class BattleManager implements Serializable {
    public boolean meet_enemy_monster = false;
    public static boolean player_first = false;
    public static boolean player_die_effect = false;
    public static boolean monster_die_effect = false;

    public int attack(Monster2 hp_monster, Monster2 attack_monster) {
        int up_attack = 0;
        int up_defence = 0;
        try {
            FightItem up_attack_item = attack_monster.have_item;
            up_attack = up_attack_item.up_attack;
        }catch (NullPointerException e){}
        try{
            FightItem up_defence_item = hp_monster.have_item;
            up_defence = up_defence_item.up_defence;
        }catch(NullPointerException e){}
        if (attack_monster.use_skill.offensive_power*attack_monster.attack + up_attack - hp_monster.defence - up_defence > 0) {
            if (hp_monster.hp - (attack_monster.use_skill.offensive_power*attack_monster.attack + up_attack - hp_monster.defence -up_defence) <= 0) {
                return 0;
            } else {
                return hp_monster.hp - (attack_monster.use_skill.offensive_power*attack_monster.attack + up_attack - hp_monster.defence - up_defence);
            }
        }else {
            return hp_monster.hp;
        }
    }
    public void turn(Monster2 attack_monster,Monster2 defense_monster,LinearLayout battle_chat,ImageView monster_of_player,ImageView enemy_monster,TextView battle_chat_text,ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw,ImageView fight_button,ImageView item_button,ImageView run_button,ImageView finish_button,FrameLayout frame_layout_player_power_up,FrameLayout frame_layout_monster_power_up, ArrayList<ArrayList<ProgressBar>> ber_gauge,ArrayList<ArrayList<TextView>> text_gauge) {
        battle_chat_text.setTextColor(Color.RED);
        AnimationQueue queue = new AnimationQueue();
        Monster2 default_monster = attack_monster;
        if (player_first) {
            graphicAllyAttack(attack_monster, defense_monster, battle_chat, monster_of_player,enemy_monster, battle_chat_text, effect, resources, frame_layout_player, frame_layout_monster, frame_layout_throw,fight_button,item_button,run_button,finish_button,frame_layout_monster_power_up,frame_layout_player_power_up,queue,ber_gauge,text_gauge);
            attack_monster = defense_monster;
            defense_monster = default_monster;
            graphicEnemyAttack(attack_monster, defense_monster, battle_chat, monster_of_player, battle_chat_text, effect, resources, frame_layout_player, frame_layout_monster, frame_layout_throw,fight_button,item_button,run_button,finish_button,frame_layout_player_power_up,frame_layout_monster_power_up,enemy_monster,queue,ber_gauge,text_gauge);
        } else {
            attack_monster = defense_monster;
            defense_monster = default_monster;
            default_monster = attack_monster;
            graphicEnemyAttack(attack_monster, defense_monster, battle_chat, monster_of_player, battle_chat_text, effect, resources, frame_layout_player, frame_layout_monster, frame_layout_throw,fight_button,item_button,run_button,finish_button,frame_layout_player_power_up,frame_layout_monster_power_up,enemy_monster,queue,ber_gauge,text_gauge);
            attack_monster = defense_monster;
            defense_monster = default_monster;
            graphicAllyAttack(attack_monster, defense_monster, battle_chat, monster_of_player,enemy_monster, battle_chat_text, effect, resources, frame_layout_player, frame_layout_monster, frame_layout_throw,fight_button,item_button,run_button,finish_button,frame_layout_monster_power_up,frame_layout_player_power_up,queue,ber_gauge,text_gauge);
        }
    }



    public void graphicEnemyAttack(Monster2 attack_monster,Monster2 defense_monster,LinearLayout battle_chat,ImageView monster_of_player,TextView battle_chat_text,ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw,ImageView fight_button,ImageView item_button,ImageView run_button,ImageView finish_button,FrameLayout frame_layout_player_power_up,FrameLayout frame_layout_monster_power_up,ImageView enemy_monster,AnimationQueue queue, ArrayList<ArrayList<ProgressBar>> ber_gauge,ArrayList<ArrayList<TextView>> text_gauge){
        if (attack_monster.is_alive) {
            if (attack_monster.mp >= attack_monster.use_skill.consumption_mp) {
                monster_die_effect = false;
                queue.enqueue(new MonsterTask(defense_monster, effect, frame_layout_player, frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,monster_of_player,frame_layout_player_power_up, resources,enemy_monster,ber_gauge,text_gauge));
                defense_monster.hp = attack(defense_monster, attack_monster);
                attack_monster.mp -= attack_monster.use_skill.consumption_mp;
                battle_chat.removeAllViews();
                battle_chat_text.setText(attack_monster.name + "の攻撃　　ドーン！！　" + defense_monster.name + "の体力が" + defense_monster.hp + "になった。　　" + attack_monster.name + "のmpが" + attack_monster.use_skill.consumption_mp + "下がって" + attack_monster.mp + "になった");
                battle_chat.addView(battle_chat_text);
            } else {
                monster_die_effect = false;
                queue.enqueue(new MonsterTask(defense_monster, effect, frame_layout_player, frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,monster_of_player,frame_layout_player_power_up, resources,enemy_monster,ber_gauge,text_gauge));
                battle_chat.removeAllViews();
                battle_chat_text.setText(attack_monster.name + "の攻撃　　しかしmpが足りなかった");
                battle_chat.addView(battle_chat_text);
            }
            if (defense_monster.hp <= 0) {
                defense_monster.is_alive = false;
                battle_chat.removeAllViews();
                battle_chat_text.setText(defense_monster.name + "は死んでしまった");
                battle_chat.addView(battle_chat_text);
                queue.enqueue(new PlayerTask(defense_monster, effect, frame_layout_player, frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,enemy_monster,frame_layout_monster_power_up, resources,monster_of_player,ber_gauge,text_gauge));
            }
        }
    }
    public void graphicAllyAttack(Monster2 attack_monster,Monster2 defense_monster,LinearLayout battle_chat,ImageView monster_of_player,ImageView enemy_monster,TextView battle_chat_text,ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw,ImageView fight_button,ImageView item_button,ImageView run_button,ImageView finish_button,FrameLayout frame_layout_monster_power_up,FrameLayout frame_layout_player_power_up,AnimationQueue queue, ArrayList<ArrayList<ProgressBar>> ber_gauge,ArrayList<ArrayList<TextView>> text_gauge){
        if (attack_monster.is_alive) {
            if (attack_monster.mp >= attack_monster.use_skill.consumption_mp) {
                player_die_effect = false;
                queue.enqueue(new PlayerTask(attack_monster, effect, frame_layout_player, frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,enemy_monster,frame_layout_monster_power_up, resources,monster_of_player,ber_gauge,text_gauge));
                defense_monster.hp = attack(defense_monster, attack_monster);
                attack_monster.mp -= attack_monster.use_skill.consumption_mp;
                battle_chat.removeAllViews();
                battle_chat_text.setText(attack_monster.name + "の攻撃　　ドーン！！　" + defense_monster.name + "の体力が" + defense_monster.hp + "になった。　　" + attack_monster.name + "のmpが" + attack_monster.use_skill.consumption_mp + "下がって" + attack_monster.mp + "になった");
                battle_chat.addView(battle_chat_text);
            } else {
                player_die_effect = false;
                queue.enqueue(new PlayerTask(attack_monster, effect, frame_layout_player, frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,enemy_monster,frame_layout_monster_power_up, resources,monster_of_player,ber_gauge,text_gauge));
                battle_chat.removeAllViews();
                battle_chat_text.setText(attack_monster.name + "の攻撃　　しかしmpが足りなかった");
                battle_chat.addView(battle_chat_text);
            }
            if (defense_monster.hp <= 0) {
                defense_monster.is_alive = false;
                battle_chat.removeAllViews();
                battle_chat_text.setText(defense_monster.name + "は死んでしまった");
                battle_chat.addView(battle_chat_text);
                queue.enqueue(new MonsterTask(attack_monster, effect, frame_layout_player, frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,monster_of_player,frame_layout_player_power_up, resources,enemy_monster,ber_gauge,text_gauge));
                monster_die_effect = false;
            }
        }
    }
   /*
    public void drawEffect(Monster2 monster,ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw){
        effect.setImageDrawable(resources.getDrawable(R.drawable.invisible_panel));
        System.out.println(monster.use_skill.name);
        if (monster.use_skill == hit_attack) {
            battle_manager_activity.drawHitAttack(monster, effect,player_first,resources,frame_layout_player,frame_layout_monster);
        } else if (monster.use_skill == throw_attack) {
            battle_manager_activity.drawThrowAttack(monster, effect,player_first,resources,frame_layout_player,frame_layout_monster,frame_layout_throw);
        } else if (monster.use_skill == little_fire) {
            battle_manager_activity.drawLittleFireAttack(monster, effect,player_first,resources,frame_layout_player,frame_layout_monster);
        }
    }
    */
    public void choose_skill(Monster2 monster2, LinearLayout battle_chat, ImageView monster_of_player, ImageView enemy_monster, TextView battle_chat_text, ImageView effect, Resources resources, FrameLayout frame_layout_player, FrameLayout frame_layout_monster, FrameLayout frame_layout_throw, ImageView fight_button, ImageView item_button, ImageView run_button,ImageView finish_button, FrameLayout frame_layout_player_power_up, FrameLayout frame_layout_monster_power_up, ArrayList<ArrayList<ProgressBar>> ber_gauge,ArrayList<ArrayList<TextView>> text_gauge) {
        //敵モンスターと味方モンスターの戦い
        battle_manager_activity.graphic_skill(monster2, battle_chat);
        click_skill(monster2, battle_chat,monster_of_player,enemy_monster,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,frame_layout_player_power_up,frame_layout_monster_power_up,ber_gauge,text_gauge);
    }
    public static double setPercent(int progress,int max){
        double percent;
        percent = (double) progress / (double) max * 100.0;
        return percent;
    }
    public boolean setPlayerFirst(boolean sente,Monster2 monster) {
        System.out.println(monster.use_skill.long_or_short + " " + monster.use_skill.name+ " "+ sente);
        if (monster.use_skill.long_or_short.equals(game.get_enemy_monster.use_skill.long_or_short)) {
            if (sente){
                player_first = true;
            }else {
                player_first = false;
            }
        } else {
            if (monster.use_skill.long_or_short.equals("short")) {
                player_first = true;
            } else {
                player_first = false;
            }
        }
        return player_first;
    }

    public void battle(Monster2 monster2, LinearLayout battle_chat, ImageView monster_of_player,ImageView enemy_monster, TextView battle_chat_text, ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw,ImageView fight_button,ImageView item_button,ImageView run_button,ImageView finish_button,FrameLayout frame_layout_player_power_up,FrameLayout frame_layout_monster_power_up, ArrayList<ArrayList<ProgressBar>> ber_gauge,ArrayList<ArrayList<TextView>> text_gauge) {
        boolean sente = judgeSente(monster2.judge_sente, game.get_enemy_monster.judge_sente);
        setPlayerFirst(sente,monster2);
        System.out.println(monster2.use_skill.name+"iiiii");
        turn(monster2, game.get_enemy_monster,battle_chat,monster_of_player,enemy_monster,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,frame_layout_player_power_up,frame_layout_monster_power_up,ber_gauge,text_gauge);
        alive(battle_chat,battle_chat_text,monster2);
    }

    public boolean judgeSente(int judgeSenteFirst, int judgeSenteSecond) {
        Random random = new Random(2);
        boolean sente = true;
        System.out.println(judgeSenteFirst);
        System.out.println(judgeSenteSecond);
        if (judgeSenteFirst < judgeSenteSecond) {
            sente = false;
        } else if (judgeSenteFirst == judgeSenteSecond) {
            sente = random.nextBoolean();
        }
        System.out.println(sente);
        return sente;
    }

    public void alive(LinearLayout battle_chat,TextView battle_chat_text,Monster2 monster2) {
        if (game.get_enemy_monster.hp<=0) {
            battle_chat_text.setText(game.get_enemy_monster.name + "を倒した");
            battle_chat_text.setTextColor(Color.RED);
            battle_chat.removeAllViews();
            battle_chat.addView(battle_chat_text);
        }
    }
    public int useItem(Monster2 my_side_monster, LinearLayout battle_chat,TextView battle_chat_text,FrameLayout frame_layout_player_power_up,ImageView effect, Resources resources){
        graphicItemBattleChat(battle_chat,battle_chat_text);
        for (int i=0;i<battle_chat.getChildCount();i++) {
            int battle_chat_i = i;
            battle_chat.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    battle_chat.removeAllViews();
                    battle_chat_text.setTextColor(Color.RED);
                    battle_chat_text.setText(game.p.fight_items.get(battle_chat_i).name+"を使った！");
                    battle_chat.addView(battle_chat_text);
                    game.p.choose_item = battle_chat_i;
                    game.p.fight_items.get(game.p.choose_item).have_point--;
                    useItemMath(my_side_monster);
                    battle_manager_activity.drawGraphicUsingItem(effect,frame_layout_player_power_up,resources);
                    if (game.p.fight_items.get(game.p.choose_item).have_point == 0){
                        game.p.items.remove(game.p.fight_items.get(game.p.choose_item));
                        game.p.fight_items.remove(game.p.choose_item);
                    }
                }
            });
            }
        return my_side_monster.hp;
    }
    public void graphicItemBattleChat(LinearLayout battle_chat,TextView battle_chat_text){
        for (FightItem fightItem : game.p.fight_items) {
            battle_chat_text.setTextColor(Color.RED);
            battle_chat_text.setText(fightItem.name + " " + fightItem.have_point + "個 ");
            battle_chat.addView(battle_chat_text);
        }
    }
    public void useItemMath(Monster2 monster2){
        if (game.p.fight_items.get(game.p.choose_item).item_group.equals("heal")){
            if (monster2.limit_hp > monster2.hp + game.p.fight_items.get(game.p.choose_item).heal){
                monster2.hp += game.p.fight_items.get(game.p.choose_item).heal;
            }else {
                monster2.hp = monster2.limit_hp;
            }
        }
    }

    public int click_skill(Monster2 monster2,LinearLayout battle_chat, ImageView monster_of_player,ImageView enemy_monster,TextView battle_chat_text,ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw,ImageView fight_button,ImageView item_button,ImageView run_button,ImageView finish_button,FrameLayout frame_layout_player_power_up,FrameLayout frame_layout_monster_power_up, ArrayList<ArrayList<ProgressBar>> ber_gauge,ArrayList<ArrayList<TextView>> text_gauge){
        for (int i=0;i<battle_chat.getChildCount();i++){
            System.out.println(battle_chat.getChildCount());
            int skill_i = i;
            battle_chat.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(skill_i+"aaaaaaaaaaaaaaaaaaaaaaa");
                    fight_button.setVisibility(View.GONE);
                    item_button.setVisibility(View.GONE);
                    run_button.setVisibility(View.GONE);
                    battle_chat.removeAllViews();
                    monster2.use_skill = monster2.all_skill.get(skill_i);
                    System.out.println(monster2.name + "の攻撃" + monster2.use_skill.name);
                    chooseEnemySkill();
                    battle(monster2,battle_chat,monster_of_player,enemy_monster,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button,finish_button,frame_layout_player_power_up,frame_layout_monster_power_up,ber_gauge,text_gauge);
                }
            });
        }
        return battle_chat.getChildCount();
    }
    public void chooseEnemySkill(){
        Random random =new Random();
        game.get_enemy_monster.use_skill = game.get_enemy_monster.all_skill.get(random.nextInt(game.get_enemy_monster.all_skill.size()));
    }
}
