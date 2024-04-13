package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BasicMonster extends Monster {

    public BasicMonster(int x, int y, int speed, GameEngine engine, int direction) {
        super(x, y, speed, engine,direction);
        engine.getBoard()[this.y][this.x].setWalkable(false);
    }
    public int getSpeed(){return this.speed;}

    /**
     *Move the monster based on it's direction
     * 1 -> up
     * 2 -> down
     * 3 -> left
     * 4 -> right
     */
    @Override
    public void move() {
        int currentX = this.x;
        int currentY = this.y;
        boolean moved = false;
        while (currentX == this.x && currentY==this.y) {
          switch (this.direction) {
            case 1:
              if (this.y - 1 >= 0 && engine.getBoard()[this.y - 1][this.x].isWalkable()) {
               this.y--;
               moved = true;
              }
              break;
            case 2:
              if (this.y + 1 < engine.getBoard().length && engine.getBoard()[this.y + 1][this.x].isWalkable()) {
               this.y++;
               moved = true;
              }
              break;
            case 3:
              if (this.x - 1 >= 0 && engine.getBoard()[this.y][this.x - 1].isWalkable()) {
                this.x--;
                moved = true;
              }
              break;
            case 4:
              if (this.x + 1 < engine.getBoard().length && engine.getBoard()[this.y][this.x + 1].isWalkable()) {
                this.x++;
                moved = true;
              }
              break;
            }
            if(!moved) {
              this.direction = (new Random()).nextInt(1,5);
            }
          }
        engine.getBoard()[currentY][currentX].setWalkable(true);
        engine.getBoard()[this.y][this.x].setWalkable(false);
    }
    

    public void draw(Graphics g) {
      int ts = engine.getTileSize();
      g.setColor(Color.ORANGE);
      g.fillRect(x * ts, y * ts,ts,ts);      
  }


}
