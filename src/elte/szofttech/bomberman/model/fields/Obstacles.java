package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacles extends Field{

  private int x;
  private int y;

  public Obstacles(int x, int y, int tileSize) {
    super(x, y, tileSize);
    this.x = x;
    this.y = y;
    this.defaultColor = Color.DARK_GRAY;
    this.setColor(defaultColor);
    this.setWalkable(false);
}
  public int getX(){return x;}
  public int getY(){return y;}

    @Override
    public boolean isDestructible() { return true;}

    @Override
    public boolean canPlaceBomb() { return false;}

    @Override
    public boolean canPlaceObstacle() {return false;}

    public void draw(Graphics g, int x, int y) {
      g.setColor(color);
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
