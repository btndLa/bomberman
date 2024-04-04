package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;

import java.util.Random;

public class BasicMonster extends Monster {
    public BasicMonster(int x, int y, int speed, GameEngine engine) {
        super(x, y, speed, engine);
        engine.getBoard()[this.x][this.y].setWalkable(false);
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
        switch (direction) {
            case 1 -> {
                if (this.y + 1 < engine.getBoard().length && engine.getBoard()[this.x][this.y+1].isWalkable()) {
                    engine.getBoard()[this.x][this.y].setWalkable(true);
                    this.y++;
                }

            }
            case 2 -> {
                if (this.y - 1 >= 0 && engine.getBoard()[this.x][this.y-1].isWalkable()) {
                    engine.getBoard()[this.x][this.y].setWalkable(true);
                    this.y--;
                }

            }
            case 3 -> {
                if (this.x - 1 >= 0 && engine.getBoard()[this.x-1][this.y].isWalkable()) {
                    engine.getBoard()[this.x][this.y].setWalkable(true);
                    this.x--;
                }

            }
            case 4 -> {
                if (this.x + 1 < engine.getBoard().length && engine.getBoard()[this.x+1][this.y].isWalkable()) {
                    engine.getBoard()[this.x][this.y].setWalkable(true);
                    this.x++;
                }
            }
            default -> engine.getBoard()[this.x][this.y].setWalkable(false);
        }
        engine.getBoard()[this.x][this.y].setWalkable(false);
    }


}
