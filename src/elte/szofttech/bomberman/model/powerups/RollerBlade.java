package elte.szofttech.bomberman.model.powerups;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import elte.szofttech.bomberman.model.Player;

/**
 * Represents a roller blade power-up in the Bomberman game.
 * This power-up allows the player to move faster for a limited time.
 */
public class RollerBlade extends PowerUp {

    /**
     * Constructs a RollerBlade object with the specified parameters.
     * @param x The initial x-coordinate of the power-up.
     * @param y The initial y-coordinate of the power-up.
     * @param tileSize The size of the tiles in the game board.
     */
    public RollerBlade(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
          this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Rollerblade.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.BLACK; // Set default color
        this.setColor(defaultColor);
    }

    /**
     * Applies the effect of the roller blade power-up on the player.
     * This method makes the player move faster for a limited time.
     * @param player The player who picks up the power-up.
     */
    @Override
    public void applyOnPlayer(Player player) {
        player.isRollerBlade = true;
        System.out.println("Is Rollerblade after application: " + player.isRollerBlade);

        Timer rollerBladeTimer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.isRollerBlade = false;
                player.isExpiring = false;
                System.out.println("Is RollerBlade after timer expiration: " + player.isRollerBlade);
            }
        });
        rollerBladeTimer.setRepeats(false);
        rollerBladeTimer.start();
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
