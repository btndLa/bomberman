package elte.szofttech.bomberman.model.fields;

public abstract class Field {
    public boolean Walkable;

    public abstract boolean isDestructible();
    public abstract boolean canPlaceBomb();
}
