package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

public class Obstacle extends PowerUp{

    Obstacle(int x, int y, int tilesize){
        super(x,y,tilesize);
    }
    @Override
    public void applyOnPlayer(Player player){
    }
}
