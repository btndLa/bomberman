package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public class BonusBomb extends PowerUp {

    public BonusBomb(int x, int y, int tileSize) {
        super(x, y, tileSize);
        try {
          this.image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/BombCount.png"));
        } catch (Exception e) {}
        this.defaultColor = Color.RED;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player){
      int bombCapacity = player.getbombCapacity();
      player.setBombCapacity(bombCapacity+1);
    }


}
