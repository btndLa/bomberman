package elte.szofttech.bomberman.GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.Box;

import elte.szofttech.bomberman.model.GameEngine;

public class GamePanel extends Scene {
  
  public GamePanel(int width, int heigth, GameEngine engine, GameGUI gui, int playerNumber){
    super(width, heigth, engine, gui);
    setPreferredSize(new Dimension(width, heigth));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    HUDPanel hud = new HUDPanel(width, heigth-width, engine, gui, playerNumber);
    add(hud);
    engine.setHUD(hud);
    engine.setPreferredSize(new Dimension(width,width));
    add(engine);
  }

}
