package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.Entity;

public abstract class Monster extends Entity {
    protected int speed;

    public Monster(int x, int y, int speed) {
        super(x,y);
        this.speed = speed;
    }
    public abstract void move();
}

