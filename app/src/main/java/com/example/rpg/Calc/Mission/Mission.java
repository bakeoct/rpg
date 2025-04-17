package com.example.rpg.Calc.Mission;

import java.io.Serializable;

public abstract class Mission implements Serializable {
    public int dangerous_lv;
    public int reward;
    public String code;
    public String name;
    public Boolean progress = false;
    public Boolean get_reward = false;
}
