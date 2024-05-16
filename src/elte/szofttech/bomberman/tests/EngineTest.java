package elte.szofttech.bomberman.tests;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.GameEngine.State;
import elte.szofttech.bomberman.model.Player;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EngineTest {
    private GameEngine engine;

    @BeforeEach
    public void setUp() {
        engine = new GameEngine(600, 2);
        engine.finishedCharSelect(2, 2,1);
        engine.StartGame();
    }

    @Test
    public void testInitialization() {
        assertEquals(2, engine.getPlayers().size());
        assertNotNull(engine.getBoard());
        assertEquals(13, engine.getBoard().length);
    }
    @Test
    public void testGameState() {
        assertEquals(State.GAME, engine.getState());
    }
    @Test
    public void testCharSelectState() {
        engine = new GameEngine(600, 2);
        assertEquals(State.CHARSELECT, engine.getState());
    }
    
}
