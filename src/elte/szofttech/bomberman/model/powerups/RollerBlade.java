package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class RollerBlade extends PowerUp {

    public RollerBlade(int x, int y, int tileSize) {
        super(x, y, tileSize);
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

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
            g.setColor(Color.BLACK);
            int iconSize = tileSize / 2;
            int iconX = x + (tileSize - iconSize) / 2;
            int iconY = y + (tileSize - iconSize) / 2;
            g.fillOval(iconX, iconY, iconSize, iconSize);
            g.setColor(Color.ORANGE);
            int crossSize = iconSize / 2;
            int crossX = iconX + (iconSize - crossSize) / 2;
            int crossY = iconY + (iconSize - crossSize) / 2;
            g.fillRect(crossX, crossY, crossSize, crossSize);

    }
}