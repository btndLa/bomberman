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
        engine = new GameEngine(900,2);
        engine.StartGame();
        player = engine.getPlayers().getFirst();
    }

    @Test
    public void moveTestValidToTheRight(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D));
        assertEquals(2,player.getX());
    }

    @Test
    public void moveTestOutOfTheBoard(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A));
        assertEquals(1, player.getX());
    }

    @Test
    public void moveTestToOccupiedField(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A));
        assertEquals(1, player.getX());
    }

    @Test
    public void onCollisionTestWithCollision(){
        assertTrue(player.isAlive);
        BasicMonster monster = new BasicMonster(1,3, engine,1);
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