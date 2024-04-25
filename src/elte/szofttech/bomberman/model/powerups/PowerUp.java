package elte.szofttech.bomberman.model.powerups;


import elte.szofttech.bomberman.model.fields.Field;

import java.awt.Color;
import java.awt.Graphics;

import elte.szofttech.bomberman.model.Player;

public abstract class PowerUp extends Field {

    public PowerUp(int x, int y, int tileSize) {
        super(x, y, tileSize);
        this.defaultColor = Color.BLUE;
        this.setColor(defaultColor);
        this.setWalkable(true); 
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }

    public void applyOnPlayer(Player player){

    }

    @Override
    public boolean canPlaceObstacle() {return false;}
    

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, tileSize, tileSize);
    }
}
