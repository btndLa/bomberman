package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.GUI.GameGUI;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.monsters.Monster;

import java.awt.event.KeyEvent;
import java.util.List;

public class GameEngine {
    private List<Player> players;
    private List<Monster> monsters;
    private Field[][] board;
    private GameGUI gameGUI;

    public void StartCharSelect(){}
    public void SwitchScene(){}
    public void StartGame(){}
    public void EventHandle(KeyEvent event){}
    public void PaintComponent(){}
    public void Update(){}
    public void EndGame(){}
    public void DetonateBomb(Bomb bomb){}
}
