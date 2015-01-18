package game.world;

import game.defaults.Defaults;
import java.awt.Color;

public enum TileList {

    GRASSLAND(new Tile(Color.GREEN, Color.GREEN, ' ', Defaults.DEFAULT_GRASSLANDS));
    public Tile id;

    TileList(Tile x) {
        id = x;
    }
}
