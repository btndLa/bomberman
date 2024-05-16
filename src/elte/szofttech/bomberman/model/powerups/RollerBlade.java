package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class RollerBlade extends PowerUp {

    public RollerBlade(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
          this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Rollerblade.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.BLACK; // Alapértelmezett szín beállítása
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player) {
        player.isRollerBlade = true;
        System.out.println("Is Rollerblade after application: " + player.isRollerBlade);

        Timer RollerBladeTimer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.isRollerBlade = false;
                player.isExpiring = false;
                System.out.println("Is RollerBlade after timer expiration: " + player.isRollerBlade);
            }
        });
        RollerBladeTimer.setRepeats(false);
        RollerBladeTimer.start();
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
