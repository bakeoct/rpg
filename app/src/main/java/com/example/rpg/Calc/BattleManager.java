package com.example.rpg.Calc;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.skill.Skill;
import com.example.rpg.Calc.Monsters.Monster2;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class BattleManager implements Serializable {
    public static int attack(Monster2 hp_monster,Monster2 attack_moster) {
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

    public static int turn(Monster2 monster,Monster2 monster2) {
        if (monster.is_alive) {
            if (monster.mp >= monster.use_skill.consumption_mp) {
                monster2.hp = attack(monster2,monster);
                monster.mp = monster.mp - monster.use_skill.consumption_mp;
                System.out.println(monster.name + "の攻撃　　ドーン！！　" + monster2.name + "の体力が" + monster2.hp + "になった。　　" + monster.name + "のmpが"+monster.use_skill.consumption_mp+"下がって" + monster.mp + "になった");
            } else {
                System.out.println(monster.name + "の攻撃　　しかしmpが足りなかった");
            }
            if (monster2.hp <= 0) {
                monster2.is_alive = false;
                System.out.println(monster2.name + "は死んでしまった");
            }
        }
        return 1;
    }

    public static String battle(Monster2 monster1, Monster2 monster2,String what_did,Person2 p) {
        System.out.println(monster2.name + "と" + monster1.name + "の戦い");
        if (monster2.is_alive) {
            while (monster1.is_alive && monster2.is_alive) {
                boolean sente = judgeSente(monster2.judge_sente, monster1.judge_sente);
                what_did = chooseDo(what_did, p, monster2);
                chooseEnemySkill(monster1);
                if (monster2.use_skill.long_or_short.equals(monster1.use_skill.long_or_short)) {
                    if (sente) {
                        if (what_did.equals("fight")) {
                            turn(monster2, monster1);
                        }
                        if (!(what_did.equals("goback"))) {
                            turn(monster1, monster2);
                            alive(monster2);
                        } else {
                            break;
                        }
                    } else {
                        if (!(what_did.equals("goback"))) {
                            turn(monster1, monster2);
                            if (monster2.is_alive) {
                                if (what_did.equals("fight")) {
                                    turn(monster2, monster1);
                                }
                            }
                            alive(monster1);
                        } else {
                            break;
                        }
                    }
                }else {
                    if (monster2.use_skill.long_or_short.equals("long")){
                        if (what_did.equals("fight")) {
                            turn(monster2, monster1);
                        }
                        if (!(what_did.equals("goback"))) {
                            turn(monster1, monster2);
                            alive(monster2);
                        } else {
                            break;
                        }
                    }else {
                        if (!(what_did.equals("goback"))) {
                            turn(monster1, monster2);
                            if (monster2.is_alive) {
                                if (what_did.equals("fight")) {
                                    turn(monster2, monster1);
                                }
                            }
                            alive(monster1);
                        } else {
                            break;
                        }
                    }
                }
            }
        } else {
            System.out.println("しかし、" + monster2.name + "はすでに死んでいる");
        }
        return what_did;
    }

    public static boolean judgeSente(int judgeSenteFirst, int judgeSenteSecond) {
        Random random = new Random(2);
        boolean sente = true;
        if (judgeSenteFirst < judgeSenteSecond) {
            sente = false;
        } else if (judgeSenteFirst == judgeSenteSecond) {
            sente = random.nextBoolean();
        }
        return sente;
    }

    public static void alive(Monster2 monster2) {
        if (monster2.hp<=0) {
            System.out.println(monster2.name + "を倒した");
        }
    }
    public static void itemsStatus(Monster2 monster2){
        try{
            if (monster2.have_item.name.equals("attack")){
                monster2.attack += monster2.have_item.up_attack;
            }else if (monster2.have_item.name.equals("armor")){
                monster2.defence += monster2.have_item.up_defence;
            }
        }catch (NullPointerException e){}
    }
    public static void goBackStatus(Monster2 monster2){
        try {
            if (monster2.have_item.name.equals("attack")) {
                monster2.attack -= monster2.have_item.up_attack;
            } else if (monster2.have_item.name.equals("armor")) {
                monster2.defence -= monster2.have_item.up_defence;
            }
        }catch (NullPointerException e){}
    }
    public static String chooseDo(String what_did,Person2 p,Monster2 monster2) {
        Scanner scanner =new Scanner(System.in);
        int endflg = 0;
        while (endflg == 0) {
            if (what_did.equals("fight")) {
                endflg = chooseSkill(monster2,endflg,scanner);
                endflg++;
            } else if (what_did.equals("item")) {
                endflg = useItem(p,endflg,monster2);
            } else if (what_did.equals("goBack")) {
                endflg++;
            }
        }
        return what_did;
    }
    public static int useItem(Person2 p,int do_flg,Monster2 monster2){
        System.out.println("インベントリ");
        System.out.println();
        Scanner scanner =new Scanner(System.in);
        int endflg = 0;
        while (true) {
            for (FightItem fightItem : p.fight_items) {
                endflg = 1;
                System.out.println(fightItem.name + " " + fightItem.have_point + "個 " + "[" + fightItem.code + "]");
            }
            if (endflg == 0){
                System.out.println("何も持っていないのでアイテムは使えません");
                break;
            }
            System.out.println("戻る [goback]");
            String what_use = scanner.next();
            for (int i=0;i<p.fight_items.size();i++) {
                if (what_use.equals(p.fight_items.get(i).code)) {
                    System.out.println(p.fight_items.get(i).name+"を使った！");
                    endflg = 0;
                    p.choose_item = i;
                    p.fight_items.get(p.choose_item).have_point--;
                    useItemMath(p.fight_items.get(p.choose_item),monster2);
                    if (p.fight_items.get(p.choose_item).have_point == 0){
                        p.fight_items.remove(p.choose_item);
                    }
                }
            }
            if (what_use.equals("goback")){
                break;
            } else if (!(endflg == 0)){
                System.out.println("選択肢から選んでください");
            }else {
                do_flg++;
                break;
            }
        }
        return do_flg;
    }
    public static void useItemMath(FightItem fightItem,Monster2 monster2){
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
    public static int chooseSkill(Monster2 monster2,int do_flg,Scanner scanner) {
        int endflg = 0;
        while (endflg == 0) {
            for (Skill skill : monster2.all_skill) {
                System.out.println(skill.name + " [" + skill.code + "]");
            }
            System.out.println("戻る　[goback]");
            String choose_skill = scanner.next();
            for (Skill skill : monster2.all_skill) {
                if (choose_skill.equals(skill.code)) {
                    monster2.use_skill = skill;
                    endflg++;
                }
            }
            if (choose_skill.equals("goback")) {
                do_flg--;
                endflg++;
            }else {
                if (endflg == 0){
                    System.out.println("選択肢から選んでください");
                }
            }
        }
        return do_flg;
    }
    public static void chooseEnemySkill(Monster2 monster1){
        Random random =new Random();
        monster1.use_skill = monster1.all_skill.get(random.nextInt(monster1.all_skill.size()));
    }
}
