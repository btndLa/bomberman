package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

import elte.szofttech.bomberman.model.fields.Field;

public class Bomb extends Field {
    private int radius;
    private int detonateTime;
    private int x;
    private int y;

    public int getX(){return x;}
    public int getY(){return y;}
    public int getDetonation(){return detonateTime;}
    public int getRadius(){return radius;}

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }
    public Bomb(int x, int y, int radius, int detonateTime) {
        super(x,y);
        this.setColor(Color.BLACK);
        this.defaultColor = Color.BLACK;
        this.tileSize = 75;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.detonateTime = detonateTime;
    }
    @Override
    public void draw(Graphics g, int x, int y) {
      g.setColor(color);
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
