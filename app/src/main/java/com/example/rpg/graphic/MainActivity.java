package com.example.rpg.graphic;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.rpg.Calc.Person2.p;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static com.example.rpg.Calc.Game.game;

import com.example.rpg.Calc.Game;
import com.example.rpg.R;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button conthinew = findViewById(R.id.conthinew);

        conthinew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    game.readSave();
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}