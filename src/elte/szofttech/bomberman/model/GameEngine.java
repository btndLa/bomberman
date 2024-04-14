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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


/* Enginge of the game. Responsible for simulation of gameplay,
 * handling explsions and moving entities.
*/
public class GameEngine extends JPanel implements KeyListener{
    private List<Player> players;
    private int[][] playerPos;
    private int playerNum;
    private static final int[][] PLAYER_CONTROLS = {{87, 83, 65, 68, 81},
                                                    {38, 40, 37, 39, 96},                                                  
                                                    {73, 75, 74, 76, 80}};
    private List<Monster> monsters;
    private boolean isGameOver;
    private Field[][] board;
    private static final int BOARD_SIZE = 13;
    private static final int TILE_SIZE = 75;
    private GameGUI gameGUI;
    private int bombRadius;
    private int bombDetonation;
    private Timer timer;
    private ArrayList<Bomb> bombs;

    public GameEngine(int playerNum){
      super();
      this.playerNum = playerNum;
      this.playerPos = new int[playerNum][2];
      isGameOver = false;
      setupTimer();
      setFocusable(true);
      addKeyListener(this);
      board = new Field[BOARD_SIZE][BOARD_SIZE];
      this.StartGame();
    }

    // Getters
    public Field[][] getBoard(){ return this.board;}
    public int getTILE_SIZE(){ return TILE_SIZE;}

    // Timer loop resoinsible for moving monsters
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

    // Player movement listener
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


    public void StartCharSelect(){}
    public void SwitchScene(){}
    public void StartGame(){
      players = new ArrayList<Player>();
      loadLevel();
      for (int i = 0; i < playerNum; i++) {
        players.add(new Player(playerPos[i][0], playerPos[i][1], PLAYER_CONTROLS[i][0], PLAYER_CONTROLS[i][1],
        PLAYER_CONTROLS[i][2], PLAYER_CONTROLS[i][3], PLAYER_CONTROLS[i][4], this));
      }
      monsters = new ArrayList<Monster>();
      monsters.add(new BasicMonster(3, 4, 1, this,1));
      monsters.add(new BasicMonster(5, 11, 1, this,1));
      bombs = new ArrayList<Bomb>();
    }

    // Setup board fields
    private void loadLevel(){
      try (BufferedReader reader = new BufferedReader(new FileReader("zmb/src/elte/szofttech/bomberman/assets/levels/level1.txt"))) {
        String line;
        int y = 0;
        int row = 0;
        line = reader.readLine();
        String[] positions = line.split(" ");
        for (int i = 0; i < playerNum; i++) {
          playerPos[i] = new int[] {Integer.parseInt(positions[i*2]), Integer.parseInt(positions[i*2+1])};
        }
        while ((line = reader.readLine()) != null) {
            int x = 0;
            int col = 0;
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                switch (ch) {
                  case 'B':
                    Box box = new Box(x, y, TILE_SIZE);
                    board[row][col] = box;
                    break;

                    case 'W':
                    Wall wall = new Wall(x, y, TILE_SIZE);
                    board[row][col] = wall;
                    break;

                    case 'F':
                    Floor floor = new Floor(x, y, TILE_SIZE);
                    board[row][col] = floor;
                    break;

                  default:
                    break;
                  }
                  x+=TILE_SIZE;
                  col+=1;
              }
              y+=TILE_SIZE;
              row+=1;
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    // Handle placement and detonation of bomb
    public void detonateBomb(Bomb bomb, Player p){
      int delay;
      delay = p == null ? 0 : bomb.getDetonation()*1000; // If player is null, the bomb is blown up by another bomb
      if(p != null){
        if(p.getPlacedBombs()>= p.getbombCapacity()){return; }
        p.setPlacedBombs(p.getPlacedBombs()+1);
      } 
      bombs.add(bomb);
      repaint();
      board[bomb.getY()/TILE_SIZE][bomb.getX()/TILE_SIZE] = bomb;
      
      Timer detonationTimer = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          bombs.remove(bomb);
          if(p!= null) p.setPlacedBombs(p.getPlacedBombs()-1);
          board[bomb.getY()/TILE_SIZE][bomb.getX()/TILE_SIZE] = new Floor(bomb.getX(),bomb.getY(), TILE_SIZE);
          explosion(bomb.getX()/TILE_SIZE, bomb.getY()/TILE_SIZE, bomb);
        }
      });
      bomb.setTimer(detonationTimer);
      detonationTimer.setRepeats(false);
      detonationTimer.start();
    }
    // Spread explosion in all directions and apply explosion effect
    private void explosion(int x, int y, Bomb bomb) {
      Queue<Field> fields = new LinkedList<>();
      fields.add(board[y][x]);
      explosionEffect(fields.remove(), bomb);
      boolean[] directions = {true, true, true, true}; // Up, Down, Left, Right
      Timer explosionTimer = new Timer(200, new ActionListener() {
          int counter = 0;
          @Override
          public void actionPerformed(ActionEvent e) {
            if (counter > bomb.getRadius()) {
              ((Timer) e.getSource()).stop();
              return;
            }
            if (y - counter >= 0 && directions[0] && board[y - counter][x].isDestructible()) {
              fields.add(board[y - counter][x]);
              if (board[y - counter][x] instanceof Box) {
                directions[0] = false;
                }
            } else { directions[0] = false;}
            if (y + counter < board.length && directions[1] && board[y + counter][x].isDestructible()) {
              fields.add(board[y + counter][x]);
              if (board[y + counter][x] instanceof Box) {
                directions[1] = false;
                }
            } else { directions[1] = false;}
            if (x - counter >= 0 && directions[2] && board[y][x - counter].isDestructible()) {
              fields.add(board[y][x - counter]);
              if (board[y][x - counter] instanceof Box) {
                  directions[2] = false;
                }
            } else {directions[2] = false;}
            if (x + counter < board[0].length && directions[3] && board[y][x + counter].isDestructible()) {
              fields.add(board[y][x + counter]);
              if (board[y][x + counter] instanceof Box) {
                  directions[3] = false;
                }
            } else {directions[3] = false;}
            
            while (!fields.isEmpty()) {
                explosionEffect(fields.remove(), bomb);
            }
            counter++;
          }
      });
      explosionTimer.start();
  }
  
  
    // Visual explosion effect and, blwing up objects
    private void explosionEffect(Field field, Bomb bomb) {
      field.setColor(Color.ORANGE);
      field.draw(getGraphics(), field.getX(), field.getY());
      Timer explosionTimer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (field instanceof Box /*&& field.hasPowerUP()*/) {
            board[field.getY()/TILE_SIZE][field.getX()/TILE_SIZE] = new Floor(field.getX(), field.getY(), TILE_SIZE);
            repaint();
          }
          if (field instanceof Bomb && (Bomb)field != bomb){
            ((Bomb)field).restartTimer();
          }
            field.setColor(field.getDefaultColor());
            field.draw(getGraphics(), field.getX(), field.getY());
          for (Player player : players) {
            if(player.getX() == field.getX()/TILE_SIZE && player.getY() == field.getY()/TILE_SIZE){
              player.die();
            }
          }
          Iterator<Monster> monsterIterator = monsters.iterator();
          while (monsterIterator.hasNext()) {
            Monster monster = monsterIterator.next();
            if (monster.getX() == field.getX() / TILE_SIZE && monster.getY() == field.getY() / TILE_SIZE) {
              field.setWalkable(true);
              monsterIterator.remove(); // Remove the monster from the list
              }
          }
        }
    });
    explosionTimer.setRepeats(false); 
    explosionTimer.start(); 
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
          x+=TILE_SIZE;
        }
        y+=TILE_SIZE;
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
