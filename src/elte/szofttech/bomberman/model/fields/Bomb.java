package elte.szofttech.bomberman.model.fields;

import elte.szofttech.bomberman.model.Entity;

public class Bomb extends Entity {
    private int radius;
    private int detonateTime;

    public Bomb(int x, int y ,int radius, int detonateTime) {
        super(x,y);
        this.radius = radius;
        this.detonateTime = detonateTime;
    }
    
}
