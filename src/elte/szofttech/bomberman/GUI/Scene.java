package elte.szofttech.bomberman.GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import elte.szofttech.bomberman.model.GameEngine;

public abstract class Scene extends JPanel {
    int width;
    int heigth;
    GameEngine engine;

    protected Scene(int width, int heigth, GameEngine engine) {
        this.setSize(new Dimension(width,heigth));
        this.setBackground(Color.yellow);
        this.engine = engine;
    }
}
