package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Invulnerable extends PowerUp {
    public Invulnerable(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
          this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Invulnerable.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player) {
        player.isInvulnerable = true;
        System.out.println("Is Invulnerable after application: " + player.isInvulnerable);
        
        Timer invulnerabilityTimer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.isInvulnerable = false;
                player.isExpiring = false;
                System.out.println("Is Invulnerable after timer expiration: " + player.isInvulnerable);
            }
        });
        invulnerabilityTimer.setRepeats(false);
        invulnerabilityTimer.start();

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
