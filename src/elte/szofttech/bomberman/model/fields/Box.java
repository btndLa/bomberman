package elte.szofttech.bomberman.model.fields;

public class Box extends Field{
    @Override
    public boolean isDestructible() {
        return true;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }
}
