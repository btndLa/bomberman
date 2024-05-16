package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Ghost extends PowerUp {

    public Ghost(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
          this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Detonator.png"));
        } catch (Exception e) {}
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

}
