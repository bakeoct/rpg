package com.example.rpg.Calc.map;
import java.io.Serializable;

import static com.example.rpg.Calc.map.Map.*;
public class PersonHome1 implements Serializable {
    public static final int INITIAL_X = 4;
    public static final int INITIAL_Y = 7;
    public static final int BACK_MAIN_WORLD_INITIAL_X = 4;
    public static final int BACK_MAIN_WORLD_INITIAL_Y = 9;
    public static final String PH1 = "people_home_1";//peopleHome1
    public static String[][] people_home1 = {
            {E,E,E,E,E,E,E,E,E},
            {E,W,W,W,W,W,W,W,E},
            {E,W,W,W,W,W,W,W,E},
            {E,W,W,W,W,W,W,W,E},
            {E,W,W,W,W,W,W,W,E},
            {E,W,W,W,W,W,W,W,E},
            {E,W,W,W,W,W,W,W,E},
            {E,W,W,W,W,W,W,W,E},
            {E,E,E,E,BW,E,E,E,E},
    };
}
