package game.world;

import game.defaults.Defaults;
import java.awt.Color;

public enum TileList {
    
    GRASSLAND, MOUNTAIN;
    
    public static Tile generateTile(TileList type) {
        switch(type) {
            case GRASSLAND:
                return new Tile(Color.GREEN, Color.GREEN, ' ', Defaults.DEFAULT_GRASSLANDS);
            case MOUNTAIN:
                return new Tile(Color.GRAY, Color.GRAY, ' ', Defaults.DEFAULT_MOUNTAINS);
        }
        return new Tile(Color.GREEN,Color.GREEN, ' ', Defaults.DEFAULT_GRASSLANDS); 
   }
}
