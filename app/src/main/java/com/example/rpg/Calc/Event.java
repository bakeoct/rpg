package com.example.rpg.Calc;

import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.map.Map;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.map.PersonHome1.*;
import com.example.rpg.Calc.Monsters.EnemeyMonster;

import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.map.cave.Cave1.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import static com.example.rpg.sound.Sound.*;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_X;
import static com.example.rpg.Calc.map.cave.Cave1.CAVE1_BACK_MAIN_WORLD_INITIAL_Y;

import android.media.MediaPlayer;
import android.media.SoundPool;

public class Event implements Serializable{
    public Map map;
    public MissionDragonKing mission_dragon_king;
    public EnemeyMonster enemey_monster;
    public Event( Map map, MissionDragonKing missionDragonKing, EnemeyMonster enemeyMonster){
        this.map = map;
        this.mission_dragon_king = missionDragonKing;
        this.enemey_monster = enemeyMonster;
    }
    public void eventPerson(String serveget_map_code, ArrayList<MediaPlayer> audio) {
        //これをmapに送って二つメソッド動かす
        String get_map_code = map.getMapCode(game.p.x, game.p.y,game.p.area);
        if (get_map_code.equals("崖") || get_map_code.equals("cliff_on_jyari")) {
            if (get_map_code.equals("cliff_on_jyari")) {
                get_map_code = "崖";
            }
            if (!(get_map_code.equals(serveget_map_code))) {
                notPoint(game.store.ladder,get_map_code,audio);
            }else {
                sound.startSounds(get_map_code,audio);
            }
        } else if (get_map_code.equals("山")) {
            if (!(get_map_code.equals(serveget_map_code))) {
                notPoint(game.store.ladder,get_map_code,audio);
            }else {
                sound.startSounds(get_map_code,audio);
            }
        } else if (get_map_code .equals("海")) {
            if (!(get_map_code.equals(serveget_map_code))) {
                notPoint(game.store.ship,get_map_code,audio);
            }else {
                sound.startSounds(get_map_code,audio);
            }
        } else if (get_map_code.equals("glass") || get_map_code.equals("wood") || get_map_code.equals("stone") || get_map_code.equals("cliff_on_glass")) {
            if (get_map_code.equals("cliff_on_glass")){
                get_map_code = "glass";
            }
            sound.startSounds(get_map_code,audio);
        }  else if (get_map_code .equals("errer")) {
            game.p.x = game.p.serve_x;
            game.p.y = game.p.serve_y;
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
                game.p.x = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
                game.p.y = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
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
    public void notPoint(Item item, String get_map_code,ArrayList<MediaPlayer> audio) {
        //アイテム（はしごや船など）をインベントリで手に持ったまま崖や海のマスに進む
        if (item == game.p.have_item) {
            sound.startSounds(get_map_code,audio);
        }else {
            System.out.println("再度選んでください");
            game.p.x = game.p.serve_x;
            game.p.y = game.p.serve_y;
        }
    }
}
