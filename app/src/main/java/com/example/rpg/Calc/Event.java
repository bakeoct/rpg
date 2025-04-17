package com.example.rpg.Calc;

import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.map.Map;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.map.PersonHome1.*;
import com.example.rpg.Calc.Monsters.EnemyMonster;
import com.example.rpg.Calc.map.tail.Tail;

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
    public Event( Map map, MissionDragonKing missionDragonKing){
        this.map = map;
        this.mission_dragon_king = missionDragonKing;
    }
    public void eventPerson(Tail serveget_map_code) {
        //これをmapに送って二つメソッド動かす
        Tail get_map_code = map.getMapCode(game.p.mpx, game.p.mpy,game.p.area);
        if (get_map_code.equals("grave")) {
            if (get_map_code.floor - serveget_map_code.floor == 1 || get_map_code.floor - serveget_map_code.floor == -1) {
                notPoint(game.store.ladder,get_map_code);
            }else {
                game.sound.startSounds(get_map_code);
            }
        } else if (get_map_code.equals("glass")) {
            if (serveget_map_code.equals("")){

            }
            if (!(get_map_code.equals(serveget_map_code))) {
                notPoint(game.store.ladder,get_map_code);
            }else {
                game.sound.startSounds(get_map_code);
            }
        } else if (get_map_code .equals("海")) {
            if (!(get_map_code.equals(serveget_map_code))) {
                notPoint(game.store.ship,get_map_code);
            }else {
                game.sound.startSounds(get_map_code);
            }
        } else if (get_map_code.equals("glass") || get_map_code.equals("wood") || get_map_code.equals("stone") || get_map_code.equals("cliff_on_glass")) {
            if (get_map_code.equals("cliff_on_glass")){
                get_map_code = "glass";
            }
            game.sound.startSounds(get_map_code);
        }  else if (get_map_code .equals("errer")) {
            game.p.mpx = game.p.serve_x;
            game.p.mpy = game.p.serve_y;
            System.out.println("noooo");
        }
    }
    public int eventMonster(int monsteri){
        String monster_get_map_code = map.getMapCode(game.enemy_monster.x, game.enemy_monster.y,game.enemy_monster.area);
        if (monster_get_map_code.equals("errer")) {
            monsteri--;
            game.enemy_monster.x = game.enemy_monster.monster_serve_x;
            game.enemy_monster.y = game.enemy_monster.monster_serve_y;
        }else if (monster_get_map_code.equals("back_world")){
            if (game.enemy_monster.area.equals("民家1")){
                game.enemy_monster.x = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X;
                game.enemy_monster.y = PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y;
            }else if (game.enemy_monster.area.equals("洞窟1")){
                game.p.mpx = CAVE1_BACK_MAIN_WORLD_INITIAL_X;
                game.p.mpy = CAVE1_BACK_MAIN_WORLD_INITIAL_Y;
            }
            game.enemy_monster.area = "メインマップ";
        }else if (monster_get_map_code.equals("people_home_1")){
            game.enemy_monster.area = "民家1";
            game.enemy_monster.x = PERSON_HOME1_INITIAL_X;
            game.enemy_monster.y = PERSON_HOME1_INITIAL_Y;
        }else if (monster_get_map_code.equals("cave1")) {
            game.enemy_monster.area = "洞窟1";
            game.enemy_monster.x = CAVE1_INITIAL_X;
            game.enemy_monster.y = CAVE1_INITIAL_Y;
        } else if (monster_get_map_code.equals("cave1_1")) {
            game.enemy_monster.area = "洞窟1_1";
            game.enemy_monster.x = CAVE1_1_INITIAL_X;
            game.enemy_monster.y = CAVE1_1_INITIAL_Y;
        } else if (monster_get_map_code.equals("back_cave_1")) {
            if (game.enemy_monster.area.equals("洞窟1_1")){
                game.enemy_monster.x = CAVE1_1_BACK_CAVE1_INITIAL_X;
                game.enemy_monster.y = CAVE1_1_BACK_CAVE1_INITIAL_Y;
            }
            game.enemy_monster.area = "洞窟1";
        }
        return monsteri;
    }
    public void notPoint(Item item, Tail get_map_code) {
        //アイテム（はしごや船など）をインベントリで手に持ったまま崖や海のマスに進む
        if (item == game.p.have_item) {
            game.sound.startSounds(get_map_code);
        }else {
            System.out.println("再度選んでください");
            game.p.mpx = game.p.serve_x;
            game.p.mpy = game.p.serve_y;
        }
    }
}
