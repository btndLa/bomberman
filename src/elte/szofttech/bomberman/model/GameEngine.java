package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.GUI.GameGUI;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Box;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.fields.Floor;
import elte.szofttech.bomberman.model.fields.Wall;
import elte.szofttech.bomberman.model.monsters.Monster;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Graphics;


import javax.swing.JPanel;



public class GameEngine extends JPanel {
    private List<Player> players;
    private List<Monster> monsters;
    private Field[][] board;
    private GameGUI gameGUI;
    private int bombRadius;
    private int bombDetonation;
    private int tileSize;

    public GameEngine(){
      super();
      board = new Field[13][13];
      tileSize = 75;
      bombRadius = 2;
      bombDetonation = 3;
      this.StartGame();
    }

    public void StartCharSelect(){}
    public void SwitchScene(){}
    public void StartGame(){
      players = new ArrayList<Player>();
      loadLevel();
      players.add(new Player(1, 2, 38, 40, 37, 39, 81, this));
      players.add(new Player(10, 2, 87, 83, 67, 68, 96, this));
    }
    public Field[][] getBoard(){
      return this.board;
    }
    public int getTileSize(){return tileSize;}
    public void EventHandle(KeyEvent event){}
    public void Update(){}
    public void EndGame(){}
    public void DetonateBomb(Bomb bomb){}
    public Component getTimerLabel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimerLabel'");
    }

    private void loadLevel(){
      try (BufferedReader reader = new BufferedReader(new FileReader("zmb/src/elte/szofttech/bomberman/assets/levels/level1.txt"))) {
        String line;
        int y = 0;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            System.out.println("Line: " + line);
            int x = 0;
            int col = 0;
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                switch (ch) {
                  case 'B':
                    Box box = new Box(x, y);
                    board[row][col] = box;
                    break;

                    case 'W':
                    Wall wall = new Wall(x, y);
                    board[row][col] = wall;
                    break;

                    case 'F':
                    Floor floor = new Floor(x, y);
                    board[row][col] = floor;
                    break;

                  default:
                    break;
                  }
                  x+=tileSize;
                  col+=1;
              }
              y+=tileSize;
              row+=1;
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
    @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      int y = 0;
      int row = 0;
      for(int i = 0; i < board.length; i++) {
        int x = 0;
        int col = 0;
        for (int z = 0; z < board[0].length; z++) {
          Field f = board[i][z];
                f.draw(g, x, y);
                x+=tileSize;
                col+=1;
            }
            y+=tileSize;
            row+=1;
        }
        if (this.players != null) {
          for (Player player : players) {
            player.draw(g);
          }
        }
      
    }
}
