package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.fields.Field;
import elte.szofttech.bomberman.model.Player;

public abstract class PowerUp extends Field {

    @Override
    public boolean isDestructible() {
        return false;
    }

    @Override
    public boolean canPlaceBomb() {
        return false;
    }

    public abstract void apply(Player player);
}
