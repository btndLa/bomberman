package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.monsters.Monster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

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
        engine.getBoard()[this.y][this.x].setWalkable(false);
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getbombCapacity(){return bombCapacity;}
    public int getPlacedBombs(){return placedBombs;}
    public void setPlacedBombs(int n){this.placedBombs = n;}

    public void move(KeyEvent key){
      if (!this.isAlive) {return;}
      int currentX = this.x;
      int currentY = this.y;
      int keyAsInt = key.getKeyCode();
      if(keyAsInt == this.up){
          if(this.y - 1 >= 0 && engine.getBoard()[this.y - 1][this.x].isWalkable()){
              this.y -= 1;
          }
      }
      else if(keyAsInt == this.down){
          if(this.y + 1 < engine.getBoard().length && engine.getBoard()[this.y + 1][this.x].isWalkable()){
              this.y += 1;
          }
      }
      else if(keyAsInt == this.left){
          if(this.x - 1 >= 0 && engine.getBoard()[this.y][this.x - 1].isWalkable()){
              this.x -= 1;
          }
      }
      else if(keyAsInt == this.right){
          if(this.x + 1 < engine.getBoard()[0].length && engine.getBoard()[this.y][this.x + 1].isWalkable()){
            this.x += 1;
          }
      }
      else if (keyAsInt == this.bombButton){
          if(engine.getBoard()[this.y][this.x].canPlaceBomb()){
              engine.DetonateBomb(new Bomb(currentX*engine.getTileSize(), currentY*engine.getTileSize(), this.bombRadius, 3),this);
          }
      }
      if((currentX != this.x || currentY != this.y) && !(engine.getBoard()[currentY][currentX] instanceof Bomb) ) engine.getBoard()[currentY][currentX].setWalkable(true);
      engine.getBoard()[this.y][this.x].setWalkable(false);
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
    public void draw(Graphics g) {
      int ts = engine.getTileSize();
      g.setColor(Color.RED);
      g.fillRect(x * ts, y * ts,ts,ts);      
  }

}