package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.GUI.GameGUI;
import elte.szofttech.bomberman.GUI.HUDPanel;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Box;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.fields.Floor;
import elte.szofttech.bomberman.model.fields.Wall;
import elte.szofttech.bomberman.model.monsters.BasicMonster;
import elte.szofttech.bomberman.model.monsters.Monster;
import elte.szofttech.bomberman.model.powerups.PowerUp;
import elte.szofttech.bomberman.model.powerups.BombDetonator;
import elte.szofttech.bomberman.model.powerups.BombRangeBonus;
import elte.szofttech.bomberman.model.powerups.BonusBomb;

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
    private static final int[][] PLAYER_CONTROLS = {{87, 83, 65, 68, 81},
                                                    {38, 40, 37, 39, 96},                                                  
                                                    {73, 75, 74, 76, 80}};
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
    public int gettileSize(){ return tileSize;}
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
        this.setLayout(new GridLayout(2,2));
        JPanel playerOnePanel = new JPanel();
        playerOnePanel.setSize(this.getWidth() / 2, this.getHeight());
        playerOnePanel.setBackground(Color.green);
        ButtonGroup playerGroup = new ButtonGroup();
        //Declared as an array because of accessibility from action listeners
        final int[] players = {0};
        final boolean[] playerChoosed = {false};
        final boolean[] monsterChoosed = {false};

        JRadioButton twoplayerBTN = new JRadioButton("2");
        twoplayerBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players[0] = 2;
                playerChoosed[0] = true;
            }
        });

        JRadioButton threeplayerBTN = new JRadioButton("3");
        threeplayerBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players[0] = 3;
                playerChoosed[0] = true;
            }
        });

        playerGroup.add(twoplayerBTN);
        playerGroup.add(threeplayerBTN);

        JTextPane text = new JTextPane();
        text.setText("Players");
        text.setEnabled(false);
        playerOnePanel.add(twoplayerBTN);
        playerOnePanel.add(threeplayerBTN);
        playerOnePanel.add(text);
        this.add(playerOnePanel);

        JPanel playerTwoPanel = new JPanel();
        playerTwoPanel.setBackground(Color.green);
        playerTwoPanel.setSize(this.getWidth() / 2, this.getHeight());

        ButtonGroup monsterGroup = new ButtonGroup();
        JRadioButton twomonsterBTN = new JRadioButton("2");
        //Declared as an array because of accessibility from action listeners
        final int[] monsterNumber = {0};
        twomonsterBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monsterNumber[0] = 2;
                monsterChoosed[0] = true;
            }
        });

        JRadioButton threemonsterBTN = new JRadioButton("3");
        threemonsterBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monsterNumber[0] = 3;
                monsterChoosed[0] = true;
            }
        });

        monsterGroup.add(twomonsterBTN);
        monsterGroup.add(threemonsterBTN);

        JTextPane monstertext = new JTextPane();
        monstertext.setText("Monster");
        monstertext.setEnabled(false);

        playerTwoPanel.add(twomonsterBTN);
        playerTwoPanel.add(threemonsterBTN);
        playerTwoPanel.add(monstertext);
        this.add(playerTwoPanel);

        JPanel startPanel = new JPanel();
        startPanel.setSize(this.getWidth(), this.getHeight()/3);

        JButton startBTN = new JButton("Start");
        startBTN.setSize(50,30);
        startBTN.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playerChoosed[0] && monsterChoosed[0]){
                    playerNum = players[0];
                    monstNum = monsterNumber[0];
                    loadLevel();
                    setupTimer();
                    SwitchScene();
                }
            }
        }));
        startPanel.add(startBTN);
        this.add(startPanel);
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
        PLAYER_CONTROLS[i][2], PLAYER_CONTROLS[i][3], PLAYER_CONTROLS[i][4], this));
      }
      monsters = new ArrayList<Monster>();
      monsters.add(new BasicMonster(3, 4,  this,1));
      monsters.add(new BasicMonster(5, 11, this,1));

      if(monstNum == 3){
          monsters.add(new BasicMonster(7, 11, this,1));
      }

      bombs = new ArrayList<Bomb>();
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
                    boolean isPowerUp = random.nextInt(2) == 1; // Véletlenszám generálása (0 vagy 1)
                    Box box = new Box(x, y, tileSize);
                    box.setPowerUp(isPowerUp);
                    board[row][col] = box;
                    System.out.println(isPowerUp);
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
      Random random = new Random();
      int randomNumber = random.nextInt(3);
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
                default:
                    break;
            }
            } else { 
                board[field.getY() / tileSize][field.getX() / tileSize] = new Floor(field.getX(), field.getY(), tileSize);
            }
            repaint();
        }
          if (field instanceof Bomb && (Bomb)field != bomb){
            ((Bomb)field).restartTimer();
          }
            field.setColor(field.getDefaultColor());
            field.draw(getGraphics(), field.getX(), field.getY());
          for (Player player : players) {
            if(player.getX() == field.getX()/tileSize && player.getY() == field.getY()/tileSize){
              player.die();
            }
          }
          Iterator<Monster> monsterIterator = monsters.iterator();
          while (monsterIterator.hasNext()) {
            Monster monster = monsterIterator.next();
            if (monster.getX() == field.getX() / tileSize && monster.getY() == field.getY() / tileSize) {
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
        }
    }
  }

  private enum State{
        CHARSELECT,GAME;
    }
}

