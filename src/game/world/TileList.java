package game.world;

import game.defaults.Defaults;
import java.awt.Color;

public enum TileList {
    
    GRASSLAND, MOUNTAIN;
    
    public static Tile generateTile(TileList type) {
        switch(type) {
            case GRASSLAND:
                return new Tile(new Color(50,189,79), new Color(50,189,79), '.', Defaults.DEFAULT_GRASSLANDS);
            case MOUNTAIN:
                return new Tile(new Color(200,20,164),new Color(200,20,164), '^', Defaults.DEFAULT_MOUNTAINS);
        }
        return new Tile(Color.GREEN,Color.GREEN, ' ', Defaults.DEFAULT_GRASSLANDS); 
   }
}
