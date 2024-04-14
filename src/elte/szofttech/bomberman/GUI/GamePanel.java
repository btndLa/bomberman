package elte.szofttech.bomberman.GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.Box;

import elte.szofttech.bomberman.model.GameEngine;

public class GamePanel extends Scene {
  
  public GamePanel(int width, int heigth, GameEngine engine){
    super(width, heigth, engine);
    setPreferredSize(new Dimension(width, heigth));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(new HUDPanel(width, heigth-width, engine));
    add(Box.createRigidArea(new Dimension(0,5)));
    engine.setPreferredSize(new Dimension(width,width));
    add(engine);
  }

}
