package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Invulnerable extends PowerUp {
    public Invulnerable(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player) {
        player.isInvulnerable = true; // Átállítjuk az isInvulnerable-t true-ra
        System.out.println("Is Invulnerable after application: " + player.isInvulnerable); // Kiírjuk az értéket
        
        // Indítunk egy időzítőt, ami 8 másodperc múlva visszaállítja az isInvulnerable-t false-ra
        Timer invulnerabilityTimer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.isInvulnerable = false;
                System.out.println("Is Invulnerable after timer expiration: " + player.isInvulnerable); // Kiírjuk az értéket
            }
        });
        invulnerabilityTimer.setRepeats(false); // Csak egyszer fusson le
        invulnerabilityTimer.start();
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        g.setColor(Color.CYAN);
        int iconSize = tileSize / 2;
        int iconX = x + (tileSize - iconSize) / 2;
        int iconY = y + (tileSize - iconSize) / 2;
        g.fillOval(iconX, iconY, iconSize, iconSize);
    }
}
