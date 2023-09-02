package com.example.rpg.Calc.map;

import java.io.Serializable;
import static com.example.rpg.Calc.map.cave.Cave1.CV1;
import static com.example.rpg.Calc.map.Map.*;
import static com.example.rpg.Calc.map.PersonHome1.*;
import android.widget.ImageView;

import com.example.rpg.Calc.Game;
import com.example.rpg.graphic.GameActivity;

public class World_map implements Serializable {
    public String[][] world_map = {
            {E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E},
            {E, G, G, G, G, O, G, G, G, G, G, C, M, M, M, M, M, M, PH1, M, M, M, E},
            {E, G, G, G, O, O, O, G, G, G, G, C, C, M, M, M, M, M, M, M, M, M, E},
            {E, G, G, O, O, O, O, O, G, G, G, G, C, C, C, C, C, C, C, C, C, C, E},
            {E, G, G, O, O, O, O, O, G, G, G, G, G, G, C, C, C, C, G, G, G, C, E},
            {E, G, G, G, O, O, O, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, O, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, CV1, G, G, G, G, G, G, G, G, G, G, G, G, G, S, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E}
            };
}
