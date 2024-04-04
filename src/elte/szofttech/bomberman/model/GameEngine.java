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

    public GameEngine(){
      super();
      bombRadius = 2;
      bombDetonation = 3;
      this.repaint();
      board = new Field[13][13];
    }

    public void StartCharSelect(){}
    public void SwitchScene(){}
    public void StartGame(){
      List<Player> players = new ArrayList<Player>();
      players.add(new Player(1, 2, 38, 40, 37, 39, this));
      players.add(new Player(10, 20, 87, 83, 67, 68, this));

      
    }
    public void EventHandle(KeyEvent event){}
    public void Update(){}
    public void EndGame(){}
    public void DetonateBomb(Bomb bomb){}
    public Component getTimerLabel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimerLabel'");
    }

    @Override
    public void paintComponent(Graphics g){
      int tileSize = 75; //multiple tilesizes
      super.paintComponent(g);
      try (BufferedReader reader = new BufferedReader(new FileReader("zmb/src/elte/szofttech/bomberman/assets/levels/level1.txt"))) {
        System.out.println("HELLO");
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
                    box.draw(g, x, y);
                    break;

                    case 'W':
                    Wall wall = new Wall(x, y);
                    board[row][col] = wall;
                    wall.draw(g, x, y);
                    break;

                    case 'F':
                    Floor floor = new Floor(x, y);
                    board[row][col] = floor;
                    floor.draw(g, x, y);
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
}
