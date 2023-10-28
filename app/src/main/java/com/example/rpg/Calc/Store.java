package com.example.rpg.Calc;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import static com.example.rpg.Calc.Game.game;
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
import com.example.rpg.Calc.treasure.TreasureChestLadder;
import com.example.rpg.R;
import com.example.rpg.graphic.StoreActivity;
import android.view.ViewGroup;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Store implements Serializable {
    public int store_lv=1;
    public Ship ship = new Ship();
    public Ladder ladder = new Ladder();
    public PutiSlimeMerchandise puti_slime_merchandise = new PutiSlimeMerchandise();
    public DragonKingMerchandise dragon_king_merchandise =new DragonKingMerchandise();
    public MetalSlimeMerchandise metal_slime_merchandise =new MetalSlimeMerchandise();
    public GorlemMerchandise gorlem_merchandise =new GorlemMerchandise();
    public HealGlass heal_glass = new HealGlass();
    public SteelArmor steel_armor = new SteelArmor();
    public SuperSword super_sword = new SuperSword();
    public Person2 p;
    public ArrayList<Item> items_all =new ArrayList<>();
    public ArrayList<MonsterItem> monster_items_all =new ArrayList<>();
    public ArrayList<FightItem> fight_items_all = new ArrayList<FightItem>();
    public ArrayList<Mission> mission_all =new ArrayList<>();
    public Store(Person2 p,MissionDragonKing missionDragonKing) {
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
    public void pushToBuy(LinearLayout shopping_items,ArrayList<Item> buyitems,int shop_J,Button enter,TextView serif,EditText choose_number) {
            enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   chooseCorrectNumber(shopping_items,buyitems,shop_J,enter,serif,choose_number);
                }
            });
    }
    private void chooseCorrectNumber(LinearLayout shopping_items,ArrayList<Item> buyitems,int shop_J,Button enter,TextView serif,EditText choose_number){
        try {
            int buy_number = Integer.parseInt(choose_number.getText().toString());
            boolean correct = buy_number >= 1;
            if (correct) {
                buy_click((TextView) shopping_items.getChildAt(shop_J), buyitems, buy_number,serif);
                choose_number.setVisibility(View.GONE);
                enter.setVisibility(View.GONE);
            } else {
                serif.setText("自然数で答えてくれ");
            }
        } catch (Exception e) {
            serif.setText("数字で答えてくれ");
        }
    }
    public void sell (LinearLayout shopping_items,LinearLayout serifs,int sell_items,Button enter,TextView serif,EditText choose_number) {
                    enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean correct = false;
                            try {
                                int sell_number = Integer.parseInt(choose_number.getText().toString());
                                if (sell_number >= 1) {
                                    if (game.store.p.items.get(sell_items).have_point >= sell_number){
                                        sell_click((TextView) shopping_items.getChildAt(sell_items), sell_number,serif);
                                        correct = true;
                                    }else {
                                        serif.setText("そんなに持ってないぞ");
                                    }
                                }else {
                                    serif.setText("自然数で答えてくれ");
                                }
                            }catch (Exception e){
                                serif.setText("数字で答えてくれ");
                            }
                            if (correct) {
                                choose_number.setVisibility(View.GONE);
                                enter.setVisibility(View.GONE);
                            }
                        }
                    });
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
        public static boolean getAfterMoney (Item item, int buy_number){
        boolean have_enough_money = game.store.p.money >= item.buy_price * buy_number;
            if (have_enough_money) {
                game.store.p.money -= item.buy_price * buy_number;
                getItems(item, buy_number);
            }
            return have_enough_money;
        }
        public static int getItems(Item item,int buy_number){
            if (item.have_point == 0) {
                item.have = true;
                for (MonsterItem alive_item : game.store.monster_items_all) {
                    if (alive_item == item) {
                        game.store.p.monsters2.add(inMonster(item));
                    }
                }
                if (item instanceof FightItem) {
                    game.store.p.fight_items.add((FightItem) item);
                } else if (item instanceof FieldItem) {
                    game.store.p.field_items.add((FieldItem) item);
                } else {
                    game.store.p.monster_items.add((MonsterItem) item);
                }
                game.store.p.items.add(item);
            }
            item.have_point += buy_number;
            return item.have_point;
        }
        public void buyMessage(int buy_number,TextView serif,Item item,boolean have_enough_money){
            if (have_enough_money) {
                serif.setText(p.name + "は" + item.name + "を"+ buy_number + "個買った");
            } else {
                serif.setText("金が足んねえ\n、、、出直して来な");
            }
        }
        public void sellMath (Item item, int sell_number,TextView serif){
            p.money += item.sell_price * sell_number;
            item.have_point -= sell_number;
            serif.setText(p.name + "は" + item.name + "を" + "売った");
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
        public static Monster2 inMonster (Item item){
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
        public void buy_click(TextView want_item,ArrayList<Item> buy_items,int buy_number,TextView serif){
        for (Item item : buy_items) {
            if (want_item.getText().equals(item.name)) {
                boolean have_enough_money = getAfterMoney(item, buy_number);
                buyMessage(buy_number, serif, item, have_enough_money);
            }
        }
    }
    public void sell_click(TextView want_sell_item,int sell_number,TextView serif){
        for (Item item : game.store.p.items) {
            if (want_sell_item.getText().equals(item.name)) {
                sellMath(item, sell_number,serif);
                System.out.println("他はどうだ？");
            }
        }
    }
}
