package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Floor;
import elte.szofttech.bomberman.model.monsters.Monster;
import elte.szofttech.bomberman.model.powerups.BombDetonator;
import elte.szofttech.bomberman.model.powerups.BombRangeBonus;
import elte.szofttech.bomberman.model.powerups.BonusBomb;
import elte.szofttech.bomberman.model.powerups.Invulnerable;
import elte.szofttech.bomberman.model.powerups.PowerUp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    //Control key codes
    private int up;
    private int down;
    private int left;
    private int right;
    private int bombButton;
    private int bombRadius;
    private GameEngine engine;
    private int bombCapacity;
    private int placedBombs;
    public boolean isAlive;
    public boolean hasDetonator;
    public List<Bomb> bombsOnGround;
    public boolean isInvulnerable;

    public Player(int x, int y, int up, int down, int left, int right, int bombButton, GameEngine engine) {
        super(x, y);
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bombButton = bombButton;
        this.engine = engine;
        this.bombCapacity = 1;
        this.bombRadius = 3;
        this.placedBombs = 0;
        this.isAlive = true;
        this.bombsOnGround = new ArrayList<>();
        this.hasDetonator = false;
        this.isInvulnerable = false;
        engine.getBoard()[this.y][this.x].setWalkable(false);
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getBombRadius(){return bombRadius;}
    public int getbombCapacity(){return bombCapacity;}
    public int getPlacedBombs(){return placedBombs;}
    public List<Bomb> getBombsOnGround() {return bombsOnGround;}
    public void setPlacedBombs(int n){this.placedBombs = n;}
    public void setBombRadius(int n) {this.bombRadius = n;}
    public void setBombCapacity(int n){this.bombCapacity = n;}

    public void move(KeyEvent key){
      if (!this.isAlive) {return;}
      int currentX = this.x;
      int currentY = this.y;
      int keyAsInt = key.getKeyCode();
      if(keyAsInt == this.up){
          if(this.y - 1 >= 0 && (engine.getBoard()[this.y - 1][this.x].isWalkable() || isMonster(x, y-1) != null)){
              this.y -= 1;
          }
      }
      else if(keyAsInt == this.down){
          if(this.y + 1 < engine.getBoard().length && (engine.getBoard()[this.y + 1][this.x].isWalkable() || isMonster(x, y+1) != null)){
              this.y += 1;
          }
      }
      else if(keyAsInt == this.left){
          if(this.x - 1 >= 0 && (engine.getBoard()[this.y][this.x - 1].isWalkable() || isMonster(x-1, y) != null)){
              this.x -= 1;
          }
      }
      else if(keyAsInt == this.right){
          if(this.x + 1 < engine.getBoard()[0].length && (engine.getBoard()[this.y][this.x + 1].isWalkable() || isMonster(x+1, y) != null)){
            this.x += 1;
          }
      }
      else if (keyAsInt == this.bombButton){
          if(engine.getBoard()[this.y][this.x].canPlaceBomb() && this.hasDetonator == false){
            Bomb newBomb = new Bomb(currentX * engine.gettileSize(), currentY * engine.gettileSize(), this.bombRadius, 3, engine.gettileSize());
            if(this.bombsOnGround.size() < this.bombCapacity){
                this.bombsOnGround.add(newBomb);
            }
            System.out.println("Bombák:" + bombsOnGround.toString());
            engine.detonateBomb(newBomb, this);
          }
          else if (engine.getBoard()[this.y][this.x].canPlaceBomb() && this.hasDetonator == true){
            if (this.bombsOnGround.size() < this.bombCapacity){
                Bomb newBomb = new Bomb(currentX * engine.gettileSize(), currentY * engine.gettileSize(), this.bombRadius, 999999, engine.gettileSize());
                if(this.bombsOnGround.size() < this.bombCapacity){
                    this.bombsOnGround.add(newBomb);
                }
                System.out.println("Bombák:" + bombsOnGround.toString());
                engine.detonateBomb(newBomb, this);
            }
            else
                engine.detonateBombsImmediately(bombsOnGround);
          }
      }
      if((currentX != this.x || currentY != this.y) && !(engine.getBoard()[currentY][currentX] instanceof Bomb) ) engine.getBoard()[currentY][currentX].setWalkable(true);
      engine.getBoard()[this.y][this.x].setWalkable(false);
      if (engine.getBoard()[currentY][currentX] instanceof PowerUp) {
        PowerUp currentPowerUp = (PowerUp) engine.getBoard()[currentY][currentX];
        powerUpPickup(currentPowerUp);
        engine.getBoard()[currentY][currentX] = new Floor(currentX * engine.gettileSize(), currentY * engine.gettileSize(), engine.gettileSize());
    }
    Monster monster = isMonster(x, y);
    if (monster != null) this.die();
  }

  // Checks if a field ha  player on it
  private Monster isMonster(int x, int y){
    for (Monster monster : engine.getMonsters()) {
      if(monster.getX() == x && monster.getY() == y && !(engine.getBoard()[y][x] instanceof Bomb)) return monster; 
    }
    return null;
  }

  public void die(){this.isAlive = false;}

    @Override
    public void onExplosion() {
        this.isAlive = false;
    }

    @Override
    public void onCollision(Entity e) {
        if(e.x == this.x && e.y == this.y && e instanceof Monster){
            this.isAlive = false;
        }
    }
    
    public void powerUpPickup(PowerUp e) {
            if (e instanceof BombRangeBonus) {
                ((BombRangeBonus) e).applyOnPlayer(this);
                System.out.println("range updated" + bombRadius);
            } else if (e instanceof BonusBomb) {
                ((BonusBomb) e).applyOnPlayer(this);
                System.out.println("counter updated" + bombCapacity);
            }else if (e instanceof BombDetonator) {
                ((BombDetonator) e).applyOnPlayer(this);
                System.out.println("Detonator collected!");
            }else if (e instanceof Invulnerable) {
                ((Invulnerable) e).applyOnPlayer(this);
                System.out.println("Invulnerability collected!");
            }
            
        }


    public void draw(Graphics g) {
      int ts = engine.gettileSize();
      g.setColor(Color.RED);
      g.fillRect(x * ts, y * ts,ts,ts); 
      if (isInvulnerable) { // Ellenőrizzük, hogy az isInvulnerable true-e, és ha igen, rajzoljuk ki az indikátort
        g.setColor(Color.CYAN);
        int indicatorSize = ts / 2;
        int indicatorX = x * ts + (ts - indicatorSize) / 2;
        int indicatorY = y * ts + (ts - indicatorSize) / 2;
        g.fillOval(indicatorX, indicatorY, indicatorSize, indicatorSize);
    }     
  }

}
