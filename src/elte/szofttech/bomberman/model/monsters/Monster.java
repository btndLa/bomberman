package elte.szofttech.bomberman.model.monsters;

import java.awt.Graphics;

import elte.szofttech.bomberman.model.Entity;
import elte.szofttech.bomberman.model.GameEngine;

/**
 * Parent class of all the monster entities in the Bomberman game.
 * Provides common functionality and properties for monsters.
 */
public abstract class Monster extends Entity {
    protected int speed;
    protected GameEngine engine;
    protected int direction;

    /**
     * Constructs a Monster object with the specified parameters.
     * @param x The initial x-coordinate of the monster.
     * @param y The initial y-coordinate of the monster.
     * @param engine The game engine associated with the monster.
     * @param direction The initial direction of the monster.
     */
    public Monster(int x, int y, GameEngine engine, int direction) {
        super(x, y);
        this.speed = 1;
        this.engine = engine;
        this.direction = direction;
    }

    /**
     * Gets the x-coordinate of the monster.
     * @return The x-coordinate of the monster.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the monster.
     * @return The y-coordinate of the monster.
     */
    public int getY() {
        return y;
    }

    /**
     * Abstract method to define how the monster moves.
     */
    public abstract void move();

    /**
     * Abstract method to define how the monster is drawn on the screen.
     * @param g The Graphics object used for drawing.
     */
    public abstract void draw(Graphics g);

    /**
     * Gets the speed of the monster.
     * @return The speed of the monster.
     */
    public int getSpeed() {
        return speed;
    }
}
