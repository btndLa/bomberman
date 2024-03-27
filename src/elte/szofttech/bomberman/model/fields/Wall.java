package elte.szofttech.bomberman.model.fields;

public class Wall extends Field{

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }
}
