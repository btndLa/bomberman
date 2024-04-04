package elte.szofttech.bomberman.model.fields;

import java.awt.Graphics;

public abstract class Field {
    private boolean Walkable;
    protected int tileSize;

    public int getTileSize(){
      return tileSize;
    }

    public Field() {
      tileSize = 75;
    }
    public void draw(Graphics g,int x, int y) {
    };
    public abstract boolean isDestructible();
    public abstract boolean canPlaceBomb();
}
