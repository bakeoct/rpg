package com.example.rpg.graphic;

import static com.example.rpg.Calc.Inventry.takeItemOfThing;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Item.MonsterItem;
import com.example.rpg.R;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class InventoryActivity extends AppCompatActivity implements Serializable {
    public static InventoryActivity inventory_activity = new InventoryActivity();
    public int runnable_if = 0;
    public final Handler handler = new Handler();
    public final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (runnable_if > 2){
                runnable_if = 0;
                click_number_of_items_inventory = 0;
            }else {
                handler.postDelayed(runnable,1000);
                runnable_if++;
            }
        }
    };
    public int click_number_of_items_inventory = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ArrayList<ImageView> save_first_image = new ArrayList<>();
        ArrayList<Boolean> item_first_clicks = new ArrayList<>();

        int image_size = getResources().getDimensionPixelSize(R.dimen.image_inventory_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_inventory_margin);

        LinearLayout linear_inventory = findViewById(R.id.linear_inventory);
        ImageView inventory_button = findViewById(R.id.inventory_button_inventory);
        ImageView setting = findViewById(R.id.setting_inventory);
        LinearLayout take_item_inventory = findViewById(R.id.take_item_of_inventory);
        ArrayList<Button> collect_new_button = new ArrayList<>();

        for (int i=0; i<game.p.items.size(); i++){
            System.out.println(game.p.items.size()+"hjbglyhgijbljhb");
            if (!(game.p.items.get(i) instanceof MonsterItem) && game.p.have_item != game.p.items.get(i)) {
                ImageView image_view = new ImageView(this);
                GridLayout.LayoutParams layout_params = new GridLayout.LayoutParams();
                layout_params.width = image_size;
                layout_params.height = image_size;
                layout_params.setMargins(margin, margin, margin, margin);
                image_view.setImageDrawable(game.p.items.get(i).item_drawable);
                image_view.setLayoutParams(layout_params);
                linear_inventory.addView(image_view);
                image_view.bringToFront();
            }else if(game.p.have_item == game.p.items.get(i)){
                ImageView image_view = new ImageView(this);
                GridLayout.LayoutParams layout_params = new GridLayout.LayoutParams();
                layout_params.width = image_size;
                layout_params.height = image_size;
                layout_params.setMargins(margin, margin, margin, margin);
                image_view.setImageDrawable(game.p.items.get(i).item_drawable);
                image_view.setLayoutParams(layout_params);
                take_item_inventory.addView(image_view);
            }
        }
        linear_inventory.bringToFront();
        for (int i=0;i<2;i++){
            Button choose_button_of_item =new Button(InventoryActivity.this);
            choose_button_of_item.setTextColor(Color.RED);
            setButtonText(i,choose_button_of_item);
            collect_new_button.add(choose_button_of_item);
        }
        inventory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_number_of_items_inventory = 0;
                Intent intent = new Intent(InventoryActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = save_transition_activity;
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_number_of_items_inventory = 0;
                Intent intent = new Intent(InventoryActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = new MainActivity();
            }
        });
        take_item_inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (take_item_inventory.getChildAt(0) != null) {
                    System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                    ImageView imageView = (ImageView)take_item_inventory.getChildAt(0);
                    click_number_of_items_inventory++;
                    if (click_number_of_items_inventory == 1) {
                        handler.post(runnable);
                    } else if (click_number_of_items_inventory == 2) {
                        take_item_inventory.removeAllViews();
                        linear_inventory.addView(imageView);
                        game.p.have_item = null;
                        click_number_of_items_inventory = 0;
                    }
                }
            }
        });
        for (int i = 0; i < game.p.items.size(); i++) {
            int inventory_i = i;
            save_first_image.add((ImageView) linear_inventory.getChildAt(inventory_i));
            item_first_clicks.add(true);
            try {
            linear_inventory.getChildAt(inventory_i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean correct_item = false;
                    int match_number = 0;
                    try {
                        for (int j = 0; j < linear_inventory.getChildCount(); j++) {
                            if (save_first_image.get(inventory_i) == linear_inventory.getChildAt(j)) {
                                correct_item = true;
                                match_number = j;
                            }
                        }
                    } catch (NullPointerException e) {
                    }
                    if (item_first_clicks.get(inventory_i)) {
                        if (correct_item) {
                            for (int j = 0; j < 2; j++) {
                                RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT
                                );
                                setCoordinate(layout_params, j, (ImageView) linear_inventory.getChildAt(match_number), collect_new_button);
                                RelativeLayout relative_layout = findViewById(R.id.relative_layout);
                                relative_layout.addView(collect_new_button.get(j), layout_params);
                                pushButtonOfItem(collect_new_button, j, match_number, take_item_inventory, linear_inventory,item_first_clicks,relative_layout);
                            }
                            item_first_clicks.set(match_number,false);
                        }
                    }
                }
            });
        }catch (NullPointerException e){}
        }
    }
    public int setButtonText(int i,Button choose_button_of_item){
        if (i == 0){
            choose_button_of_item.setText("てにもつ");
        }else if (i == 1){
            choose_button_of_item.setText("捨てる");
        }
        return i;
    }
    private void setCoordinate(RelativeLayout.LayoutParams layout_params,int j,ImageView click_item,ArrayList<Button> collect_new_button){
        int item_margin = getResources().getDimensionPixelSize(R.dimen.image_inventory_item_margin);
        if (j == 0) {
            layout_params.addRule(RelativeLayout.RIGHT_OF, click_item.getId());
            layout_params.leftMargin = item_margin;
            layout_params.topMargin = item_margin;
        }else {
            layout_params.addRule(RelativeLayout.BELOW, collect_new_button.get(j-1).getId());
            layout_params.topMargin = item_margin;
            System.out.println(collect_new_button.get(j-1).getText());
        }
    }
    private void pushButtonOfItem(ArrayList<Button> collect_new_button,int j,int match_number,LinearLayout take_item_inventory,LinearLayout linear_inventory,ArrayList<Boolean> item_first_click,RelativeLayout relative_layout){
        if (j == 0) {
            collect_new_button.get(j).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = (ImageView) linear_inventory.getChildAt(match_number);
                    for (Item item : game.p.items) {
                        if (item.item_drawable == imageView.getDrawable()) {
                            takeItemOfThing(item);
                            if (take_item_inventory.getChildAt(0) == null && item.can_hold) {
                                linear_inventory.removeView(imageView);
                                take_item_inventory.addView(imageView);
                                System.out.println("lllllllllllllllllllll");
                            } else if (item.can_hold){
                                ImageView imageView_take_item = (ImageView)take_item_inventory.getChildAt(0);
                                take_item_inventory.removeAllViews();
                                linear_inventory.addView(imageView_take_item);
                                linear_inventory.removeView(imageView);
                                take_item_inventory.addView(imageView);
                            }
                        }
                    }
                    for (Button button : collect_new_button) {
                        relative_layout.removeView(button);
                    }
                    item_first_click.set(match_number,true);
                }
            });
        }else {
            collect_new_button.get(j).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = (ImageView) linear_inventory.getChildAt(match_number);
                    for (Item item : game.p.items) {
                        if (item.item_drawable == imageView.getDrawable()) {
                            game.p.items.remove(item);
                        }
                    }
                    linear_inventory.removeView(linear_inventory.getChildAt(match_number));
                    for (Button button : collect_new_button) {
                        relative_layout.removeView(button);
                    }
                    item_first_click.set(match_number,true);
                }
            });
        }
    }
}
