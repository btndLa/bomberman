package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;

import java.awt.*;

/**
 * Az alapfeladatban szereplőhöz hasonló szörny, amely a falakon és dobozokon át tud menni.
 * (De a bombákon nem.) Amennyiben akadályhoz, azaz falhoz vagy dobozhoz érkezik és
 * egyenes továbbhaladással van még nem akadály mező a pályán, akkor bizonyos eséllyel ne
 * irányt váltson, hanem folytassa haladási irányát. Ilyenkor, ha több akadály mező is van
 * egymás után, akkor azokon már irányváltás nélkül haladjon végig.
 */
public class Ghost extends Monster {
    public Ghost(int x, int y, GameEngine engine, int direction) {
        super(x, y, engine, direction);
    }

    @Override
    public void move() {

    }

    @Override
    public void draw(Graphics g) {

    }

}
