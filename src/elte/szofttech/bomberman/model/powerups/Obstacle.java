package elte.szofttech.bomberman.model.powerups;

import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import elte.szofttech.bomberman.model.Player;

/**
 * Represents an obstacle power-up in the Bomberman game.
 * When picked up by a player, it increases the player's obstacle capacity.
 */
public class Obstacle extends PowerUp {
    /**
     * Constructs an Obstacle object with the specified parameters.
     * @param x The initial x-coordinate of the obstacle power-up.
     * @param y The initial y-coordinate of the obstacle power-up.
     * @param tileSize The size of the tiles in the game board.
     */
    public Obstacle(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
            this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Obstacle.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
    }

    /**
     * Applies the effect of the obstacle power-up on the player.
     * Increases the player's obstacle capacity.
     * @param player The player who picks up the power-up.
     */
    @Override
    public void applyOnPlayer(Player player) {
        player.obstacleCapacity += 3;
    }

    /**
     * Determines whether the player can place an obstacle after picking up this power-up.
     * @return Always returns false, as this power-up does not enable obstacle placement.
     */
    @Override
    public boolean canPlaceObstacle() {
        return false;
    }
}
