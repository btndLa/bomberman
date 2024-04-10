package elte.szofttech.bomberman.model.powerups;

import elte.szofttech.bomberman.model.Player;

public class BonusBomb extends PowerUp{
    @Override
    public void apply(Player player) {
        player.bonusBombApply();
    }
}
