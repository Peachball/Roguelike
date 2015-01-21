package game.players;

import game.defaults.Defaults;
import game.items.Item;
import game.world.World;
import java.util.ArrayList;

public class Human extends Player {

    public Coord originalLocation;
    public Item[] items;
    public ArrayList<Item> inventory;
    public int maxInventory;
    public String name;

    //This is to determine which hand to switch the weapon out with
    //It could be replaced with more commands on switching specific items out
    //through mapping more keys (e.g. k key switches out left hand weapons, etc...
    private boolean whichHand;

    public Human(Coord location) {

        super(new PlayerStat(10, 0, 0, 0,0, 0), '@');
        whichHand = true;
        this.location = new Coord(location.x, location.y);
        this.originalLocation = new Coord(location.x, location.y);
        items = new Item[Defaults.maxCarriableItems];
        inventory = new ArrayList<Item>();
        maxInventory = 20;
    }

    public Human(Coord location, String name) {
        this(location);
        this.name = name;
    }

    public void moveUp() {
        location.y--;
        originalLocation.y--;
    }

    public void moveDown() {
        location.y++;
        originalLocation.y++;
    }

    public void moveRight() {
        location.x++;
        originalLocation.x++;
    }

    public void moveLeft() {
        location.x--;
        originalLocation.x--;
    }

    //LOL NO ERROR CHECKING
    private void switchItems(int position, World world) {
        Item buffer = items[position];
        items[position] = world.get(this.location).items.get(0);
        world.get(this.location).items.set(0, buffer);
    }

    public void switchItem(int position, int position2) {
        if (position >= inventory.size() || position2 >= items.length) {
            return;
        }
        Item buffer = inventory.get(position);
        inventory.set(position, items[position2]);
        items[position2] = buffer;
    }
    
    public void consume(int position){
        if(position>=items.length){
            return;
        }
        
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
    }
}
