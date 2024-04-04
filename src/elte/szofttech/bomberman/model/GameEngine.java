package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.GUI.GameGUI;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Box;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.fields.Floor;
import elte.szofttech.bomberman.model.fields.Wall;
import elte.szofttech.bomberman.model.monsters.BasicMonster;
import elte.szofttech.bomberman.model.monsters.Monster;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;



public class GameEngine extends JPanel implements KeyListener{
    private List<Player> players;
    private List<Monster> monsters;
    private boolean isGameOver;
    private Field[][] board;
    private GameGUI gameGUI;
    private int bombRadius;
    private int bombDetonation;
    private int tileSize;
    private Timer timer;
    private ArrayList<Bomb> bombs;

    public GameEngine(){
      super();
      isGameOver = false;
      setupTimer();
      setFocusable(true);
      addKeyListener(this);
      board = new Field[13][13];
      tileSize = 75;
      bombRadius = 2;
      bombDetonation = 3;
      this.StartGame();
    }

    private void setupTimer() {
      timer = new Timer(1000, new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
          for (Monster monster : monsters) {
            monster.move();
          }
          repaint();
        }
    });
    timer.start(); 

    }

    public void StartCharSelect(){}
    public void SwitchScene(){}
    public void StartGame(){
      players = new ArrayList<Player>();
      loadLevel();
      players.add(new Player(1, 2, 38, 40, 37, 39, 96, this));
      players.add(new Player(10, 2, 87, 83, 65, 68, 81, this));
      monsters = new ArrayList<Monster>();
      monsters.add(new BasicMonster(3, 4, 1, this));
      monsters.add(new BasicMonster(5, 11, 1, this));
      bombs = new ArrayList<Bomb>();
    }
    public Field[][] getBoard(){
      return this.board;
    }
    public int getTileSize(){return tileSize;}
    public void keyPressed(KeyEvent e) {
      for (Player player : players) {
        player.move(e);
      }
      repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    public void Update(){}
    public void EndGame(){}

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

    public void DetonateBomb(Bomb bomb){
      bombs.add(bomb);
      Timer detonationTimer = new Timer(bomb.getDetonation()*1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          explosion(bomb.getX(), bomb.getY(), bomb);
            bombs.remove(bomb);
            repaint();
        }
    });
    detonationTimer.setRepeats(false);
    detonationTimer.start();
    }

    private void explosion(int x, int y,Bomb bomb){
      
      ArrayList<Field> fields = new ArrayList<>();
      fields.addAll(spreadExplosionUp(board[y-1][x],bomb.getRadius()));
      // fields.add(board[y+1][x]);
      // fields.add(board[y][x-1]);
      // fields.add(board[y][x+1]);
      //
      fields.add(board[y][x]);
      ArrayList<Field> explosionFields = new ArrayList<>();
      explosionFields.addAll(fields);
      for (Field field : explosionFields) {
        field.setColor(Color.ORANGE);
        field.draw(getGraphics(), field.getX(), field.getY());
      }

    }
    
    private ArrayList<Field> spreadExplosionUp(Field field, int radius){
      ArrayList<Field> fields = new ArrayList<>();
      for (int i = 0; i < radius; i++) {
        if (board[field.getY()/this.tileSize-i][getX()/this.tileSize].isDestructible()){
          System.out.println(field.getY()/this.tileSize-i);
          fields.add(board[field.getY()/this.tileSize-i][getX()/this.tileSize]);
        }else{
          return fields;
        }
      }
      return fields;
    }
    

    @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      int y = 0;
      for(int i = 0; i < board.length; i++) {
        int x = 0;
        for (int z = 0; z < board[0].length; z++) {
          Field f = board[i][z];
                f.draw(g, x, y);
                x+=tileSize;
            }
            y+=tileSize;
        }
        if (this.players != null) {
          for (Player player : players) {
            player.draw(g);
          }
        }
        if (this.monsters != null) {
          for (Monster monster : monsters) {
            monster.draw(g);
          }
        }
        if (this.bombs != null) {
          for (Bomb bomb : bombs) {
            bomb.draw(g, bomb.getX(), bomb.getY());
          }
        }
      
    }
}
