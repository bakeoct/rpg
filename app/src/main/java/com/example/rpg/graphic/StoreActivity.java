package com.example.rpg.graphic;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import static com.example.rpg.Calc.Game.game;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Store;
import com.example.rpg.R;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity implements Serializable {
    boolean buy_push_button = false;
    boolean sell_push_button = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ArrayList<TextView> display_buyitems =new ArrayList<>();
        ArrayList<TextView> display_sellitems =new ArrayList<>();
        TextView buy =findViewById(R.id.buy);
        TextView sell =findViewById(R.id.sell);
        TextView talk =findViewById(R.id.talk);
        TextView misstion =findViewById(R.id.misstion);
        TextView go_shopping =findViewById(R.id.go_shopping);
        LinearLayout display_shopping_items = findViewById(R.id.group_items);
        LinearLayout serifs = findViewById(R.id.serifs);
        TextView serif =new TextView(this);

        serif.setText("いらっしゃい！");
        serifs.addView(serif);
        //ready_buy
        for (int j=0;j<game.store.items_all.size();j++) {
            TextView shopping_item = new TextView(this);
            shopping_item.setTextColor(Color.WHITE);
            shopping_item.setText(game.store.items_all.get(j).name);
            display_buyitems.add(shopping_item);
        }
        EditText buy_choose_number = new EditText(this);
        Button buy_enter = new Button(this);
        buy_enter.setText("決定");
        //ready_sell
        for (Item item : game.store.p.items) {
            TextView shopping_item = new TextView(this);
            shopping_item.setTextColor(Color.WHITE);
            shopping_item.setText(item.name);
            display_sellitems.add(shopping_item);
        }
        Button sell_enter = new Button(this);
        sell_enter.setText("決定");
        EditText sell_choose_number = new EditText(this);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serif.setText("何を買うんだ？");
                ArrayList<Item> buyitems = new ArrayList<>();
                buyitems.clear();
                System.out.println(game.store.items_all.size()+"aaaaaaaaaaaaaaaaaaaaaaa");
                for (int i=0;i<game.store.items_all.size();i++) {
                    if (game.store.items_all.get(i).item_lv <= game.store.store_lv) {
                        display_shopping_items.addView(display_buyitems.get(i));
                        buyitems.add(game.store.items_all.get(i));
                    }
                }
                while (true) {
                    for (int i = 0; i < buyitems.size(); i++) {
                        int shop_J = i;
                        display_shopping_items.getChildAt(shop_J).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView serif = (TextView) serifs.getChildAt(0);
                                serif.setText("何個買うんだ？");
                                serifs.addView(buy_enter);
                                serifs.addView(buy_choose_number);
                                buy_push_button = true;
                                game.store.buy(display_shopping_items, serifs, buyitems, shop_J, sell_enter, serif, sell_choose_number);
                            }
                        });
                    }
                    if (buy_push_button){
                        break;
                    }
                }
                display_shopping_items.removeAllViews();
                serif.setText("他はどうだ？");
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serif.setText("何を売るんだ？");
                ArrayList<Item> sellitems = new ArrayList<>();
                for (int i = 0; i < game.store.p.items.size(); i++) {
                    display_shopping_items.addView(display_sellitems.get(i));
                    sellitems.add(game.store.p.items.get(i));
                }

                while (true){
                    for (int i = 0; i < sellitems.size(); i++) {
                        int sell_items = i;
                        display_shopping_items.getChildAt(sell_items).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView serif = (TextView) serifs.getChildAt(0);
                                serif.setText("何個買うんだ？");
                                serifs.addView(sell_choose_number);
                                serifs.addView(sell_enter);
                                game.store.sell(display_shopping_items, serifs, sellitems, sell_items, sell_enter, serif, sell_choose_number);
                                sell_push_button = true;
                            }
                        });
                    }
                    if (sell_push_button) {
                        break;
                    }
                }
                display_shopping_items.removeAllViews();
                serif.setText("他はどうだ？");
            }
        });
        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        misstion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        go_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void display_buy(LinearLayout display_shopping_items,LinearLayout serifs,ArrayList<TextView>display_buyitems){

    }
    public void display_sell(LinearLayout display_shopping_items,LinearLayout serifs,ArrayList<TextView>display_sellitems) {

    }
}
