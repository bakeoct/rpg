package com.example.rpg.Calc.map;

import java.io.Serializable;

import static com.example.rpg.Calc.map.cave.Cave1.*;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
import static com.example.rpg.Calc.map.PersonHome1.*;
import static com.example.rpg.Calc.map.World_map.*;

    public class Map implements Serializable {
        public static final String TL = "treasure_chest_ladder";
        public static final String TS = "treasure_chest_ship";
        public static final String O = "海";//Ocean
        public static final String M = "山";//Mounten
        public static final String C = "崖";//Cliff
        public static final String G = "glass";//Glass
        public static final String E = "errer";//ErrerPoString
        public static final String S = "store";//Store
        public static final String BW = "back_world";//BackWorld
        public static final String W = "wood";//wood
        public static final String SN = "stone";//Stone;
        public static final String BCV1 = "back_cave_1";//BackCave1;

        public String getMapCode(int pointx, int pointy,String area) {
            String map_place_code = null;
            if (area.equals("メインマップ")){
                map_place_code = calc_world_map[pointy][pointx];
            }else if (area.equals("民家1")){
                map_place_code = people_home1[pointy][pointx];
            }else if (area.equals("洞窟1")){
                map_place_code = cave1[pointy][pointx];
            }else if (area.equals("洞窟1_1")){
                map_place_code = cave1_1[pointy][pointx];
            }
            return map_place_code;
        }
        public int[] getRange(String area){
            int[] map =new int[2];
            if (area.equals("メインマップ")){
                map[0] = 22;
                map[1] = 12;
            }else if (area.equals("民家1")){
                map[0] = 8;
                map[1] = 8;
            }else if (area.equals("洞窟1")){
                map[0] = 4;
                map[1] = 8;
            }else if (area.equals("洞窟1_1")){
                map[0] = 8;
                map[1] = 8;
            }
            return map;
        }
    }
