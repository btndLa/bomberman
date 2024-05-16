package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Timer;

/**
 * Represents the bomb field in the game.
 * The Bomb class handles the bomb's position, radius, detonation time, and rendering.
 */
public class Bomb extends Field {
    private int radius;
    private int detonateTime;
    private Timer detonateTimer;
    private int x;
    private int y;

    /**
     * Constructs a Bomb object with the specified parameters.
     *
     * @param x           the x-coordinate of the bomb
     * @param y           the y-coordinate of the bomb
     * @param radius      the radius of the bomb's explosion
     * @param detonateTime the time before the bomb detonates
     * @param tileSize    the size of the tile
     */
    public Bomb(int x, int y, int radius, int detonateTime, int tileSize) {
        super(x, y, tileSize);
        this.setColor(Color.BLACK);
        this.defaultColor = Color.BLACK;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.detonateTime = detonateTime;
        this.detonateTimer = null;
        this.setWalkable(false);
    }

    // Getters
    /**
     * Gets the x-coordinate of the bomb.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the bomb.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the detonation time of the bomb.
     *
     * @return the detonation time
     */
    public int getDetonation() {
        return detonateTime;
    }

    /**
     * Gets the radius of the bomb's explosion.
     *
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets the detonation timer.
     *
     * @param timer the detonation timer
     */
    public void setTimer(Timer timer) {
        this.detonateTimer = timer;
    }

    /**
     * Gets the detonation timer.
     *
     * @return the detonation timer
     */
    public Timer getTimer() {
        return this.detonateTimer;
    }

    /**
     * Restarts the detonation timer.
     */
    public void restartTimer() {
        if (this.detonateTimer != null) {
            this.detonateTimer.stop();
            this.detonateTimer.setInitialDelay(0);
            this.detonateTimer.restart();
        }
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }

    @Override
    public boolean canPlaceObstacle() {
        return false;
    }

    /**
     * Sets the detonation time.
     *
     * @param detonateTime the detonation time
     */
    public void setDetonation(int detonateTime) {
        this.detonateTime = detonateTime;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, this.tileSize, this.tileSize);
    }
}
