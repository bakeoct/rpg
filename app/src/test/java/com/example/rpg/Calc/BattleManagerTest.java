package com.example.rpg.Calc;

import com.example.rpg.Calc.Monsters.DragonKing;
import com.example.rpg.Calc.Monsters.Gorlem;

import junit.framework.TestCase;

import org.junit.Assert;

public class BattleManagerTest extends TestCase {

    public void testJudgeSente() {
        Gorlem g = new Gorlem();
        DragonKing d = new DragonKing();
        Assert.assertFalse(BattleManager.judgeSente(g.judge_sente, d.judge_sente));
        Assert.assertTrue(BattleManager.judgeSente(d.judge_sente, g.judge_sente));
    }

}