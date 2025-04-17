package com.example.rpg.Calc;

import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.Mission.MissionSab;
import com.example.rpg.Calc.map.Map;
import com.example.rpg.Calc.Monsters.*;
import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.sound.MediaPlayerManager;
import com.example.rpg.sound.Sound;

import java.io.Serializable;


import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import android.widget.ImageView;

//敵を一回倒したらその敵をエラーの場所以外のどこかへ飛ばしまた倒されたら別の場所にまた飛ばす
public class Game implements Serializable {
    public int image_size;
    public int margin;
    public Sound sound = new Sound();
    public MediaPlayerManager mpm = new MediaPlayerManager();//mpmはMediaPlayerManagerの略称
    public Action action = new Action();
    public static Game game =new Game();
    public Person p = new Person();
    public EnemyMonster enemy_monster =new EnemyMonster();
    public Monster2 get_enemy_monster=null;
    public Map map =new Map();
    public Level level =new Level();
    public MissionSab mission_sab = new MissionSab();
    public BattleManager battle_manager = new BattleManager();
    public MissionDragonKing mission_dragon_king =new MissionDragonKing();
    public Store store = new Store(mission_dragon_king);
    public Event event =new Event(map,mission_dragon_king);
    public void readSave(){
        game = saveWriteAndRead.read();
    }
    public void gameTurn() {
        Tail serve_get_map_code = map.getMapCode(p.mpx, p.mpy, p.area);
        p.mpWalk();
        event.eventPerson(serve_get_map_code);
//        p.graphic_walk(gridLayout,hero,p,image_size);
        p.serve_x = p.mpx;
        p.serve_y = p.mpy;
        int monsteri = 0;
        while (monsteri == 0) {
            enemy_monster.walk((ImageView) game.enemy_monster.image,p);
            monsteri++;
            monsteri = event.eventMonster(monsteri);
            enemy_monster.graphic_walk();
        }
        enemy_monster.monster_serve_x = enemy_monster.x;
        enemy_monster.monster_serve_y = enemy_monster.y;
        if (p.mpx == enemy_monster.x && p.mpy == enemy_monster.y && p.area.equals(enemy_monster.area)) {
          game.battle_manager.meet_enemy_monster = true;
        }
    }
}