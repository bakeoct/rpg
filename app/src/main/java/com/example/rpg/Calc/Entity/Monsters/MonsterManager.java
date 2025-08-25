package com.example.rpg.Calc.Entity.Monsters;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;
import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MonsterManager extends AppCompatActivity implements Serializable {
    public void appearMonsterOnMap(){
        Random random = new Random();
        int i = 0;
        while(i <= random.nextInt(from_activity.max_monster_num)){//それらの基本情報入力（画像、何の種族にするか）
            //新しいモンスターの立ち上げand何の種類のモンスターか
            Monster monster = allMonster().get(new Random().nextInt(allMonster().size()));
            System.out.println(monster.name);
            //マップ上の座標
            int end_flg = 0;
            while (end_flg == 0) {
                monster.mpy = random.nextInt(from_activity.map.length);
                monster.mpx = random.nextInt(from_activity.map[monster.mpy].length);
                end_flg++;
                if (from_activity.map[monster.mpy][monster.mpx].tail_id.equals("error") || from_activity.map[monster.mpy][monster.mpx].tail_id.equals("ocean")){
                    end_flg = 0;
                }
            }
            monster.serve_mpx = monster.mpx;
            monster.serve_mpy = monster.mpy;
            //モンスターの向いてる方向
            monster.direction = monsterDirectly();
            //モンスターの画像設定
            monster.image = new ImageView(from_activity);
            FrameLayout.LayoutParams layout_params = new FrameLayout.LayoutParams(game.image_size,game.image_size);
            layout_params.setMargins(game.margin, game.margin, game.margin, game.margin);
            monster.image.setLayoutParams(layout_params);
            monster.image.setImageDrawable(setMonsterImage(monster));
            from_activity.entity_map.addView(monster.image);
            //マップにグラフィック上のモンスターを表示
            end_flg = 0;
            setMonsterInitialCoordinates(end_flg,monster);
            System.out.println("X "+monster.image.getX());
            System.out.println("Y "+monster.image.getY());
            //プレイヤーとの距離を算出しdistance_from_playerに代入する
            monster.distance_from_player = (float) Math.sqrt((float) Math.pow(game.player.image.getX() + game.image_size / 2 - monster.image.getX() + game.image_size / 2,2) + (float) Math.pow(game.player.image.getY() + game.image_size / 2 - monster.image.getY() + game.image_size / 2,2));
            //モンスターを前に持ってくる
            monster.image.bringToFront();
            //マップを表示するActivityに何体のモンスターがいるかのArrayListに代入していってる
            from_activity.monster_on_map.add(monster);
            i++;
        }
    }
    private void setMonsterInitialCoordinates(int end_flg, Monster monster){
        Random random = new Random();
        int graphic_x = 0;
        int graphic_y = 0;
        while(end_flg == 0) {
            graphic_x = from_activity.map[0][0].x_start + game.image_size * monster.mpx + random.nextInt(game.image_size) + game.player.screen_x;
            graphic_y = from_activity.map[0][0].y_start + game.image_size * monster.mpy + random.nextInt(game.image_size) + game.player.screen_y;
            end_flg++;
            if (game.event.notAppear(graphic_x,graphic_y)){
                end_flg = 0;
            }
        }
        monster.image.setX(graphic_x);
        monster.image.setY(graphic_y);
        monster.world_x = graphic_x;
        monster.world_y = graphic_y;
    }
    private Drawable setMonsterImage(Monster monster) {
        Drawable drawable = null;
        if (monster.name.equals("dragon_king")) {
            switch(monster.direction) {
                case "over":
                    drawable = from_activity.getDrawable(R.drawable.dragon_king);
                    break;
                case "right":
                    drawable = from_activity.getDrawable(R.drawable.dragon_king_right);
                    break;
                case "left":
                    drawable = from_activity.getDrawable(R.drawable.dragon_king_left);
                    break;
                case "under":
                    drawable = from_activity.getDrawable(R.drawable.dragon_king_under);
                    break;
            }
        } else if (monster.name.equals("metal_slime")) {
            switch(monster.direction) {
                case "over":
                    drawable = from_activity.getDrawable(R.drawable.metal_slime);
                    break;
                case "right":
                    drawable = from_activity.getDrawable(R.drawable.metal_slime_right);
                    break;
                case "left":
                    drawable = from_activity.getDrawable(R.drawable.metal_slime_left);
                    break;
                case "under":
                    drawable = from_activity.getDrawable(R.drawable.metal_slime_under);
                    break;
            }
        } else if (monster.name.equals("puti_slime")) {
            switch(monster.direction) {
                case "over":
                    drawable = from_activity.getDrawable(R.drawable.puti_slime);
                    break;
                case "right":
                    drawable = from_activity.getDrawable(R.drawable.puti_slime_right);
                    break;
                case "left":
                    drawable = from_activity.getDrawable(R.drawable.puti_slime_left);
                    break;
                case "under":
                    drawable = from_activity.getDrawable(R.drawable.puti_slime_under);
                    break;
            }
        } else if (monster.name.equals("golem")) {
            switch(monster.direction) {
                case "over":
                    drawable = from_activity.getDrawable(R.drawable.gorlem);
                    break;
                case "right":
                    drawable = from_activity.getDrawable(R.drawable.gorlem_right);
                    break;
                case "left":
                    drawable = from_activity.getDrawable(R.drawable.gorlem_left);
                    break;
                case "under":
                    drawable = from_activity.getDrawable(R.drawable.gorlem_under);
                    break;
            }
        }
        return drawable;
    }
    private String monsterDirectly(){
        String directly = null;
        Random random = new Random();
        switch(random.nextInt(4)){
            case 0:
                directly = "over";
                break;
            case 1:
                directly = "right";
                break;
            case 2:
                directly = "left";
                break;
            case 3:
                directly = "under";
                break;
        }
        return directly;
    }
//    public void randomNewEnemyMonster(){
//        Random random_new_enemy_monster =new Random();
//        Map map =new Map();
//        int[] range = map.getRange(this.area);
//        int world_x = random_new_enemy_monster.nextInt(range[0]);
//        int world_y = random_new_enemy_monster.nextInt(range[1]);
//        Tail price =map.getMapCode(world_x,world_y,this.area);
//        while (price.tail_id.equals("error")){
//            world_x =random_new_enemy_monster.nextInt(range[0]);
//            world_y = random_new_enemy_monster.nextInt(range[1]);
//            price =map.getMapCode(world_x,world_y,this.area);
//        }
//        this.world_x = world_x;
//        this.world_y = world_y;
//    }
    public Monster walk(Monster monster){
        Tail serve_get_map_code = from_activity.map[monster.mpy][monster.mpx];
        switch (monster.direction){
            case "right":
                monster.image.setImageResource(monster.monster_drawable[2]);
                if (!(game.event.notMonsterEnter(monster,monster.speed,monster.speed))){
                    monster.world_x += monster.speed;
                }
                game.navmesh.knowWhereTail(monster);
                break;
            case "left":
                monster.image.setImageResource(monster.monster_drawable[1]);
                if (!(game.event.notMonsterEnter(monster,-monster.speed,-monster.speed))){
                    monster.world_x -= monster.speed;
                }
                game.navmesh.knowWhereTail(monster);
                break;
            case "under":
                monster.image.setImageResource(monster.monster_drawable[0]);
                if (!(game.event.notMonsterEnter(monster,monster.speed,monster.speed))){
                    monster.world_y += monster.speed;
                }
                game.navmesh.knowWhereTail(monster);
                break;
            case "over":
                monster.image.setImageResource(monster.monster_drawable[3]);
                if (game.event.notMonsterEnter(monster,-monster.speed,-monster.speed)){
                    monster.world_y -= monster.speed;
                }
                game.navmesh.knowWhereTail(monster);
                break;
        }
        monster.serve_mpx = monster.mpx;
        monster.serve_mpy = monster.mpy;
        return monster;
    }
    public ArrayList<Monster> allMonster(){
        ArrayList<Monster> monsterList = new ArrayList<>();
        monsterList.add(new DragonKing());
        monsterList.add(new MetalSlime());
        monsterList.add(new Gorlem());
        monsterList.add(new PutiSlime());
        return monsterList;
    }

}