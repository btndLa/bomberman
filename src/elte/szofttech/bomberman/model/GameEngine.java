package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.GUI.GameGUI;
import elte.szofttech.bomberman.GUI.HUDPanel;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Box;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.fields.Floor;
import elte.szofttech.bomberman.model.fields.Obstacles;
import elte.szofttech.bomberman.model.fields.Wall;
import elte.szofttech.bomberman.model.monsters.BasicMonster;
import elte.szofttech.bomberman.model.monsters.Monster;
import elte.szofttech.bomberman.model.powerups.PowerUp;
import elte.szofttech.bomberman.model.powerups.RollerBlade;
import elte.szofttech.bomberman.model.powerups.BombDetonator;
import elte.szofttech.bomberman.model.powerups.BombRangeBonus;
import elte.szofttech.bomberman.model.powerups.BonusBomb;
import elte.szofttech.bomberman.model.powerups.Ghost;
import elte.szofttech.bomberman.model.powerups.Invulnerable;
import elte.szofttech.bomberman.model.powerups.Obstacle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.KeyListener;


/* Enginge of the game. Responsible for simulation of gameplay,
 * handling explsions and moving entities.
*/
public class GameEngine extends JPanel implements KeyListener{
    private List<Player> players;
    private int[][] playerPos;
    private int playerNum;
    private static final int[][] PLAYER_CONTROLS = {{87, 83, 65, 68, 81, 69},
                                                    {38, 40, 37, 39, 96, 97},                                                  
                                                    {73, 75, 74, 76, 85, 79}};
    private List<Monster> monsters;
    private int monstNum;
    private boolean isGameOver;
    private Field[][] board;
    private int boardWidth;
    private State currentState;
    private static final int BOARD_SIZE = 13;
    private int tileSize;
    private GameGUI gameGUI;
    private int bombRadius;
    private int bombDetonation;
    private Timer timer;
    private ArrayList<Bomb> bombs;
    private ArrayList<Obstacles> obstacles;
    private HUDPanel hud;
    private int timeElapsed;

    public GameEngine(int width, int playerNum){
      super();
      this.hud = null;
      this.boardWidth = width;
      this.tileSize = width/BOARD_SIZE;
      this.playerNum = playerNum;

      this.playerPos = new int[playerNum][2];
      this.currentState = State.CHARSELECT;
      isGameOver = false;
    //  setupTimer();
      setFocusable(true);
      addKeyListener(this);
      board = new Field[BOARD_SIZE][BOARD_SIZE];
      this.StartCharSelect();
      //  StartGame();
    }

    // Getters
    public Field[][] getBoard(){ return this.board;}

    public List<Player> getPlayers() {
        return players;
    }

    public int gettileSize(){ return tileSize;}
    public List<Monster> getMonsters(){ return monsters;}
    public void setHUD(HUDPanel hud){ this.hud = hud;}
    // Timer loop resoinsible for moving monsters
    private void setupTimer() {
      timer = new Timer(1000, new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
          timeElapsed+=1;
          int minutes = timeElapsed / 60;
          int seconds = timeElapsed % 60;
          hud.updateTime(String.format("%02d:%02d", minutes, seconds));
          for (Monster monster : monsters) {
            monster.move();
            for(Player player : players){
                player.onCollision(monster);
            }
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


    public void StartCharSelect(){

    }

    public void finishedCharSelect(int playerNum, int monstNum){
        this.playerNum = playerNum;
        this.monstNum = monstNum;
        loadLevel();
        SwitchScene();
        StartGame();
        setupTimer();
    }
    public void SwitchScene(){
        this.removeAll();
        this.currentState = State.values()[(this.currentState.ordinal() + 1) % State.values().length];
        if(this.currentState == State.GAME){
            StartGame();
        }
        repaint();
    }
    public void StartGame(){
      players = new ArrayList<Player>();
      loadLevel();
      for (int i = 0; i < playerNum; i++) {
        players.add(new Player(playerPos[i][0], playerPos[i][1], PLAYER_CONTROLS[i][0], PLAYER_CONTROLS[i][1],
        PLAYER_CONTROLS[i][2], PLAYER_CONTROLS[i][3], PLAYER_CONTROLS[i][4],PLAYER_CONTROLS[i][5], this));
      }
      monsters = new ArrayList<Monster>();
      monsters.add(new BasicMonster(3, 4,  this,1));
      monsters.add(new BasicMonster(5, 11, this,1));

      if(monstNum == 3){
          monsters.add(new BasicMonster(7, 11, this,1));
      }

      bombs = new ArrayList<Bomb>();
      obstacles = new ArrayList<Obstacles>();
    }

    public void newRound(){
      loadLevel();
      monsters = new ArrayList<Monster>();
      monsters.add(new BasicMonster(3, 4,  this,1));
      monsters.add(new BasicMonster(5, 11, this,1));
      if(monstNum == 3){
          monsters.add(new BasicMonster(7, 11, this,1));
      }

      bombs = new ArrayList<Bomb>();
      setupPlayers();
      setupTimer();
      timeElapsed = 0;
      hud.updateTime("00:00");
      repaint();
    }

    private void setupPlayers(){
      for (int i = 0; i < playerNum; i++) {
        players.get(i).setX(playerPos[i][0]);
        players.get(i).setY(playerPos[i][1]);
        players.get(i).setAlive();
      }
    }
  

    // Setup board fields
    private void loadLevel(){
       Random random = new Random();
      try (BufferedReader reader = new BufferedReader(new FileReader("src/elte/szofttech/bomberman/assets/levels/level1.txt"))) {
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
                    boolean isPowerUp = random.nextInt(6) == 1;
                    Box box = new Box(x, y, tileSize);
                    box.setPowerUp(isPowerUp);
                    board[row][col] = box;
                    //System.out.println(isPowerUp);
                    break;

                    case 'W':
                    Wall wall = new Wall(x, y, tileSize);
                    board[row][col] = wall;
                    break;

                    case 'F':
                    Floor floor = new Floor(x, y, tileSize);
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
      board[bomb.getY()/tileSize][bomb.getX()/tileSize] = bomb;
      
      Timer detonationTimer = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          bombs.remove(bomb);
          if(p!= null) {
            p.getBombsOnGround().remove(bomb);
            System.out.println("Bombák:" + p.bombsOnGround.toString());
            p.setPlacedBombs(p.getPlacedBombs()-1);
            System.out.println("BombCount" + p.getPlacedBombs());
          }
          board[bomb.getY()/tileSize][bomb.getX()/tileSize] = new Floor(bomb.getX(),bomb.getY(), tileSize);
          explosion(bomb.getX()/tileSize, bomb.getY()/tileSize, bomb);
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
              if (board[y - counter][x] instanceof Box || board[y - counter][x] instanceof Obstacles) {
                directions[0] = false;
                }
            } else { directions[0] = false;}
            if (y + counter < board.length && directions[1] && board[y + counter][x].isDestructible()) {
              fields.add(board[y + counter][x]);
              if (board[y + counter][x] instanceof Box || board[y + counter][x] instanceof Obstacles) {
                directions[1] = false;
                }
            } else { directions[1] = false;}
            if (x - counter >= 0 && directions[2] && board[y][x - counter].isDestructible()) {
              fields.add(board[y][x - counter]);
              if (board[y][x - counter] instanceof Box || board[y][x - counter] instanceof Obstacles) {
                  directions[2] = false;
                }
            } else {directions[2] = false;}
            if (x + counter < board[0].length && directions[3] && board[y][x + counter].isDestructible()) {
              fields.add(board[y][x + counter]);
              if (board[y][x + counter] instanceof Box || board[y][x + counter] instanceof Obstacles) {
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
      Random random = new Random();
      int randomNumber = random.nextInt(6);
      int teszt = 6;
      field.setColor(Color.ORANGE);
      field.draw(getGraphics(), field.getX(), field.getY());
      Timer explosionTimer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if (field instanceof Box) {
            Box box = (Box) field;
            if (box.isPowerUp == true) { 
              switch (randomNumber) {
                case 0:
                board[field.getY() / tileSize][field.getX() / tileSize] = new BombRangeBonus(field.getX(), field.getY(), tileSize);
                    break;
                case 1:
                board[field.getY() / tileSize][field.getX() / tileSize] = new BonusBomb(field.getX(), field.getY(), tileSize);
                    break;
                case 2:
                board[field.getY() / tileSize][field.getX() / tileSize] = new BombDetonator(field.getX(), field.getY(), tileSize);
                    break;
                case 3:
                board[field.getY() / tileSize][field.getX() / tileSize] = new Invulnerable(field.getX(), field.getY(), tileSize);
                    break;
                case 4:
                board[field.getY() / tileSize][field.getX() / tileSize] = new Ghost(field.getX(), field.getY(), tileSize);
                    break;
                case 5:
                board[field.getY() / tileSize][field.getX() / tileSize] = new RollerBlade(field.getX(), field.getY(), tileSize);
                    break;
                case 6:
                board[field.getY() / tileSize][field.getX() / tileSize] = new Obstacle(field.getX(), field.getY(), tileSize);
                    break;
                default:
                    break;
            }
            } else { 
                board[field.getY() / tileSize][field.getX() / tileSize] = new Floor(field.getX(), field.getY(), tileSize);
            }
            repaint();
        }
        if (field instanceof Obstacles) {
          board[field.getY() / tileSize][field.getX() / tileSize] = new Floor(field.getX(), field.getY(), tileSize);
          obstacles.remove((Obstacles) field);         
          repaint();
        }
          if (field instanceof Bomb && (Bomb)field != bomb){
            ((Bomb)field).restartTimer();
          }
            field.setColor(field.getDefaultColor());
            field.draw(getGraphics(), field.getX(), field.getY());
          for (Player player : players) {
            if(player.getX() == field.getX()/tileSize && player.getY() == field.getY()/tileSize && player.isInvulnerable == false){
              player.die();
            }
          }
          Iterator<Monster> monsterIterator = monsters.iterator();
          while (monsterIterator.hasNext()) {
            Monster monster = monsterIterator.next();
            if (monster.getX() == field.getX() / tileSize && monster.getY() == field.getY() / tileSize) {
              field.setWalkable(true);
              monsterIterator.remove();
              }
          }
        }
    });
    explosionTimer.setRepeats(false); 
    explosionTimer.start(); 
  } 

  public void checkEndGame(){
    int alive = 0;
    for (Player player : players) {
      if (player.isAlive()) alive+= 1;
    }
    if (alive == 1){ 
      Timer timer = new Timer(3000, new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
          endGame();
        }
      });
      timer.start();
      timer.setRepeats(false);
    }
  }

  public void endGame(){
    this.timer.stop();
    for (Player player : players) {
      if (player.isAlive()) {
        announceWinner(player);
        return;
      }
    }
    announceWinner(null);
  }

  public void announceWinner(Player player){
    String message;
    if (player != null) {
      player.win();
      hud.updatePlayerPoints(players.indexOf(player), player.getPoints());
      message = "Player " + Integer.toString(players.indexOf(player) + 1) +" wins!";
    }else{
      message = "Draw!";
    }
    Object[] options = { "New round!" };
    int optionChosen = JOptionPane.showOptionDialog(this.getParent(), message, "Game Over",
    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    if (optionChosen == 0) newRound();
  }
  public void detonateBombsImmediately(List<Bomb> bombs) {
    List<Bomb> allBombs = new ArrayList<>();
    for (Bomb bomb : bombs) {
        allBombs.add(bomb);
    }
    for (Bomb bomb : bombs) {
      Timer detonationTimer = bomb.getTimer();
      if (detonationTimer != null) {
          detonationTimer.stop();
      }
      bomb.setDetonation(0);
  }
  for (Bomb bomb : bombs) {
      ActionListener[] listeners = bomb.getTimer().getActionListeners();
      for (ActionListener listener : listeners) {
          listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
      }
  }
}

  public void placeObstacle(Obstacles obstacle, Player p){
    if(p != null){
      if(p.getPlacedObstacles()<= 0){return; }
      p.setObstacleCapacity((p.getPlacedObstacles()-1));
    } 
      obstacles.add(obstacle);
      repaint();
      board[obstacle.getY()/tileSize][obstacle.getX()/tileSize] = obstacle;
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    switch(currentState){
        case CHARSELECT -> System.out.println();
        case GAME -> {
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
            if (this.obstacles != null) {
              for (Obstacles obstacle : obstacles) {
                obstacle.draw(g, obstacle.getX(), obstacle.getY());
              }
          }
        }
    }
  }

  private enum State{
        CHARSELECT,GAME;
    }
}

