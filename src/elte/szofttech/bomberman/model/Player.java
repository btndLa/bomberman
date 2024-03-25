package elte.szofttech.bomberman.model;

import java.awt.event.KeyEvent;

public class Player extends Entity {
    //Control key codes
    private int up;
    private int down;
    private int left;
    private int right;
    private int bombRadius;
    private GameEngine engine;
    private int bombCapacity;
    private int placedBombs;

    public Player(int x, int y, int up, int down, int left, int right, GameEngine engine) {
        super(x, y);
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.engine = engine;
        this.bombCapacity = 1;
        this.bombRadius = 3;
        this.placedBombs = 0;
    }

    public void move(KeyEvent key){}
}