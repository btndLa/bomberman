package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;
public class Wall extends Field{


    public Wall(int x, int y){
      super(x, y);
      this.setColor(Color.GRAY);
    }
    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }
    public void draw(Graphics g, int x, int y) {
      g.setColor(color);
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
