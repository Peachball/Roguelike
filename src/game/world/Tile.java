package game.world;

import game.defaults.Defaults;
import game.items.Item;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

public class Tile {

    public Color background;
    public Color currentBackground;
    public Color currentForeground;
    public Color foreground;
    public char representer;
    public ArrayList<Item> items;
    public boolean wall; //Determines whether or not you can pass through
    public boolean isVisible;
    public int type; //Affects various things (e.g. extra damage/dodge or visibility

    public Tile(Color background, Color foreground, char x, int type) {
        this.background = background;
        this.foreground = foreground;
        currentBackground = background;
        this.representer = x;
        wall = (x == Defaults.WALL_CHAR);
        items = new ArrayList<Item>();
        this.type = type;
        isVisible = true;
    }

    public Tile(Color background, Color foreground, char x, int type,boolean isEmpty) {
        this.background = background;
        this.foreground = foreground;
        currentBackground = background;
        this.representer = x;
        wall = (x == Defaults.WALL_CHAR);
        items = new ArrayList<Item>();
        this.type = type;
        isVisible = true;
    }

    public boolean addItem(Item x) {
        items.add(x);
        update();
        return true;
    }

    public boolean removeItem(Item x) {
        items.remove(x);
        update();
        return true;
    }

    public boolean update() {
        if (items.isEmpty()) {
            representer = ' ';
            return false;
        }
        currentForeground = items.get(0).foreground;
        representer = items.get(0).symbol;
        return true;
    }

    public Tile() {
        this(Color.BLACK, Color.WHITE, ' ', Defaults.DEFAULT_GRASSLANDS);
    }

    public Tile(char a) {
        this(Color.BLACK, Color.WHITE, a, Defaults.DEFAULT_GRASSLANDS);
    }

}

class ItemComparator implements Comparator<Item> {

    @Override
    public int compare(Item t, Item t1) {
        return t.name.hashCode() - t1.name.hashCode();
    }

}
