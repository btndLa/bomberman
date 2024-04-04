package elte.szofttech.bomberman.model.fields;

public abstract class Field {
    public boolean isWalkable() {
        return Walkable;
    }

    public void setWalkable(boolean walkable) {
        Walkable = walkable;
    }

    protected boolean Walkable;

    public abstract boolean isDestructible();
    public abstract boolean canPlaceBomb();
}
