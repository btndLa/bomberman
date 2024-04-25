package elte.szofttech.bomberman.tests;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.monsters.BasicMonster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTest {

    BasicMonster monster;
    GameEngine engine;
    @BeforeEach
    public void setUp(){
        engine = new GameEngine(1000,1000);
        monster = new BasicMonster(1,3,engine,1);
    }

    @Test
    public void testConstructor(){
        assertEquals(1,monster.getX());
        assertEquals(3,monster.getY());
    }

    @Test
    public void canMoveToValidField(){
        assertEquals(1, monster.getX());
        monster.move();
    }

}
