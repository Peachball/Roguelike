package game.items.consumables;

import game.items.Item;
import game.players.Player;
import java.awt.Color;

public abstract class Consumable extends Item {

    public int uses;

    public Consumable(int uses, Color color, char representer,String name) {
        super(name, null, '?', -1);
        this.uses = uses;
    }

    public abstract void effect(Player player);

    public boolean update() {
        return (uses <= 0);
    }
}
