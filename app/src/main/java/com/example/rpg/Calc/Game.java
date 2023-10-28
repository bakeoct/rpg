package com.example.rpg.Calc;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Item.monsteritem.DragonKingMerchandise;
import com.example.rpg.Calc.Item.monsteritem.GorlemMerchandise;
import com.example.rpg.Calc.Item.monsteritem.MetalSlimeMerchandise;
import com.example.rpg.Calc.Item.monsteritem.PutiSlimeMerchandise;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.map.Map;
import com.example.rpg.Calc.Monsters.*;
import com.example.rpg.Calc.map.World_map;
import com.example.rpg.graphic.GameActivity;

import java.io.Serializable;
import static com.example.rpg.Calc.Monsters.EnemeyMonster.enemey_monster;
import static com.example.rpg.Calc.Monsters.Monster2.getMonsterRandomly;
import static com.example.rpg.Calc.Person2.p;
import static com.example.rpg.graphic.GameActivity.monster_cara_now;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import android.widget.GridLayout;
import android.widget.ImageView;

//敵を一回倒したらその敵をエラーの場所以外のどこかへ飛ばしまた倒されたら別の場所にまた飛ばす
public class Game implements Serializable {
    public static Game game =new Game(enemey_monster);
    public static Monster2 get_enemey_monster=null;
    public Map map =new Map();
    public static MissionDragonKing mission_dragon_king =new MissionDragonKing();
    public Store store = new Store(p,mission_dragon_king);
    public Event event =new Event(p,map,mission_dragon_king,enemey_monster);
    public String serve_get_map_code = map.getMapCode(p.x, p.y, p.area);
    public Game(EnemeyMonster in_enemey_monster) {
        enemey_monster = in_enemey_monster;
    }
    public void readSave(){
        game = saveWriteAndRead.read();
    }
    public void gameTurn(GameActivity game_activity, GridLayout gridLayout,ImageView enemy_monster,ImageView yuusya,int image_size) {
        p.walk(yuusya);
        event.eventPerson(serve_get_map_code);
        p.graphic_walk(gridLayout,yuusya,p,image_size);
        p.serve_x = p.x;
        p.serve_y = p.y;
        int monsteri = 0;
        while (monsteri == 0) {
            enemey_monster.walk(game_activity,enemy_monster,p);
            monsteri++;
            monsteri = event.eventMonster(monsteri);
            enemey_monster.graphic_walk(gridLayout,enemy_monster,enemey_monster,image_size,p);
        }
        enemey_monster.monster_serve_x = enemey_monster.x;
        enemey_monster.monster_serve_y = enemey_monster.y;
        //if (p.x == enemeyMonster.x && p.y == enemeyMonster.y && p.area.equals(enemeyMonster.area)) {
        // System.out.println("!");
        //  System.out.println("モンスターと出会った！！");
        //  p.turnBattle(get_enemey_monster,mission_dragon_king);
        //  monster_cara_now = null;
        //  get_enemey_monster = getMonsterRandomly(gameActivity);
        //  enemeyMonster.randomNewEnemeyMonster(world_map);
        // }
    }
}