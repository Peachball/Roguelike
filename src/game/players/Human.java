package game.players;

import game.defaults.Defaults;
import game.items.Item;
import game.world.World;

public class Human extends Player {

    public Coord originalLocation;
    public Item[] items;

    //This is to determine which hand to switch the weapon out with
    //It could be replaced with more commands on switching specific items out
    //through mapping more keys (e.g. k key switches out left hand weapons, etc...
    private boolean whichHand;

    public Human(Coord location) {

        super(new PlayerStat(10, 0, 0, 0, 0), '@');
        whichHand = true;
        this.location = location;
        this.originalLocation = location;
        items = new Item[Defaults.maxCarriableItems];
    }

    public void moveUp() {
        this.location.y--;
        originalLocation.y--;
    }

    public void moveDown() {
        this.location.y++;
        originalLocation.y++;
    }

    public void moveRight() {
        this.location.x++;
        originalLocation.x++;
    }

    public void moveLeft() {
        this.location.x--;
        originalLocation.x--;
    }

    //LOL NO ERROR CHECKING
    private void switchItems(int position, World world) {
        Item buffer = items[position];
        items[position] = world.get(this.location).items.get(0);
        world.get(this.location).items.set(0, buffer);
    }

    public void pickup(World world) {
        if (world.get(this.location).items.isEmpty()) {
            return;
        }

        //I'll finish this later
        //Method title should explain purpose of this method...
        if (world.get(this.location).items.get(0).type == Defaults.WeaponID) {
            if (items[Defaults.rHand] == null) {
                switchItems(Defaults.rHand, world);
                world.get(this.location).items.remove(0);
            } else if (items[Defaults.lHand] == null) {
                
            }
        }
    }
}
