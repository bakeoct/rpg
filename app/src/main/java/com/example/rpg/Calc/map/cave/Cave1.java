package com.example.rpg.Calc.map.cave;
import java.io.Serializable;

import static com.example.rpg.Calc.map.Map.*;
import static com.example.rpg.Calc.map.cave.Cave1_1.*;

import com.example.rpg.Calc.map.Map;
import com.example.rpg.Calc.map.tail.EntranceAndExitTail;
import com.example.rpg.Calc.map.tail.ErrorTail;
import com.example.rpg.Calc.map.tail.StoneTail;
import com.example.rpg.Calc.map.tail.Tail;

public class Cave1 extends Map implements Serializable {
    public static final int CAVE1_INITIAL_X = 2;
    public static final int CAVE1_INITIAL_Y = 9;
    public static final int CAVE1_BACK_MAIN_WORLD_INITIAL_X = 2;
    public static final int CAVE1_BACK_MAIN_WORLD_INITIAL_Y = 9;
    public static Tail[][] cave1 = {
            {new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new ErrorTail(),new EntranceAndExitTail(0,"to_cave1_1",0),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new StoneTail(0,0),new StoneTail(0,0),new StoneTail(0,0),new ErrorTail()},
            {new ErrorTail(),new ErrorTail(),new EntranceAndExitTail(0,"to_main",0),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()}
    };
}
