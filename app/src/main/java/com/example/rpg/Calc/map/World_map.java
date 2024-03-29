package com.example.rpg.Calc.map;

import java.io.Serializable;
import static com.example.rpg.Calc.map.cave.Cave1.CV1;
import static com.example.rpg.Calc.map.Map.*;
import static com.example.rpg.Calc.map.PersonHome1.*;
import android.widget.ImageView;

import com.example.rpg.Calc.Game;
import com.example.rpg.graphic.GameActivity;

public class World_map implements Serializable {
    public static String[][] world_map = {
            {E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E},
            {E, G, G, G, G, O, G, G, G, G, G, C, M, M, M, M, M, M, PH1, M, M, M, E},
            {E, G, G, G, O, O, O, G, G, G, G, C, CJ, M, M, M, M, M, M, M, M, M, E},
            {E, G, G, O, O, O, O, O, G, G, G, CG, C, CJ, CJ, CJ, CJ, CJ, CJ, CJ, CJ, CJ, E},
            {E, G, G, O, O, O, O, O, G, G, G, G, CG, CG, C, C, C, C, CG, CG, CG, C, E},
            {E, G, G, G, O, O, O, G, G, G, G, G, G, G, CG, CG, CG, CG, G, G, G, CG, E},
            {E, G, G, G, G, O, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, CV1, G, G, G, G, G, G, G, G, G, G, G, G, G, S, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, E},
            {E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E, E}
            };
}
