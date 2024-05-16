package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Floor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * Represents a zombie monster in the Bomberman game.
 * Similar to the GhostMonster, but can change direction at junctions based on a described heuristic.
 * May make wrong decisions and choose the wrong path with a certain chance.
 */
public class Zombie extends Monster {

    private Image image;

    /**
     * Constructs a Zombie object with the specified parameters.
     * @param x The initial x-coordinate of the zombie.
     * @param y The initial y-coordinate of the zombie.
     * @param engine The game engine associated with the zombie.
     * @param direction The initial direction of the zombie.
     */
    public Zombie(int x, int y, GameEngine engine, int direction) {
        super(x, y, engine, direction);
        try {
          image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Zombie.png"));
        } catch (Exception e) {}
    }

    /**
     * Gets the speed of the zombie.
     * @return The speed of the zombie.
     */
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
                this.direction = random.nextInt(4) + 1;
            } else {
                int numFloors = countAdjacentFloors();
                if (numFloors > 1 && random.nextInt(10) < 8) {
                    Player nearestPlayer = findNearestPlayer();
                    if (nearestPlayer != null) {
                        this.direction = getBestDirectionTowards(nearestPlayer);
                    }
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

    private int countAdjacentFloors() {
        int count = 0;
        if (this.y - 1 >= 0 && engine.getBoard()[this.y - 1][this.x] instanceof Floor) {
            count++;
        }
        if (this.y + 1 < engine.getBoard().length && engine.getBoard()[this.y + 1][this.x] instanceof Floor) {
            count++;
        }
        if (this.x - 1 >= 0 && engine.getBoard()[this.y][this.x - 1] instanceof Floor) {
            count++;
        }
        if (this.x + 1 < engine.getBoard()[this.y].length && engine.getBoard()[this.y][this.x + 1] instanceof Floor) {
            count++;
        }
        return count;
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
            if (player.getX() == x && player.getY() == y && player.isAlive() && player.isRollerBlade == false && !(engine.getBoard()[y][x] instanceof Bomb)) return player;
        }
        return null;
    }

    /**
     * Draws the zombie on the screen.
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
      int ts = engine.gettileSize();
      g.drawImage(image, x * ts, y * ts, ts, ts, null);
    }
}
