package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Represents a bomb detonator power-up in the Bomberman game.
 * When picked up by a player, it allows the player to detonate bombs remotely.
 */
public class BombDetonator extends PowerUp {

    /**
     * Constructs a BombDetonator object with the specified parameters.
     * @param x The initial x-coordinate of the bomb detonator.
     * @param y The initial y-coordinate of the bomb detonator.
     * @param tileSize The size of the tiles in the game board.
     */
    public BombDetonator(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
            this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Detonator.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
    }

    /**
     * Applies the effect of the bomb detonator power-up on the player.
     * Sets the player's detonator status to true.
     * @param player The player who picks up the power-up.
     */
    @Override
    public void applyOnPlayer(Player player) {
        player.hasDetonator = true;
    }
}
