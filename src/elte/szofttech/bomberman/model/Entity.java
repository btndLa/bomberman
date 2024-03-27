package elte.szofttech.bomberman.model;

public abstract class Entity {
    protected int x;
    protected int y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void takeDamage(){}
    public void onExplosion(){}
    public void onCollision(){}
}
