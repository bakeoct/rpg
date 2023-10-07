package com.example.rpg.Calc;

import com.example.rpg.Calc.Monsters.DragonKing;
import com.example.rpg.Calc.Monsters.Gorlem;
import com.example.rpg.Calc.Monsters.MetalSlime;
import com.example.rpg.Calc.Monsters.PutiSlime;

import junit.framework.TestCase;

public class BattleManagerTest extends TestCase {
    public void testAttack(){
    }

    public void testTurn() {
    }

    public void testBattle() {
    }

    public void testJudgeSente() {
        Gorlem g = new Gorlem();
        DragonKing d = new DragonKing();
        assertFalse(BattleManager.judgeSente(g.judge_sente, d.judge_sente));
        assertTrue(BattleManager.judgeSente(d.judge_sente, g.judge_sente));
    }
}