package com.example.rpg.Calc;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Monsters.Monster2;

import java.io.Serializable;
import java.util.Scanner;

public class Inventry implements Serializable {
    public static void chooseItems(Person2 p,Scanner scanner) throws Finish {
        int end_flg = 0;
        while (end_flg == 0){
            for (int i = 0; i < p.items.size(); i++) {
                System.out.println(p.items.get(i).name + " " + p.items.get(i).have_point + "個 [" + p.items.get(i).code + "]");
            }
            System.out.println("何も持たない　[nothave]");
            System.out.println("捨てる　[dispose]");
            System.out.println("戻る　[goback]");
            String use_items = scanner.next();
            for (int i = 0; i < p.items.size(); i++) {
                if (use_items.equals(p.items.get(i).code) && p.items.get(i).can_hold) {
                    int end_flg_item = 0;
                    end_flg++;
                    while (end_flg_item==0) {
                        System.out.println("手にもつ　[take]");
                        System.out.println("モンスターに持たせる　[take_monster]");
                        System.out.println("戻る　[goback]");
                        use_items = scanner.next();
                        if (use_items.equals("take")){
                            end_flg_item++;
                            p.have_item = p.items.get(i);
                            System.out.println(p.have_item.name+"を手に持った");
                        }else if (use_items.equals("take_monster")){
                            end_flg_item++;
                            int end_flg_item_monster = 0;
                            while (end_flg_item_monster==0) {
                                for (Monster2 monster : p.monsters2){
                                    System.out.println(monster.name+" ["+monster.name+"]");
                                }
                                System.out.println("戻る");
                                use_items = scanner.next();
                                for (Monster2 monster : p.monsters2){
                                    if (monster.name.equals(use_items) && p.items.get(i) instanceof FightItem){
                                        end_flg_item_monster++;
                                        monster.have_item = (FightItem) p.items.get(i);
                                    }else {
                                        if (monster.name.equals(use_items)){
                                            System.out.println("そのアイテムはモンスタ－に持たせることはできません。");
                                            end_flg_item_monster++;
                                        }
                                    }
                                }
                                if (use_items.equals("goback")){
                                    end_flg_item_monster++;
                                    System.out.println("インベントリから去った");
                                }else if (use_items.equals("finish")){
                                    throw new Finish();
                                }else if (end_flg_item_monster == 0){
                                    System.out.println("インベントリの中のアイテムから選んでください");
                                }
                            }
                        }else if (use_items.equals("goback")){
                            System.out.println("インベントリから去った");
                            end_flg_item++;
                        }else if (use_items.equals("finish")){
                            throw new Finish();
                        }else if (end_flg_item == 0){
                            System.out.println("選択肢の中から選んでください");
                        }
                    }
                }else {
                    if (use_items.equals(p.items.get(i).code) && !(p.items.get(i).can_hold)){
                        System.out.println("このアイテムは持てません");
                        end_flg++;
                    }
                }
            }
            if (use_items.equals("nothave")) {
                if (p.have_item == null) {
                    System.out.println("しかし何も持っていなかった");
                }else {
                    System.out.println(p.have_item.name + "をしまった");
                }
                end_flg++;
                p.have_item = null;
            }else if (use_items.equals("goback")){
                end_flg++;
                System.out.println("インベントリから去った");
            }else if (use_items.equals("dispose")){
                end_flg++;
                int end_flg_dispose=0;
                while (end_flg_dispose == 0){
                    for (int i = 0; i < p.items.size(); i++) {
                        System.out.println(p.items.get(i).name + " " + p.items.get(i).have_point + "個 [" + p.items.get(i).code + "]");
                    }
                    System.out.println("戻る　[goback]");
                    use_items =scanner.next();
                    for (int i = 0; i < p.items.size(); i++) {
                        if (use_items.equals(p.items.get(i).code)) {
                            System.out.println(p.items.get(i).name+"を捨てた");
                            p.items.remove(i);
                            end_flg_dispose++;
                        }
                    }
                    if (use_items.equals("goback")){
                        end_flg_dispose++;
                        System.out.println("インベントリから去った");
                    }else if (use_items.equals("finish")){
                        throw new Finish();
                    }else if (end_flg_dispose == 0){
                        System.out.println("インベントリの中のアイテムから選んでください");
                    }
                }
            }else if (use_items.equals("finish")){
                throw new Finish();
            }else if (end_flg == 0){
                System.out.println("インベントリの中のアイテムから選んでください");
            }
        }
    }
}
