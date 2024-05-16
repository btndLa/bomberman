package elte.szofttech.bomberman.tests;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.monsters.BasicMonster;
import elte.szofttech.bomberman.model.monsters.Hunter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MonsterTest {

    Hunter monster;
    Hunter monsterInCorner;
    Player player;
    GameEngine engine;
    @BeforeEach
    public void setUp(){
        engine = new GameEngine(1000,2);
        engine.finishedCharSelect(2,2,1);
        player = engine.getPlayers().get(0);
        monster = new Hunter(1,3,engine,1);
        monsterInCorner = new Hunter(2,1,engine,1);
    }
    @Test
    public void testConstructor(){
        assertEquals(1,monster.getX());
        assertEquals(3,monster.getY());
    }
    @Test
public void testMoveUp() {
    monster = new Hunter(2, 2, engine, 1);
    monster.move();
    assertEquals(1, monster.getY());
    assertEquals(2, monster.getX());
}

@Test
public void testMoveDown() {
    monster = new Hunter(2, 2, engine, 2);
    monster.move();
    assertEquals(3, monster.getY());
    assertEquals(2, monster.getX());
}

@Test
public void testMoveLeft() {
    monster = new Hunter(2, 2, engine, 3);
    monster.move();
    assertEquals(1, monster.getX());
    assertEquals(2, monster.getY());
}

@Test
public void testMoveRight() {
    monster = new Hunter(2, 2, engine, 4);
    monster.move();
    assertEquals(3, monster.getX());
    assertEquals(2, monster.getY());
}
    @Test
    public void canMoveToValidField(){
        monster.move();
        assertEquals(2, monster.getY());
    }
    @Test
    public void cantMoveToThroughWall(){
        monsterInCorner.move();
        assertEquals(1, monsterInCorner.getY());
    }
    @Test
    public void cantMoveToThroughBox(){
        monsterInCorner = new Hunter(2,1,engine,3);
        monsterInCorner.move();
        assertNotEquals(1, monsterInCorner.getX());
    }
    @Test
    public void movingToPlayerEliminatesPlayer(){
      monster = new Hunter(2,3,engine,3);
      monster.move();
      assertEquals(1, monster.getX());
      assertFalse(player.isAlive());
    }
    @Test
    public void notMovingToPlayerDoesntEliminatePlayer(){
      monster = new Hunter(3,3,engine,3);
      monster.move();
      player.onCollision(monster);
      assertTrue(player.isAlive());
    }

}
