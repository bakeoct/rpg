package com.example.rpg.Calc.map.cave;
import java.io.Serializable;

import static com.example.rpg.Calc.map.Map.*;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
public class Cave1  implements Serializable {
    public static final int CAVE1_INITIAL_X = 2;
    public static final int CAVE1_INITIAL_Y = 9;
    public static final int CAVE1_BACK_MAIN_WORLD_INITIAL_X = 2;
    public static final int CAVE1_BACK_MAIN_WORLD_INITIAL_Y = 9;
    public static final String CV1 = "cave1";//Cave1;
    public static String[][] cave1 = {
            {E,E,E,E,E},
            {E,E,CV1_1,E,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,E,BW,E,E},
            {E,E,E,E,E}
    };
}
