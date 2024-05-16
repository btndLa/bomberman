package elte.szofttech.bomberman.tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.fields.Box;
import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.monsters.*;

public class BoxTest {

  GameEngine engine;
  Box box;
  @BeforeEach
  public void setUp(){
    engine = new GameEngine(600,2);
    engine.finishedCharSelect(2,2,4);   
    box = (Box)engine.getBoard()[2][1]; 
  }
  @Test
  public void testConstructor() {
    assertTrue(box instanceof Box);
  }
  @Test
  public void testSetPowerUp() {
    box.setPowerUp(true);
    assertTrue(box.isPowerUp);
    box.setPowerUp(false);
    assertFalse(box.isPowerUp);
  }
  @Test
  public void testIsDestructible() {
    assertTrue(box.isDestructible());
  }
  @Test
  public void testCanPlaceBomb() {
    assertFalse(box.canPlaceBomb());
  }
  @Test
  public void testCanPlaceObstacle() {
    assertFalse(box.canPlaceObstacle());
  }
}
