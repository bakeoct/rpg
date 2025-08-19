package com.example.rpg.Calc;

import com.example.rpg.Calc.Entity.Entity;
import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.Calc.map.Map;

import static com.example.rpg.Calc.Game.game;

import com.example.rpg.Calc.map.tail.Tail;

import java.io.Serializable;

import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.widget.ImageView;

public class Event implements Serializable{
    public Map map;
    public MissionDragonKing mission_dragon_king;
    public Event( Map map, MissionDragonKing missionDragonKing){
        this.map = map;
        this.mission_dragon_king = missionDragonKing;
    }
    public boolean haveItemDiagnosis(Item item, Tail get_map_code) {
        boolean cant_enter = false;
        //アイテム（はしごや船など）をインベントリで手に持ったまま崖や海のマスに進む
        if (item == game.player.have_item) {
            game.sound.startSounds(get_map_code.tail_id);
        }else {
            cant_enter = true;
        }
        return cant_enter;
    }
    public boolean notAppear(float x,float y) {
        for (int i = 0; i < from_activity.map.length;i++){
            for(int j = 0; j < from_activity.map[i].length; j++){
                if (x < (from_activity.map[i][j].image.getX() + game.image_size)
                        && x + game.image_size > (from_activity.map[i][j].image.getX())
                        && y < (from_activity.map[i][j].image.getY() + game.image_size)
                        && y + game.image_size > (from_activity.map[i][j].image.getY())) {
                    System.out.println("Y座標"+i);
                    System.out.println("X座標"+j);
                    System.out.println(from_activity.map[i][j].image.getX());
                    if (from_activity.map[i][j].tail_id.equals("error") || from_activity.map[i][j].tail_id.equals("ocean")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean notPlayerEnter(float x,float y){
        Tail get_map_code = from_activity.map[game.player.mpy][game.player.mpx];
        for (int i = 0; i < from_activity.map.length;i++){
            for(int j = 0; j < from_activity.map[i].length; j++){
                if (x < (from_activity.map[i][j].image.getX() + game.image_size)
                        && x + game.image_size > (from_activity.map[i][j].image.getX())
                        && y < (from_activity.map[i][j].image.getY() + game.image_size)
                        && y + game.image_size > (from_activity.map[i][j].image.getY())) {
                    if (from_activity.map[i][j].tail_id.equals("error")) {//移動先がエラーゾーンやったときに実行
                        return true;
                    } else if (from_activity.map[i][j].tail_id.equals("ocean")) {//移動先が海やったときに実行
                            return haveItemDiagnosis(game.store.ship, get_map_code);
                    }else if (get_map_code.floor - from_activity.map[i][j].floor == +-1) { //前の座標との高さ関係
                        return haveItemDiagnosis(game.store.ladder, get_map_code);
                    } else if (get_map_code.floor - from_activity.map[i][j].floor == 0) {
                        game.sound.startSounds(get_map_code.tail_id);
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean notMonsterEnter(Monster monster){
        Tail get_map_code = from_activity.map[monster.mpy][monster.mpx];
        for (int i = 0; i < from_activity.map.length;i++){
            for(int j = 0; j < from_activity.map[i].length; j++){
                if (monster.x < (from_activity.map[i][j].image.getX() + game.image_size)
                        && monster.x + game.image_size > (from_activity.map[i][j].image.getX())
                        && monster.y < (from_activity.map[i][j].image.getY() + game.image_size)
                        && monster.y + game.image_size > (from_activity.map[i][j].image.getY())){
                    if (from_activity.map[i][j].tail_id.equals("error")
                            || from_activity.map[i][j].tail_id.equals("ocean")
                            || get_map_code.floor - from_activity.map[i][j].floor != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
