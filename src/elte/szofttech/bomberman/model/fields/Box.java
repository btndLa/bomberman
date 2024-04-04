package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

public class Box extends Field{

    public Box(){
      super();
    }
    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }
    public void draw(Graphics g, int x, int y) {
      g.setColor(new Color(222, 184, 135));
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
