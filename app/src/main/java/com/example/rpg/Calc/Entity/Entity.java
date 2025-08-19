package com.example.rpg.Calc.Entity;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Entity extends AppCompatActivity implements Serializable {
    public int mpx;//マップ上のx座標
    public int mpy;//マップ上のy座標
    public int serve_mpx;//マップ上のひとつ前のx座標
    public int serve_mpy;//マップ上のひとつ前のy座標
    public float x;//グラフィック上のx座標
    public float y;//グラフィック上のy座標
    public float speed;//エンティティの移動速度。これとActionクラスの34行目(プレイヤー)とか128行目(モンスター)との合算で移動速度が決定する
    public String direction = null;//エンティティがどの方向に移動するかを確認する
    public ImageView image = null;//エンティティの画像
}
