package com.example.rpg.graphic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import static com.example.rpg.Calc.Game.game;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity implements Serializable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        TextView buy =findViewById(R.id.buy);
        TextView sell =findViewById(R.id.sell);
        TextView talk =findViewById(R.id.talk);
        TextView misstion =findViewById(R.id.misstion);
        TextView go_shopping =findViewById(R.id.go_shopping);
        LinearLayout shopping_items = findViewById(R.id.group_items);
        LinearLayout serifs = findViewById(R.id.serifs);
        TextView serif =new TextView(this);
        serif.setText("いらっしゃい！");
        serifs.addView(serif);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serif.setText("何を買うんだ？");
                game.store.buy(shopping_items,serifs);
                serif.setText("他はどうだ？");
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serif.setText("何を売るんだ？");
                game.store.sell(shopping_items,serifs);
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
    public TextView shop_item(){
        TextView textView = new TextView(this);
        return  textView;
    }
    public EditText edit_shop(){
        EditText choose_number =new EditText(this);
        return choose_number;
    }
    public Button button_shop(){
        Button button = new Button(this);
        return button;
    }
}
