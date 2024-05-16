package elte.szofttech.bomberman.tests;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.monsters.BasicMonster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private GameEngine engine;

    @BeforeEach
    public void setUp(){
        engine = new GameEngine(900,2);
        engine.finishedCharSelect(2, 2, 4);
        player = engine.getPlayers().get(0);
    }

    @Test
    public void moveTestValidToTheRight(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D,'D'));
        assertEquals(2,player.getX());
    }

    @Test
    public void moveTestOutOfTheBoard(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A,'A'));
        assertEquals(1, player.getX());
    }
    // Rewrite with testmap
    @Test
    public void moveTestToOccupiedField(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A,'A'));
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
        player.onCollision(monster);
        assertTrue(player.isAlive);
    }

    // Bomb Placement Tests
    @Test
    public void testBombPlacementUnderNormalConditions() {
      assertEquals(0, player.getPlacedBombs());
      player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Q,'Q'));
      assertEquals(1, player.getPlacedBombs());

    }

    @Test
    public void testBombPlacementWithZeroBombCapacity() {
        player.setBombCapacity(0);
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Q,'Q'));
        assertEquals(0, player.getPlacedBombs());
    }

    // Obstacle Placement Tests
    @Test
    public void testObstaclePlacementUnderNormalConditions() {
        player.setObstacleCapacity(1);
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Q,'Q'));
        assertEquals(1, player.getPlacedObstacles());
    }

    @Test
    public void testObstaclePlacementWithZeroObstacleCapacity() {
        // Test code
    }

    // Power-Up Tests
    @Test
    public void testPickupOfBombRangeBonus() {
        // Test code
    }

    @Test
    public void testPickupOfBonusBomb() {
        // Test code
    }

    // Collision Tests
    @Test
    public void testCollisionWithMonstersWhenNotInvulnerable() {
        // Test code
    }

    @Test
    public void testCollisionWithMonstersWhenInvulnerable() {
        // Test code
    }

    // Death Tests
    @Test
    public void testPlayerDeathUnderVariousConditions() {
        // Test code
    }

    // Winning Tests
    @Test
    public void testIncrementOfPointsUponWinning() {
        // Test code
    }

    // Drawing Tests
    @Test
    public void testDrawingOfPlayerSpriteOnBoard() {
        // Test code
    }

    @Test
    public void testDrawingOfIndicatorColorsForPowerUps() {
        // Test code
    }

    // Integration Tests
    @Test
    public void testInteractionsBetweenPlayerActionsAndOtherGameElements() {
        // Test code
    }

    // Edge Cases
    @Test
    public void testExtremeCases() {
        // Test code
    }

}
