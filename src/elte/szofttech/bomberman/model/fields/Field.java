package elte.szofttech.bomberman.model.fields;

import java.awt.Graphics;

public abstract class Field {
    private boolean Walkable;
    protected int tileSize;
    private int x;
    private int y;

    public int getTileSize(){
      return tileSize;
    }

    public Field(int x, int y) {
      tileSize = 75;
      this.x = x;
      this.y = y;
    }
    public void draw(Graphics g,int x, int y) {
    };
    public abstract boolean isDestructible();
    public abstract boolean canPlaceBomb();
}
