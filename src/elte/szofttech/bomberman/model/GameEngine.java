package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.GUI.GameGUI;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.fields.Floor;
import elte.szofttech.bomberman.model.monsters.Monster;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameEngine {
    private List<Player> players;
    private List<Monster> monsters;

    public Field[][] getBoard() {
        return board;
    }
    private Field[][] board;
    private GameGUI gameGUI;

    public GameEngine(){
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.board = new Floor[12][12];
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                this.board[i][j] = new Floor();
            }
        }
    }

    public void StartCharSelect(){}
    public void SwitchScene(){}
    public void StartGame(){}
    public void EventHandle(KeyEvent event){}
    public void PaintComponent(){}
    public void Update(){}
    public void EndGame(){}
    public void DetonateBomb(Bomb bomb){}
}
