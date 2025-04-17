package com.example.rpg.Calc.map.cave;
import java.io.Serializable;

import static com.example.rpg.Calc.map.Map.*;

import com.example.rpg.Calc.map.Map;
import com.example.rpg.Calc.map.tail.EntranceAndExitTail;
import com.example.rpg.Calc.map.tail.ErrorTail;
import com.example.rpg.Calc.map.tail.StoneTail;
import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.Calc.map.tail.TreasureBoxTail;

public class Cave1_1 extends Map implements Serializable {
    public static final int CAVE1_1_INITIAL_X = 2;
    public static final int CAVE1_1_INITIAL_Y = 8;
    public static final int CAVE1_1_BACK_CAVE1_INITIAL_X = 2;
    public static final int CAVE1_1_BACK_CAVE1_INITIAL_Y = 1;
    public static Tail[][] cave1_1 = {
            {new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new TreasureBoxTail(0,"cave1_1"),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new ErrorTail(),new EntranceAndExitTail(0,"to_cave1"),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()},
    };
}
