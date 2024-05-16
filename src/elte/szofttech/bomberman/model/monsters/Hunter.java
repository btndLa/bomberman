package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Floor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Hunter extends Monster {

    public Hunter(int x, int y, GameEngine engine, int direction) {
        super(x, y, engine, direction);
    }

    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void move() {
        int currentX = this.x;
        int currentY = this.y;
        boolean moved = false;
        Random random = new Random();

        while (!moved) {
            switch (this.direction) {
                case 1: // Up
                    if (this.y - 1 >= 0 && (engine.getBoard()[this.y - 1][this.x].isWalkable() || isPlayer(this.x, this.y - 1) != null)) {
                        this.y--;
                        moved = true;
                    }
                    break;
                case 2: // Down
                    if (this.y + 1 < engine.getBoard().length && (engine.getBoard()[this.y + 1][this.x].isWalkable() || isPlayer(this.x, this.y + 1) != null)) {
                        this.y++;
                        moved = true;
                    }
                    break;
                case 3: // Left
                    if (this.x - 1 >= 0 && (engine.getBoard()[this.y][this.x - 1].isWalkable() || isPlayer(this.x - 1, this.y) != null)) {
                        this.x--;
                        moved = true;
                    }
                    break;
                case 4: // Right
                    if (this.x + 1 < engine.getBoard()[this.y].length && (engine.getBoard()[this.y][this.x + 1].isWalkable() || isPlayer(this.x + 1, this.y) != null)) {
                        this.x++;
                        moved = true;
                    }
                    break;
            }

            if (!moved) {
                Player nearestPlayer = findNearestPlayer();
                if (nearestPlayer != null) {
                    this.direction = getBestDirectionTowards(nearestPlayer);
                } else {
                    this.direction = random.nextInt(4) + 1;
                }
            } else {
                if (random.nextInt(10) < 2) {
                    this.direction = random.nextInt(4) + 1;
                }
            }
        }

        engine.getBoard()[currentY][currentX].setWalkable(true);
        engine.getBoard()[this.y][this.x].setWalkable(false);
        Player player = isPlayer(this.x, this.y);
        if (player != null) {
            player.die();
            //engine.getPlayers().remove(player);
        }
    }

    private Player findNearestPlayer() {
        Player nearestPlayer = null;
        int minDistance = Integer.MAX_VALUE;

        for (Player player : engine.getPlayers()) {
            if (player.isAlive() && player.isRollerBlade == false) {
                int distance = Math.abs(player.getX() - this.x) + Math.abs(player.getY() - this.y);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPlayer = player;
                }
            }
        }

        return nearestPlayer;
    }

    private int getBestDirectionTowards(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int bestDirection = -1;
        int minDistance = Integer.MAX_VALUE;

        int[][] directions = {
            {this.x, this.y - 1, 1}, // Up
            {this.x, this.y + 1, 2}, // Down
            {this.x - 1, this.y, 3}, // Left
            {this.x + 1, this.y, 4}  // Right
        };

        for (int[] dir : directions) {
            int newX = dir[0];
            int newY = dir[1];
            int direction = dir[2];

            if (newX >= 0 && newX < engine.getBoard()[0].length && newY >= 0 && newY < engine.getBoard().length) {
                if (engine.getBoard()[newY][newX].isWalkable() || engine.getBoard()[newY][newX] instanceof Floor) {
                    int distance = Math.abs(playerX - newX) + Math.abs(playerY - newY);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestDirection = direction;
                    }
                }
            }
        }

        if (bestDirection == -1) {
            bestDirection = new Random().nextInt(4) + 1;
        }

        return bestDirection;
    }

    private Player isPlayer(int x, int y) {
        for (Player player : engine.getPlayers()) {
            if (player.getX() == x && player.getY() == y && player.isAlive() && player.isRollerBlade == false && !(engine.getBoard()[y][x] instanceof Bomb)) {
                return player;
            }
        }
        return null;
    }

    public void draw(Graphics g) {
        int ts = engine.gettileSize();
        g.setColor(Color.BLACK);
        g.fillRect(x * ts, y * ts, ts, ts);
    }
}
