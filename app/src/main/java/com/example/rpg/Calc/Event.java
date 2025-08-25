package com.example.rpg.Calc;

import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;

import static com.example.rpg.Calc.Game.game;

import com.example.rpg.Calc.map.tail.Tail;

import java.io.Serializable;

import static com.example.rpg.graphic.TransitionActivity.from_activity;

public class Event implements Serializable {
    public MissionDragonKing mission_dragon_king;

    public Event(MissionDragonKing missionDragonKing) {
        this.mission_dragon_king = missionDragonKing;
    }

    public boolean haveItemDiagnosis(Item item, Tail get_map_code) {
        boolean cant_enter = false;
        //アイテム（はしごや船など）をインベントリで手に持ったまま崖や海のマスに進む
        if (item == game.player.have_item) {
            game.sound.startSounds(get_map_code.tail_id);
        } else {
            cant_enter = true;
        }
        return cant_enter;
    }

    public boolean notAppear(float world_x, float world_y) {
        for (int i = 0; i < from_activity.map.length; i++) {
            for (int j = 0; j < from_activity.map[i].length; j++) {
                if (world_x < from_activity.map[i][j].x_end
                        && world_x + game.image_size > from_activity.map[i][j].x_start
                        && world_y < from_activity.map[i][j].y_end
                        && world_y + game.image_size > from_activity.map[i][j].y_start) {
                    System.out.println("Y座標" + i);
                    System.out.println("X座標" + j);
                    System.out.println(from_activity.map[i][j].image.getX());
                    if (from_activity.map[i][j].tail_id.equals("error") || from_activity.map[i][j].tail_id.equals("ocean")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean notPlayerEnter(float x, float y) {
        Tail get_map_code = from_activity.map[game.player.mpy][game.player.mpx];
        for (int i = 0; i < from_activity.map.length; i++) {
            for (int j = 0; j < from_activity.map[i].length; j++) {
                if (x < from_activity.map[i][j].x_end
                    && x + game.image_size > from_activity.map[i][j].x_start
                    && y < from_activity.map[i][j].y_end
                    && y + game.image_size > from_activity.map[i][j].y_start) {
                    System.out.println("a"+from_activity.map[i][j].tail_id);
                    System.out.println("b"+get_map_code.tail_id);
                    System.out.println("Y" + i);
                    System.out.println("x" + j);
                    if (from_activity.map[i][j].tail_id.equals("error")) {//移動先がエラーゾーンやったときに実行
                        System.out.println("e");
                        return true;
                    } else if (from_activity.map[i][j].tail_id.equals("ocean")) {//移動先が海やったときに実行
                        System.out.println("o");
                        return haveItemDiagnosis(game.store.ship, get_map_code);
                    } else if (get_map_code.floor - from_activity.map[i][j].floor == +-1) { //前の座標との高さ関係
                        System.out.println("c");
                        return haveItemDiagnosis(game.store.ladder, get_map_code);
                    } else if (get_map_code.floor - from_activity.map[i][j].floor == 0) {
                        System.out.println("safe");
                        game.sound.startSounds(get_map_code.tail_id);
                    } else {
                        System.out.println("a");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean notMonsterEnter(Monster monster,float x,float y) {
        Tail get_map_code = from_activity.map[monster.mpy][monster.mpx];
        for (int i = 0; i < from_activity.map.length; i++) {
            for (int j = 0; j < from_activity.map[i].length; j++) {
                if (monster.world_x + x< from_activity.map[i][j].x_end
                        && monster.world_x + x + game.image_size > from_activity.map[i][j].x_start
                        && monster.world_y + y < from_activity.map[i][j].y_end
                        && monster.world_y + y + game.image_size > from_activity.map[i][j].y_start) {
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
