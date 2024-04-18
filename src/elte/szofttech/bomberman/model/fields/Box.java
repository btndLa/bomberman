package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

// Represents an explodable box
public class Box extends Field{
  public boolean isPowerUp;

  public Box(int x, int y, int tileSize) {
    super(x, y, tileSize);
    this.defaultColor = new Color(222, 184, 135);
    this.setColor(defaultColor);
    this.isPowerUp = false;
}
    public void setPowerUp(boolean isPowerUp) { 
        this.isPowerUp = isPowerUp;
    }

    @Override
    public boolean isDestructible() { return true;}

    @Override
    public boolean canPlaceBomb() { return false;}
    public void draw(Graphics g, int x, int y) {
      g.setColor(color);
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
