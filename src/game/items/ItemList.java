package game.items;

import game.defaults.Defaults;
import java.awt.Color;

public enum ItemList {

    //Physical Melee Weapons
    STICK,
    SWORD, AXE;

    //Spells
    public Item item;

    //Stats are as followed: Accuracy, Damage, Weight, PhysicalDef, MagDef, Rarity, Range, Crit
    public static Item getItem(ItemList item) {
        switch (item) {
            case STICK:
                return new PhysicalWeapon("Fists", new ItemStat(75, 1, 0, 0, 0, 0, 1, 0), '/', Defaults.WeaponID, Color.WHITE);
            case SWORD:
                return new PhysicalWeapon("Sword", new ItemStat(100, 3, 3, 0, 0, 1, 1, 0), '/', Defaults.WeaponID, Color.GRAY);
            case AXE:
                return new PhysicalWeapon("Axe", new ItemStat(85, 5, 6, 0, 0, 1, 1, 0), '>', Defaults.WeaponID, Color.GRAY);
        }
        return new Item("BUG", new ItemStat(0, 0, 0, 0, 0, 0), '.', Defaults.WeaponID, Color.WHITE);
    }
}
