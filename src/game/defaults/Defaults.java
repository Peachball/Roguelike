package game.defaults;

import game.players.PlayerStat;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Defaults {

    //These values determine what color an item should be...
    //Not sure how else to determine item color aside from type
    public static final int LOW_RARITY = 5;
    public static final int MEDIUM_RARITY = 10;
    public static final int HIGH_RARITY = 15;

    //These Color are wayyy too bright...
    public static final Color LOW_RARITY_COLOR = Color.GREEN;
    public static final Color MEDIUM_RARITY_COLOR = Color.YELLOW;
    public static final Color HIGH_RARITY_COLOR = Color.RED;

    public static final int RARITY_CONSTANT = 5;
    public static final int MAX_RARITY = 50;
    public static final int WEAPON_EFFECTIVNESS = 2;
    public static final char DEFAULT_PLAYER_CHAR = '@';
    public static final Color FOREGROUND_COLOR = Color.WHITE;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final PlayerStat DEFAULT_PLAYER_STATS = new PlayerStat(10, 5, 5, 5, 5, 5);

    public static final int SCREENX_SIZE = 81;
    public static final int SCREENY_SIZE = 28;
    public static final int CHUNK_SIZE = 50;
    public static final int GAMESCREEN_SIZEX = 41;
    public static final int GAMESCREEN_SIZEY = 21;
    public static final int STARTSCREEN_Y_PARAMATAR = 20;

    //Tile types:
    public static final int DEFAULT_GRASSLANDS = 0;
    public static final int DEFAULT_MOUNTAINS = 1;
    public static final int DEFAULT_FOREST = 2;
    public static final int DEFAULT_BUSH = 3;
    public static final int DEFAULT_WALL = -1;

    //Tile chars
    public static final char WALL_CHAR = '#';

    //Weapon types
    public static final int WeaponID = 0;
    public static final int ShieldID = 1;
    public static final int BootsID = 2;
    public static final int ArmorID = 3;
    public static final int SwordID = 0;
    public static final int AxeID = 1;
    public static final int LanceID = 2;
    public static final int LightID = 0;
    public static final int AnimusID = 1;
    public static final int DarkID = 2;

    //Controls:
    public static final int right = KeyEvent.VK_RIGHT;
    public static final int left = KeyEvent.VK_LEFT;
    public static final int up = KeyEvent.VK_UP;
    public static final int down = KeyEvent.VK_DOWN;
    public static final int A = KeyEvent.VK_COMMA;
    public static final int B = KeyEvent.VK_PERIOD;
    public static final int Start = KeyEvent.VK_M;

    //Misc Item related constants
    public static final int maxCarriableItems = 5;
    public static final int rHand = 0;
    public static final int glove = 1;
    public static final int pants = 2;
    public static final int shirt = 3;
    public static final int boots = 4;

    //Inventory maker
    public static final int MAX_INVENTORY_PER_SCREEN = 10;

    //LOLOLOLOLOLOL
    public static final int TRIANGLE_BONUS = 10;
    public static final int CRIT_MULTIPLIER = 3;

    public static final int LOG_SIZE = 6;
    
    //Monster spawn nums
    public static final int LOW_LEVEL_MONSTERS = 0;

}
