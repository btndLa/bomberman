package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.GUI.GameGUI;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.monsters.Monster;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JPanel;

public class GameEngine extends JPanel {
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
    public Component getTimerLabel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimerLabel'");
    }
}
