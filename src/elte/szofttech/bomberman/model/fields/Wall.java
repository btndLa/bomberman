package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents wall fields in the game, which the player cannot walk through.
 */
public class Wall extends Field {

    /**
     * Constructs a Wall object with the specified parameters.
     *
     * @param x        the x-coordinate of the wall
     * @param y        the y-coordinate of the wall
     * @param tileSize the size of the tile
     */
    public Wall(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.GRAY;
        this.setColor(defaultColor);
        this.setWalkable(false);
    }

    /**
     * Determines if the wall is destructible.
     *
     * @return false because the wall is not destructible
     */
    @Override
    public boolean isDestructible() {
        return false;
    }

    /**
     * Determines if a bomb can be placed on the wall.
     *
     * @return false because a bomb cannot be placed on the wall
     */
    @Override
    public boolean canPlaceBomb() {
        return false;
    }

    /**
     * Determines if another obstacle can be placed on this wall.
     *
     * @return false because another obstacle cannot be placed on this wall
     */
    @Override
    public boolean canPlaceObstacle() {
        return false;
    }

    /**
     * Draws the wall on the given graphics context.
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
