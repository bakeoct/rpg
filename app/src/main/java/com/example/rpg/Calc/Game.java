package com.example.rpg.Calc;

import com.example.rpg.Calc.Entity.Player;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.Mission.MissionSab;
import com.example.rpg.Calc.ai.NavMesh;
import com.example.rpg.Calc.map.Map;
import com.example.rpg.Calc.Entity.Monsters.*;
import com.example.rpg.sound.MediaPlayerManager;
import com.example.rpg.sound.Sound;

import java.io.Serializable;


import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

//敵を一回倒したらその敵をエラーの場所以外のどこかへ飛ばしまた倒されたら別の場所にまた飛ばす
public class Game implements Serializable {
    public NavMesh navmesh = new NavMesh();
    public int image_size;
    public int margin;
    public Sound sound = new Sound();
    public MediaPlayerManager mpm = new MediaPlayerManager();//mpmはMediaPlayerManagerの略称
    public Action action = new Action();
    public static Game game =new Game();
    public Player player = new Player();
    public Map map =new Map();
    public Level level =new Level();
    public MissionSab mission_sab = new MissionSab();
//    public BattleManager battle_manager = new BattleManager();
    public MissionDragonKing mission_dragon_king =new MissionDragonKing();
    public Store store = new Store(mission_dragon_king);
    public Event event =new Event(map,mission_dragon_king);
    public MonsterManager monster_manager = new MonsterManager();
    public void readSave(){
        game = saveWriteAndRead.read();
    }
}