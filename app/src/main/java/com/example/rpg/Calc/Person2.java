package com.example.rpg.Calc;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.Mission.MissionSab;
import com.example.rpg.Calc.Monsters.*;
import com.example.rpg.Calc.map.World_map;
import com.example.rpg.R;
import com.example.rpg.graphic.GameActivity;
import static com.example.rpg.Calc.BattleManager.*;
import static com.example.rpg.Calc.map.PersonHome1.people_home1;
import static com.example.rpg.Calc.map.cave.Cave1.cave1;
import static com.example.rpg.Calc.map.cave.Cave1_1.cave1_1;

import android.widget.GridLayout;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class Person2 implements Serializable {
    public static Person2 p = new Person2("takumi","dannsei",new MetalSlime(),new Gorlem());
    public Level level =new Level();
    public ArrayList<Monster2> monsters2 =new ArrayList<Monster2>();
    public int lv=1;
    public int have_experince_point = 0;
    public int need_experince_point = 100;
    public static String what_did=null;
    public ArrayList<FieldItem> field_items =new ArrayList<FieldItem>();
    public ArrayList<MonsterItem> monster_items =new ArrayList<MonsterItem>();
    public ArrayList<FightItem> fight_items =new ArrayList<FightItem>();
    public ArrayList<Item> items =new ArrayList<>();
    public String name;
    public String seibetu;
    public int money=100;
    public Item have_item = null;
    public String area = "メインマップ";
    public int x=12;
    public int y=6;
    public int serve_x = 12;
    public int serve_y = 6;
    public int choose_item;
    public Person2(String namae, String seibetu2, MetalSlime metal_slime, Gorlem gorlem) {
        this.name = namae;
        this.seibetu = seibetu2;
        this.monsters2.add(metal_slime);
        this.monsters2.add(gorlem);
        items.addAll(field_items);
        items.addAll(fight_items);
        items.addAll(monster_items);
            if (this.seibetu.equals("dannsei")) {
                System.out.print(namae + "くんが持っているはモンスターは(モンスター");
                for (Monster2 mons : monsters2) {
                    System.out.print("," + mons.name);
                }
                System.out.println(")です");
                for (FightItem fight_item : fight_items) {
                    System.out.println("そして、" + namae + "君は" + fight_item.name + "を持っています");
                }
                for (MonsterItem monster_item : monster_items){
                    System.out.println("そして、" + namae + "君は" + monster_item.name + "を持っています");
                }
                for (FieldItem field_item : field_items){
                    System.out.println("そして、" + namae + "君は" + field_item.name + "を持っています");
                }
            }
            if (this.seibetu.equals("josei")) {
                System.out.print(namae + "さんが持っているはモンスターは(モンスター");
                for (Monster2 mons : monsters2) {
                    System.out.print("," + mons.name);
                }
                System.out.println(")です");
                for (FightItem fight_item : fight_items) {
                    System.out.println("そして、" + namae + "君は" + fight_item.name + "を持っています");
                }
                for (MonsterItem monster_item : monster_items){
                    System.out.println("そして、" + namae + "君は" + monster_item.name + "を持っています");
                }
                for (FieldItem field_item : field_items){
                    System.out.println("そして、" + namae + "君は" + field_item.name + "を持っています");
                }
            }
            if (!(this.seibetu.equals("dannsei") || this.seibetu.equals("josei"))) {
                System.out.println("(josei)または(dannsei)を入力してください。");
            }
    }
    public void walk(String place, ImageView yuusya){
        if (place.equals("right")){
            this.x++;
            yuusya.setImageResource(R.drawable.yuusya_right);
        }else if (place.equals("left")) {
            this.x--;
            yuusya.setImageResource(R.drawable.yuusya_left);
        }else if (place.equals("under")){
            this.y++;
            yuusya.setImageResource(R.drawable.yuusya);
        }else if (place.equals("over")) {
            this.y--;
            yuusya.setImageResource(R.drawable.yuusya_usiro);
        }
    }
    public void turnBattle(Monster2 enemeymonster,  MissionDragonKing missionDragon_king) {
        MissionSab missionSab =new MissionSab();
        int win_flg =0;
        for (Monster2 mons : this.monsters2) {

            //能力上昇で死ぬ、だから防御値を設定して割合でダメ－ジを軽減するのに変えるHP上限値を作る

            itemsStatus(mons);
            what_did = battle(enemeymonster,mons,what_did,this);
            goBackStatus(mons);
            if (what_did.equals("goback")){
                System.out.println(this.name+"は逃げ出した");
                win_flg++;
                break;
            }
            if (enemeymonster.hp<=0){
                for (Monster2 monsters : this.monsters2) {
                    monsters.have_experince_point += enemeymonster.can_get_experince_point;
                }
                this.have_experince_point += enemeymonster.can_get_experince_point;
                level.upLevel(this);
                System.out.println("勝利した");
                win_flg++;
                System.out.println(missionDragon_king.progress);
                if (enemeymonster.name.equals("竜王") && missionDragon_king.progress){
                    missionSab.missionProgres(missionDragon_king);
                    System.out.println(missionDragon_king.name+"を達成した！");
                    System.out.println("お店で報酬をもらおう！");
                }
                break;
            }
        }
        if (win_flg == 0){
            System.out.println("負けてしまった");
        }
    }
    public void graphic_walk(GridLayout gridLayout,ImageView yuusya,Person2 p,int image_size){
        if (area.equals("メインマップ")){
            yuusya.setX(gridLayout.getX() + image_size * p.x);
            yuusya.setY(gridLayout.getY() + image_size * p.y);
        }
    }
}
