package game.players;

import game.defaults.Defaults;
import game.world.Tile;

public class Player extends Tile {

    public String name;
    public PlayerStat stats;

    public Player(PlayerStat startStat, char a) {
        stats = startStat;
    }

    public Player() {
        super(Defaults.DEFAULT_PLAYER_CHAR);
        stats = new PlayerStat(0, 0, 0, 0, 0);
    }
}