package elte.szofttech.bomberman.model;

// Parent class for all entities
public abstract class Entity {
    protected int x;
    protected int y;

    public Entity(int x, int y) {
        if(x < 0 || y < 0){throw new IllegalArgumentException();}
        this.x = x;
        this.y = y;
    }

    public void takeDamage(){}
    public void onExplosion(){}
    public void onCollision(Entity e){}
}
