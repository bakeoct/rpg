package com.example.rpg.Calc.map.tail;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.Calc.treasure.TreasureChestLadder.treasure_chest_ladder;
import static com.example.rpg.Calc.treasure.TreasureChestShip.treasure_chest_ship;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.R;

public class Tail extends AppCompatActivity {
    public GridLayout.LayoutParams layout_params = new GridLayout.LayoutParams();
    public ImageView image;
    public Drawable drawable;
    public float x_start = 0;
    public float y_start = 0;
    public float x_end = 0;
    public float y_end = 0;
    public int on_it;//0=何もついてない,1=崖がついてる,2=海がついてる
    public String distinction;//例:"to_WorldMap","to_cave1"
    public String direct;
    public boolean need_ship = false;
    public int floor;//0から始まる
    public int wko;//EntranceAndExitの例:wko=what_kind_of(何の種類の入口？),0=洞窟,1=民家,2=店  //treasureBoxの例:wko=what_kind_of(何の種類の入口？),例(実装するかは別)：0=普通の宝箱,1=ミミック,2=高級宝箱
    public String tail_id;
    public Tail(){
        layout_params.width = game.image_size;
        layout_params.height = game.image_size;
        layout_params.setMargins(game.margin, game.margin, game.margin, game.margin);
    }
    public Handler HANDLER;
    public Runnable OPEN_TREASURE;
}
