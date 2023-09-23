package com.example.rpg.Calc;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.map.Map;
import com.example.rpg.Calc.Monsters.*;
import com.example.rpg.Calc.map.World_map;
import com.example.rpg.graphic.GameActivity;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.WeakHashMap;

import static  com.example.rpg.Calc.Inventry.chooseItems;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemeyMonster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.Calc.Person2.p;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import android.widget.GridLayout;
import android.widget.ImageView;

//敵を一回倒したらその敵をエラーの場所以外のどこかへ飛ばしまた倒されたら別の場所にまた飛ばす
public class Game implements Serializable {
    public static Game game =new Game(enemeyMonster);
    public static Monster2 get_enemey_monster=null;
    public Ship ship = new Ship();
    public Ladder ladder = new Ladder();
    public Map map =new Map();
    public World_map world_map = new World_map();
    public static MissionDragonKing mission_dragon_king =new MissionDragonKing();
    public Store store = new Store(p,ship,ladder,mission_dragon_king);
    public Event event =new Event(p,map,ladder,ship,mission_dragon_king,enemeyMonster);
    public String serve_get_map_code = map.getMapCode(p.x, p.y, p.area,world_map);
    public Game(EnemeyMonster enemey_monster) {
        enemeyMonster = enemey_monster;
    }
    public void readSave(){
        game = saveWriteAndRead.read();
    }
    public void gameTurn(String place, GameActivity game_activity, GridLayout gridLayout,ImageView enemy_monster,ImageView yuusya,int image_size) {
            p.walk(place, yuusya);
            event.eventPerson(serve_get_map_code,world_map,game_activity);
            p.graphic_walk(gridLayout,yuusya,p,image_size);
            p.serve_x = p.x;
            p.serve_y = p.y;
            int monsteri = 0;
            while (monsteri == 0) {
                enemeyMonster.walk(game_activity,enemy_monster);
                monsteri++;
                monsteri = event.eventMonster(monsteri,world_map);
                enemeyMonster.graphic_walk(gridLayout,enemy_monster,enemeyMonster,image_size);
            }
            enemeyMonster.monster_serve_x = enemeyMonster.x;
            enemeyMonster.monster_serve_y = enemeyMonster.y;
            //if (p.x == enemeyMonster.x && p.y == enemeyMonster.y && p.area.equals(enemeyMonster.area)) {
               // System.out.println("!");
              //  System.out.println("モンスターと出会った！！");
              //  p.turnBattle(get_enemey_monster,mission_dragon_king);
              //  get_enemey_monster = getMonsterRandomly(gameActivity);
              //  enemeyMonster.randomNewEnemeyMonster(world_map);
           // }
    }
}