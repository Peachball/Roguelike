package game.items;

import game.defaults.Defaults;
import java.awt.Color;

public enum ItemList {

    FIST(new Item("Fists", new ItemStat(1, 0, 0, 0, 0, 0), ')', Defaults.WeaponID,Color.WHITE)),
    SWORD(new Item("Sword", new ItemStat(2, 0, 0, 0, 0, 0), '/', Defaults.WeaponID,Color.GRAY));

    public Item item;

    ItemList(Item item) {
        this.item = item;
    }
}
