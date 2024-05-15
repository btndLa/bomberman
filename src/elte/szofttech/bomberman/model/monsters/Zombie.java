package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;

import java.awt.*;


/**
 * Az előzőhöz hasonló szörny, azonban útelágazásnál válthasson irányt a leírt heurisztika
 * alapján. Bizonyos eséllyel hozzon hibás döntést és válasszon rossz utat.
 */
public class Zombie extends Monster {
    public Zombie(int x, int y, GameEngine engine, int direction) {
        super(x, y, engine, direction);
    }

    @Override
    public void move() {

    }

    @Override
    public void draw(Graphics g) {

    }
}
