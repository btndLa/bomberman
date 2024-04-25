package elte.szofttech.bomberman.GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.Box;

import elte.szofttech.bomberman.model.GameEngine;

public class GamePanel extends Scene {
  
  public GamePanel(int width, int heigth, GameEngine engine, GameGUI gui){
    super(width, heigth, engine, gui);
    setPreferredSize(new Dimension(width, heigth));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    HUDPanel hud = new HUDPanel(width, heigth-width, engine, gui);
    add(hud);
    engine.setHUD(hud);
    engine.setPreferredSize(new Dimension(width,width));
    add(engine);
  }

}
