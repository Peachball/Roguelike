package game.world;

import game.defaults.Defaults;
import java.awt.Color;

public enum TileList {

    GRASSLAND(new Tile(Color.GREEN, Color.GREEN, ' ', Defaults.DEFAULT_GRASSLANDS)),
    MOUNTAIN(new Tile(Color.GRAY, Color.GRAY, ' ', Defaults.DEFAULT_MOUNTAINS));
    public Tile id;

    TileList(Tile x) {
        id = x;
    }
}
