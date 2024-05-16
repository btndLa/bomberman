package elte.szofttech.bomberman.model.powerups;

import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import elte.szofttech.bomberman.model.Player;

/**
 * Represents a bonus bomb power-up in the Bomberman game.
 * When picked up by a player, it increases the player's bomb capacity.
 */
public class BonusBomb extends PowerUp {

    /**
     * Constructs a BonusBomb object with the specified parameters.
     * @param x The initial x-coordinate of the bonus bomb power-up.
     * @param y The initial y-coordinate of the bonus bomb power-up.
     * @param tileSize The size of the tiles in the game board.
     */
    public BonusBomb(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
            this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/BombCount.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.RED;
        this.setColor(defaultColor);
    }

    /**
     * Applies the effect of the bonus bomb power-up on the player.
     * Increases the bomb capacity of the player.
     * @param player The player who picks up the power-up.
     */
    @Override
    public void applyOnPlayer(Player player){
        int bombCapacity = player.getbombCapacity();
        player.setBombCapacity(bombCapacity+1);
    }
}
