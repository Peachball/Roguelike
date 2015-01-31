package game.screens;

import asciiPanel.AsciiPanel;
import game.items.Item;
import game.players.Human;
import game.world.World;

public class HUD {

    private AsciiPanel output;
    private World world;
    private Human human;

    public HUD(AsciiPanel output, World world, Human player) {
        this.world = world;
        this.human = player;
        this.output = output;
    }

    public void displayPlayerStats(int x, int y) {
        output.write(human.name, x, y);
        output.write("HP:", x, 1 + y);
        output.write(Integer.toString(human.stats.hp) + "/" + Integer.toString(human.stats.maxHp),
                x + 4, 1 + y);
        output.write("OTHER STAT?", x, 2 + y);
        output.write("CURRENT LEVEL: " + Integer.toString(human.stats.level), x, 4 + y);
        output.write("SKILL:", x, 5 + y);
        output.write(Integer.toString(human.stats.skill), x + 10, 5 + y);
        output.write("STRENGTH:", x, 6 + y);
        output.write(Integer.toString(human.stats.skill), x + 10, 6 + y);
        output.write("SPEED:", x + 15, 5 + y);
        output.write(Integer.toString(human.stats.speed), x + 20, 5 + y);
        output.write("LUCK:", x + 15, 6 + y);
        output.write(Integer.toString(human.stats.luck), x + 20, 6 + y);
        output.write("RES", x + 30, 5 + y);
        output.write(Integer.toString(human.stats.magicResist), x + 36, 5 + y);
        output.write("DEF", x + 30, 6 + y);
        output.write(Integer.toString(human.stats.damageResist), x + 36, 6 + y);
    }

    public void displayItemStats(int x, int y, Item item) {
        if (item == null) {
            return;
        }
        output.write("ITEM: " + item.name, x, y);
        output.write("DAMAGE:" + item.stats.damage, x, y + 1);
        output.write("WEIGHT:" + item.stats.weight, x, y + 2);
        output.write("ACCURACY: " + item.stats.accuracy, x + 13, y + 1);
        output.write("CRIT CHANCE: " + item.stats.critChance, x + 13, y + 2);
        output.write("DEFENSE: " + item.stats.damageDefense, x, y + 3);
        output.write("RES: " + item.stats.magicDefense, x + 29, y + 1);
        output.write("RARITY LEVEL: " + item.stats.rarity, x + 13, y + 3);
    }
}
