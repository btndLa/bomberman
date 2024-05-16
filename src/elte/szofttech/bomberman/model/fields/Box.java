package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents an explodable box in the game.
 * The Box class handles the box's position, color, and rendering.
 */
public class Box extends Field {
    public boolean isPowerUp;

    /**
     * Constructs a Box object with the specified parameters.
     *
     * @param x        the x-coordinate of the box
     * @param y        the y-coordinate of the box
     * @param tileSize the size of the tile
     */
    public Box(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = new Color(222, 184, 135);
        this.setColor(defaultColor);
        this.isPowerUp = false;
    }

    /**
     * Sets whether the box contains a power-up.
     *
     * @param isPowerUp true if the box contains a power-up, false otherwise
     */
    public void setPowerUp(boolean isPowerUp) {
        this.isPowerUp = isPowerUp;
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }

    @Override
    public boolean canPlaceObstacle() {
        return false;
    }

    /**
     * Draws the box on the given graphics context.
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
