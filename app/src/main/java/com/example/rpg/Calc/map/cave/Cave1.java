package com.example.rpg.Calc.map.cave;
import java.io.Serializable;

import static com.example.rpg.Calc.map.Map.*;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;
public class Cave1  implements Serializable {
    public static final int INITIAL_X = 2;
    public static final int INITIAL_Y = 7;
    public static final int BACK_MAIN_WORLD_INITIAL_X = 11;
    public static final int BACK_MAIN_WORLD_INITIAL_Y = 8;
    public static final String CV1 = "cave1";//Cave1;
    public static String[][] cave1 = {
            {E,E,CV1_1,E,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,SN,SN,SN,E},
            {E,E,BW,E,E},
    };
}
