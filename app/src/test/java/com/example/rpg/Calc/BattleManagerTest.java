package com.example.rpg.Calc;

import static com.example.rpg.Calc.Game.game;

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
        MetalSlime m = new MetalSlime();
        assertFalse(game.battle_manager.judgeSente(g.judge_sente, m.judge_sente));
        assertFalse(game.battle_manager.judgeSente(m.judge_sente, g.judge_sente));
    }

}