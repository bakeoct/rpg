package com.example.rpg.Calc.map.cave;
import java.io.Serializable;

import static com.example.rpg.Calc.map.Map.*;
public class Cave1_1 implements Serializable {
    public static final int INITIAL_X = 2;
    public static final int INITIAL_Y = 7;
    public static final int BACK_CAVE1_INITIAL_X = 2;
    public static final int BACK_CAVE1_INITIAL_Y = 1;
    public static final String CV1_1 = "cave1_1";//Cave1_1;
    public static String[][] cave1_1 = {
            {E,E,E,E,E,E,E,E,E},
            {E,SN,SN,SN,SN,SN,SN,SN,E},
            {E,SN,SN,SN,SN,SN,SN,SN,E},
            {E,SN,SN,SN,SN,SN,SN,SN,E},
            {E,SN,SN,SN,SN,SN,SN,SN,E},
            {E,SN,SN,SN,E,E,E,E,E,E,E},
            {E,SN,SN,SN,E,E,E,E,E,E,E},
            {E,SN,SN,SN,E,E,E,E,E,E,E},
            {E,E,BCV1,E,E,E,E,E,E},
    };
}
