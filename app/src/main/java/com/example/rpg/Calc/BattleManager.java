package com.example.rpg.Calc;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.skill.Hit.hit_attack;
import static com.example.rpg.Calc.skill.LittleFire.little_fire;
import static com.example.rpg.Calc.skill.Throw.throw_attack;
import static com.example.rpg.graphic.BattleManagerActivity.battle_manager_activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.R;
import com.example.rpg.graphic.AnimationQueue;
import com.example.rpg.graphic.HitAttackMonsterTask;
import com.example.rpg.graphic.HitAttackPlayerTask;

import java.io.Serializable;
import java.util.Random;

public class BattleManager implements Serializable {

    public boolean meet_enemy_monster = false;
    public boolean player_first = false;

    public int attack(Monster2 hp_monster, Monster2 attack_moster) {
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
    public void turn(Monster2 monster,Monster2 monster2,LinearLayout battle_chat,ImageView monster_of_player,TextView battle_chat_text,ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw) {
        battle_chat_text.setTextColor(Color.RED);
        System.out.println(monster.name + "は" + monster.use_skill.name);
        System.out.println(monster2.name + "は" + monster2.use_skill.name);
        Monster2 save_monster1 = monster;
        if (!(player_first)){
            monster = monster2;
            monster2 = save_monster1;
            save_monster1 = monster;
        }

        for (int i=0;i<2;i++) {
            if (monster.is_alive) {
                if (monster.mp >= monster.use_skill.consumption_mp) {
                    monster2.hp = attack(monster2, monster);
                    monster.mp -= monster.use_skill.consumption_mp;
                    System.out.println(player_first);
                    System.out.println(monster.use_skill.name);
                    AnimationQueue queue = new AnimationQueue();

// アニメーションをキューに追加
                    queue.enqueue(new HitAttackPlayerTask(monster, effect, frame_layout_player, frame_layout_monster, resources));
                    queue.enqueue(new HitAttackMonsterTask(monster2, effect, frame_layout_player, frame_layout_monster, resources));
//                    drawEffect(monster, effect, resources, frame_layout_player, frame_layout_monster,frame_layout_throw);
                    battle_chat.removeAllViews();
                    battle_chat_text.setText(monster.name + "の攻撃　　ドーン！！　" + monster2.name + "の体力が" + monster2.hp + "になった。　　" + monster.name + "のmpが" + monster.use_skill.consumption_mp + "下がって" + monster.mp + "になった");
                    battle_chat.addView(battle_chat_text);
                } else {
                    battle_manager_activity.graphicShortageMp(battle_chat, monster_of_player);
                    battle_chat.removeAllViews();
                    battle_chat_text.setText(monster.name + "の攻撃　　しかしmpが足りなかった");
                    battle_chat.addView(battle_chat_text);
                }
                if (monster2.hp <= 0) {
                    monster2.is_alive = false;
                    battle_manager_activity.graphicDie(battle_chat, monster_of_player);
                    battle_chat.removeAllViews();
                    battle_chat_text.setText(monster2.name + "は死んでしまった");
                    battle_chat.addView(battle_chat_text);
                }
                monster = monster2;
                monster2 = save_monster1;
                save_monster1 = monster;
                if (player_first){
                    player_first = false;
                }else {
                    player_first = true;
                }
            }
        }
        System.out.println(monster.name + "の体力は" + monster.hp + "になった");
        System.out.println(monster2.name + "の体力は" + monster2.hp + "になった");

    }
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
    public void choose_skill(Monster2 monster2, LinearLayout battle_chat, ImageView monster_of_player, TextView battle_chat_text, ImageView effect, Resources resources, FrameLayout frame_layout_player, FrameLayout frame_layout_monster, FrameLayout frame_layout_throw, ImageView fight_button, ImageView item_button, ImageView run_button) {
        //敵モンスターと味方モンスターの戦い
        battle_manager_activity.graphic_skill(monster2, battle_chat);
        click_skill(monster2, battle_chat,monster_of_player,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw,fight_button,item_button,run_button);
    }

    public boolean setPlayerFirst(boolean sente,Monster2 monster) {
        System.out.println(monster.use_skill.long_or_short + " " + monster.use_skill.name+ " "+ sente);
        if (monster.use_skill.long_or_short.equals(game.get_enemey_monster.use_skill.long_or_short)) {
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

    public void battle(Monster2 monster2, LinearLayout battle_chat, ImageView monster_of_player, TextView battle_chat_text, ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw) {
        boolean sente = judgeSente(monster2.judge_sente, game.get_enemey_monster.judge_sente);
        setPlayerFirst(sente,monster2);
        System.out.println(monster2.use_skill.name+"iiiii");
        turn(monster2, game.get_enemey_monster,battle_chat,monster_of_player,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw);
        alive(battle_chat,battle_chat_text);
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

    public void alive(LinearLayout battle_chat,TextView battle_chat_text) {
        if (game.get_enemey_monster.hp<=0) {
            battle_chat_text.setText(game.get_enemey_monster.name + "を倒した");
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

    public int click_skill(Monster2 monster2,LinearLayout battle_chat,ImageView monster_of_player,TextView battle_chat_text,ImageView effect,Resources resources,FrameLayout frame_layout_player,FrameLayout frame_layout_monster,FrameLayout frame_layout_throw,ImageView fight_button,ImageView item_button,ImageView run_button){
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
                    battle(monster2,battle_chat,monster_of_player,battle_chat_text,effect,resources,frame_layout_player,frame_layout_monster,frame_layout_throw);
                    fight_button.setVisibility(View.VISIBLE);
                    item_button.setVisibility(View.VISIBLE);
                    run_button.setVisibility(View.VISIBLE);
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
