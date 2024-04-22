package elte.szofttech.bomberman.tests;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
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
        engine = new GameEngine(2);
        player = new Player(0,0, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 1, engine);
    }

    @Test
    public void moveTestValidToTheRight(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        assertEquals(1,player.getX());
    }

    @Test
    public void moveTestOutOfTheBoard(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT));
        assertEquals(0, player.getX());
    }

    @Test
    public void moveTestToOccupiedField(){
        BasicMonster monster = new BasicMonster(0,0, engine,1);
        monster.move();
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        assertEquals(0, player.getX());
    }

    @Test
    public void onCollisionTestWithCollision(){
        assertTrue(player.isAlive);
        BasicMonster monster = new BasicMonster(0,0, engine,1);
        player.onCollision(monster);
        assertFalse(player.isAlive);
    }

    @Test
    public void onCollisionTestDifferentCoordinates(){
        assertTrue(player.isAlive);
        BasicMonster monster = new BasicMonster(10,1, engine,1);
        ;player.onCollision(monster);
        assertTrue(player.isAlive);
    }

}
