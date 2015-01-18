package game.defaults;

import game.players.PlayerStat;
import java.awt.Color;

public class Defaults {
    public static final int WEAPON_EFFECTIVNESS = 2;
    public static final char DEFAULT_PLAYER_CHAR = '@';
    public static final Color FOREGROUND_COLOR = Color.WHITE;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final PlayerStat DEFAULT_PLAYER_STATS = new PlayerStat(10, 5, 5, 5, 5);
    
    public static final int SCREENX_SIZE = 80;
    public static final int SCREENY_SIZE = 28;
    public static final int CHUNK_SIZE = 50;
    public static final int GAMESCREEN_SIZEX = 40;
    public static final int GAMESCREEN_SIZEY = 20;
    public static final int STARTSCREEN_Y_PARAMATAR = 20;
    
    //Tile types:
    public static final int DEFAULT_ARBITRARY = -1;
    public static final int DEFAULT_GRASSLANDS = 0;
    public static final int DEFAULT_MOUNTAINS = 1;
    public static final int DEFAULT_FOREST = 2;
    public static final int DEFAULT_BUSH = 3;
    public static final int DEFAULT_WALL = 4;
    
    //Tile chars
    public static final char WALL_CHAR = '#';
    
    //Weapon types
    public static final int WeaponID = 0;
    public static final int ShieldID = 1;
    public static final int BootsID = 2;
}
