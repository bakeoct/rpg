package com.example.rpg.Calc.map;
import java.io.Serializable;

import static com.example.rpg.Calc.map.Map.*;

import com.example.rpg.Calc.map.tail.EntranceAndExitTail;
import com.example.rpg.Calc.map.tail.ErrorTail;
import com.example.rpg.Calc.map.tail.Tail;
import com.example.rpg.Calc.map.tail.TreasureBoxTail;
import com.example.rpg.Calc.map.tail.WoodTail;

public class PersonHome1 extends Map implements Serializable {
    public static final int PERSON_HOME1_INITIAL_X = 4;
    public static final int PERSON_HOME1_INITIAL_Y = 7;
    public static final int PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_X = 18;
    public static final int PERSON_HOME1_BACK_MAIN_WORLD_INITIAL_Y = 1;
    public static final String PH1 = "people_home_1";
    public static final String BWH = "back_world_home";
    public static Tail[][] people_home1 = {
            {new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new TreasureBoxTail(0,"people_home1_1",0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new ErrorTail()},
            {new ErrorTail(),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new ErrorTail()},
            {new ErrorTail(),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new ErrorTail()},
            {new ErrorTail(),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new ErrorTail()},
            {new ErrorTail(),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new ErrorTail()},
            {new ErrorTail(),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new ErrorTail()},
            {new ErrorTail(),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new WoodTail(0,0),new ErrorTail()},
            {new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new EntranceAndExitTail(1,"to_main",0),new ErrorTail(),new ErrorTail(),new ErrorTail()},
            {new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail(),new ErrorTail()}
    };
}
