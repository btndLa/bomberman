package elte.szofttech.bomberman.tests;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.monsters.BasicMonster;
import elte.szofttech.bomberman.model.powerups.*;
import elte.szofttech.bomberman.model.fields.Bomb;
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
    public void initialAliveTest(){
        assertTrue(player.isAlive());
    }
    @Test
    public void initialRadiusTest(){
      assertEquals(3,player.getBombRadius());
    }
    @Test
    public void initialBombCapacityTest(){
      assertEquals(1,player.getbombCapacity());
    }
    @Test
    public void moveTestValidToTheRight(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D,'D'));
        assertEquals(2,player.getX());
    }

    @Test
    public void placeBombTest(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Q,'Q'));
        assertEquals(1,engine.getBombs().size());
    }

    @Test
    public void moveTestOutOfTheBoard(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A,'A'));
        assertEquals(1, player.getX());
    }
    @Test
    public void moveTestToBoxField(){
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A,'A'));
        assertEquals(1, player.getX());
    }
    @Test
    public void moveTestToBombField(){
      Bomb bomb = new Bomb(2,1,1,3,1);
      engine.getBoard()[2][1] = bomb;
      player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A,'A'));
      assertTrue(player.isAlive());
    }
    @Test
    public void moveTestToPlayerField(){
      player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D,'D'));
      player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D,'D'));
      assertEquals(2, player.getX());
    }
    @Test
    public void moveTestToMonsterWithInvulnerable(){
      BasicMonster monster = new BasicMonster(2,1, engine,1);
      Invulnerable i = new Invulnerable(1, 1, 1);
      player.powerUpPickup(i);
      player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D,'D'));
      player.onCollision(monster);
      assertEquals(2, player.getX());
      assertTrue(player.isAlive());
    }
    @Test
    public void moveTestWhenNotAlive(){
      player.die();
      player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D,'D'));
      assertEquals(1, player.getX());
    }
    // Bomb Placement Tests
    @Test
    public void testBombPlacementUnderNormalConditions() {
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
        player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_E,'E'));
        assertEquals(1, engine.getObstacles().size());
    }

    @Test
    public void testObstaclePlacementWithZeroObstacleCapacity() {
      player.setObstacleCapacity(0);
      player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_E,'E'));
      assertEquals(0, engine.getObstacles().size());
    }

    // Power-Up Tests
    @Test
    public void testPickupOfBombRangeBonus() {
        player.setBombRadius(3);
        BombRangeBonus b = new BombRangeBonus(1, 1, 1);
        player.powerUpPickup(b);
        assertEquals(4, player.getBombRadius());
    }

    @Test
    public void testPickupOfBonusBomb() {
      player.setBombCapacity(1);
      BonusBomb b = new BonusBomb(1, 1, 1);
      player.powerUpPickup(b);
      assertEquals(2, player.getbombCapacity());
    }

    @Test
    public void testPickupOfGhost() {
      Ghost g = new Ghost(1, 3, 1);
      player.powerUpPickup(g);
      player.move(new KeyEvent((new JPanel()),KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S,'S'));
      assertEquals(2, player.getY());
    }
    // Collision Tests
    @Test
    public void onCollisionTestWithCollision(){
        assertTrue(player.isAlive);
        BasicMonster monster = new BasicMonster(1,1, engine,1);
        player.onCollision(monster);
        assertFalse(player.isAlive);
    }

    @Test
    public void onCollisionTestDifferentCoordinates(){
        assertTrue(player.isAlive);
        BasicMonster monster = new BasicMonster(2,1, engine,1);
        player.onCollision(monster);
        assertTrue(player.isAlive);
    }

    @Test
    public void testCollisionWithMonstersWhenInvulnerable() {
      assertTrue(player.isAlive);
      Invulnerable i = new Invulnerable(1, 1, 1);
      player.powerUpPickup(i);
      BasicMonster monster = new BasicMonster(1,1, engine,1);
      player.onCollision(monster);
      assertTrue(player.isAlive);
    }

    // Death Tests
    @Test
    public void testPlayerDeathWhenOnBomb() {
      player.onExplosion();
      assertFalse(player.isAlive());
    }
    @Test
    public void testPlayerDeathOnBombWhenInvulnerble() {
      Invulnerable i = new Invulnerable(1, 1, 1);
      player.powerUpPickup(i);
      player.onExplosion();
      assertTrue(player.isAlive());
    }
    // Winning Tests
    @Test
    public void testIncrementOfPointsUponWinning() {
        player.win();
        assertEquals(1, player.getPoints());
    }    
}
