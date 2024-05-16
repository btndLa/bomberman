package elte.szofttech.bomberman.model.powerups;

import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import elte.szofttech.bomberman.model.Player;

/**
 * Represents a bomb range bonus power-up in the Bomberman game.
 * When picked up by a player, it increases the player's bomb explosion range.
 */
public class BombRangeBonus extends PowerUp {

    /**
     * Constructs a BombRangeBonus object with the specified parameters.
     * @param x The initial x-coordinate of the bomb range bonus power-up.
     * @param y The initial y-coordinate of the bomb range bonus power-up.
     * @param tileSize The size of the tiles in the game board.
     */
    public BombRangeBonus(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
            this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/BombRadius.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.RED;
        this.setColor(defaultColor);
    }

    /**
     * Applies the effect of the bomb range bonus power-up on the player.
     * Increases the bomb explosion range of the player.
     * @param player The player who picks up the power-up.
     */
    @Override
    public void applyOnPlayer(Player player){
        int bombRadius = player.getBombRadius();
        player.setBombRadius(bombRadius+1);
    }
}
