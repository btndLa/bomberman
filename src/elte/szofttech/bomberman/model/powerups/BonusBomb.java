package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;

public class BonusBomb extends PowerUp {

    public BonusBomb(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.RED;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player){
      int bombCapacity = player.getbombCapacity();
      player.setBombCapacity(bombCapacity+1);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        g.setColor(Color.ORANGE);
        int iconSize = tileSize / 2;
        int iconX = x + (tileSize - iconSize) / 2;
        int iconY = y + (tileSize - iconSize) / 2;
        g.fillRect(iconX, iconY, iconSize, iconSize);
    }
}
