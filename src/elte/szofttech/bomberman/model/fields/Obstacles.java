package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents the obstacles in the game field.
 */
public class Obstacles extends Field {

    private int x;
    private int y;

    /**
     * Constructs an Obstacles object with the specified parameters.
     *
     * @param x        the x-coordinate of the obstacle
     * @param y        the y-coordinate of the obstacle
     * @param tileSize the size of the tile
     */
    public Obstacles(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.x = x;
        this.y = y;
        this.defaultColor = Color.DARK_GRAY;
        this.setColor(defaultColor);
        this.setWalkable(false);
    }

    /**
     * Gets the x-coordinate of the obstacle.
     *
     * @return the x-coordinate of the obstacle
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the obstacle.
     *
     * @return the y-coordinate of the obstacle
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Determines if the obstacle is destructible.
     *
     * @return true because the obstacle is destructible
     */
    @Override
    public boolean isDestructible() {
        return true;
    }

    /**
     * Determines if a bomb can be placed on the obstacle.
     *
     * @return false because a bomb cannot be placed on the obstacle
     */
    @Override
    public boolean canPlaceBomb() {
        return false;
    }

    /**
     * Determines if another obstacle can be placed on this obstacle.
     *
     * @return false because another obstacle cannot be placed on this obstacle
     */
    @Override
    public boolean canPlaceObstacle() {
        return false;
    }

    /**
     * Draws the obstacle on the given graphics context.
     *
     * @param g the graphics context
     * @param x the x-coordinate for drawing
     * @param y the y-coordinate for drawing
     */
    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, this.tileSize, this.tileSize);
    }
}
