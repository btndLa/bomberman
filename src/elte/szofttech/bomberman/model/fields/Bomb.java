package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

import elte.szofttech.bomberman.model.fields.Field;

public class Bomb extends Field {
    private int radius;
    private int detonateTime;

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }
    public Bomb(int x, int y, int radius, int detonateTime) {
        super(x, y);
        this.radius = radius;
        this.detonateTime = detonateTime;
    }
    public void draw(Graphics g, int x, int y) {
      g.setColor(Color.GRAY);
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
