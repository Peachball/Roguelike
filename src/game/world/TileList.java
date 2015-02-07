package game.world;

import game.defaults.Defaults;
import java.awt.Color;

public enum TileList {
    
    GRASSLAND, MOUNTAIN, DESERT;
    
    public static Tile generateTile(TileList type) {
        switch(type) {
            case GRASSLAND:
                return new Tile(new Color(127,255,161), new Color(127,255,161), '.', Defaults.DEFAULT_GRASSLANDS);
            case MOUNTAIN:
                return new Tile(new Color(53,135,178),new Color(218,242,255), '^', Defaults.DEFAULT_MOUNTAINS);
            case DESERT:
                if(Math.random()<.1){
                    return new Tile(new Color(255,219,173),new Color(178,134,76),'*', Defaults.DEFAULT_DESERT);
                }
                else{
                    return new Tile(new Color(255,219,173),new Color(255,219,173),' ', Defaults.DEFAULT_DESERT);
                }
                
        }
        return new Tile(Color.GREEN,Color.GREEN, ' ', Defaults.DEFAULT_GRASSLANDS); 
   }
}
