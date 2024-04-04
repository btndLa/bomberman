package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;

import java.util.Random;

public class BasicMonster extends Monster {
    public BasicMonster(int x, int y, int speed, GameEngine engine) {
        super(x, y, speed, engine);
    }

    /**
     *Choose a random number between 1 and 4 and moves according to that number
     * 1 -> up
     * 2 -> down
     * 3 -> left
     * 4 -> right
     */
    @Override
    public void move(int direction) {
        int randomDirection;
        do{
            randomDirection = (new Random()).nextInt(1,5);
        }while (randomDirection == direction);
        switch (direction) {
            case 1 -> {
                if (this.y + 1 < engine.getBoard().length && engine.getBoard()[this.x][this.y].Walkable) {
                    this.y++;
                }
                else{
                    this.move(randomDirection);
                }
            }
            case 2 -> {
                if (this.y - 1 >= 0 && engine.getBoard()[this.x][this.y].Walkable) {
                    this.y--;
                }
                else{
                    this.move(randomDirection);
                }
            }
            case 3 -> {
                if (this.x - 1 >= 0 && engine.getBoard()[this.x][this.y].Walkable) {
                    this.x--;
                }
                else{
                    this.move(randomDirection);
                }
            }
            case 4 -> {
                if (this.x + 1 < engine.getBoard().length && engine.getBoard()[this.x][this.y].Walkable) {
                    this.x++;
                }
                else{
                    this.move(randomDirection);
                }
            }
            default -> this.move(randomDirection);
        }
        engine.getBoard()[this.x][this.y].Walkable = false;
    }


}
