package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

// Represents the bomb field of the game
public class Bomb extends Field {
    private int radius;
    private int detonateTime;
    private int x;
    private int y;

    // Getters
    public int getX(){return x;}
    public int getY(){return y;}
    public int getDetonation(){return detonateTime;}
    public int getRadius(){return radius;}

    @Override
    public boolean isDestructible() { return false;}

    @Override
    public boolean canPlaceBomb() { return false; }
    
    public Bomb(int x, int y, int radius, int detonateTime, int tileSize) {
        super(x,y, tileSize);
        this.setColor(Color.BLACK);
        this.defaultColor = Color.BLACK;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.detonateTime = detonateTime;
        this.setWalkable(false);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
      g.setColor(color);
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
