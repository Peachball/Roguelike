package game.items;

import game.defaults.Defaults;
import java.awt.Color;

public enum ItemList {

    //Physical Melee Weapons
    STICK,
    SWORD,
    AXE,
    LANCE;

    //Spells
    public Item item;

    //Stats are as followed: Accuracy, Damage, Weight, PhysicalDef, MagDef, Rarity, Range, Crit
    public static Item getItem(ItemList item) {
        switch (item) {
            case STICK:
                return new PhysicalWeapon("Stick", new ItemStat(75, 1, 0, 0, 0, 0, 1, 0), '/', Defaults.SwordID, Color.WHITE);
            case SWORD:
                return new PhysicalWeapon("Sword", new ItemStat(100, 3, 3, 0, 0, 1, 1, 0), '/', Defaults.SwordID, Color.GRAY);
            case AXE:
                return new PhysicalWeapon("Axe", new ItemStat(85, 5, 6, 0, 0, 1, 1, 0), '>', Defaults.AxeID, Color.GRAY);
            case LANCE:
                return new PhysicalWeapon("Lance", new ItemStat(90, 4, 4, 0, 0, 1, 1, 0), '\\', Defaults.LanceID, Color.GRAY);
        }
        //Remember that items declared here are either special cases or starting items
        //as these items will probably be branded later...
        return new Item("BUG", new ItemStat(0, 0, 0, 0, 0, 0), '.', Defaults.SwordID, Color.WHITE);
    }
}
