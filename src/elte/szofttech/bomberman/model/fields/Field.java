package elte.szofttech.bomberman.model.fields;

import java.awt.Graphics;
import java.awt.Color;

// Parent class for all the fields
public abstract class Field {
    private boolean Walkable;
    protected int tileSize;
    private int x;
    private int y;
    protected Color defaultColor;
    protected Color color;

    // Getters
    public int getTileSize(){ return tileSize;}
    public int getX(){return x;}
    public int getY(){return y;}
    public Color getDefaultColor(){return this.defaultColor;}
    public void setColor(Color color){this.color = color;}
    public abstract boolean isDestructible();
    public abstract boolean canPlaceBomb();
    public void setWalkable(boolean w){this.Walkable = w;}
    public boolean isWalkable(){return this.Walkable;}

    public Field(int x, int y, int tileSize) {
      this.tileSize = tileSize;
      this.x = x;
      this.y = y;
    }
    public void draw(Graphics g,int x, int y) {
    };
    
}
