package game.defaults;

import game.players.PlayerStat;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Defaults {

    public static final int RARITY_CONSTANT = 5;
    public static final int MAX_RARITY = 50;
    public static final int WEAPON_EFFECTIVNESS = 2;
    public static final char DEFAULT_PLAYER_CHAR = '@';
    public static final Color FOREGROUND_COLOR = Color.WHITE;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final PlayerStat DEFAULT_PLAYER_STATS = new PlayerStat(10, 5, 5, 5, 5);

    public static final int SCREENX_SIZE = 80;
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

    //Controls:
    public static final int right = KeyEvent.VK_RIGHT;
    public static final int left = KeyEvent.VK_LEFT;
    public static final int up = KeyEvent.VK_UP;
    public static final int down = KeyEvent.VK_DOWN;

    //Misc Item related constants
    public static final int maxCarriableItems = 5;
    public static final int rHand = 0;
    public static final int lHand = 1;
    public static final int pants = 2;
    public static final int shirt = 3;
    public static final int boots = 4;

}
