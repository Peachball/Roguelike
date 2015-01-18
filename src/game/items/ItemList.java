package game.items;

import game.defaults.Defaults;

public enum ItemList {

    FIST(new Item("Fists", new ItemStat(1, 0, 0, 0, 0, 0), ')', Defaults.WeaponID)),
    SWORD(new Item("Sword", new ItemStat(2, 0, 0, 0, 0, 0), '/', Defaults.WeaponID));

    public Item item;

    ItemList(Item item) {
        this.item = item;
    }
}
