package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents the walkable floor fields of the game.
 */
public class Floor extends Field {

    /**
     * Constructs a Floor object with the specified parameters.
     *
     * @param x        the x-coordinate of the floor
     * @param y        the y-coordinate of the floor
     * @param tileSize the size of the tile
     */
    public Floor(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.setWalkable(true);
        this.defaultColor = Color.GREEN;
        this.setColor(defaultColor);
    }

    /**
     * Determines if the floor is destructible.
     *
     * @return true because the floor is destructible
     */
    @Override
    public boolean isDestructible() {
        return true;
    }

    /**
     * Determines if a bomb can be placed on the floor.
     *
     * @return true because a bomb can be placed on the floor
     */
    @Override
    public boolean canPlaceBomb() {
        return true;
    }

    /**
     * Determines if an obstacle can be placed on the floor.
     *
     * @return true because an obstacle can be placed on the floor
     */
    @Override
    public boolean canPlaceObstacle() {
        return true;
    }

    /**
     * Draws the floor on the given graphics context.
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
