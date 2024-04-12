package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.model.monsters.BasicMonster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private GameEngine engine;

    @BeforeEach
    public void setUp(){
        engine = new GameEngine();
        player = new Player(0,0, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 1, engine);
    }

    @Test
    public void moveTestValidToTheRight(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        assertEquals(1,player.x);
    }

    @Test
    public void moveTestOutOfTheBoard(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT));
        assertEquals(0, player.x);
    }

    @Test
    public void moveTestToOccupiedField(){
        BasicMonster monster = new BasicMonster(0,0,1, engine);
        monster.move(4);
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        assertEquals(0, player.x);
    }

    @Test
    public void onCollisionTestWithCollision(){
        assertTrue(player.isAlive);
        BasicMonster monster = new BasicMonster(0,0,1, engine);
        player.onCollision(monster);
        assertFalse(player.isAlive);
    }

    @Test
    public void onCollisionTestDifferentCoordinates(){
        assertTrue(player.isAlive);
        BasicMonster monster = new BasicMonster(10,1,1, engine);
        ;player.onCollision(monster);
        assertTrue(player.isAlive);
    }

}