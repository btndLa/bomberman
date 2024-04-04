package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;


public class Floor extends Field{
 
  public Floor(int x, int y){
      super(x, y);
      this.setWalkable(true);
      this.setColor(Color.GREEN);
      
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean canPlaceBomb() {
        return true;
    }
    public void draw(Graphics g, int x, int y) {
      g.setColor(color);
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
