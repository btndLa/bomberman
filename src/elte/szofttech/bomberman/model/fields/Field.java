package elte.szofttech.bomberman.model.fields;

import java.awt.Graphics;
import java.awt.Color;

public abstract class Field {
    private boolean Walkable;
    protected int tileSize;
    private int x;
    private int y;
    protected Color color;

    public int getTileSize(){
      return tileSize;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public void setColor(Color color){this.color = color;}

    public Field(int x, int y) {
      this.tileSize = 75;
      this.x = x;
      this.y = y;
    }
    public void draw(Graphics g,int x, int y) {
    };
    public abstract boolean isDestructible();
    public abstract boolean canPlaceBomb();
    public void setWalkable(boolean w){this.Walkable = w;}
    public boolean isWalkable(){return this.Walkable;}
}
