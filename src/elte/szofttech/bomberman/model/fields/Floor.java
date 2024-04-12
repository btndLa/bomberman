package elte.szofttech.bomberman.model.fields;

public class Floor extends Field{

    public Floor(){
        this.Walkable = true;
    }

    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean canPlaceBomb() {
        return true;
    }
}
