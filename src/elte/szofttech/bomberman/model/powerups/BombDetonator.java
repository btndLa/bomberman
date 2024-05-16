package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;


public class BombDetonator extends PowerUp {
    public BombDetonator(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
          this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Detonator.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player) {
        player.hasDetonator = true;
    }


}
