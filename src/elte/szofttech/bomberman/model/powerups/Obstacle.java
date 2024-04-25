package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends PowerUp {
    public Obstacle(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player) {
        player.obstacleCapacity += 3;
    }

    @Override
    public boolean canPlaceObstacle() { return false; }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
            g.setColor(Color.RED);
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
