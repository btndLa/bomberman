package elte.szofttech.bomberman.GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;

import elte.szofttech.bomberman.model.GameEngine;

public class HUDPanel extends Scene {
  
  public HUDPanel(int width, int heigth, GameEngine engine){
    super(width, heigth, engine);
    setPreferredSize(new Dimension(width, heigth));
  }

}
