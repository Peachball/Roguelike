package game.players;

import game.defaults.Defaults;
import game.items.Item;
import game.world.World;
import java.awt.Color;
import java.util.ArrayList;

public class Human extends Player {

    public Coord originalLocation;
    public Item[] items;
    public ArrayList<Item> inventory;
    public int maxInventory;
    public String name;
    public int special;
    public int experience;

    public Human(Coord location, World world) {
        super(new PlayerStat(10, 0, 0, 0, 0, 0), '@', Color.BLACK, world);
        this.location = new Coord(location.x, location.y);
        this.originalLocation = new Coord(location.x, location.y);
        items = new Item[Defaults.maxCarriableItems];
        inventory = new ArrayList<Item>();
        maxInventory = 20;
        special = 0;
        experience = 0;
        currentWeapon = items[0];
    }

    public Human(Coord location, String name, World world) {
        this(location, world);
        this.name = name;
    }

    @Override
    public boolean moveUp() {
        boolean buffer = super.moveUp();
        if (buffer) {
            originalLocation.y--;
        }
        return buffer;
    }

    @Override
    public boolean moveDown() {
        boolean buffer = super.moveDown();
        if (buffer) {
            originalLocation.y++;
        }
        return buffer;
    }

    @Override
    public boolean moveRight() {
        boolean buffer = super.moveRight();
        if (buffer) {
            originalLocation.x++;
        }
        return buffer;
    }

    @Override
    public boolean moveLeft() {
        boolean buffer = super.moveLeft();
        if (buffer) {
            originalLocation.x--;
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

    public void addXP(int num) {
        experience += num;
        if (experience > 100) {
            experience -= 100;
            stats.level++;
        }
    }
}
