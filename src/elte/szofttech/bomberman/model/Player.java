package elte.szofttech.bomberman.model;

import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.monsters.Monster;

import java.awt.event.KeyEvent;

public class Player extends Entity {
    //Control key codes
    private int up;
    private int down;
    private int left;
    private int right;
    private int bomb;
    private int bombRadius;
    private GameEngine engine;
    private int bombCapacity;
    private int placedBombs;
    public boolean isAlive;

    public Player(int x, int y, int up, int down, int left, int right, int bomb, GameEngine engine) {
        super(x, y);
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bomb = bomb;
        this.engine = engine;
        this.bombCapacity = 1;
        this.bombRadius = 3;
        this.placedBombs = 0;
        this.isAlive = true;
    }

    public void move(KeyEvent key){
        int keyAsInt = key.getKeyCode();
        System.out.println(keyAsInt);
        if(keyAsInt == this.up){
            if(this.y + 1 < engine.getBoard().length && engine.getBoard()[this.x][this.y+1].Walkable){
                this.y += 1;
            }
        }
        else if(keyAsInt == this.down){
            if(this.y - 1 >= 0 && engine.getBoard()[this.x][this.y-1].Walkable){
                this.y -= 1;
            }
        }
        else if(keyAsInt == this.left){
            if(this.x - 1 >= 0 && engine.getBoard()[this.x-1][this.y].Walkable){
                this.x -= 1;
            }
        }
        else if(keyAsInt == this.right){
            if(this.x + 1 < engine.getBoard().length && engine.getBoard()[this.x +1][this.y].Walkable){
                this.x += 1;
            }
        }
        else if (keyAsInt == this.bomb){
            if(engine.getBoard()[this.x][this.y].canPlaceBomb()){
                engine.DetonateBomb(new Bomb(this.x, this.y, this.bombRadius, 3));
            }
        }
        engine.getBoard()[this.x][this.y].Walkable = false;
    }

    @Override
    public void onExplosion() {
        this.isAlive = false;
    }

    @Override
    public void onCollision(Entity e) {
        if(e.x == this.x && e.y == this.y && e instanceof Monster){
            this.isAlive = false;
        }
    }

}