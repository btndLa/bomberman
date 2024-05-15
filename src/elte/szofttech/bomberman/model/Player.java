package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Obstacles;
import elte.szofttech.bomberman.model.fields.Floor;
import elte.szofttech.bomberman.model.fields.Wall;
import elte.szofttech.bomberman.model.monsters.Monster;
import elte.szofttech.bomberman.model.powerups.BombDetonator;
import elte.szofttech.bomberman.model.powerups.BombRangeBonus;
import elte.szofttech.bomberman.model.powerups.BonusBomb;
import elte.szofttech.bomberman.model.powerups.Ghost;
import elte.szofttech.bomberman.model.powerups.Invulnerable;
import elte.szofttech.bomberman.model.powerups.Obstacle;
import elte.szofttech.bomberman.model.powerups.PowerUp;
import elte.szofttech.bomberman.model.powerups.RollerBlade;

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
    private int obstacleButton;
    private int bombRadius;
    private int points;
    private GameEngine engine;
    private int bombCapacity;
    private int placedBombs;
    public boolean isAlive;
    public boolean hasDetonator;
    public List<Bomb> bombsOnGround;
    public boolean isInvulnerable;
    public boolean isGhost;
    public boolean isExpiring;
    public boolean isRollerBlade;
    public int obstacleCapacity;
        

    public Player(int x, int y, int up, int down, int left, int right, int bombButton, int obstacleButton, GameEngine engine) {
        super(x, y);
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bombButton = bombButton;
        this.obstacleButton = obstacleButton;
        this.engine = engine;
        this.bombCapacity = 1;
        this.bombRadius = 3;
        this.placedBombs = 0;
        this.points = 0;
        this.isAlive = true;
        this.bombsOnGround = new ArrayList<>();
        this.hasDetonator = false;
        this.isInvulnerable = false;
        this.isGhost = false;
        this.isExpiring = false;
        this.isRollerBlade=false;
        this.obstacleCapacity = 0;
        engine.getBoard()[this.y][this.x].setWalkable(false);
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public int getPoints(){return points;}
    public boolean isAlive(){return isAlive;}
    public boolean isInvulnerable(){return isInvulnerable;}
    public void setAlive(){isAlive = true;}
    public int getBombRadius(){return bombRadius;}
    public int getbombCapacity(){return bombCapacity;}
    public int getPlacedBombs(){return placedBombs;}
    public int getPlacedObstacles(){return obstacleCapacity;}
    public List<Bomb> getBombsOnGround() {return bombsOnGround;}
    public void setPlacedBombs(int n){this.placedBombs = n;}
    public void setBombRadius(int n) {this.bombRadius = n;}
    public void setBombCapacity(int n){this.bombCapacity = n;}
    public void setObstacleCapacity(int n){this.obstacleCapacity = n;}

    public void move(KeyEvent key){
      if (!this.isAlive) {return;}
      int currentX = this.x;
      int currentY = this.y;
      int keyAsInt = key.getKeyCode();
      if (keyAsInt == this.up) {
        if (!isGhost) {
            if (this.y - 1 >= 0 && (engine.getBoard()[this.y - 1][this.x].isWalkable() || isMonster(x, y - 1) != null)) {
                this.y -= 1;
            }
        } else {
            if (this.y - 1 > 0) {
                this.y -= 1;
            }
        }
    } else if (keyAsInt == this.down) {
        if (!isGhost) {
            if (this.y + 1 < engine.getBoard().length && (engine.getBoard()[this.y + 1][this.x].isWalkable() || isMonster(x, y + 1) != null)) {
                this.y += 1;
            }
        } else {
            if (this.y + 1 < engine.getBoard().length-1 ) {
                this.y += 1;
            }
        }
    } else if (keyAsInt == this.left) {
        if (!isGhost) {
            if (this.x - 1 >= 0 && (engine.getBoard()[this.y][this.x - 1].isWalkable() || isMonster(x - 1, y) != null)) {
                this.x -= 1;
            }
        } else {
            if (this.x - 1 >= 1) {
                this.x -= 1;
            }
        }
    } else if (keyAsInt == this.right) {
        if (!isGhost) {
            if (this.x + 1 < engine.getBoard()[0].length && (engine.getBoard()[this.y][this.x + 1].isWalkable() || isMonster(x + 1, y) != null)) {
                this.x += 1;
            }
        } else {
            if (this.x + 1 < engine.getBoard().length-1) {
                this.x += 1;
            }
        }
    }
      else if (keyAsInt == this.bombButton){
          if(engine.getBoard()[this.y][this.x].canPlaceBomb() && this.hasDetonator == false){
            Bomb newBomb = new Bomb(currentX * engine.gettileSize(), currentY * engine.gettileSize(), this.bombRadius, 3, engine.gettileSize());
            if(this.bombsOnGround.size() < this.bombCapacity){
              addBomb(newBomb);
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
      else if (keyAsInt == this.obstacleButton){
        if(engine.getBoard()[this.y][this.x].canPlaceObstacle() && obstacleCapacity > 0){
            Obstacles newObs = new Obstacles(currentX * engine.gettileSize(), currentY * engine.gettileSize(), engine.gettileSize());
            engine.placeObstacle(newObs, this);
          }
        
      }
      if((currentX != this.x || currentY != this.y) && !(engine.getBoard()[currentY][currentX] instanceof Bomb || engine.getBoard()[currentY][currentX] instanceof Obstacles ) ) engine.getBoard()[currentY][currentX].setWalkable(true);
      engine.getBoard()[this.y][this.x].setWalkable(false);
      if (engine.getBoard()[currentY][currentX] instanceof PowerUp) {
        PowerUp currentPowerUp = (PowerUp) engine.getBoard()[currentY][currentX];
        powerUpPickup(currentPowerUp);
        engine.getBoard()[currentY][currentX] = new Floor(currentX * engine.gettileSize(), currentY * engine.gettileSize(), engine.gettileSize());
    }
    Monster monster = isMonster(x, y);
    if (monster != null && isInvulnerable == false) this.die();
    if (!isGhost && !(engine.getBoard()[this.y][this.x] instanceof Floor) && 
    !(engine.getBoard()[this.y][this.x] instanceof Bomb) && 
    !(engine.getBoard()[this.y][this.x] instanceof PowerUp) &&
    !(engine.getBoard()[this.y][this.x] instanceof Obstacles)){
    this.die();
}

  }

  // Checks if a field ha  player on it
  private Monster isMonster(int x, int y){
    for (Monster monster : engine.getMonsters()) {
      if(monster.getX() == x && monster.getY() == y && !(engine.getBoard()[y][x] instanceof Bomb)) return monster; 
    }
    return null;
  }

    public void die(){
      if (!isInvulnerable) {
        this.isAlive = false;
        engine.checkEndGame();
      }
    }

    public void win(){
      this.points += 1;
    }

    @Override
    public void onExplosion() {
      if(!this.isInvulnerable){
        this.isAlive = false;
      }
    }
    public void addBomb(Bomb bomb){
      this.bombsOnGround.add(bomb);
    }

    @Override
    public void onCollision(Entity e) {
        if(e.x == this.x && e.y == this.y && e instanceof Monster && !this.isInvulnerable){
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
            }else if (e instanceof Ghost) {
                ((Ghost) e).applyOnPlayer(this);
                System.out.println("Ghost collected!");
            }else if (e instanceof RollerBlade) {
                ((RollerBlade) e).applyOnPlayer(this);
                System.out.println("RollerBlade collected!");
            }else if (e instanceof Obstacle) {
                ((Obstacle) e).applyOnPlayer(this);
                System.out.println("Obstacle collected!" + obstacleCapacity);
            }         
        }


        public void draw(Graphics g) {
            int ts = engine.gettileSize();
            g.setColor(Color.RED);
            g.fillRect(x * ts, y * ts, ts, ts);
            
            if (isInvulnerable) {
                if (isExpiring) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.CYAN);
                }
            } else if (isGhost) {
                if (isExpiring) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.ORANGE);
                }
            } else if (isRollerBlade) {
                if (isExpiring) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.PINK);
                }
            }
            
            int indicatorSize = ts / 2;
            int indicatorX = x * ts + (ts - indicatorSize) / 2;
            int indicatorY = y * ts + (ts - indicatorSize) / 2;
            g.fillOval(indicatorX, indicatorY, indicatorSize, indicatorSize);
        }
  

}
