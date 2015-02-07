package game.players;

import game.defaults.Defaults;
import game.items.Item;
import static game.items.ItemList.STICK;
import static game.items.ItemList.getItem;
import game.world.World;
import java.awt.Color;
import java.util.ArrayList;

public class Human extends Player {

    public Coord originalLocation;
    public Item[] items;
    public ArrayList<Item> inventory;
    public int maxInventory;
    public int special;

    private static final Buff DEFAULT_LEVEL_UP = new Buff(new PlayerStat(1, 1, 1, 1, 1, 1), 1);

    public Human(Coord location, World world) {
        super(new PlayerStat(10, 10, 10, 10, 10, 10), '@', Color.BLACK, world);
        this.location = new Coord(location.x, location.y);
        this.originalLocation = new Coord(location.x, location.y);
        items = new Item[Defaults.maxCarriableItems];
        inventory = new ArrayList<Item>();
        maxInventory = 20;
        special = 0;
        experience = 0;
        currentWeapon = items[Defaults.rHand] = getItem(STICK);
    }

    public Human(Coord location, String name, World world) {
        this(location, world);
        this.name = name;
    }

    @Override
    public boolean moveUp() {
        boolean buffer = super.moveUp();
        if (buffer) {
            originalLocation.y++;
        } else {
            attack(up(), currentWeapon);
        }
        return buffer;
    }

    @Override
    public boolean moveDown() {
        boolean buffer = super.moveDown();
        if (buffer) {
            originalLocation.y--;
        } else {
            attack(down(), currentWeapon);
        }
        return buffer;
    }

    @Override
    public boolean moveRight() {
        boolean buffer = super.moveRight();
        if (buffer) {
            originalLocation.x++;
        } else {
            attack(right(), currentWeapon);
        }
        return buffer;
    }

    @Override
    public boolean moveLeft() {
        boolean buffer = super.moveLeft();
        if (buffer) {
            originalLocation.x--;
        } else {
            attack(left(), currentWeapon);
        }
        return buffer;
    }

    public void switchItem(int position, int position2) {
        if (position >= inventory.size() || position2 >= items.length) {
            return;
        }
        Item buffer = inventory.get(position);
        inventory.set(position, items[position2]);
        items[position2] = buffer;
    }

    public void pickup(World world) {
        if (world.get(this.location).items.isEmpty()) {
            return;
        }
        if (inventory.size() >= maxInventory) {
            return;
        }

        inventory.add(world.get(this.location).items.get(0));
        world.get(this.location).items.remove(0);
        world.get(this.location).update();
    }

    //Equip can only be done through player inventory... (keep in mind)
    public void equip(int position) {
        if (position >= inventory.size()) {
            return;
        }
        Item buffer = inventory.get(position);
        if (buffer == null) {
            return;
        }
        switch (buffer.type) {
            case Defaults.AxeID:
                if (items[Defaults.rHand] != null) {
                    inventory.set(position, items[Defaults.rHand]);
                }

                items[Defaults.rHand] = buffer;
                break;
            case Defaults.LanceID:
                if (items[Defaults.rHand] != null) {
                    inventory.set(position, items[Defaults.rHand]);
                }

                items[Defaults.rHand] = buffer;
                break;
            case Defaults.SwordID:
                if (items[Defaults.rHand] != null) {
                    inventory.set(position, items[Defaults.rHand]);
                }

                items[Defaults.rHand] = buffer;
                break;
            case Defaults.ArmorID:
                if (items[Defaults.shirt] != null) {
                    inventory.set(position, items[Defaults.shirt]);
                }
                items[Defaults.shirt] = buffer;
                break;
            case Defaults.BootsID:
                if (items[Defaults.boots] != null) {
                    inventory.set(position, items[Defaults.boots]);
                }
                items[Defaults.boots] = buffer;
                break;
            case Defaults.GloveID:
                if (items[Defaults.glove] != null) {
                    inventory.set(position, items[Defaults.glove]);
                }
                items[Defaults.glove] = buffer;
                break;
        }
    }

    public void equip() {
        equip(inventory.size() - 1);
    }

    //For inventory
    public void drop(int position) {
        if (position >= inventory.size()) {
            return;
        }
        world.get(location).items.add(inventory.get(position));
        inventory.remove(position);
    }

    public void addXP(int num) {
        experience += num;
        if (experience > 100) {
            experience -= 100;
            stats.level++;
        }
    }

    public void levelUp() {
        DEFAULT_LEVEL_UP.applyBuff(stats);
    }
}
