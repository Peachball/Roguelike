package game.world;

import game.defaults.Defaults;
import game.items.Item;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

public class Tile {

    public Color background;
    public Color foreground;
    public char representer;
    public ArrayList<Item> items;
    public boolean wall;
    public int type;
    public Tile ground;

    public Tile(Color background, Color foreground, char x, int type) {
        this.background = background;
        this.foreground = foreground;
        this.representer = x;
        wall = (x == Defaults.WALL_CHAR);
        if (!wall) {
            items = new ArrayList<Item>();
        }
        this.type = type;
    }

    public boolean addItem(Item x) {
        items.add(x);
        representer = items.get(0).symbol;
        background = items.get(0).background;
        foreground = items.get(0).foreground;
        return true;
    }

    public boolean removeItem(Item x) {
        return items.remove(x);
    }

    public Tile() {
        this(Color.BLACK, Color.WHITE, ' ', Defaults.DEFAULT_ARBITRARY);
    }

    public Tile(char a) {
        this(Color.BLACK, Color.WHITE, a, Defaults.DEFAULT_ARBITRARY);
    }
}

class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item t, Item t1) {
        return t.name.hashCode() - t1.name.hashCode();
    }

}
