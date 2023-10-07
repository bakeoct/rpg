package com.example.rpg.Calc;

import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.map.cave.Cave1;
import com.example.rpg.Calc.map.cave.Cave1_1;
import com.example.rpg.Calc.map.Map;
import static com.example.rpg.Calc.map.PersonHome1.*;
import com.example.rpg.Calc.Monsters.EnemeyMonster;
import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.graphic.GameActivity;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.map.cave.Cave1.*;
import java.io.File;
import java.io.Serializable;
import java.util.Scanner;
import static com.example.rpg.Calc.Sound.*;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_Y;

import android.content.Intent;

public class Event implements Serializable{
    public Person2 p;
    public String item_box;
    public Map map;
    public Ladder ladder;
    public Ship ship;
    public MissionDragonKing mission_dragon_king;
    public EnemeyMonster enemey_monster;
    public Event(Person2 p, Map map, Ladder ladder, Ship ship, MissionDragonKing missionDragonKing, EnemeyMonster enemeyMonster){
        this.p = p;
        this.map = map;
        this.ladder = ladder;
        this.ship = ship;
        this.mission_dragon_king = missionDragonKing;
        this.enemey_monster = enemeyMonster;
    }
    public void eventPerson(String serveget_map_code) {
        //これをmapに送って二つメソッド動かす
        String get_map_code = map.getMapCode(p.x, p.y,p.area);
        if (get_map_code.equals("崖")) {
            if (!(get_map_code.equals(serveget_map_code))) {
                notPoint(ladder,ON_GRAVEL_AUDIO);
            }else {
                startAudio(ON_GRAVEL_AUDIO);
            }
        } else if (get_map_code.equals("山")) {
            if (!(get_map_code.equals(serveget_map_code))) {
                notPoint(ladder, ON_FALLEN_LEAVES_AUDIO);
            }else {
                startAudio(ON_FALLEN_LEAVES_AUDIO);
            }
        } else if (get_map_code .equals("海")) {
            if (!(get_map_code.equals(serveget_map_code))) {
                //notPointにもしたのサウンドを出すのを入れる。
                //notPointのtureをおすと、の場所で押すが選択されたときにの場所にサウンドを入れる。
                notPoint(ship,IN_SEA_AUDIO);
            }else {
                startAudio(IN_SEA_AUDIO);
            }
        } else if (get_map_code .equals("treasure_chest_ship")) {
            item_box ="宝箱";
            openTreasureChest(ship,OPEN_TREASURE_CHEST_AUDIO);
        } else if (get_map_code .equals("treasure_chest_ladder")) {
            item_box = "宝箱";
            openTreasureChest(ladder, OPEN_TREASURE_CHEST_AUDIO);
        } else if (get_map_code.equals("back_world")) {
            if (p.area.equals("民家1")){

            }
        } else if (get_map_code.equals("people_home_1")) {
            p.area = "民家1";
            startAudio(OPEN_DOOR_AUDIO);
            p.x = PERSON_HOME1_INITIAL_X;
            p.y = PERSON_HOME1_INITIAL_Y;
        } else if (get_map_code.equals("glass")) {
            startAudio(ON_GLASS_AUDIO);
        } else if (get_map_code.equals("stone")) {
            startAudio(ON_STONE_AUDIO);
        } else if (get_map_code.equals("wood")) {
            startAudio(ON_WOOD_AUDIO);
        }  else if (get_map_code .equals("errer")) {
            p.x = p.serve_x;
            p.y = p.serve_y;
            System.out.println("noooo");
        }
    }
    public int eventMonster(int monsteri){
        String monster_get_map_code = map.getMapCode(this.enemey_monster.x, this.enemey_monster.y,this.enemey_monster.area);
        if (monster_get_map_code.equals("errer")) {
            monsteri--;
            enemey_monster.x = enemey_monster.monster_serve_x;
            enemey_monster.y = enemey_monster.monster_serve_y;
        }else if (monster_get_map_code.equals("back_world")){
            if (enemey_monster.area.equals("民家1")){
                enemey_monster.x = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X;
                enemey_monster.y = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y;
            }else if (enemey_monster.area.equals("洞窟1")){
                p.x = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
                p.y = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
            }
            enemey_monster.area = "メインマップ";
        }else if (monster_get_map_code.equals("people_home_1")){
            enemey_monster.area = "民家1";
            enemey_monster.x = PERSON_HOME1_INITIAL_X;
            enemey_monster.y = PERSON_HOME1_INITIAL_Y;
        }else if (monster_get_map_code.equals("cave1")) {
            enemey_monster.area = "洞窟1";
            enemey_monster.x = CAVE1_INITIAL_X;
            enemey_monster.y = CAVE1_INITIAL_Y;
        } else if (monster_get_map_code.equals("cave1_1")) {
            enemey_monster.area = "洞窟1_1";
            enemey_monster.x = CAVE1_1_INITIAL_X;
            enemey_monster.y = CAVE1_1_INITIAL_Y;
        } else if (monster_get_map_code.equals("back_cave_1")) {
            if (enemey_monster.area.equals("洞窟1_1")){
                enemey_monster.x = CAVE1_1_BACK_CAVE1_INITIAL_X;
                enemey_monster.y = CAVE1_1_BACK_CAVE1_INITIAL_Y;
            }
            enemey_monster.area = "洞窟1";
        }
        return monsteri;
    }
    public void notPoint(Item item, File audio_file) {
        Scanner scanner = new Scanner(System.in);
        //map.oceanxそれかyの中の数字に該当する数字だった場合tureを返す
            int endflg = 0;
            //アイテム（はしごや船など）をインベントリで手に持ったまま崖や海のマスに進む
            while (item == p.have_item && endflg == 0) {
                System.out.println(item.name + "を使いますか？ 使う「ture」 使わない「false」");
                if (scanner.next().equals("ture")) {
                    System.out.println(item.name + "を使った！");
                    startAudio(audio_file);
                    endflg++;
                } else if (scanner.next().equals("false")) {
                    System.out.println("再度選んでください");
                    p.x = p.serve_x;
                    p.y = p.serve_y;
                    endflg++;
                } else {
                    System.out.println("tureかfalseを選んでください");
                }
            }
            if (endflg == 0) {
                System.out.println("再度選んでください");
                p.x = p.serve_x;
                p.y = p.serve_y;
            }
    }

    public void openTreasureChest(Item item,File audio_file) {
        Scanner scanner = new Scanner(System.in);
        int endflg = 0;
        while (endflg == 0) {
            System.out.println("これは" + item_box + "を開けますか？ 開ける「ture」 開けない「false」");
            if (scanner.next().equals("ture")) {
                if (item.have_number >= 1) {
                    System.out.println(item_box + "はすでに空っぽだ。再度選んでください");
                    endflg++;
                } else {
                    System.out.println(item_box + "を開けた！," + item.name + "を手に入れた");
                    item.have_number++;
                    item.have = true;
                    item.have_point++;
                    startAudio(audio_file);
                    if (item instanceof FightItem) {
                        p.fight_items.add((FightItem) item);
                    } else if (item instanceof FieldItem) {
                        p.field_items.add((FieldItem) item);
                    } else {
                        this.p.monster_items.add((MonsterItem) item);
                    }
                    p.items.add(item);
                    endflg++;
                }
            } else if (scanner.next().equals("false")) {
                System.out.println("再度選んでください");
                endflg++;
            } else {
                System.out.println("tureかfalseを選んでください");
            }
        }
        p.x = p.serve_x;
        p.y = p.serve_y;
    }
}