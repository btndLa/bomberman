package elte.szofttech.bomberman.tests;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EngineTest {
    private GameEngine engine;

    @BeforeEach
    public void setUp() {
        engine = new GameEngine(600, 2, "src/elte/szofttech/bomberman/assets/levels/testMap.txt");
        engine.StartGame();
    }

    @Test
    public void testInitialization() {
        assertEquals(2, engine.getPlayers().size());
        assertNotNull(engine.getBoard());
        assertEquals(13, engine.getBoard().length);
    }
    @Test
    public void testGameStateTransition() {
        engine.finishedCharSelect(2, 2);
        assertEquals(engine.State.GAME, engine.getCurrentState());
    }
    
}
