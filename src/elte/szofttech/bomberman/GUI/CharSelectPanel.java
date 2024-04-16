package elte.szofttech.bomberman.GUI;

import elte.szofttech.bomberman.model.GameEngine;

import java.awt.*;

public class CharSelectPanel extends Scene {
    CharSelectPanel(int width, int height, GameEngine engine){
        super(width,height,engine);
        setBackground(Color.green);
    }
}
