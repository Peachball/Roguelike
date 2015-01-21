package game.items;

import game.defaults.Defaults;
import java.awt.Color;

public enum ItemList {

    //
    FIST,
    SWORD;

    public Item item;

    public static Item getItem(ItemList item) {
        switch (item) {
            case FIST:
                return new Item("Fists", new ItemStat(1, 0, 0, 0, 0, 0), ')', Defaults.WeaponID, Color.WHITE);
            case SWORD:
                return new Item("Sword", new ItemStat(2, 0, 0, 0, 0, 0), 'v', Defaults.WeaponID, Color.GRAY);
        }
        return new Item("BUG", new ItemStat(0, 0, 0, 0, 0, 0), '*', Defaults.WeaponID, Color.WHITE);
    }
}
