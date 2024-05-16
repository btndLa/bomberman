package elte.szofttech.bomberman.model.powerups;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.Player;

/**
 * Represents a power-up in the Bomberman game.
 * This is an abstract class providing common functionality for all power-ups.
 */
public abstract class PowerUp extends Field {
    protected Image image;

    /**
     * Constructs a PowerUp object with the specified parameters.
     * @param x The initial x-coordinate of the power-up.
     * @param y The initial y-coordinate of the power-up.
     * @param tileSize The size of the tiles in the game board.
     */
    public PowerUp(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
        this.setWalkable(true); 
    }

    /**
     * Indicates whether the power-up is destructible.
     * @return Always returns true, as power-ups can be destroyed.
     */
    @Override
    public boolean isDestructible() {
        return true;
    }

    /**
     * Indicates whether the power-up allows the player to place a bomb.
     * @return Always returns false, as power-ups do not allow bomb placement.
     */
    @Override
    public boolean canPlaceBomb() {
        return false;
    }

    /**
     * Applies the effect of the power-up on the player.
     * This method should be overridden by subclasses to define specific power-up effects.
     * @param player The player who picks up the power-up.
     */
    public void applyOnPlayer(Player player){

    }

    /**
     * Indicates whether the player can place an obstacle after picking up this power-up.
     * @return Always returns false, as this power-up does not enable obstacle placement.
     */
    @Override
    public boolean canPlaceObstacle() {
        return false;
    }

    /**
     * Draws the power-up on the game board.
     * @param g The graphics context to use for drawing.
     * @param x The x-coordinate at which to draw the power-up.
     * @param y The y-coordinate at which to draw the power-up.
     */
    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x , y , tileSize, tileSize, null);
    }
}
