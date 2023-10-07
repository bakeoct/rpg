package com.example.rpg.Calc;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Item.*;
import com.example.rpg.Calc.Item.monsteritem.DragonKingMerchandise;
import com.example.rpg.Calc.Item.monsteritem.GorlemMerchandise;
import com.example.rpg.Calc.Item.monsteritem.MetalSlimeMerchandise;
import com.example.rpg.Calc.Item.monsteritem.PutiSlimeMerchandise;
import com.example.rpg.Calc.Mission.Mission;
import com.example.rpg.Calc.Mission.MissionDragonKing;
import com.example.rpg.Calc.Mission.MissionSab;
import com.example.rpg.Calc.Monsters.*;
import com.example.rpg.R;
import com.example.rpg.graphic.StoreActivity;
import android.view.ViewGroup;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Store implements Serializable {
    public int store_lv=1;
    public Person2 p;
    public StoreActivity storeActivity =new StoreActivity();
    public ArrayList<Item> items_all =new ArrayList<>();
    public ArrayList<MonsterItem> monster_items_all =new ArrayList<>();
    public ArrayList<FightItem> fight_items_all = new ArrayList<FightItem>();
    public SuperSword super_sword = new SuperSword();
    public PutiSlimeMerchandise puti_slime_merchandise = new PutiSlimeMerchandise();
    public DragonKingMerchandise dragon_king_merchandise =new DragonKingMerchandise();
    public MetalSlimeMerchandise metal_slime_merchandise =new MetalSlimeMerchandise();
    public GorlemMerchandise gorlem_merchandise =new GorlemMerchandise();
    public HealGlass heal_glass = new HealGlass();
    public SteelArmor steel_armor = new SteelArmor();
    public ArrayList<Mission> mission_all =new ArrayList<>();
    public Store(Person2 p,Ship ship,Ladder ladder,MissionDragonKing missionDragonKing) {
        this.p = p;
        this.items_all.add(ship);
        this.items_all.add(ladder);
        this.items_all.add(puti_slime_merchandise);
        this.items_all.add(dragon_king_merchandise);
        this.items_all.add(metal_slime_merchandise);
        this.items_all.add(gorlem_merchandise);
        this.items_all.add(heal_glass);
        this.items_all.add(steel_armor);
        this.items_all.add(super_sword);
        this.fight_items_all.add(heal_glass);
        this.fight_items_all.add(steel_armor);
        this.fight_items_all.add(super_sword);
        this.monster_items_all.add(puti_slime_merchandise);
        this.monster_items_all.add(dragon_king_merchandise);
        this.monster_items_all.add(metal_slime_merchandise);
        this.monster_items_all.add(gorlem_merchandise);
        this.mission_all.add(missionDragonKing);
    }
    public void buy(LinearLayout shopping_items,LinearLayout serifs,ArrayList<Item> buyitems,int shop_J,Button enter,TextView serif,EditText choose_number) {
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int buy_number = Integer.parseInt(choose_number.getText().toString());
                    if (buy_number >= 1) {
                        buy_click((TextView) shopping_items.getChildAt(shop_J), buyitems, buy_number);
                    }else {
                        serif.setText("正の数で答えてくれ");
                    }
                }catch (Exception e){
                    serif.setText("数字で答えてくれ");
                }
            }
        });
        serifs.removeView(choose_number);
        serifs.removeView(enter);
    }

    public void sell (LinearLayout shopping_items,LinearLayout serifs,ArrayList<Item> sellitems,int sell_items,Button enter,TextView serif,EditText choose_number) {
                    enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                int sell_number = Integer.parseInt(choose_number.getText().toString());
                                if (sell_number >= 1) {
                                    if (sellitems.get(sell_items).have_point >= sell_number){
                                        sell_click((TextView) shopping_items.getChildAt(sell_items), sellitems, sell_number);
                                    }else {
                                        serif.setText("そんなに持ってないぞ");
                                    }
                                }else {
                                    serif.setText("自然数で答えてくれ");
                                }
                            }catch (Exception e){
                                serif.setText("数字で答えてくれ");
                            }
                        }
                    });
                    serifs.removeView(choose_number);
                    serifs.removeView(enter);

    }
        public void mission () {
            Boolean endflg = false;
            MissionSab missionSab = new MissionSab();
            for (Mission mission : this.mission_all) {
                if (mission.get_reward) {
                    endflg = true;
                    mission.get_reward = false;
                    System.out.println("お！、お前" + mission.name + "のミッションを達成しているな");
                    System.out.println("ほら報酬だ！");
                    p.money += mission.reward;
                }
            }
            if (!endflg) {
                System.out.println("ミッションを受けるんだな");
                missionSab.receive(p, this.mission_all);
                System.out.println(this.mission_all.get(0).progress);
            }
        }
        public void talk () {
            System.out.println("いい天気ですね");
        }
        public void go () {
            System.out.println("じゃあな");
        }
        public void buyMath (Item item, ArrayList <MonsterItem> monster_items_all,int buy_number){
            if (p.money >= item.buy_price * buy_number) {
                p.money -= item.buy_price * buy_number;
                System.out.println(p.name + "は" + item.name + "を" + "買った");
                if (item.have_point == 0) {
                    item.have = true;
                    for (MonsterItem alive_item : monster_items_all) {
                        if (alive_item == item) {
                            p.monsters2.add(inMonster(item));
                        }
                    }
                    if (item instanceof FightItem) {
                        p.fight_items.add((FightItem) item);
                    } else if (item instanceof FieldItem) {
                        p.field_items.add((FieldItem) item);
                    } else {
                        p.monster_items.add((MonsterItem) item);
                    }
                    p.items.add(item);
                }
                item.have_point += buy_number;
            } else {
                System.out.println("金が足んねえ");
                System.out.println("、、、出直して来な");
            }
        }
        public void sellMath (Item item, ArrayList <MonsterItem> monster_items_all,int sell_number){
            p.money += item.sell_price * sell_number;
            item.have_point -= item.sell_price * sell_number;
            System.out.println(p.name + "は" + item.name + "を" + "売った");
            if (item.have_point == 0) {
                item.have = false;
                for (int i = 0; i < p.monsters2.size(); i++) {
                    if (p.monsters2.get(i).name.equals(item.name)) {
                        p.monsters2.remove(i);
                    }
                }
                for (int i = 0; i < p.items.size(); i++) {
                    if (p.items.get(i) == item) {
                        p.items.remove(i);
                    }
                }
            }
        }
        public Monster2 inMonster (Item item){
            Monster2 monster2 = null;
            PutiSlime puti_slime = new PutiSlime();
            MetalSlime metal_slime = new MetalSlime();
            Gorlem gorlem = new Gorlem();
            DragonKing dragon_king = new DragonKing();
            if (item.name.equals("プチスライム")) {
                monster2 = puti_slime;
            } else if (item.name.equals("ゴーレム")) {
                monster2 = gorlem;
            } else if (item.name.equals("竜王")) {
                monster2 = dragon_king;
            } else if (item.name.equals("メタルスライム")) {
                monster2 = metal_slime;
            }
            return monster2;
        }
        public void buy_click(TextView want_item,ArrayList<Item> buy_items,int buy_number){
        for (Item item : buy_items) {
            if (want_item.getText().equals(item.name)) {
                buyMath(item, monster_items_all,buy_number);
                System.out.println("他はどうだ？");
            }
        }
    }
    public void sell_click(TextView want_sell_item,ArrayList<Item> sell_items,int sell_number){
        for (Item item : sell_items) {
            if (want_sell_item.getText().equals(item.name)) {
                sellMath(item, monster_items_all,sell_number);
                System.out.println("他はどうだ？");
            }
        }
    }
}
