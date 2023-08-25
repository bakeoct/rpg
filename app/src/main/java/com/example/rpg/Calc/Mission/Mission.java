package com.example.rpg.Calc.Mission;

import com.example.rpg.Calc.Person2;
import com.example.rpg.Calc.Store;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Mission implements Serializable {
    public int dangerous_lv;
    public int reward;
    public String code;
    public String name;
    public Boolean progress = false;
    public Boolean get_reward = false;
}
