package elte.szofttech.bomberman.model;

/**
 * Represents a parent class for all entities in the Bomberman game.
 * An entity is any object that exists within the game world.
 */
public abstract class Entity {
    protected int x; // X-coordinate of the entity
    protected int y; // Y-coordinate of the entity

    /**
     * Constructs an Entity object with the specified coordinates.
     * @param x The initial x-coordinate of the entity.
     * @param y The initial y-coordinate of the entity.
     * @throws IllegalArgumentException if either x or y is less than 0.
     */
    public Entity(int x, int y) {
        if(x < 0 || y < 0){
            throw new IllegalArgumentException("Coordinates must be non-negative.");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Method to be overridden by subclasses to handle entity taking damage.
     */
    public void takeDamage(){}

    /**
     * Method to be overridden by subclasses to handle entity actions upon explosion.
     */
    public void onExplosion(){}

    /**
     * Method to be overridden by subclasses to handle entity collision with another entity.
     * @param e The entity with which this entity collides.
     */
    public void onCollision(Entity e){}
}
