package elte.szofttech.bomberman.model.monsters;

import elte.szofttech.bomberman.model.GameEngine;
import elte.szofttech.bomberman.model.Player;
import elte.szofttech.bomberman.model.fields.Bomb;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Represents a basic monster in the game.
 */
public class BasicMonster extends Monster {

    private Image image;

    /**
     * Constructs a BasicMonster with the specified parameters.
     *
     * @param x         the x-coordinate of the monster
     * @param y         the y-coordinate of the monster
     * @param engine    the game engine
     * @param direction the initial direction of the monster
     */
    public BasicMonster(int x, int y, GameEngine engine, int direction) {
        super(x, y, engine, direction);
        try {
            image = ImageIO.read(getClass().getResource("/elte/szofttech/bomberman/assets/images/Basic-Monster.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the speed of the monster.
     *
     * @return the speed of the monster
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Moves the monster based on its direction:
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

        while (currentX == this.x && currentY == this.y) {
            switch (this.direction) {
                case 1:
                    if (canMoveTo(this.x, this.y - 1)) {
                        this.y--;
                        moved = true;
                    }
                    break;
                case 2:
                    if (canMoveTo(this.x, this.y + 1)) {
                        this.y++;
                        moved = true;
                    }
                    break;
                case 3:
                    if (canMoveTo(this.x - 1, this.y)) {
                        this.x--;
                        moved = true;
                    }
                    break;
                case 4:
                    if (canMoveTo(this.x + 1, this.y)) {
                        this.x++;
                        moved = true;
                    }
                    break;
                default:
                    this.direction = (new Random()).nextInt(4) + 1;
            }

            if (!moved) {
                this.direction = (new Random()).nextInt(4) + 1;
            }
        }

        updateBoardPosition(currentX, currentY);

        Player player = isPlayer(this.x, this.y);
        if (player != null) {
            player.die();
        }
    }

    /**
     * Checks if the monster can move to the specified coordinates.
     *
     * @param x the x-coordinate to move to
     * @param y the y-coordinate to move to
     * @return true if the monster can move to the specified coordinates, false otherwise
     */
    private boolean canMoveTo(int x, int y) {
        return y >= 0 && y < engine.getBoard().length && x >= 0 && x < engine.getBoard()[y].length && 
               (engine.getBoard()[y][x].isWalkable() || isPlayer(x, y) != null);
    }

    /**
     * Updates the board position after the monster moves.
     *
     * @param currentX the current x-coordinate of the monster
     * @param currentY the current y-coordinate of the monster
     */
    private void updateBoardPosition(int currentX, int currentY) {
        engine.getBoard()[currentY][currentX].setWalkable(true);
        engine.getBoard()[this.y][this.x].setWalkable(false);
    }

    /**
     * Checks if there is a player at the specified coordinates.
     *
     * @param x the x-coordinate to check
     * @param y the y-coordinate to check
     * @return the player if one is found at the specified coordinates, null otherwise
     */
    private Player isPlayer(int x, int y) {
        for (Player player : engine.getPlayers()) {
            if (player.getX() == x && player.getY() == y && !(engine.getBoard()[y][x] instanceof Bomb)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Draws the monster on the given graphics context.
     *
     * @param g the graphics context
     */
    public void draw(Graphics g) {
        int ts = engine.gettileSize();
        g.drawImage(image, x * ts, y * ts, ts, ts, null);
    }
}
