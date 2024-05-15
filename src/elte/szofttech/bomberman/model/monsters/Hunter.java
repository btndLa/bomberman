package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.fields.Field;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * Az alapfeladatban szereplőhöz hasonló szörny, azonban akadályba ütközéskor bekövetkező
 * irányváltáskor olyan irányt válasszon, hogy a legrövidebb út alapján a hozzá legközelebb eső
 * játékos felé induljon el. (Ha egyik játékost sem tudja elérni a pályán lévő akadályok miatt,
 * akkor véletlenszerűen bolyongjon.)
 */
public class Hunter extends Monster {
    Player target;

    public Hunter(int x, int y, GameEngine engine, int direction){
        super(x,y,engine,direction);
    }
    @Override
    public void move() {

    }

    @Override
    public void draw(Graphics g) {
        int ts = engine.gettileSize();
        g.setColor(Color.magenta);
        g.fillRect(x * ts, y * ts,ts,ts);
    }
}
