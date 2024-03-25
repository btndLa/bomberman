package elte.szofttech.bomberman.model.fields;

public abstract class Field {
    private boolean Walkable;

    public abstract boolean isDestructible();
    public abstract boolean canPlaceBomb();
}
