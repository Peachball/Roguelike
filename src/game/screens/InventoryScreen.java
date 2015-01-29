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
        output.write('>', 0, marker + 1);

        //Add player stats
        //Should this be hidden away in another method?
        output.write(human.name, Defaults.GAMESCREEN_SIZEX, 0);
        output.write("HP:", Defaults.GAMESCREEN_SIZEX, 1);
        output.write(Integer.toString(human.stats.hp) + "/" + Integer.toString(human.stats.maxHp),
                Defaults.GAMESCREEN_SIZEX + 4, 1);
        output.write("OTHER STAT?", Defaults.GAMESCREEN_SIZEX, 2);
        output.write("CURRENT LEVEL: " + Integer.toString(human.stats.level), Defaults.GAMESCREEN_SIZEX, 4);
        output.write("SKILL:", Defaults.GAMESCREEN_SIZEX, 5);
        output.write(Integer.toString(human.stats.skill), Defaults.GAMESCREEN_SIZEX + 10, 5);
        output.write("STRENGTH:", Defaults.GAMESCREEN_SIZEX, 6);
        output.write(Integer.toString(human.stats.skill), Defaults.GAMESCREEN_SIZEX + 10, 6);
        output.write("SPEED:", Defaults.GAMESCREEN_SIZEX + 15, 5);
        output.write(Integer.toString(human.stats.speed), Defaults.GAMESCREEN_SIZEX + 20, 5);
        output.write("LUCK:", Defaults.GAMESCREEN_SIZEX + 15, 6);
        output.write(Integer.toString(human.stats.luck), Defaults.GAMESCREEN_SIZEX + 20, 6);
        output.write("RES", Defaults.GAMESCREEN_SIZEX + 30, 5);
        output.write(Integer.toString(human.stats.magicResist), Defaults.GAMESCREEN_SIZEX + 36, 5);
        output.write("DEF", Defaults.GAMESCREEN_SIZEX + 30, 6);
        output.write(Integer.toString(human.stats.damageResist), Defaults.GAMESCREEN_SIZEX + 36, 6);

        //See item stats:
        if (!human.inventory.isEmpty()) {
            Item buffer;
            if (equippedScreen && human.items[emarker] != null) {
                buffer = human.items[emarker];
            } else if(!equippedScreen) {
                buffer = human.inventory.get(position + marker);
            }
            output.write("ITEM: " + buffer.name, Defaults.GAMESCREEN_SIZEX, 8);
            output.write("DAMAGE:" + buffer.stats.damage, Defaults.GAMESCREEN_SIZEX, 9);
            output.write("WEIGHT:" + buffer.stats.weight, Defaults.GAMESCREEN_SIZEX, 10);
            output.write("ACCURACY: " + buffer.stats.accuracy, Defaults.GAMESCREEN_SIZEX + 12, 9);
            output.write("CRIT CHANCE: " + buffer.stats.critChance, Defaults.GAMESCREEN_SIZEX + 12, 10);
            output.write("DEFENSE: " + buffer.stats.damageDefense, Defaults.GAMESCREEN_SIZEX, 11);
            output.write("RES: " + buffer.stats.magicDefense, Defaults.GAMESCREEN_SIZEX, 12);
            output.write("RARITY LEVEL: " + buffer.stats.rarity, Defaults.GAMESCREEN_SIZEX + 12, 11);
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
                human.equip(marker + position);
                break;
            case Defaults.B:
                human.drop(marker + position);
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
