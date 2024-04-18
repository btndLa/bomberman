package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;

import java.awt.Color;
import java.awt.Graphics;

public class BombDetonator extends PowerUp {
    BombDetonator(int x, int y, int tilesize){
        super(x,y,tilesize);
    }

    public BombDetonator(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
    }

    @Override
    public void applyOnPlayer(Player player) {
        player.hasDetonator = true;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        g.setColor(Color.CYAN);
        int iconSize = tileSize / 2;
        int iconX = x + (tileSize - iconSize) / 2;
        int iconY = y + (tileSize - iconSize) / 2;
        g.fillRect(iconX, iconY, iconSize, iconSize);
    }
}
