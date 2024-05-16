package elte.szofttech.bomberman.model.powerups;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

import elte.szofttech.bomberman.model.Player;

public class BombRangeBonus extends PowerUp {

    public BombRangeBonus(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
          this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/BombRadius.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.RED;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player){
      int bombRadius = player.getBombRadius();
      player.setBombRadius(bombRadius+1);
    }

}
