package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.fields.Bomb;
import elte.szofttech.bomberman.model.fields.Floor;
import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.imageio.ImageIO;

// Represents the ghost monster, which always moves through obstacles but doesn't step off the board
public class GhostMonster extends Monster {
    
    private Image image;
    public GhostMonster(int x, int y, GameEngine engine, int direction) {
        super(x, y, engine, direction);
        try {
          image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Ghost.png"));
        } catch (Exception e) {}
    }

    public int getSpeed() {
        return this.speed;
    }

    /**
     * Move the monster based on its direction
     * 1 -> up
     * 2 -> down
     * 3 -> left
     * 4 -> right
     */
    @Override
    public void move() {
        int currentX = this.x;
        int currentY = this.y;
        boolean moved = false;
        Random random = new Random();
        while (!moved) {
            switch (this.direction) {
                case 1: // Up
                    if (this.y - 1 >= 1 && !isObstacle(this.x, this.y - 1)) {
                        this.y--;
                        moved = true;
                    } else {
                        this.direction = 2; // Switch to down
                    }
                    break;
                case 2: // Down
                    if (this.y + 1 < engine.getBoard().length-1 && !isObstacle(this.x, this.y + 1)) {
                        this.y++;
                        moved = true;
                    } else {
                        this.direction = 1; // Switch to up
                    }
                    break;
                case 3: // Left
                    if (this.x - 1 >= 1 && !isObstacle(this.x - 1, this.y)) {
                        this.x--;
                        moved = true;
                    } else {
                        this.direction = 4; // Switch to right
                    }
                    break;
                case 4: // Right
                    if (this.x + 1 < engine.getBoard()[this.y].length-1 && !isObstacle(this.x + 1, this.y)) {
                        this.x++;
                        moved = true;
                    } else {
                        this.direction = 3; // Switch to left
                    }
                    break;
            }

            if (moved && engine.getBoard()[this.y][this.x] instanceof Floor) {
                if (random.nextInt(10) < 7) {
                    this.direction = random.nextInt(4) + 1;
                }
            }
        }

        Player player = isPlayer(this.x, this.y);
        if (player != null) {           
            player.die();
           //engine.getPlayers().remove(player);
        }
    }

    private Player isPlayer(int x, int y) {
        for (Player player : engine.getPlayers()) {
            if (player.getX() == x && player.getY() == y && !(engine.getBoard()[y][x] instanceof Bomb)) return player;
        }
        return null;
    }

    private boolean isObstacle(int x, int y) {
        // Check for bomb
        if (engine.getBoard()[y][x] instanceof Bomb) {
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
      int ts = engine.gettileSize();
      g.drawImage(image, x * ts, y * ts, ts, ts, null);
    }
}
