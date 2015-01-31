package game.items.consumables;

import game.players.Player;

public abstract class Consumable {

    public int uses;

    public Consumable(int uses) {
        this.uses = uses;
    }

    public abstract void effect(Player player);

    public boolean update() {
        return (uses <= 0);
    }
}
