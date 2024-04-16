package elte.szofttech.bomberman.model.monsters;

import java.awt.Graphics;

import elte.szofttech.bomberman.model.Entity;
import elte.szofttech.bomberman.model.GameEngine;


//Parent class of all the monster entities
public abstract class Monster extends Entity {
    protected int speed;
    protected GameEngine engine;
    protected int direction;

    public Monster(int x, int y, GameEngine engine,int direction) {
        super(x,y);
        this.speed = 1;
        this.engine = engine;
        this.direction = direction;
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public abstract void move();
    public abstract void draw(Graphics g);   
    public int getSpeed(){return speed;};
}

