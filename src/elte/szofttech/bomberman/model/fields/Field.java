package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Parent class for all fields in the game.
 * The Field class handles basic properties and behaviors that all fields share.
 */
public abstract class Field {
    private boolean walkable;
    protected int tileSize;
    private int x;
    private int y;
    protected Color defaultColor;
    protected Color color;

    /**
     * Constructs a Field object with the specified parameters.
     *
     * @param x        the x-coordinate of the field
     * @param y        the y-coordinate of the field
     * @param tileSize the size of the tile
     */
    public Field(int x, int y, int tileSize) {
        this.tileSize = tileSize;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the size of the tile.
     *
     * @return the size of the tile
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Gets the x-coordinate of the field.
     *
     * @return the x-coordinate of the field
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the field.
     *
     * @return the y-coordinate of the field
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the default color of the field.
     *
     * @return the default color of the field
     */
    public Color getDefaultColor() {
        return this.defaultColor;
    }

    /**
     * Sets the color of the field.
     *
     * @param color the new color of the field
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Determines if the field is destructible.
     *
     * @return true if the field is destructible, false otherwise
     */
    public abstract boolean isDestructible();

    /**
     * Determines if a bomb can be placed on the field.
     *
     * @return true if a bomb can be placed, false otherwise
     */
    public abstract boolean canPlaceBomb();

    /**
     * Determines if an obstacle can be placed on the field.
     *
     * @return true if an obstacle can be placed, false otherwise
     */
    public abstract boolean canPlaceObstacle();

    /**
     * Sets whether the field is walkable.
     *
     * @param walkable true if the field is walkable, false otherwise
     */
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    /**
     * Determines if the field is walkable.
     *
     * @return true if the field is walkable, false otherwise
     */
    public boolean isWalkable() {
        return this.walkable;
    }

    /**
     * Draws the field on the given graphics context.
     *
     * @param g the graphics context
     * @param x the x-coordinate for drawing
     * @param y the y-coordinate for drawing
     */
    public void draw(Graphics g, int x, int y) {
    }
}
