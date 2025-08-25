package com.example.rpg.Calc.ai;

import static com.example.rpg.Calc.Game.game;
import static com.example.rpg.graphic.TransitionActivity.from_activity;

import android.widget.ImageView;

import com.example.rpg.Calc.Entity.Monsters.super_monster.Monster;

import java.util.Random;

public class NavMesh {
    public Monster wandering(int which_monster) {
        Monster monster = from_activity.monster_on_map.get(which_monster);
        game.monster_manager.walk(monster);
        try {
            Thread.sleep(25); //0.1秒イベント中断。値が小さいほど、高速で連続する
        } catch (InterruptedException e) {
        }
        return monster;
    }

    public void tracking(Monster monster) {
        float dx = game.player.image.getX() - monster.image.getX();
        float dy = game.player.image.getY() - monster.image.getY();
        float len = distanceFromPlayer(game.player.image, monster.image);
        if (50f < len) {//この数値はモンスターをプレイヤーの手前で止めるもの
            direction(dx, dy, monster);
            float x = monster.speed / len * dx;
            float y = monster.speed / len * dy;
            if (!(game.event.notMonsterEnter(monster,x,y))){
                monster.world_x += monster.speed / len * dx;
                monster.world_y += monster.speed / len * dy;
            }
            knowWhereTail(monster);
        }
        try {
            Thread.sleep(25); //0.1秒イベント中断。値が小さいほど、高速で連続する
        } catch (InterruptedException e) {
        }
    }

    private void direction(float dx, float dy, Monster monster) {
        String dire_x;
        String dire_y;
        if (game.player.world_x < monster.world_x) {
            dire_x = "left";
        } else {
            dire_x = "right";
        }
        if (game.player.world_y < monster.world_y) {
            dire_y = "over";
        } else {
            dire_y = "behind";
        }
        if (Math.abs(dx) < Math.abs(dy)) {

            switch (dire_y) {
                case "behind":
                    monster.image.setImageResource(monster.monster_drawable[0]);
                    break;
                case "over":
                    monster.image.setImageResource(monster.monster_drawable[3]);
                    break;
            }
        } else {

            switch (dire_x) {
                case "right":
                    monster.image.setImageResource(monster.monster_drawable[2]);
                    break;
                case "left":
                    monster.image.setImageResource(monster.monster_drawable[1]);
                    break;
            }
        }
    }

    public float distanceFromPlayer(ImageView player_image, ImageView monster_image) {
        return (float) Math.sqrt(
                (float) Math.pow(player_image.getX() - monster_image.getX(), 2)
                        + (float) Math.pow(player_image.getY() - monster_image.getY(), 2));
    }

    public String directionMonster() {
        Random random = new Random();
        int i = random.nextInt(4);
        String direction = null;
        switch (i) {
            case 0:
                direction = "right";
                break;
            case 1:
                direction = "left";
                break;
            case 2:
                direction = "under";
                break;
            case 3:
                direction = "over";
        }
        return direction;
    }
    public void knowWhereTail(Monster monster){
        for (int i = 0; i < from_activity.map.length;i++){
            for(int j = 0; j < from_activity.map[i].length; j++){
                if (from_activity.map[i][j].x_start <= monster.image.getX() + game.image_size / 2
                        && from_activity.map[i][j].x_end >= monster.image.getX()
                        && from_activity.map[i][j].y_start <= monster.image.getY() + game.image_size / 2
                        && from_activity.map[i][j].y_end >= monster.image.getY() ){
                    monster.mpx = j;
                    monster.mpy = i;
                }
            }
        }
    }

}
