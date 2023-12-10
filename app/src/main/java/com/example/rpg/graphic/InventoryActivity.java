package com.example.rpg.graphic;

import static com.example.rpg.Calc.Inventry.takeItemOfThing;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.save_transition_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Item.MonsterItem;
import com.example.rpg.Calc.Monsters.Monster2;
import com.example.rpg.R;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity implements Serializable {
    public static InventoryActivity inventory_activity = new InventoryActivity();
    public int runnable_if = 0;
    public boolean when_click_player = true;
    public Monster2 select_monster_now = null;
    public ImageView player_equipment_item = null;
    public ImageView monster_equipment_item = null;
    public final Handler handler = new Handler();
    public final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (runnable_if > 2){
                runnable_if = 0;
                click_number_of_object = 0;
            }else {
                handler.postDelayed(runnable,1000);
                runnable_if++;
            }
        }
    };
    public int click_number_of_object = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ArrayList<ImageView> save_first_image_player = new ArrayList<>();
        ArrayList<Boolean> item_first_clicks_player = new ArrayList<>();
        ArrayList<ImageView> save_first_image_monster = new ArrayList<>();
        ArrayList<Monster2> monster_in_linear = new ArrayList<>();


        int image_size = getResources().getDimensionPixelSize(R.dimen.image_inventory_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_inventory_margin);

        LinearLayout item_aria_linear = findViewById(R.id.item_aria_linear);
        RelativeLayout item_aria_relative = findViewById(R.id.item_aria_relative);
        ImageView inventory_button = findViewById(R.id.inventory_button_inventory);
        ImageView setting = findViewById(R.id.setting_inventory);
        LinearLayout take_item = findViewById(R.id.take_item);
        LinearLayout kind_of_monster_linear = findViewById(R.id.kind_of_monster_linear);
        ImageView monster_inventory = findViewById(R.id.monster_inventory);
        ImageView player_inventory = findViewById(R.id.player_inventory);
        ImageView garbage_button = findViewById(R.id.garbage_button);
        LinearLayout garbage_aria = findViewById(R.id.garbage_aria);
        LinearLayout selected_monster = findViewById(R.id.selected_monster);
        ImageView inventory_of_player = findViewById(R.id.inventory_of_player);
        ImageView inventory_of_monster = findViewById(R.id.inventory_of_monster);
        ArrayList<Button> collect_new_button = new ArrayList<>();
        int selected_monster_number = -1;
        for (int i=0;i<game.p.monsters2.size();i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(setMonsterImage(game.p.monsters2.get(i)));
            try {
                if (select_monster_now == game.p.monsters2.get(i)) {
                    selected_monster_number = i;
                    selected_monster.addView(imageView);
                } else {
                    monster_in_linear.add(game.p.monsters2.get(i));
                    kind_of_monster_linear.addView(imageView);
                }
            }catch (NullPointerException e){
                monster_in_linear.add(game.p.monsters2.get(i));
                kind_of_monster_linear.addView(imageView);
            }
        }
        if (selected_monster_number != -1){
            monster_in_linear.add(game.p.monsters2.get(selected_monster_number));
        }
        kind_of_monster_linear.setVisibility(View.GONE);
        inventory_of_monster.setVisibility(View.GONE);
        selected_monster.setVisibility(View.GONE);
        item_aria_linear.bringToFront();

        for (int i = 0; i < game.p.items.size(); i++) {
            if (!(game.p.items.get(i) instanceof MonsterItem) && game.p.have_item != game.p.items.get(i)) {
                ImageView image_view = new ImageView(this);
                GridLayout.LayoutParams layout_params = new GridLayout.LayoutParams();
                layout_params.width = image_size;
                layout_params.height = image_size;
                layout_params.setMargins(margin, margin, margin, margin);
                image_view.setImageDrawable(game.p.items.get(i).item_drawable);
                image_view.setLayoutParams(layout_params);
                item_aria_linear.addView(image_view);
                image_view.bringToFront();
            } else if (game.p.have_item == game.p.items.get(i)) {
                ImageView image_view = new ImageView(this);
                GridLayout.LayoutParams layout_params = new GridLayout.LayoutParams();
                layout_params.width = image_size;
                layout_params.height = image_size;
                layout_params.setMargins(margin, margin, margin, margin);
                image_view.setImageDrawable(game.p.items.get(i).item_drawable);
                image_view.setLayoutParams(layout_params);
                take_item.addView(image_view);
            }
        }
        item_aria_linear.bringToFront();
        for (int i = 0; i < 2; i++) {
            Button choose_button_of_item = new Button(InventoryActivity.this);
            choose_button_of_item.setTextColor(Color.RED);
            setButtonText(i, choose_button_of_item);
            collect_new_button.add(choose_button_of_item);
        }
        inventory_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_number_of_object = 0;
                Intent intent = new Intent(InventoryActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = save_transition_activity;
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_number_of_object = 0;
                Intent intent = new Intent(InventoryActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity = new MainActivity();
            }
        });
        selected_monster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDoubleAction(selected_monster, kind_of_monster_linear);
            }
        });
        take_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDoubleAction(take_item,item_aria_linear);
            }
        });
        player_inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventory_of_monster.setVisibility(View.GONE);
                selected_monster.setVisibility(View.GONE);
                inventory_of_player.setVisibility(View.VISIBLE);
                kind_of_monster_linear.setVisibility(View.GONE);
                ConstraintLayout.LayoutParams layout_params = (ConstraintLayout.LayoutParams) item_aria_relative.getLayoutParams();
                layout_params.verticalBias = 0.569F;
                layout_params.height = 251;
                item_aria_relative.setLayoutParams(layout_params);
                if (player_equipment_item != null) {
                    take_item.removeAllViews();
                    take_item.addView(player_equipment_item);
                }
                when_click_player = true;
            }
        });
        monster_inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventory_of_monster.setVisibility(View.VISIBLE);
                selected_monster.setVisibility(View.VISIBLE);
                inventory_of_player.setVisibility(View.GONE);
                kind_of_monster_linear.setVisibility(View.VISIBLE);
                ConstraintLayout.LayoutParams layout_params = (ConstraintLayout.LayoutParams) item_aria_relative.getLayoutParams();
                layout_params.verticalBias = 0.300F;
                layout_params.height = 108;
                item_aria_relative.setLayoutParams(layout_params);
                if (monster_equipment_item != null) {
                    take_item.removeAllViews();
                    take_item.addView(monster_equipment_item);
                }
                when_click_player = false;
            }
        });
        garbage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                garbage_aria.removeAllViews();
            }
        });
        for (int i = 0; i < game.p.items.size(); i++) {
            int inventory_i = i;
            save_first_image_player.add((ImageView) item_aria_linear.getChildAt(inventory_i));
            item_first_clicks_player.add(true);
            try {
                item_aria_linear.getChildAt(inventory_i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean correct_item = false;
                        int match_number = 0;
                        try {
                            for (int j = 0; j < item_aria_linear.getChildCount(); j++) {
                                System.out.println("shgfffffffffddd");
                                if (save_first_image_player.get(inventory_i) == item_aria_linear.getChildAt(j)) {
                                    correct_item = true;
                                    match_number = j;
                                }
                            }
                        } catch (NullPointerException e) {
                        }
                        if (item_first_clicks_player.get(inventory_i)) {
                            if (correct_item) {
                                if (when_click_player) {
                                    for (int j = 0; j < 2; j++) {
                                        RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(
                                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                RelativeLayout.LayoutParams.WRAP_CONTENT
                                        );
                                        setCoordinate(layout_params, j, (ImageView) item_aria_linear.getChildAt(match_number), collect_new_button);
                                        RelativeLayout relative_layout = findViewById(R.id.item_aria_relative);
                                        relative_layout.addView(collect_new_button.get(j), layout_params);
                                        pushButtonOfItem(collect_new_button, j, match_number, take_item, item_aria_linear, item_first_clicks_player, relative_layout, garbage_aria);
                                    }
                                    item_first_clicks_player.set(match_number, false);
                                }else {
                                    if (selected_monster.getChildAt(0) != null) {
                                        for (int j = 0; j < 2; j++) {
                                            RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(
                                                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                    RelativeLayout.LayoutParams.WRAP_CONTENT
                                            );
                                            setCoordinate(layout_params, j, (ImageView) item_aria_linear.getChildAt(match_number), collect_new_button);
                                            RelativeLayout relative_layout = findViewById(R.id.item_aria_relative);
                                            relative_layout.addView(collect_new_button.get(j), layout_params);
                                            pushButtonOfItem(collect_new_button, j, match_number, take_item, item_aria_linear, item_first_clicks_player, relative_layout, garbage_aria);
                                        }
                                        item_first_clicks_player.set(match_number, false);
                                    }
                                }
                            }
                        }
                    }
                });
            } catch (NullPointerException e) {
            }
        }
        for (int i = 0; i < game.p.monsters2.size(); i++) {
            int inventory_i = i;
            save_first_image_monster.add((ImageView) kind_of_monster_linear.getChildAt(inventory_i));
            try {
                kind_of_monster_linear.getChildAt(inventory_i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            for (int j = 0; j < kind_of_monster_linear.getChildCount(); j++) {
                                System.out.println("shgfffffffffddd");
                                if (save_first_image_monster.get(inventory_i) == kind_of_monster_linear.getChildAt(j)) {
                                    select_monster_now = monster_in_linear.get(j);
                                    if (selected_monster.getChildCount() > 0) {
                                        View view = selected_monster.getChildAt(0);
                                        selected_monster.removeView(view);
                                        kind_of_monster_linear.addView(view);
                                    }
                                    View view = kind_of_monster_linear.getChildAt(j);
                                    kind_of_monster_linear.removeView(view);
                                    selected_monster.addView(view);
                                    Monster2 monster = monster_in_linear.get(j);
                                    monster_in_linear.remove(monster);
                                    monster_in_linear.add(monster);
                                }
                            }
                        } catch (NullPointerException e) {
                        }
                    }
                });
            }catch (NullPointerException e) {}
        }
    }
    public void clickDoubleAction(LinearLayout click_object,LinearLayout specified_object){
        if (click_object.getChildAt(0) != null) {
            ImageView imageView = (ImageView) click_object.getChildAt(0);
            click_number_of_object++;
            if (click_number_of_object == 1) {
                handler.post(runnable);
            } else if (click_number_of_object == 2) {
                click_object.removeAllViews();
                specified_object.addView(imageView);
                if (when_click_player) {
                    game.p.have_item = null;
                }else {
                    select_monster_now = null;
                }
                click_number_of_object = 0;
            }
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
    public Drawable setMonsterImage(Monster2 monster){
        Drawable drawable = null;
        if (monster.name.equals("竜王")){
            drawable = getResources().getDrawable(R.drawable.dragon_king);
        }else if (monster.name.equals("ゴ－レム")){
            drawable = getResources().getDrawable(R.drawable.gorlem);
        }else if (monster.name.equals("プチスライム")){
            drawable = getResources().getDrawable(R.drawable.puti_slime);
        }else if (monster.name.equals("メタルスライム")){
            drawable = getResources().getDrawable(R.drawable.metal_slime);
        }
        return drawable;
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
    private void pushButtonOfItem(ArrayList<Button> collect_new_button,int j,int match_number,LinearLayout take_item,LinearLayout item_of_player,ArrayList<Boolean> item_first_click,RelativeLayout relative_layout,LinearLayout garbage_of_player){
        if (j == 0) {
            collect_new_button.get(j).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = (ImageView) item_of_player.getChildAt(match_number);
                    for (Item item : game.p.items) {
                        if (item.item_drawable == imageView.getDrawable()) {
                            takeItemOfThing(item, when_click_player, select_monster_now);
                            if (when_click_player) {
                                if (take_item.getChildAt(0) == null && item.can_hold) {
                                    item_of_player.removeView(imageView);
                                    take_item.addView(imageView);
                                    System.out.println("lllllllllllllllllllll");
                                } else if (item.can_hold) {
                                    ImageView imageView_take_item = (ImageView) take_item.getChildAt(0);
                                    take_item.removeAllViews();
                                    item_of_player.addView(imageView_take_item);
                                    item_of_player.removeView(imageView);
                                    take_item.addView(imageView);
                                    player_equipment_item = imageView;
                                }
                            }else {
                                if (take_item.getChildAt(0) == null && item instanceof FightItem){
                                    item_of_player.removeView(imageView);
                                    take_item.addView(imageView);
                                }else if (item instanceof FightItem){
                                    ImageView imageView_take_item = (ImageView) take_item.getChildAt(0);
                                    take_item.removeAllViews();
                                    item_of_player.addView(imageView_take_item);
                                    item_of_player.removeView(imageView);
                                    take_item.addView(imageView);
                                    monster_equipment_item = imageView;
                                }
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
                    ImageView imageView = (ImageView) item_of_player.getChildAt(match_number);
                    item_of_player.removeView(imageView);
                    garbage_of_player.addView(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                for (int j = 0; j < garbage_of_player.getChildCount(); j++) {
                                    if (imageView == garbage_of_player.getChildAt(j)) {
                                        click_number_of_object++;
                                        if (click_number_of_object == 1){
                                            handler.post(runnable);
                                        }else if (click_number_of_object == 2){
                                            click_number_of_object = 0;
                                            ImageView imageView = (ImageView) garbage_of_player.getChildAt(j);
                                            garbage_of_player.removeView(imageView);
                                            item_of_player.addView(imageView);
                                        }
                                    }
                                }
                            } catch (NullPointerException e) { }
                        }
                    });
                    for (Button button : collect_new_button) {
                        relative_layout.removeView(button);
                    }
                    item_first_click.set(match_number,true);
                }
            });
        }
    }
}
