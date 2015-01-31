package game.screens;

import asciiPanel.AsciiPanel;
import game.defaults.Defaults;
import game.items.Item;
import game.items.PhysicalWeapon;
import game.players.Human;
import game.world.World;
import java.awt.event.KeyEvent;

public class InventoryScreen implements Screen {

    private World world;
    private Human human;
    private int marker;
    private int maxMarker;
    private int position;
    private boolean equippedScreen;
    private int emarker;

    public InventoryScreen(World world, Human human) {
        this.world = world;
        this.human = human;
        marker = 0;
        position = 0;
        maxMarker = human.inventory.size();
        equippedScreen = false;
        emarker = 0;
    }

    @Override
    public void display(AsciiPanel output) {
        HUD hud = new HUD(output, world, human);
        output.write("Inventory:" + human.inventory.size() + "/" + human.maxInventory, 0, 0);
        maxMarker = human.inventory.size();
        if (maxMarker > Defaults.MAX_INVENTORY_PER_SCREEN) {
            maxMarker = Defaults.MAX_INVENTORY_PER_SCREEN;
        }
        if (equippedScreen) {
            output.write("Equipped Items:              ", 0, 0);
            if (emarker >= human.items.length) {
                emarker = human.items.length - 1;
            }
            if (emarker < 0) {
                emarker = 0;
            }
            for (int counter = 0; counter < human.items.length; counter++) {
                if (human.items[counter] == null) {
                    continue;
                }
                output.write(human.items[counter].name + "   " + human.items[counter].durability, 1, counter + 1);
            }
        } else {
            for (int counter = 0; counter < maxMarker; counter++) {
                output.write(human.inventory.get(counter + position).name, 1, counter + 1);
                output.write(Integer.toString(human.inventory.get(counter + position).durability), 10, counter + 1);
            }

            //List the appropriate items in the screen
            if (human.inventory.isEmpty()) {
                output.write("LOL YOU GOT NO ITEMS BRAH", 1, 1);
            }
        }
        //Add the cool arrow
        if (!equippedScreen) {
           
            output.write('>', 0, (marker + 1 <= 0) ? 1 : marker + 1);
        } else {
             output.write('>', 0, emarker + 1);
        }
        //Add player stats
        //Should this be hidden away in another method?
        hud.displayPlayerStats(Defaults.GAMESCREEN_SIZEX, 0);

        //See item stats:
        Item buffer = null;
        if (equippedScreen && human.items[emarker] != null) {
            buffer = human.items[emarker];
        } else if (!equippedScreen && !human.inventory.isEmpty()) {
            buffer = human.inventory.get(position + marker);
        }
        if (buffer != null) {
            hud.displayItemStats(Defaults.GAMESCREEN_SIZEX, 8, buffer);

        }
    }

    @Override
    public Screen response(KeyEvent key) {
        switch (key.getKeyCode()) {
            case Defaults.up:
                if (equippedScreen) {
                    emarker--;
                } else {
                    moveUp();
                }
                break;
            case Defaults.down:
                if (equippedScreen) {
                    emarker++;
                } else {
                    moveDown();
                }
                break;
            case Defaults.right:
                equippedScreen = !equippedScreen;
                break;
            case Defaults.left:
                equippedScreen = !equippedScreen;
                break;
            case Defaults.A:
                if (!equippedScreen) {
                    human.equip(marker + position);
                }
                break;
            case Defaults.B:
                if (!equippedScreen) {
                    human.drop(marker + position);
                }
                break;
            case Defaults.Start:
                return new PlayScreen(world, human);
        }
        return this;
    }

    private void moveUp() {
        marker--;
        if (marker < 1 && position > 0) {
            position--;
            marker++;
        }
        if (marker < 0) {
            if (human.inventory.size() > maxMarker) {
                position = human.inventory.size() - maxMarker;
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
