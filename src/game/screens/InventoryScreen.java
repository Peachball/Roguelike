package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.players.Human;
import game.world.World;
import java.awt.event.KeyEvent;

public class InventoryScreen implements Screen {

    private World world;
    private Human human;
    private int marker;
    private int maxMarker;
    private int position;

    public InventoryScreen(World world, Human human) {
        this.world = world;
        this.human = human;
        marker = 0;
        position = 0;
        maxMarker = human.inventory.size();
    }

    @Override
    public void display(AsciiPanel output) {
        output.write("Inventory:" + human.inventory.size() + "/" + human.maxInventory, 0, 0);
        maxMarker = human.inventory.size();
        if (maxMarker > Defaults.MAX_INVENTORY_PER_SCREEN) {
            maxMarker = Defaults.MAX_INVENTORY_PER_SCREEN;
        }
        //List the appropriate items in the screen
        if (human.inventory.isEmpty()) {
            output.write("LOL YOU GOT NO ITEMS BRAH", 1, 1);
        }
        for (int counter = 0; counter < maxMarker; counter++) {
            output.write(human.inventory.get(counter + position).name, 1, counter + 1);
            output.write(Integer.toString(human.inventory.get(counter + position).durability), 10, counter + 1);
        }
        //Add the cool arrow
        output.write('>', 0, marker + 1);
    }

    @Override
    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case Defaults.up:
                moveUp();
                break;
            case Defaults.down:
                moveDown();
                break;
            case Defaults.A:
                break;
            case Defaults.B:
                return new PlayScreen(world, human);
        }
        return this;
    }

    private void moveUp() {
        marker--;
        if (marker < 1 && position>0) {
            position--;
            marker++;
        }
        if (marker < 0) {
            if(human.inventory.size()>maxMarker){
                position = human.inventory.size()-maxMarker;
            }
            marker = maxMarker - 1;
        }

    }

    private void moveDown() {
        marker++;
        if (marker >= maxMarker - 1 && position + maxMarker < human.inventory.size()) {
            position++;
            marker--;
        }
        if (marker >= maxMarker) {
            position = 0;
            marker = 0;
        }
    }

}
