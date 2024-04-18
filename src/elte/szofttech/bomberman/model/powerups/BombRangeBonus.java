package elte.szofttech.bomberman.model.powerups;

import java.awt.Color;
import java.awt.Graphics;
import elte.szofttech.bomberman.model.Player;

public class BombRangeBonus extends PowerUp {

    public BombRangeBonus(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.RED;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player){
      int bombRadius = player.getBombRadius();
      player.setBombRadius(bombRadius+1);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        g.setColor(Color.YELLOW);
        int iconSize = tileSize / 2;
        int iconX = x + (tileSize - iconSize) / 2;
        int iconY = y + (tileSize - iconSize) / 2;
        g.fillOval(iconX, iconY, iconSize, iconSize);
    }
}
