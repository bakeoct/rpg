package com.example.rpg.graphic;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.map_activity.GameActivity.game_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Item.Item;
import com.example.rpg.R;
import com.example.rpg.sound.MediaPlayerManager;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity implements Serializable {
    public static StoreActivity store_activity = new StoreActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        MediaPlayerManager.media_player.stop();
        MediaPlayerManager.media_player.release();
        MediaPlayerManager.media_player = MediaPlayer.create(this, R.raw.storemusic);
        MediaPlayerManager.media_player.setVolume(0.5f,0.5f);
        MediaPlayerManager.media_player.start();
        ArrayList<TextView> display_buyitems =new ArrayList<>();
        ArrayList<TextView> display_sellitems =new ArrayList<>();
        TextView buy =findViewById(R.id.buy);
        TextView sell =findViewById(R.id.sell);
        TextView talk =findViewById(R.id.talk);
        TextView misstion =findViewById(R.id.misstion);
        TextView go_shopping =findViewById(R.id.go_shopping);
        LinearLayout display_shopping_items = findViewById(R.id.group_items);
        LinearLayout serifs = findViewById(R.id.serifs);
        serifs.bringToFront();
        TextView serif =new TextView(this);
        serif.setText("いらっしゃい！");
        TextView textView = new TextView(this);
        textView.setTextColor(Color.RED);
        textView.setText("aaaaaaaa");
        display_shopping_items.addView(textView);
        display_shopping_items.bringToFront();
        serifs.addView(serif);
        //ready_buy
        for (int j=0;j<game.store.items_all.size();j++) {
            TextView shopping_item = new TextView(this);
            shopping_item.setTextColor(Color.RED);
            shopping_item.setText(game.store.items_all.get(j).name);
            display_buyitems.add(shopping_item);
        }
        //ready_sell
        for (Item item : game.p.items) {
            TextView shopping_item = new TextView(this);
            shopping_item.setTextColor(Color.WHITE);
            shopping_item.setText(item.name);
            display_sellitems.add(shopping_item);
        }
        EditText buy_choose_number = new EditText(this);
        Button buy_enter = new Button(this);
        buy_enter.setText("決定");
        buy_choose_number.setVisibility(View.GONE);
        buy_enter.setVisibility(View.GONE);
        buy_enter.setTextColor(Color.BLACK);
        buy_choose_number.setTextColor(Color.BLACK);
        serifs.addView(buy_choose_number);
        serifs.addView(buy_enter);
        Button sell_enter = new Button(this);
        sell_enter.setText("決定");
        EditText sell_choose_number = new EditText(this);
        sell_choose_number.setVisibility(View.GONE);
        sell_enter.setVisibility(View.GONE);
        sell_choose_number.setTextColor(Color.BLACK);
        sell_enter.setTextColor(Color.BLACK);
        serifs.addView(sell_choose_number);
        serifs.addView(sell_enter);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_shopping_items.removeAllViews();
                serif.setText("何を買うんだ？");
                ArrayList<Item> buyitems = new ArrayList<>();
                addNewTextViewBuy(buyitems);
                display_shopping_items.bringToFront();
                        for (int i = 0; i < buyitems.size(); i++) {
                            int buy_items = i;
                            display_shopping_items.getChildAt(buy_items).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TextView serif = (TextView) serifs.getChildAt(0);
                                    serif.setText("何個買うんだ？");
                                    buy_choose_number.setVisibility(View.VISIBLE);
                                    buy_enter.setVisibility(View.VISIBLE);
                                    game.store.pushToBuy(display_shopping_items, buyitems, buy_items, buy_enter, serif, buy_choose_number);
                                }
                            });
                        }
                    }
                });
                sell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        display_shopping_items.removeAllViews();
                        serif.setText("何を売るんだ？");
                        addNewTextViewSell();
                        display_shopping_items.bringToFront();
                        for (int i = 0; i < game.p.items.size(); i++) {
                            int sell_items = i;
                            display_shopping_items.getChildAt(sell_items).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TextView serif = (TextView) serifs.getChildAt(0);
                                    serif.setText("何個売るんだ？");
                                    sell_choose_number.setVisibility(View.VISIBLE);
                                    sell_enter.setVisibility(View.VISIBLE);
                                    game.store.sell(display_shopping_items, serifs, sell_items, sell_enter, serif, sell_choose_number);
                                }
                            });
                        }
                    }
                });
                talk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        display_shopping_items.removeAllViews();
                    }
                });
                misstion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        display_shopping_items.removeAllViews();
                    }
                });
                go_shopping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StoreActivity.this, TransitionActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        transition_activity.to_activity = game_activity;
                    }
                });
            }
    private void addNewTextViewBuy(ArrayList<Item> buyitems) {
        LinearLayout linearContainer = findViewById(R.id.group_items);
        for (int i = 0; i < game.store.items_all.size(); i++) {
            if (game.store.items_all.get(i).item_lv <= game.store.store_lv) {
                TextView textView = new TextView(this);
                textView.setTextColor(Color.RED);
                textView.setText(game.store.items_all.get(i).name);
                linearContainer.addView(textView);
                buyitems.add(game.store.items_all.get(i));
            }
        }
        linearContainer.bringToFront();
    }
    private void addNewTextViewSell(){
        LinearLayout linearContainer = findViewById(R.id.group_items);
        System.out.println(game.p.items.size()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (int i = 0; i < game.p.items.size(); i++) {
            TextView textView = new TextView(this);
            textView.setTextColor(Color.RED);
            textView.setText(game.p.items.get(i).name);
            linearContainer.addView(textView);
        }
    }
}
