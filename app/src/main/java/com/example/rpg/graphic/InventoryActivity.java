package com.example.rpg.graphic;

import static com.example.rpg.Calc.Inventry.takeItemOfThing;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.MainActivity.main_activity;
import static com.example.rpg.graphic.TransitionActivity.transition_activity;
import static com.example.rpg.save.SaveWriteAndRead.saveWriteAndRead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.rpg.Calc.Item.FightItem;
import com.example.rpg.Calc.Item.Item;
import com.example.rpg.Calc.Item.MonsterItem;
import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.R;

import java.io.Serializable;
import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity implements Serializable {
    public static InventoryActivity inventory_activity = new InventoryActivity();
    public int runnable_if = 0;
    public boolean when_click_player = true;
    public Monster select_monster_now = null;
    public ImageView player_equipment_item_image = null;
    public ImageView monster_equipment_item_image = null;
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
        ArrayList<Monster> monster_in_linear = new ArrayList<>();
        ArrayList<Item> dispose_of_item = new ArrayList<>();
        ArrayList<Item> joint_item_aria = new ArrayList<>();

        int image_size = getResources().getDimensionPixelSize(R.dimen.image_inventory_size);
        int margin = getResources().getDimensionPixelSize(R.dimen.image_inventory_margin);

        LinearLayout item_aria_linear = findViewById(R.id.item_aria_linear);
        RelativeLayout item_aria_relative = findViewById(R.id.item_aria_relative);
        ImageView inventory_button = findViewById(R.id.inventory_button);
        ImageView setting = findViewById(R.id.setting);
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
        for (int i=0;i<game.player.monsters2.size();i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(setMonsterImage(game.player.monsters2.get(i)));
            try {
                if (select_monster_now == game.player.monsters2.get(i)) {
                    selected_monster_number = i;
                    selected_monster.addView(imageView);
                } else {
                    monster_in_linear.add(game.player.monsters2.get(i));
                    kind_of_monster_linear.addView(imageView);
                }
            }catch (NullPointerException e){
                monster_in_linear.add(game.player.monsters2.get(i));
                kind_of_monster_linear.addView(imageView);
            }
        }
        if (selected_monster_number != -1){
            monster_in_linear.add(game.player.monsters2.get(selected_monster_number));
        }
        kind_of_monster_linear.setVisibility(View.GONE);
        inventory_of_monster.setVisibility(View.GONE);
        selected_monster.setVisibility(View.GONE);
        item_aria_linear.bringToFront();

        for (int i = 0; i < game.player.items.size(); i++) {
            if (!(game.player.items.get(i) instanceof MonsterItem) && game.player.have_item != game.player.items.get(i)) {
                ImageView image_view = new ImageView(this);
                GridLayout.LayoutParams layout_params = new GridLayout.LayoutParams();
                layout_params.width = image_size;
                layout_params.height = image_size;
                layout_params.setMargins(margin, margin, margin, margin);
                image_view.setImageDrawable(game.player.items.get(i).item_drawable);
                image_view.setLayoutParams(layout_params);
                item_aria_linear.addView(image_view);
                image_view.bringToFront();
                joint_item_aria.add(game.player.items.get(i));
            } else if (game.player.have_item == game.player.items.get(i)) {
                ImageView image_view = new ImageView(this);
                GridLayout.LayoutParams layout_params = new GridLayout.LayoutParams();
                layout_params.width = image_size;
                layout_params.height = image_size;
                layout_params.setMargins(margin, margin, margin, margin);
                image_view.setImageDrawable(game.player.items.get(i).item_drawable);
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
                transition_activity.to_activity = transition_activity.save_transition_activity;
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.mpm.media_player.stop();
                game.mpm.media_player.release();
                saveWriteAndRead.write();
                click_number_of_object = 0;
                Intent intent = new Intent(InventoryActivity.this, TransitionActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                transition_activity.to_activity = main_activity;
            }
        });
        selected_monster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveSelectedItem(selected_monster, kind_of_monster_linear,take_item);
            }
        });
        take_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (take_item.getChildAt(0) != null) {
                    ImageView imageView = (ImageView) take_item.getChildAt(0);
                    click_number_of_object++;
                    if (click_number_of_object == 1) {
                        handler.post(runnable);
                    } else if (click_number_of_object == 2) {
                        take_item.removeAllViews();
                        item_aria_linear.addView(imageView);
                        int inventory_i = 0;
                        for (int i=0; i<save_first_image_player.size();i++){
                            if (save_first_image_player.get(i) == imageView){
                                inventory_i = i;
                            }
                        }
                        setRepeatButton(imageView,item_aria_linear,save_first_image_player,inventory_i,item_first_clicks_player,collect_new_button,take_item,selected_monster,garbage_aria,dispose_of_item,joint_item_aria);
                        if (when_click_player) {
                            joint_item_aria.add(game.player.have_item);
                            game.player.have_item = null;
                            player_equipment_item_image = null;
                        }else {
                            game.player.items.add(select_monster_now.have_item);
                            joint_item_aria.add(select_monster_now.have_item);
                            select_monster_now.have_item = null;
                            monster_equipment_item_image = null;
                        }
                        click_number_of_object = 0;
                    }
                }
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
                take_item.removeAllViews();
                if (player_equipment_item_image != null) {
                    take_item.addView(player_equipment_item_image);
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
                layout_params.verticalBias = 0.200F;
                layout_params.height = 300;
                item_aria_relative.setLayoutParams(layout_params);
                take_item.removeAllViews();
                if (monster_equipment_item_image != null) {
                    take_item.addView(monster_equipment_item_image);
                }
                take_item.bringToFront();
                when_click_player = false;

            }
        });
        garbage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                garbage_aria.removeAllViews();
                for (int i=0;i<dispose_of_item.size();i++) {
                    for (int j=0;j<game.player.items.size();j++) {
                        if (dispose_of_item.get(i) == game.player.items.get(j)) {
                            game.player.items.get(j).have = false;
                            game.player.items.get(j).have_point = 0;
                            game.player.items.remove(j);
                        }
                    }
                }
                dispose_of_item.clear();
            }
        });
        for (int i = 0; i < game.player.items.size(); i++) {
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
                                        pushButtonOfItem(collect_new_button, j, match_number, take_item, item_aria_linear, item_first_clicks_player, relative_layout, garbage_aria,dispose_of_item,joint_item_aria,save_first_image_player,inventory_i,item_first_clicks_player,selected_monster,garbage_aria);
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
                                            collect_new_button.get(j).bringToFront();
                                            pushButtonOfItem(collect_new_button, j, match_number, take_item, item_aria_linear, item_first_clicks_player, relative_layout, garbage_aria,dispose_of_item,joint_item_aria,save_first_image_player,inventory_i,item_first_clicks_player,selected_monster,garbage_aria);
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
        ImageView monster_item_image_view = new ImageView(this);
        for (int i = 0; i < game.player.monsters2.size(); i++) {
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
                                        take_item.removeAllViews();
                                        monster_equipment_item_image = null;
                                    }
                                    View view = kind_of_monster_linear.getChildAt(j);
                                    kind_of_monster_linear.removeView(view);
                                    selected_monster.addView(view);

                                    if (monster_in_linear.get(j).have_item != null){
                                        GridLayout.LayoutParams layout_params = new GridLayout.LayoutParams();
                                        layout_params.width = image_size;
                                        layout_params.height = image_size;
                                        layout_params.setMargins(margin, margin, margin, margin);
                                        monster_item_image_view.setImageDrawable(monster_in_linear.get(j).have_item.item_drawable);
                                        monster_item_image_view.setLayoutParams(layout_params);
                                        take_item.addView(monster_item_image_view);
                                        monster_equipment_item_image = monster_item_image_view;
                                    }
                                    Monster monster = monster_in_linear.get(j);
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
    public void moveSelectedItem(LinearLayout click_object,LinearLayout specified_object,LinearLayout take_item){
        if (click_object.getChildAt(0) != null) {
            ImageView imageView = (ImageView) click_object.getChildAt(0);
            click_number_of_object++;
            if (click_number_of_object == 1) {
                handler.post(runnable);
            } else if (click_number_of_object == 2) {
                click_object.removeAllViews();
                specified_object.addView(imageView);
                take_item.removeAllViews();
                select_monster_now = null;
                monster_equipment_item_image = null;
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
    public Drawable setMonsterImage(Monster monster){
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
    private void pushButtonOfItem(ArrayList<Button> collect_new_button,int j,int match_number,LinearLayout take_item,LinearLayout item_of_player,ArrayList<Boolean> item_first_click,RelativeLayout relative_layout,LinearLayout garbage_of_player,ArrayList<Item> dispose_of_item,ArrayList<Item> joint_item_aria,ArrayList<ImageView> save_first_image_player,int inventory_i,ArrayList<Boolean> item_first_clicks_player,LinearLayout selected_monster,LinearLayout garbage_aria){
        if (j == 0) {
            collect_new_button.get(j).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = (ImageView) item_of_player.getChildAt(match_number);
                    for (int i=0;i<game.player.items.size();i++) {
                        if (game.player.items.get(i).item_drawable == imageView.getDrawable()) {
                            takeItemOfThing(game.player.items.get(i), when_click_player, select_monster_now);
                            if (when_click_player) {
                                Item save_have_item_player = game.player.have_item;
                                if (take_item.getChildAt(0) == null && game.player.items.get(i).can_hold) {
                                    joint_item_aria.remove(game.player.items.get(i));
                                    item_of_player.removeView(imageView);
                                    take_item.addView(imageView);
                                    player_equipment_item_image = imageView;
                                } else if (game.player.items.get(i).can_hold) {
                                    joint_item_aria.remove(game.player.items.get(i));
                                    ImageView imageView_take_item = (ImageView) take_item.getChildAt(0);
                                    take_item.removeAllViews();
                                    item_of_player.addView(imageView_take_item);
                                    joint_item_aria.add(save_have_item_player);
                                    item_of_player.removeView(imageView);
                                    take_item.addView(imageView);
                                    player_equipment_item_image = imageView;
                                }
                            }else {
                                if (select_monster_now != null) {
                                    Item save_have_item_monster = select_monster_now.have_item;
                                    if (take_item.getChildAt(0) == null && game.player.items.get(i) instanceof FightItem) {
                                        joint_item_aria.remove(game.player.items.get(i));
                                        item_of_player.removeView(imageView);
                                        take_item.addView(imageView);
                                        game.player.items.remove(i);
                                        monster_equipment_item_image = imageView;
                                        System.out.println(game.player.items.size());
                                    } else if (game.player.items.get(i) instanceof FightItem) {
                                        joint_item_aria.remove(game.player.items.get(i));
                                        game.player.items.remove(i);
                                        ImageView imageView_take_item = (ImageView) take_item.getChildAt(0);
                                        take_item.removeAllViews();
                                        item_of_player.addView(imageView_take_item);
                                        joint_item_aria.add(save_have_item_monster);
                                        game.player.items.add(save_have_item_monster);
                                        item_of_player.removeView(imageView);
                                        take_item.addView(imageView);
                                        monster_equipment_item_image = imageView;
                                    }
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
                    dispose_of_item.add(joint_item_aria.get(match_number));
                    joint_item_aria.remove(joint_item_aria.get(match_number));
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
                                            joint_item_aria.add(dispose_of_item.get(j));
                                            dispose_of_item.remove(dispose_of_item.get(j));
                                            setRepeatButton(imageView,item_of_player,save_first_image_player,inventory_i,item_first_clicks_player,collect_new_button,take_item,selected_monster,garbage_aria,dispose_of_item,joint_item_aria);
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
    private void setRepeatButton(ImageView target_image,LinearLayout item_aria_linear,ArrayList<ImageView> save_first_image_player, int inventory_i,ArrayList<Boolean> item_first_clicks_player,ArrayList<Button> collect_new_button,LinearLayout take_item,LinearLayout selected_monster,LinearLayout garbage_aria,ArrayList<Item> dispose_of_item,ArrayList<Item> joint_item_aria) {
        target_image.setOnClickListener(new View.OnClickListener() {
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
                if (item_first_clicks_player.get(match_number)) {
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
                                pushButtonOfItem(collect_new_button, j, match_number, take_item, item_aria_linear, item_first_clicks_player, relative_layout, garbage_aria, dispose_of_item, joint_item_aria, save_first_image_player, inventory_i, item_first_clicks_player, selected_monster, garbage_aria);
                            }
                            item_first_clicks_player.set(match_number, false);
                        } else {
                            System.out.println("1");
                            if (selected_monster.getChildAt(0) != null) {
                                for (int j = 0; j < 2; j++) {
                                    RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(
                                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                                            RelativeLayout.LayoutParams.WRAP_CONTENT
                                    );
                                    setCoordinate(layout_params, j, (ImageView) item_aria_linear.getChildAt(match_number), collect_new_button);
                                    System.out.println("2");
                                    RelativeLayout relative_layout = findViewById(R.id.item_aria_relative);
                                    System.out.println("3");
                                    relative_layout.addView(collect_new_button.get(j), layout_params);
                                    collect_new_button.get(j).bringToFront();
                                    System.out.println("4");
                                    pushButtonOfItem(collect_new_button, j, match_number, take_item, item_aria_linear, item_first_clicks_player, relative_layout, garbage_aria, dispose_of_item, joint_item_aria, save_first_image_player, inventory_i, item_first_clicks_player, selected_monster, garbage_aria);
                                }
                                item_first_clicks_player.set(match_number, false);
                            }
                        }
                    }
                }
            }
        });
    }
}
