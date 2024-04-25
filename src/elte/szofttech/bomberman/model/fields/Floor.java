package elte.szofttech.bomberman.model.fields;

import java.awt.Color;
import java.awt.Graphics;


//Represents the walkable floor fields of the game
public class Floor extends Field{
 
  public Floor(int x, int y, int tileSize){
      super(x, y, tileSize);
      this.setWalkable(true);
      this.defaultColor = Color.GREEN;
      this.setColor(defaultColor);
      
    }

    @Override
    public boolean isDestructible() { return true;}

    @Override
    public boolean canPlaceBomb() {return true;}

    @Override
    public boolean canPlaceObstacle() {return true;}
    
    public void draw(Graphics g, int x, int y) {
      g.setColor(color);
      g.fillRect(x, y,this.tileSize, this.tileSize);      
  }
}
