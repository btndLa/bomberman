package elte.szofttech.bomberman.model.powerups;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import elte.szofttech.bomberman.model.Player;

/**
 * Represents a ghost power-up in the Bomberman game.
 * When picked up by a player, it grants the player ghost ability for a limited time.
 */
public class Ghost extends PowerUp {

    /**
     * Constructs a Ghost object with the specified parameters.
     * @param x The initial x-coordinate of the ghost power-up.
     * @param y The initial y-coordinate of the ghost power-up.
     * @param tileSize The size of the tiles in the game board.
     */
    public Ghost(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
            this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Ghostpowerup.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.ORANGE;
        this.setColor(defaultColor);
    }

    /**
     * Applies the effect of the ghost power-up on the player.
     * Grants the player ghost ability for a limited time and sets timers for expiration.
     * @param player The player who picks up the power-up.
     */
    @Override
    public void applyOnPlayer(Player player) {
        player.isGhost = true;
        System.out.println("Is Ghost after application: " + player.isGhost);

        Timer ghostTimer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.isGhost = false;
                player.isExpiring = false;
                System.out.println("Is Ghost after timer expiration: " + player.isGhost);
            }
        });
        ghostTimer.setRepeats(false);
        ghostTimer.start();
        Timer expiringTimer = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.isExpiring = true;
                System.out.println("Is Expiring after 6 seconds: " + player.isExpiring);
            }
        });
        expiringTimer.setRepeats(false);
        expiringTimer.start();
    }
}
