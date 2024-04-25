package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Ghost extends PowerUp {

    public Ghost(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.ORANGE;
        this.setColor(defaultColor);
    }

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

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
            g.setColor(Color.ORANGE);
            int iconSize = tileSize / 2;
            int iconX = x + (tileSize - iconSize) / 2;
            int iconY = y + (tileSize - iconSize) / 2;
            g.fillOval(iconX, iconY, iconSize, iconSize);
            g.setColor(Color.BLACK);
            int crossSize = iconSize / 2;
            int crossX = iconX + (iconSize - crossSize) / 2;
            int crossY = iconY + (iconSize - crossSize) / 2;
            g.fillRect(crossX, crossY, crossSize, crossSize);

    }
}
