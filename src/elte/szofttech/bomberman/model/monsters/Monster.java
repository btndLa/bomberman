package elte.szofttech.bomberman.model.monsters;

import java.awt.Graphics;

import elte.szofttech.bomberman.GUI.GameGUI;
import elte.szofttech.bomberman.model.Entity;
import elte.szofttech.bomberman.model.GameEngine;

public abstract class Monster extends Entity {
    protected int speed;
    protected GameEngine engine;

    public Monster(int x, int y, int speed, GameEngine engine) {
        super(x,y);
        this.speed = speed;
        this.engine = engine;
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public abstract void move();
    public abstract void draw(Graphics g);   
    public abstract int getSpeed();
  
}

